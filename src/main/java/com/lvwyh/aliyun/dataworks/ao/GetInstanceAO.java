package com.lvwyh.aliyun.dataworks.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Schema(description = "获取实例详情入参")
public class GetInstanceAO implements Serializable {

    @Schema(description = "实例 ID", example = "11713307578", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "instanceId不能为空")
    private Long instanceId;

    @Schema(description = "工作空间环境，DEV / PROD", example = "PROD", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "projectEnv不能为空")
    private String projectEnv;

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public String getProjectEnv() {
        return projectEnv;
    }

    public void setProjectEnv(String projectEnv) {
        this.projectEnv = projectEnv;
    }
}