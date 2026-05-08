package com.lvwyh.aliyun.dataworks.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Schema(description = "查询工作空间列表入参")
public class ListProjectsAO implements Serializable {

    @Schema(description = "页码，最小 1", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "pageNumber不能为空")
    @Min(value = 1, message = "pageNumber不能小于1")
    @Max(value = 100, message = "pageNumber不能大于100")
    private Integer pageNumber;

    @Schema(description = "每页条数，最大 100", example = "10")
    @Min(value = 1, message = "pageSize不能小于1")
    @Max(value = 100, message = "pageSize不能大于100")
    private Integer pageSize;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}