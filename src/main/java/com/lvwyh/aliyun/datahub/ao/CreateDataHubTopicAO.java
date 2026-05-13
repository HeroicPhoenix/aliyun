package com.lvwyh.aliyun.datahub.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Schema(description = "创建DataHub Topic入参")
public class CreateDataHubTopicAO implements Serializable {

    @Schema(description = "DataHub项目名称", example = "demo_project", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "projectName不能为空")
    @Size(max = 128, message = "projectName长度不能超过128")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]*$", message = "projectName只能包含字母、数字、下划线，并且必须以字母开头")
    private String projectName;

    @Schema(description = "Topic名称", example = "demo_topic", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "topicName不能为空")
    @Size(max = 128, message = "topicName长度不能超过128")
    private String topicName;

    @Schema(description = "Shard数量", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "shardCount不能为空")
    @Min(value = 1, message = "shardCount不能小于1")
    @Max(value = 1000, message = "shardCount不能大于1000")
    private Integer shardCount;

    @Schema(description = "数据生命周期，单位天", example = "7", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "lifeCycle不能为空")
    @Min(value = 1, message = "lifeCycle不能小于1")
    private Integer lifeCycle;

    @Schema(description = "记录类型，TUPLE或BLOB", example = "TUPLE", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "recordType不能为空")
    @Pattern(regexp = "TUPLE|BLOB", message = "recordType只能是TUPLE或BLOB")
    private String recordType;

    @Schema(description = "Topic备注", example = "demo topic")
    @Size(max = 255, message = "comment长度不能超过255")
    private String comment;

    @Schema(description = "是否开启Shard扩展模式，开启时使用ONLY_EXTEND，关闭时使用ONLY_SPLIT", example = "true")
    private Boolean shardExtendEnabled = true;

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

    public Integer getShardCount() {
        return shardCount;
    }

    public void setShardCount(Integer shardCount) {
        this.shardCount = shardCount;
    }

    public Integer getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(Integer lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getShardExtendEnabled() {
        return shardExtendEnabled;
    }

    public void setShardExtendEnabled(Boolean shardExtendEnabled) {
        this.shardExtendEnabled = shardExtendEnabled;
    }

}
