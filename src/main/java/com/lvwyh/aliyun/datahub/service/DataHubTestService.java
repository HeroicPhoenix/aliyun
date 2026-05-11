package com.lvwyh.aliyun.datahub.service;

import com.lvwyh.aliyun.datahub.vo.DataHubPutRecordVO;
import com.lvwyh.aliyun.datahub.vo.DataHubRecordsVO;
import com.lvwyh.aliyun.datahub.vo.DataHubTopicListVO;
import com.lvwyh.aliyun.datahub.vo.DataHubTopicVO;

import java.util.Map;

public interface DataHubTestService {

    DataHubTopicListVO listTopics(String projectName);

    DataHubTopicVO getTopic(String projectName, String topicName);

    DataHubPutRecordVO putRecord(String projectName, String topicName, String partitionKey, Map<String, String> fields);

    DataHubRecordsVO getRecords(String projectName, String topicName, String shardId, String cursorType, Integer limit);
}
