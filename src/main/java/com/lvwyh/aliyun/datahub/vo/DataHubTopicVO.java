package com.lvwyh.aliyun.datahub.vo;

import java.io.Serializable;
import java.util.List;

public class DataHubTopicVO implements Serializable {

    private String projectName;

    private String topicName;

    private Integer shardCount;

    private Integer lifeCycle;

    private String recordType;

    private String comment;

    private List<DataHubFieldVO> fields;

    private List<DataHubShardVO> shards;

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

    public Integer getShardCount() {
        return shardCount;
    }

    public void setShardCount(Integer shardCount) {
        this.shardCount = shardCount;
    }

    public Integer getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(Integer lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<DataHubFieldVO> getFields() {
        return fields;
    }

    public void setFields(List<DataHubFieldVO> fields) {
        this.fields = fields;
    }

    public List<DataHubShardVO> getShards() {
        return shards;
    }

    public void setShards(List<DataHubShardVO> shards) {
        this.shards = shards;
    }
}
