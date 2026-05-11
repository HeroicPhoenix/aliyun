package com.lvwyh.aliyun.datahub.vo;

import java.io.Serializable;
import java.util.Map;

public class DataHubRecordVO implements Serializable {

    private Long sequence;

    private Long systemTime;

    private String shardId;

    private Map<String, String> attributes;

    private Map<String, Object> fields;

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public Long getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(Long systemTime) {
        this.systemTime = systemTime;
    }

    public String getShardId() {
        return shardId;
    }

    public void setShardId(String shardId) {
        this.shardId = shardId;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }
}
