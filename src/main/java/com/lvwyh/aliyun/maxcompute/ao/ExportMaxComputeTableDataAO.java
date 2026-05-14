package com.lvwyh.aliyun.maxcompute.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Schema(description = "导出MaxCompute表数据入参")
public class ExportMaxComputeTableDataAO implements Serializable {

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

    @Schema(description = "页码，从1开始", example = "1")
    @Min(value = 1, message = "pageNum不能小于1")
    private Integer pageNum = 1;

    @Schema(description = "分页大小，最大1000", example = "100")
    @Min(value = 1, message = "pageSize不能小于1")
    @Max(value = 1000, message = "pageSize不能大于1000")
    private Integer pageSize = 100;

    @Schema(description = "导出文件名，不传时自动生成", example = "demo_table_data.csv")
    @Size(max = 120, message = "fileName长度不能超过120")
    @Pattern(regexp = "^$|^[A-Za-z0-9._-]+\\.csv$", message = "fileName只能包含字母、数字、点、下划线、中划线，并且必须以.csv结尾")
    private String fileName;

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

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
