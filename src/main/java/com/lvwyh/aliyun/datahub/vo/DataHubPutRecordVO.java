package com.lvwyh.aliyun.datahub.vo;

import java.io.Serializable;

public class DataHubPutRecordVO implements Serializable {

    private String projectName;

    private String topicName;

    private Integer failedRecordCount;

    private String requestId;

    public DataHubPutRecordVO() {
    }

    public DataHubPutRecordVO(String projectName, String topicName, Integer failedRecordCount, String requestId) {
        this.projectName = projectName;
        this.topicName = topicName;
        this.failedRecordCount = failedRecordCount;
        this.requestId = requestId;
    }

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

    public Integer getFailedRecordCount() {
        return failedRecordCount;
    }

    public void setFailedRecordCount(Integer failedRecordCount) {
        this.failedRecordCount = failedRecordCount;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
