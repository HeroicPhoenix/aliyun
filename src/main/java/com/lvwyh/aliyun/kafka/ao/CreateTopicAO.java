package com.lvwyh.aliyun.kafka.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

    @Schema(description = "Kafka创建Topic入参")
    public class CreateTopicAO implements Serializable {

    @Schema(description = "Topic名称", example = "test-topic", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "topic不能为空")
    private String topic;

    @Schema(description = "分区数", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "partitions不能为空")
    @Min(value = 1, message = "partitions不能小于1")
    @Max(value = 1000, message = "partitions不能大于1000")
    private Integer partitions = 1;

    @Schema(description = "副本数", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "replicationFactor不能为空")
    @Min(value = 1, message = "replicationFactor不能小于1")
    @Max(value = 10, message = "replicationFactor不能大于10")
    private Short replicationFactor = 1;

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

    public Short getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(Short replicationFactor) {
        this.replicationFactor = replicationFactor;
    }
}
