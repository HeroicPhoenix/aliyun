package com.lvwyh.aliyun.dataworks.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.aliyun.dataworks_public20200518.models.ListProjectsResponseBody;
import com.lvwyh.aliyun.dataworks.enums.DataWorksProjectStatusEnum;

import java.io.Serializable;

/**
 * 工作空间列表返回对象
 */
public class ListProjectsVO implements Serializable {

    /**
     * 工作空间状态码（字符串）
     * AVAILABLE / DELETED / INITIALIZING 等
     */
    private String projectStatusCode;

    /**
     * 工作空间状态（数值）
     * 0~9，对应不同状态
     */
    private Integer projectStatus;

    /**
     * 工作空间的显示名称
     */
    private String projectName;

    /**
     * 工作空间的唯一标识名称
     */
    private String projectIdentifier;

    /**
     * 工作空间的 ID
     */
    private Long projectId;

    /**
     * 工作空间的描述信息
     */
    private String projectDescription;

    /**
     * 工作空间所有者的用户 ID
     */
    private String projectOwnerBaseId;

    public ListProjectsVO() {
    }

    public ListProjectsVO(String projectStatusCode, Integer projectStatus, String projectName, String projectIdentifier, Long projectId, String projectDescription, String projectOwnerBaseId) {
        this.projectStatusCode = projectStatusCode;
        this.projectStatus = projectStatus;
        this.projectName = projectName;
        this.projectIdentifier = projectIdentifier;
        this.projectId = projectId;
        this.projectDescription = projectDescription;
        this.projectOwnerBaseId = projectOwnerBaseId;
    }

    public ListProjectsVO(ListProjectsResponseBody.ListProjectsResponseBodyPageResultProjectList project) {
        if (project == null) {
            return;
        }
        this.projectStatusCode = project.getProjectStatusCode();
        this.projectStatus = project.getProjectStatus();
        this.projectName = project.getProjectName();
        this.projectIdentifier = project.getProjectIdentifier();
        this.projectId = project.getProjectId();
        this.projectDescription = project.getProjectDescription();
        this.projectOwnerBaseId = project.getProjectOwnerBaseId();
    }



    // ========================
    // getter / setter
    // ========================

    public String getProjectStatusCode() {
        return projectStatusCode;
    }

    public void setProjectStatusCode(String projectStatusCode) {
        this.projectStatusCode = projectStatusCode;
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectOwnerBaseId() {
        return projectOwnerBaseId;
    }

    public void setProjectOwnerBaseId(String projectOwnerBaseId) {
        this.projectOwnerBaseId = projectOwnerBaseId;
    }

    // ========================
    // 枚举扩展方法（重点）
    // ========================

    /**
     * 根据数值状态获取枚举
     */
    @JsonIgnore
    public DataWorksProjectStatusEnum getProjectStatusEnum() {
        return DataWorksProjectStatusEnum.fromCode(this.projectStatus);
    }

    /**
     * 根据字符串状态获取枚举（兜底）
     */
    @JsonIgnore
    public DataWorksProjectStatusEnum getProjectStatusEnumByCode() {
        return DataWorksProjectStatusEnum.fromName(this.projectStatusCode);
    }

    @Override
    public String toString() {
        return "ListProjectVO{" +
                "projectStatusCode='" + projectStatusCode + '\'' +
                ", projectStatus=" + projectStatus +
                ", projectName='" + projectName + '\'' +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                ", projectId=" + projectId +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectOwnerBaseId='" + projectOwnerBaseId + '\'' +
                '}';
    }
}
