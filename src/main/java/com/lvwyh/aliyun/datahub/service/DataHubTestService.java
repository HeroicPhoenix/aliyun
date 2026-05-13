package com.lvwyh.aliyun.datahub.service;

import com.lvwyh.aliyun.datahub.ao.CreateDataHubConnectorAO;
import com.lvwyh.aliyun.datahub.vo.DataHubOperationVO;
import com.lvwyh.aliyun.datahub.vo.DataHubPutRecordVO;
import com.lvwyh.aliyun.datahub.vo.DataHubRecordsVO;
import com.lvwyh.aliyun.datahub.vo.DataHubTopicListVO;
import com.lvwyh.aliyun.datahub.vo.DataHubTopicVO;

import java.util.Map;

public interface DataHubTestService {

    DataHubTopicListVO listTopics(String projectName);

    DataHubTopicVO getTopic(String projectName, String topicName);

    DataHubOperationVO createTopic(String projectName, String topicName, Integer shardCount, Integer lifeCycle,
                                   String recordType, String comment, Boolean shardExtendEnabled);

    DataHubOperationVO deleteTopic(String projectName, String topicName);

    DataHubOperationVO createConnector(CreateDataHubConnectorAO ao);

    DataHubOperationVO deleteConnector(String projectName, String topicName);

    DataHubPutRecordVO putRecord(String projectName, String topicName, String partitionKey, Map<String, String> fields);

    DataHubRecordsVO getRecords(String projectName, String topicName, String shardId, String cursorType, Integer limit);
}
