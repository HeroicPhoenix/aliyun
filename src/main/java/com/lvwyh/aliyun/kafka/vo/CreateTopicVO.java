package com.lvwyh.aliyun.kafka.vo;

import java.io.Serializable;

public class CreateTopicVO implements Serializable {

    private String topic;

    private Integer partitions;

    private Boolean existed;

    public CreateTopicVO() {
    }

    public CreateTopicVO(String topic, Integer partitions, Boolean existed) {
        this.topic = topic;
        this.partitions = partitions;
        this.existed = existed;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getPartitions() {
        return partitions;
    }

    public void setPartitions(Integer partitions) {
        this.partitions = partitions;
    }

    public Boolean getExisted() {
        return existed;
    }

    public void setExisted(Boolean existed) {
        this.existed = existed;
    }
}
