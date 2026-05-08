package com.lvwyh.aliyun.api.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "统一接口返回结构")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    @Schema(description = "业务状态码", example = "200")
    private Integer code;

    @Schema(description = "是否成功", example = "true")
    private Boolean status;

    @Schema(description = "提示信息", example = "操作成功")
    private String message;

    @Schema(description = "实际返回数据")
    private T data;

    @Schema(description = "时间戳", example = "1710000000000")
    private Long timestamp;

    public ApiResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.code = 200;
        response.status = true;
        response.message = "操作成功";
        response.data = data;
        return response;
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.code = 200;
        response.status = true;
        response.message = message;
        response.data = data;
        return response;
    }

    public static <T> ApiResponse<T> fail(int code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.code = code;
        response.status = false;
        response.message = message;
        return response;
    }

    public static <T> ApiResponse<T> fail(String message, int code) {
        return fail(code, message);
    }

    public Integer getCode() {
        return code;
    }

    public Boolean getStatus() {
        return status;
    }

    public boolean isStatus() {
        return Boolean.TRUE.equals(status);
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
