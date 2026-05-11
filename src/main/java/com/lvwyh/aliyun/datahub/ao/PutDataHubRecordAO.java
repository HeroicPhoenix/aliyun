package com.lvwyh.aliyun.datahub.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Map;

@Schema(description = "DataHub写入记录入参")
public class PutDataHubRecordAO implements Serializable {

    @Schema(description = "DataHub项目名称", example = "demo_project", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "projectName不能为空")
    @Size(max = 128, message = "projectName长度不能超过128")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]*$", message = "projectName只能包含字母、数字、下划线，并且必须以字母开头")
    private String projectName;

    @Schema(description = "Topic名称", example = "demo_topic", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "topicName不能为空")
    @Size(max = 128, message = "topicName长度不能超过128")
    private String topicName;

    @Schema(description = "分区Key", example = "test-key")
    @Size(max = 512, message = "partitionKey长度不能超过512")
    private String partitionKey;

    @Schema(description = "字段和值，TUPLE Topic使用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "fields不能为空")
    private Map<String, String> fields;

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

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}
