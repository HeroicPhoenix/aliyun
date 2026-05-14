package com.lvwyh.aliyun.maxcompute.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Schema(description = "查询MaxCompute表结构入参")
public class GetMaxComputeTableSchemaAO implements Serializable {

    @Schema(description = "MaxCompute项目名称", example = "demo_project", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "projectName不能为空")
    @Size(max = 128, message = "projectName长度不能超过128")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]*$", message = "projectName只能包含字母、数字、下划线，并且必须以字母开头")
    private String projectName;

    @Schema(description = "表名称", example = "demo_table", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "tableName不能为空")
    @Size(max = 128, message = "tableName长度不能超过128")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]*$", message = "tableName只能包含字母、数字、下划线，并且必须以字母开头")
    private String tableName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
