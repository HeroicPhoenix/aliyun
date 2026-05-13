package com.lvwyh.aliyun.kafka.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

    @Schema(description = "Kafka发送消息入参")
    public class ProduceMessageAO implements Serializable {

    @Schema(description = "Topic名称", example = "demo_project.demo_topic", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "topic不能为空")
    private String topic;

    @Schema(description = "消息Key", example = "test-key")
    private String key;

    @Schema(description = "消息内容", example = "hello", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "message不能为空")
    private String message;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
