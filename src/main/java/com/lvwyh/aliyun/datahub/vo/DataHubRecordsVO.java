package com.lvwyh.aliyun.datahub.vo;

import java.io.Serializable;
import java.util.List;

public class DataHubRecordsVO implements Serializable {

    private String projectName;

    private String topicName;

    private String shardId;

    private String nextCursor;

    private Integer recordCount;

    private List<DataHubRecordVO> records;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getShardId() {
        return shardId;
    }

    public void setShardId(String shardId) {
        this.shardId = shardId;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public List<DataHubRecordVO> getRecords() {
        return records;
    }

    public void setRecords(List<DataHubRecordVO> records) {
        this.records = records;
    }
}
