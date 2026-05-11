package com.lvwyh.aliyun.maxcompute.vo;

import java.io.Serializable;

public class ExportMaxComputeMetadataVO implements Serializable {

    private String projectName;

    private String fileName;

    private String filePath;

    private Integer tableCount;

    private Integer columnCount;

    public ExportMaxComputeMetadataVO() {
    }

    public ExportMaxComputeMetadataVO(String projectName, String fileName, String filePath, Integer tableCount, Integer columnCount) {
        this.projectName = projectName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.tableCount = tableCount;
        this.columnCount = columnCount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getTableCount() {
        return tableCount;
    }

    public void setTableCount(Integer tableCount) {
        this.tableCount = tableCount;
    }

    public Integer getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }
}
