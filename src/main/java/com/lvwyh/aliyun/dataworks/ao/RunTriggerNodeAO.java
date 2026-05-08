package com.lvwyh.aliyun.dataworks.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Schema(description = "运行触发式节点入参")
public class RunTriggerNodeAO implements Serializable {

    @Schema(description = "DataWorks 工作空间 ID", example = "306754", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "projectId不能为空")
    private Long projectId;

    @Schema(description = "触发式节点 ID", example = "700008592141", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "nodeId不能为空")
    private Long nodeId;

    @Schema(description = "13 位毫秒级定时时间戳", example = "1774508400000", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "cycleTime不能为空")
    private Long cycleTime;

    @Schema(description = "业务日期时间戳", example = "1774508400000", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "bizDate不能为空")
    private Long bizDate;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(Long cycleTime) {
        this.cycleTime = cycleTime;
    }

    public Long getBizDate() {
        return bizDate;
    }

    public void setBizDate(Long bizDate) {
        this.bizDate = bizDate;
    }
}