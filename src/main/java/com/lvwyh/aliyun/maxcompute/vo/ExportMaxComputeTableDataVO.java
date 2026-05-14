package com.lvwyh.aliyun.maxcompute.vo;

import java.io.Serializable;

public class ExportMaxComputeTableDataVO implements Serializable {

    private String projectName;

    private String tableName;

    private String fileName;

    private String filePath;

    private Integer pageNum;

    private Integer pageSize;

    private Integer columnCount;

    private Long rowCount;

    public ExportMaxComputeTableDataVO() {
    }

    public ExportMaxComputeTableDataVO(String projectName, String tableName, String fileName,
                                       String filePath, Integer pageNum, Integer pageSize,
                                       Integer columnCount, Long rowCount) {
        this.projectName = projectName;
        this.tableName = tableName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.columnCount = columnCount;
        this.rowCount = rowCount;
    }

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

    public Integer getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }

    public Long getRowCount() {
        return rowCount;
    }

    public void setRowCount(Long rowCount) {
        this.rowCount = rowCount;
    }
}
