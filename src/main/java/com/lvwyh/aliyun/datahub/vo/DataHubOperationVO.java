package com.lvwyh.aliyun.datahub.vo;

import java.io.Serializable;

public class DataHubOperationVO implements Serializable {

    private String projectName;

    private String topicName;

    private String connectorId;

    private String requestId;

    public DataHubOperationVO() {
    }

    public DataHubOperationVO(String projectName, String topicName, String connectorId, String requestId) {
        this.projectName = projectName;
        this.topicName = topicName;
        this.connectorId = connectorId;
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

    public String getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(String connectorId) {
        this.connectorId = connectorId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
