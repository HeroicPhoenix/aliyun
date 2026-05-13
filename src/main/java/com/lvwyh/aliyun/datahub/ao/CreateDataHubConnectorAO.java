package com.lvwyh.aliyun.datahub.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Schema(description = "创建DataHub到MaxCompute同步链路入参")
public class CreateDataHubConnectorAO implements Serializable {

    @Schema(description = "DataHub项目名称", example = "demo_project", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "projectName不能为空")
    @Size(max = 128, message = "projectName长度不能超过128")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]*$", message = "projectName只能包含字母、数字、下划线，并且必须以字母开头")
    private String projectName;

    @Schema(description = "Topic名称", example = "demo_topic", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "topicName不能为空")
    @Size(max = 128, message = "topicName长度不能超过128")
    private String topicName;

    @Schema(description = "Topic字段列表，TUPLE Topic使用", example = "[\"data_value\"]", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "columnFields不能为空")
    private List<String> columnFields;

    @Schema(description = "MaxCompute Endpoint", example = "http://service.cn-shanghai.maxcompute.aliyun.com/api", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "odpsEndpoint不能为空")
    private String odpsEndpoint;

    @Schema(description = "MaxCompute项目", example = "odps_project", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "odpsProject不能为空")
    private String odpsProject;

    @Schema(description = "MaxCompute表名", example = "target_table", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "odpsTable不能为空")
    private String odpsTable;

    @Schema(description = "MaxCompute AccessKeyId，不传时使用DataHub配置", example = "accessId")
    private String odpsAccessId;

    @Schema(description = "MaxCompute AccessKeySecret，不传时使用DataHub配置", example = "accessKey")
    private String odpsAccessKey;

    @Schema(description = "分区字段名", example = "ds", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "partitionField不能为空")
    private String partitionField;

    @Schema(description = "分区格式", example = "%Y%m%d", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "partitionFormat不能为空")
    private String partitionFormat;

    @Schema(description = "SYSTEM_TIME分区时间窗口，单位分钟", example = "60", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "timeRangeMinutes不能为空")
    @Min(value = 1, message = "timeRangeMinutes不能小于1")
    private Integer timeRangeMinutes;

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

    public List<String> getColumnFields() {
        return columnFields;
    }

    public void setColumnFields(List<String> columnFields) {
        this.columnFields = columnFields;
    }

    public String getOdpsEndpoint() {
        return odpsEndpoint;
    }

    public void setOdpsEndpoint(String odpsEndpoint) {
        this.odpsEndpoint = odpsEndpoint;
    }

    public String getOdpsProject() {
        return odpsProject;
    }

    public void setOdpsProject(String odpsProject) {
        this.odpsProject = odpsProject;
    }

    public String getOdpsTable() {
        return odpsTable;
    }

    public void setOdpsTable(String odpsTable) {
        this.odpsTable = odpsTable;
    }

    public String getOdpsAccessId() {
        return odpsAccessId;
    }

    public void setOdpsAccessId(String odpsAccessId) {
        this.odpsAccessId = odpsAccessId;
    }

    public String getOdpsAccessKey() {
        return odpsAccessKey;
    }

    public void setOdpsAccessKey(String odpsAccessKey) {
        this.odpsAccessKey = odpsAccessKey;
    }

    public String getPartitionField() {
        return partitionField;
    }

    public void setPartitionField(String partitionField) {
        this.partitionField = partitionField;
    }

    public String getPartitionFormat() {
        return partitionFormat;
    }

    public void setPartitionFormat(String partitionFormat) {
        this.partitionFormat = partitionFormat;
    }

    public Integer getTimeRangeMinutes() {
        return timeRangeMinutes;
    }

    public void setTimeRangeMinutes(Integer timeRangeMinutes) {
        this.timeRangeMinutes = timeRangeMinutes;
    }
}
