package com.lvwyh.aliyun.dataworks.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Schema(description = "查询实例列表入参")
public class ListInstancesAO implements Serializable {

    @Schema(description = "页码，最小 1，最大 100", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "pageNumber不能为空")
    @Min(value = 1, message = "pageNumber不能小于1")
    @Max(value = 100, message = "pageNumber不能大于100")
    private Integer pageNumber;

    @Schema(description = "每页条数，最小 1，最大 100", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "pageSize不能为空")
    @Min(value = 1, message = "pageSize不能小于1")
    @Max(value = 100, message = "pageSize不能大于100")
    private Integer pageSize;

    @Schema(description = "工作空间 ID", example = "12345", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "projectId不能为空")
    private Long projectId;

    @Schema(description = "运行环境，DEV / PROD", example = "DEV", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "projectEnv不能为空")
    private String projectEnv;

    @Schema(description = "节点 ID", example = "100000000000")
    private Long nodeId;

    @Schema(description = "任务状态", example = "RUNNING")
    private String status;

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

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}