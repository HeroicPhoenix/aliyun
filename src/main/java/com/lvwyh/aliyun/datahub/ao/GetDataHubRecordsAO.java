package com.lvwyh.aliyun.datahub.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Schema(description = "DataHub读取记录入参")
public class GetDataHubRecordsAO implements Serializable {

    @Schema(description = "DataHub项目名称", example = "demo_project", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "projectName不能为空")
    @Size(max = 128, message = "projectName长度不能超过128")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]*$", message = "projectName只能包含字母、数字、下划线，并且必须以字母开头")
    private String projectName;

    @Schema(description = "Topic名称", example = "demo_topic", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "topicName不能为空")
    @Size(max = 128, message = "topicName长度不能超过128")
    private String topicName;

    @Schema(description = "Shard ID，不传时使用第一个可用Shard", example = "0")
    private String shardId;

    @Schema(description = "游标类型", example = "OLDEST")
    @Pattern(regexp = "OLDEST|LATEST", message = "cursorType只能是OLDEST或LATEST")
    private String cursorType = "OLDEST";

    @Schema(description = "读取数量", example = "10")
    @Min(value = 1, message = "limit不能小于1")
    @Max(value = 1000, message = "limit不能大于1000")
    private Integer limit = 10;

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

    public String getShardId() {
        return shardId;
    }

    public void setShardId(String shardId) {
        this.shardId = shardId;
    }

    public String getCursorType() {
        return cursorType;
    }

    public void setCursorType(String cursorType) {
        this.cursorType = cursorType;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
