package com.lvwyh.aliyun.datahub.controller;

import com.lvwyh.aliyun.api.common.response.ApiResponse;
import com.lvwyh.aliyun.datahub.ao.GetDataHubRecordsAO;
import com.lvwyh.aliyun.datahub.ao.GetDataHubTopicAO;
import com.lvwyh.aliyun.datahub.ao.ListDataHubTopicsAO;
import com.lvwyh.aliyun.datahub.ao.PutDataHubRecordAO;
import com.lvwyh.aliyun.datahub.service.DataHubTestService;
import com.lvwyh.aliyun.datahub.vo.DataHubPutRecordVO;
import com.lvwyh.aliyun.datahub.vo.DataHubRecordsVO;
import com.lvwyh.aliyun.datahub.vo.DataHubTopicListVO;
import com.lvwyh.aliyun.datahub.vo.DataHubTopicVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "DataHub测试")
@Validated
@RestController
@RequestMapping("/api/datahub")
public class DataHubTestController {

    private static final Logger log = LogManager.getLogger(DataHubTestController.class);

    @Autowired
    private DataHubTestService dataHubTestService;

    @Operation(summary = "查询Topic列表", description = "查询DataHub项目下的Topic列表")
    @PostMapping("/topics/list")
    public ApiResponse<DataHubTopicListVO> listTopics(@RequestBody @Valid ListDataHubTopicsAO ao) {

        log.info("List DataHub topics request: projectName={}", ao.getProjectName());

        DataHubTopicListVO vo = dataHubTestService.listTopics(ao.getProjectName());

        log.info("List DataHub topics success: projectName={}, topicCount={}",
                vo.getProjectName(), vo.getTopicCount());

        return ApiResponse.success("查询成功", vo);
    }

    @Operation(summary = "查询Topic详情", description = "查询DataHub Topic详情和Shard列表")
    @PostMapping("/topic/get")
    public ApiResponse<DataHubTopicVO> getTopic(@RequestBody @Valid GetDataHubTopicAO ao) {

        log.info("Get DataHub topic request: projectName={}, topicName={}",
                ao.getProjectName(), ao.getTopicName());

        DataHubTopicVO vo = dataHubTestService.getTopic(ao.getProjectName(), ao.getTopicName());

        log.info("Get DataHub topic success: projectName={}, topicName={}, shardCount={}",
                vo.getProjectName(), vo.getTopicName(), vo.getShardCount());

        return ApiResponse.success("查询成功", vo);
    }

    @Operation(summary = "写入记录", description = "向DataHub TUPLE Topic写入一条测试记录")
    @PostMapping("/record/put")
    public ApiResponse<DataHubPutRecordVO> putRecord(@RequestBody @Valid PutDataHubRecordAO ao) {

        log.info("Put DataHub record request: projectName={}, topicName={}, partitionKey={}",
                ao.getProjectName(), ao.getTopicName(), ao.getPartitionKey());

        DataHubPutRecordVO vo = dataHubTestService.putRecord(
                ao.getProjectName(),
                ao.getTopicName(),
                ao.getPartitionKey(),
                ao.getFields()
        );

        log.info("Put DataHub record success: projectName={}, topicName={}, failedRecordCount={}",
                vo.getProjectName(), vo.getTopicName(), vo.getFailedRecordCount());

        return ApiResponse.success("写入成功", vo);
    }

    @Operation(summary = "读取记录", description = "从DataHub Topic指定Shard读取记录")
    @PostMapping("/records/get")
    public ApiResponse<DataHubRecordsVO> getRecords(@RequestBody @Valid GetDataHubRecordsAO ao) {

        log.info("Get DataHub records request: projectName={}, topicName={}, shardId={}, cursorType={}, limit={}",
                ao.getProjectName(), ao.getTopicName(), ao.getShardId(), ao.getCursorType(), ao.getLimit());

        DataHubRecordsVO vo = dataHubTestService.getRecords(
                ao.getProjectName(),
                ao.getTopicName(),
                ao.getShardId(),
                ao.getCursorType(),
                ao.getLimit()
        );

        log.info("Get DataHub records success: projectName={}, topicName={}, shardId={}, recordCount={}",
                vo.getProjectName(), vo.getTopicName(), vo.getShardId(), vo.getRecordCount());

        return ApiResponse.success("读取成功", vo);
    }
}
