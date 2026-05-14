package com.lvwyh.aliyun.maxcompute.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MaxComputeTableSchemaVO implements Serializable {

    private String projectName;

    private String tableName;

    private String tableComment;

    private String tableType;

    private String owner;

    private Date createdTime;

    private Long recordNum;

    private Long size;

    private Integer columnCount;

    private Integer partitionColumnCount;

    private List<MaxComputeColumnVO> columns;

    private List<MaxComputeColumnVO> partitionColumns;

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

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(Long recordNum) {
        this.recordNum = recordNum;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }

    public Integer getPartitionColumnCount() {
        return partitionColumnCount;
    }

    public void setPartitionColumnCount(Integer partitionColumnCount) {
        this.partitionColumnCount = partitionColumnCount;
    }

    public List<MaxComputeColumnVO> getColumns() {
        return columns;
    }

    public void setColumns(List<MaxComputeColumnVO> columns) {
        this.columns = columns;
    }

    public List<MaxComputeColumnVO> getPartitionColumns() {
        return partitionColumns;
    }

    public void setPartitionColumns(List<MaxComputeColumnVO> partitionColumns) {
        this.partitionColumns = partitionColumns;
    }
}
