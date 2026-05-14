package com.lvwyh.aliyun.maxcompute.vo;

import java.io.Serializable;

public class MaxComputeColumnVO implements Serializable {

    private String name;

    private String type;

    private String comment;

    private Boolean nullable;

    private Boolean partitionColumn;

    private Integer position;

    public MaxComputeColumnVO() {
    }

    public MaxComputeColumnVO(String name, String type, String comment, Boolean nullable,
                              Boolean partitionColumn, Integer position) {
        this.name = name;
        this.type = type;
        this.comment = comment;
        this.nullable = nullable;
        this.partitionColumn = partitionColumn;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public Boolean getPartitionColumn() {
        return partitionColumn;
    }

    public void setPartitionColumn(Boolean partitionColumn) {
        this.partitionColumn = partitionColumn;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
