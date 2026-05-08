package com.lvwyh.aliyun.dataworks.vo;

import com.aliyun.dataworks_public20200518.models.ListInstancesResponseBody;

import java.io.Serializable;

/**
 * 实例列表返回对象
 */
public class ListInstancesVO implements Serializable {

    /**
     * 实例 ID
     */
    private Long instanceId;

    /**
     * 任务状态
     */
    private String status;

    /**
     * 执行调度任务的日期
     */
    private Long bizdate;

    /**
     * 调度任务的运行时间
     */
    private Long cycTime;

    /**
     * 实例开始运行的时间
     */
    private Long beginRunningTime;

    /**
     * 调度任务的结束时间
     */
    private Long finishTime;

    /**
     * 实例的创建时间
     */
    private Long createTime;

    /**
     * 任务实例的调度类型
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

    public ListInstancesVO() {
    }

    public ListInstancesVO(ListInstancesResponseBody.ListInstancesResponseBodyDataInstances instance) {
        if (instance == null) {
            return;
        }

        this.instanceId = instance.getInstanceId();
        this.status = instance.getStatus();
        this.bizdate = instance.getBizdate();
        this.cycTime = instance.getCycTime();
        this.beginRunningTime = instance.getBeginRunningTime();
        this.finishTime = instance.getFinishTime();
        this.createTime = instance.getCreateTime();
        this.taskType = instance.getTaskType();
        this.paramValues = instance.getParamValues();
        this.businessId = instance.getBusinessId();
        this.nodeId = instance.getNodeId();
        this.nodeName = instance.getNodeName();
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
        return "ListInstancesVO{" +
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
