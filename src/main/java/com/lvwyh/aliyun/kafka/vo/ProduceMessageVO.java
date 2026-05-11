package com.lvwyh.aliyun.kafka.vo;

import java.io.Serializable;

public class ProduceMessageVO implements Serializable {

    private String topic;

    private Integer partition;

    private Long offset;

    public ProduceMessageVO() {
    }

    public ProduceMessageVO(String topic, Integer partition, Long offset) {
        this.topic = topic;
        this.partition = partition;
        this.offset = offset;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }
}
