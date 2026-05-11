package com.lvwyh.aliyun.datahub.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Schema(description = "查询DataHub Topic列表入参")
public class ListDataHubTopicsAO implements Serializable {

    @Schema(description = "DataHub项目名称", example = "demo_project", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "projectName不能为空")
    @Size(max = 128, message = "projectName长度不能超过128")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]*$", message = "projectName只能包含字母、数字、下划线，并且必须以字母开头")
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
