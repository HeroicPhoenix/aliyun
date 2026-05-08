package com.lvwyh.aliyun.dataworks.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.aliyun.dataworks_public20200518.models.GetNodeResponseBody;
import com.lvwyh.aliyun.dataworks.enums.DataWorksFileTypeEnum;
import com.lvwyh.aliyun.dataworks.enums.DataWorksSchedulerTypeEnum;

import java.io.Serializable;

/**
 * 节点详情返回对象
 */
public class GetNodeVO implements Serializable {

    /**
     * 节点的 ID
     */
    private Long nodeId;

    /**
     * 文件类型
     */
    private Integer fileType;

    /**
     * 节点的名称
     */
    private String nodeName;

    /**
     * 节点的描述信息
     */
    private String description;

    /**
     * 节点的类型
     */
    private String programType;

    /**
     * 调度的类型
     */
    private String schedulerType;

    /**
     * cron 表达式
     */
    private String cronExpress;

    /**
     * 业务流程 ID
     */
    private Long businessId;

    /**
     * 工作空间的 ID
     */
    private Long projectId;

    /**
     * 负责人的 ID
     */
    private String ownerId;

    public GetNodeVO() {
    }

    public GetNodeVO(Long nodeId, Integer fileType, String nodeName, String description, String programType, String schedulerType, String cronExpress, Long businessId, Long projectId, String ownerId) {
        this.nodeId = nodeId;
        this.fileType = fileType;
        this.nodeName = nodeName;
        this.description = description;
        this.programType = programType;
        this.schedulerType = schedulerType;
        this.cronExpress = cronExpress;
        this.businessId = businessId;
        this.projectId = projectId;
        this.ownerId = ownerId;
    }

    public GetNodeVO(GetNodeResponseBody.GetNodeResponseBodyData data) {
        if (data == null) {
            return;
        }
        this.nodeId = data.getNodeId();
        this.fileType = data.getFileType();
        this.nodeName = data.getNodeName();
        this.description = data.getDescription();
        this.programType = data.getProgramType();
        this.schedulerType = data.getSchedulerType();
        this.cronExpress = data.getCronExpress();
        this.businessId = data.getBusinessId();
        this.projectId = data.getProjectId();
        this.ownerId = data.getOwnerId();
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCronExpress() {
        return cronExpress;
    }

    public void setCronExpress(String cronExpress) {
        this.cronExpress = cronExpress;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 获取调度类型枚举
     */
    @JsonIgnore
    public DataWorksSchedulerTypeEnum getSchedulerTypeEnum() {
        return DataWorksSchedulerTypeEnum.fromCode(this.schedulerType);
    }

    /**
     * 获取文件类型枚举
     */
    @JsonIgnore
    public DataWorksFileTypeEnum getFileTypeEnum() {
        return DataWorksFileTypeEnum.fromCode(this.fileType);
    }

    @Override
    public String toString() {
        return "GetNodeVO{" +
                "nodeId=" + nodeId +
                ", fileType=" + fileType +
                ", nodeName='" + nodeName + '\'' +
                ", description='" + description + '\'' +
                ", programType='" + programType + '\'' +
                ", schedulerType='" + schedulerType + '\'' +
                ", cronExpress='" + cronExpress + '\'' +
                ", businessId=" + businessId +
                ", projectId=" + projectId +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }
}
