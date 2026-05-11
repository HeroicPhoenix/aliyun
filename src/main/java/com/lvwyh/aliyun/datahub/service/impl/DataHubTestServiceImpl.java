package com.lvwyh.aliyun.datahub.service.impl;

import com.aliyun.datahub.client.DatahubClient;
import com.aliyun.datahub.client.model.CursorType;
import com.aliyun.datahub.client.model.Field;
import com.aliyun.datahub.client.model.GetCursorResult;
import com.aliyun.datahub.client.model.GetRecordsResult;
import com.aliyun.datahub.client.model.GetTopicResult;
import com.aliyun.datahub.client.model.ListShardResult;
import com.aliyun.datahub.client.model.ListTopicResult;
import com.aliyun.datahub.client.model.PutRecordsResult;
import com.aliyun.datahub.client.model.RecordEntry;
import com.aliyun.datahub.client.model.RecordSchema;
import com.aliyun.datahub.client.model.RecordType;
import com.aliyun.datahub.client.model.ShardEntry;
import com.aliyun.datahub.client.model.TupleRecordData;
import com.lvwyh.aliyun.api.common.exception.BusinessException;
import com.lvwyh.aliyun.datahub.config.DataHubProperties;
import com.lvwyh.aliyun.datahub.service.DataHubTestService;
import com.lvwyh.aliyun.datahub.vo.DataHubFieldVO;
import com.lvwyh.aliyun.datahub.vo.DataHubPutRecordVO;
import com.lvwyh.aliyun.datahub.vo.DataHubRecordVO;
import com.lvwyh.aliyun.datahub.vo.DataHubRecordsVO;
import com.lvwyh.aliyun.datahub.vo.DataHubShardVO;
import com.lvwyh.aliyun.datahub.vo.DataHubTopicListVO;
import com.lvwyh.aliyun.datahub.vo.DataHubTopicVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataHubTestServiceImpl implements DataHubTestService {

    private static final Logger log = LogManager.getLogger(DataHubTestServiceImpl.class);

    private final DatahubClient datahubClient;

    private final DataHubProperties properties;

    public DataHubTestServiceImpl(DatahubClient dataHubClient, DataHubProperties properties) {
        this.datahubClient = dataHubClient;
        this.properties = properties;
    }

    @Override
    public DataHubTopicListVO listTopics(String projectName) {
        validateConfig();
        try {
            ListTopicResult result = datahubClient.listTopic(projectName);
            return new DataHubTopicListVO(projectName, result.getTopicNames());
        } catch (Exception e) {
            log.error("List DataHub topics failed: projectName={}", projectName, e);
            throw new BusinessException("查询DataHub Topic列表失败", e);
        }
    }

    @Override
    public DataHubTopicVO getTopic(String projectName, String topicName) {
        validateConfig();
        try {
            GetTopicResult topic = datahubClient.getTopic(projectName, topicName);
            ListShardResult shardResult = datahubClient.listShard(projectName, topicName);

            DataHubTopicVO vo = new DataHubTopicVO();
            vo.setProjectName(topic.getProjectName());
            vo.setTopicName(topic.getTopicName());
            vo.setShardCount(topic.getShardCount());
            vo.setLifeCycle(topic.getLifeCycle());
            vo.setRecordType(topic.getRecordType() == null ? null : topic.getRecordType().name());
            vo.setComment(topic.getComment());
            vo.setFields(convertFields(topic.getRecordSchema()));
            vo.setShards(convertShards(shardResult.getShards()));
            return vo;
        } catch (Exception e) {
            log.error("Get DataHub topic failed: projectName={}, topicName={}", projectName, topicName, e);
            throw new BusinessException("查询DataHub Topic详情失败", e);
        }
    }

    @Override
    public DataHubPutRecordVO putRecord(String projectName, String topicName, String partitionKey, Map<String, String> fields) {
        validateConfig();
        try {
            GetTopicResult topic = datahubClient.getTopic(projectName, topicName);
            if (!RecordType.TUPLE.equals(topic.getRecordType())) {
                throw new BusinessException("当前写入接口仅支持TUPLE类型Topic");
            }

            RecordEntry recordEntry = new RecordEntry();
            if (StringUtils.hasText(partitionKey)) {
                recordEntry.setPartitionKey(partitionKey);
            }
            TupleRecordData recordData = new TupleRecordData(topic.getRecordSchema());
            for (Field field : topic.getRecordSchema().getFields()) {
                String value = fields.get(field.getName());
                if (value != null) {
                    recordData.setField(field.getName(), convertFieldValue(field, value));
                }
            }
            recordEntry.setRecordData(recordData);

            PutRecordsResult result = datahubClient.putRecords(projectName, topicName, Collections.singletonList(recordEntry));
            return new DataHubPutRecordVO(projectName, topicName, result.getFailedRecordCount(), result.getRequestId());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Put DataHub record failed: projectName={}, topicName={}, partitionKey={}",
                    projectName, topicName, partitionKey, e);
            throw new BusinessException("写入DataHub记录失败", e);
        }
    }

    @Override
    public DataHubRecordsVO getRecords(String projectName, String topicName, String shardId, String cursorType, Integer limit) {
        validateConfig();
        try {
            GetTopicResult topic = datahubClient.getTopic(projectName, topicName);
            String finalShardId = StringUtils.hasText(shardId) ? shardId : getFirstShardId(projectName, topicName);
            GetCursorResult cursorResult = datahubClient.getCursor(
                    projectName,
                    topicName,
                    finalShardId,
                    CursorType.valueOf(cursorType)
            );
            GetRecordsResult recordsResult = datahubClient.getRecords(
                    projectName,
                    topicName,
                    finalShardId,
                    topic.getRecordSchema(),
                    cursorResult.getCursor(),
                    limit
            );

            DataHubRecordsVO vo = new DataHubRecordsVO();
            vo.setProjectName(projectName);
            vo.setTopicName(topicName);
            vo.setShardId(finalShardId);
            vo.setNextCursor(recordsResult.getNextCursor());
            vo.setRecordCount(recordsResult.getRecordCount());
            vo.setRecords(convertRecords(topic.getRecordSchema(), recordsResult.getRecords()));
            return vo;
        } catch (Exception e) {
            log.error("Get DataHub records failed: projectName={}, topicName={}, shardId={}, cursorType={}, limit={}",
                    projectName, topicName, shardId, cursorType, limit, e);
            throw new BusinessException("读取DataHub记录失败", e);
        }
    }

    private List<DataHubFieldVO> convertFields(RecordSchema schema) {
        List<DataHubFieldVO> fields = new ArrayList<DataHubFieldVO>();
        if (schema == null || schema.getFields() == null) {
            return fields;
        }
        for (Field field : schema.getFields()) {
            fields.add(new DataHubFieldVO(
                    field.getName(),
                    field.getType() == null ? null : field.getType().name(),
                    field.isAllowNull(),
                    field.getComment()
            ));
        }
        return fields;
    }

    private List<DataHubShardVO> convertShards(List<ShardEntry> shards) {
        List<DataHubShardVO> result = new ArrayList<DataHubShardVO>();
        if (shards == null) {
            return result;
        }
        for (ShardEntry shard : shards) {
            result.add(new DataHubShardVO(
                    shard.getShardId(),
                    shard.getState() == null ? null : shard.getState().name(),
                    shard.getBeginHashKey(),
                    shard.getEndHashKey()
            ));
        }
        return result;
    }

    private List<DataHubRecordVO> convertRecords(RecordSchema schema, List<RecordEntry> records) {
        List<DataHubRecordVO> result = new ArrayList<DataHubRecordVO>();
        if (records == null) {
            return result;
        }
        for (RecordEntry record : records) {
            DataHubRecordVO vo = new DataHubRecordVO();
            vo.setSequence(record.getSequence());
            vo.setSystemTime(record.getSystemTime());
            vo.setShardId(record.getShardId());
            vo.setAttributes(record.getAttributes());
            vo.setFields(convertRecordFields(schema, record));
            result.add(vo);
        }
        return result;
    }

    private Map<String, Object> convertRecordFields(RecordSchema schema, RecordEntry record) {
        Map<String, Object> fields = new LinkedHashMap<String, Object>();
        if (schema == null || schema.getFields() == null || !(record.getRecordData() instanceof TupleRecordData)) {
            return fields;
        }
        TupleRecordData recordData = (TupleRecordData) record.getRecordData();
        for (Field field : schema.getFields()) {
            fields.put(field.getName(), recordData.getField(field.getName()));
        }
        return fields;
    }

    private Object convertFieldValue(Field field, String value) {
        switch (field.getType()) {
            case BIGINT:
                return Long.valueOf(value);
            case INTEGER:
                return Integer.valueOf(value);
            case DOUBLE:
                return Double.valueOf(value);
            case FLOAT:
                return Float.valueOf(value);
            case BOOLEAN:
                return Boolean.valueOf(value);
            case DECIMAL:
                return new BigDecimal(value);
            case TIMESTAMP:
                return Long.valueOf(value);
            case TINYINT:
                return Byte.valueOf(value);
            case SMALLINT:
                return Short.valueOf(value);
            case STRING:
            default:
                return value;
        }
    }

    private String getFirstShardId(String projectName, String topicName) {
        ListShardResult result = datahubClient.listShard(projectName, topicName);
        if (result.getShards() == null || result.getShards().isEmpty()) {
            throw new BusinessException("DataHub Topic没有可用Shard");
        }
        return result.getShards().get(0).getShardId();
    }

    private void validateConfig() {
        if (!StringUtils.hasText(properties.getAccessKeyId())) {
            throw new BusinessException("DataHub accessKeyId未配置");
        }
        if (!StringUtils.hasText(properties.getAccessKeySecret())) {
            throw new BusinessException("DataHub accessKeySecret未配置");
        }
        if (!StringUtils.hasText(properties.getEndpoint())) {
            throw new BusinessException("DataHub endpoint未配置");
        }
    }
}
