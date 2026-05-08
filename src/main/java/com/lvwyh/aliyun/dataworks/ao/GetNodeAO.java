package com.lvwyh.aliyun.dataworks.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Schema(description = "获取节点详情入参")
public class GetNodeAO implements Serializable {

    @Schema(description = "节点 ID", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "nodeId不能为空")
    private Long nodeId;

    @Schema(description = "工作空间环境，DEV / PROD", example = "DEV", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "projectEnv不能为空")
    private String projectEnv;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getProjectEnv() {
        return projectEnv;
    }

    public void setProjectEnv(String projectEnv) {
        this.projectEnv = projectEnv;
    }
}