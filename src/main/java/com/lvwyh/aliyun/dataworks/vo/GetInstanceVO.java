package com.lvwyh.aliyun.dataworks.vo;

import com.aliyun.dataworks_public20200518.models.GetInstanceResponseBody;

import java.io.Serializable;

/**
 * 实例详情返回对象
 */
public class GetInstanceVO implements Serializable {

    /**
     * 实例 ID
     */
    private Long instanceId;

    /**
     * 任务状态
     */
    private String status;

    /**
     * 业务日期
     */
    private Long bizdate;

    /**
     * 实例的定时时间
     */
    private Long cycTime;

    /**
     * 实例开始运行时间
     */
    private Long beginRunningTime;

    /**
     * 实例结束运行时间
     */
    private Long finishTime;

    /**
     * 实例创建时间
     */
    private Long createTime;

    /**
     * 实例调度类型
     */
    private String taskType;

    /**
     * 参数信息
     */
    private String paramValues;

    /**
     * 业务流程 ID
     */
    private Long businessId;

    /**
     * 节点 ID
     */
    private Long nodeId;

    /**
     * 节点名称
     */
    private String nodeName;

    public GetInstanceVO() {
    }

    public GetInstanceVO(Long instanceId, String status, Long bizdate, Long cycTime,
                         Long beginRunningTime, Long finishTime, Long createTime,
                         String taskType, String paramValues, Long businessId,
                         Long nodeId, String nodeName) {
        this.instanceId = instanceId;
        this.status = status;
        this.bizdate = bizdate;
        this.cycTime = cycTime;
        this.beginRunningTime = beginRunningTime;
        this.finishTime = finishTime;
        this.createTime = createTime;
        this.taskType = taskType;
        this.paramValues = paramValues;
        this.businessId = businessId;
        this.nodeId = nodeId;
        this.nodeName = nodeName;
    }

    public GetInstanceVO(GetInstanceResponseBody.GetInstanceResponseBodyData data) {
        if (data == null) {
            return;
        }
        this.instanceId = data.getInstanceId();
        this.status = data.getStatus();
        this.bizdate = data.getBizdate();
        this.cycTime = data.getCycTime();
        this.beginRunningTime = data.getBeginRunningTime();
        this.finishTime = data.getFinishTime();
        this.createTime = data.getCreateTime();
        this.taskType = data.getTaskType();
        this.paramValues = data.getParamValues();
        this.businessId = data.getBusinessId();
        this.nodeId = data.getNodeId();
        this.nodeName = data.getNodeName();
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getBizdate() {
        return bizdate;
    }

    public void setBizdate(Long bizdate) {
        this.bizdate = bizdate;
    }

    public Long getCycTime() {
        return cycTime;
    }

    public void setCycTime(Long cycTime) {
        this.cycTime = cycTime;
    }

    public Long getBeginRunningTime() {
        return beginRunningTime;
    }

    public void setBeginRunningTime(Long beginRunningTime) {
        this.beginRunningTime = beginRunningTime;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getParamValues() {
        return paramValues;
    }

    public void setParamValues(String paramValues) {
        this.paramValues = paramValues;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public String toString() {
        return "GetInstanceVO{" +
                "instanceId=" + instanceId +
                ", status='" + status + '\'' +
                ", bizdate=" + bizdate +
                ", cycTime=" + cycTime +
                ", beginRunningTime=" + beginRunningTime +
                ", finishTime=" + finishTime +
                ", createTime=" + createTime +
                ", taskType='" + taskType + '\'' +
                ", paramValues='" + paramValues + '\'' +
                ", businessId=" + businessId +
                ", nodeId=" + nodeId +
                ", nodeName='" + nodeName + '\'' +
                '}';
    }
}
