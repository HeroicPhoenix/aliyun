package com.lvwyh.aliyun.datahub.vo;

import java.io.Serializable;

public class DataHubShardVO implements Serializable {

    private String shardId;

    private String state;

    private String beginHashKey;

    private String endHashKey;

    public DataHubShardVO() {
    }

    public DataHubShardVO(String shardId, String state, String beginHashKey, String endHashKey) {
        this.shardId = shardId;
        this.state = state;
        this.beginHashKey = beginHashKey;
        this.endHashKey = endHashKey;
    }

    public String getShardId() {
        return shardId;
    }

    public void setShardId(String shardId) {
        this.shardId = shardId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBeginHashKey() {
        return beginHashKey;
    }

    public void setBeginHashKey(String beginHashKey) {
        this.beginHashKey = beginHashKey;
    }

    public String getEndHashKey() {
        return endHashKey;
    }

    public void setEndHashKey(String endHashKey) {
        this.endHashKey = endHashKey;
    }
}
