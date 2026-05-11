package com.lvwyh.aliyun.datahub.vo;

import java.io.Serializable;
import java.util.List;

public class DataHubTopicListVO implements Serializable {

    private String projectName;

    private Integer topicCount;

    private List<String> topicNames;

    public DataHubTopicListVO() {
    }

    public DataHubTopicListVO(String projectName, List<String> topicNames) {
        this.projectName = projectName;
        this.topicNames = topicNames;
        this.topicCount = topicNames == null ? 0 : topicNames.size();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(Integer topicCount) {
        this.topicCount = topicCount;
    }

    public List<String> getTopicNames() {
        return topicNames;
    }

    public void setTopicNames(List<String> topicNames) {
        this.topicNames = topicNames;
    }
}
