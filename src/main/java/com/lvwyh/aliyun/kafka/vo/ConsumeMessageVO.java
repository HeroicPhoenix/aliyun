package com.lvwyh.aliyun.kafka.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConsumeMessageVO implements Serializable {

    private String groupId;

    private Integer consumedCount;

    private List<KafkaMessageVO> messages;

    public ConsumeMessageVO() {
        this.messages = new ArrayList<KafkaMessageVO>();
    }

    public ConsumeMessageVO(String groupId, List<KafkaMessageVO> messages) {
        this.groupId = groupId;
        this.messages = messages;
        this.consumedCount = messages == null ? 0 : messages.size();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getConsumedCount() {
        return consumedCount;
    }

    public void setConsumedCount(Integer consumedCount) {
        this.consumedCount = consumedCount;
    }

    public List<KafkaMessageVO> getMessages() {
        return messages;
    }

    public void setMessages(List<KafkaMessageVO> messages) {
        this.messages = messages;
    }
}
