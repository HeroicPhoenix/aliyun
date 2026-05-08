package com.lvwyh.aliyun.dataworks.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Schema(description = "查询节点列表入参")
public class ListNodesAO implements Serializable {

    @Schema(description = "页码，最小 1", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "pageNumber不能为空")
    @Min(value = 1, message = "pageNumber不能小于1")
    @Max(value = 100, message = "pageNumber不能大于100")
    private Integer pageNumber;

    @Schema(description = "每页条数，最大 100", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "pageSize不能为空")
    @Min(value = 1, message = "pageSize不能小于1")
    @Max(value = 100, message = "pageSize不能大于100")
    private Integer pageSize;

    @Schema(description = "工作空间 ID", example = "306754", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "projectId不能为空")
    private Long projectId;

    @Schema(description = "环境，DEV / PROD", example = "DEV", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "projectEnv不能为空")
    private String projectEnv;

    @Schema(description = "节点名称", example = "test_sql")
    private String nodeName;

    @Schema(description = "负责人 ID", example = "10001")
    private String owner;

    @Schema(description = "业务流程名称", example = "数据开发")
    private String bizName;

    @Schema(description = "节点类型，例如 ODPS_SQL", example = "ODPS_SQL")
    private String programType;

    @Schema(description = "调度类型，例如 NORMAL / MANUAL / PAUSE / SKIP", example = "NORMAL")
    private String schedulerType;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectEnv() {
        return projectEnv;
    }

    public void setProjectEnv(String projectEnv) {
        this.projectEnv = projectEnv;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getSchedulerType() {
        return schedulerType;
    }

    public void setSchedulerType(String schedulerType) {
        this.schedulerType = schedulerType;
    }
}