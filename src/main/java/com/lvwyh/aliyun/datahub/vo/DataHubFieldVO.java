package com.lvwyh.aliyun.datahub.vo;

import java.io.Serializable;

public class DataHubFieldVO implements Serializable {

    private String name;

    private String type;

    private Boolean allowNull;

    private String comment;

    public DataHubFieldVO() {
    }

    public DataHubFieldVO(String name, String type, Boolean allowNull, String comment) {
        this.name = name;
        this.type = type;
        this.allowNull = allowNull;
        this.comment = comment;
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

    public Boolean getAllowNull() {
        return allowNull;
    }

    public void setAllowNull(Boolean allowNull) {
        this.allowNull = allowNull;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
