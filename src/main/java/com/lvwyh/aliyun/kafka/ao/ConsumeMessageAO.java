package com.lvwyh.aliyun.kafka.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

    @Schema(description = "Kafka消费消息入参")
    public class ConsumeMessageAO implements Serializable {

    @Schema(description = "Topic名称", example = "demo_project.demo_topic", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "topic不能为空")
    private String topic;

    @Schema(description = "消费组ID，不传时自动生成", example = "kafka-test-group")
    private String groupId;

    @Schema(description = "最大消费消息数", example = "1")
    @Min(value = 1, message = "maxMessages不能小于1")
    @Max(value = 1000, message = "maxMessages不能大于1000")
    private Integer maxMessages = 1;

    @Schema(description = "消费超时时间，单位毫秒", example = "10000")
    @Min(value = 100, message = "timeoutMs不能小于100")
    @Max(value = 300000, message = "timeoutMs不能大于300000")
    private Integer timeoutMs = 10000;

    @Schema(description = "偏移量重置策略", example = "earliest")
    @Pattern(regexp = "earliest|latest|none", message = "autoOffsetReset只能是earliest、latest或none")
    private String autoOffsetReset = "earliest";

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getMaxMessages() {
        return maxMessages;
    }

    public void setMaxMessages(Integer maxMessages) {
        this.maxMessages = maxMessages;
    }

    public Integer getTimeoutMs() {
        return timeoutMs;
    }

    public void setTimeoutMs(Integer timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    public String getAutoOffsetReset() {
        return autoOffsetReset;
    }

    public void setAutoOffsetReset(String autoOffsetReset) {
        this.autoOffsetReset = autoOffsetReset;
    }
}
