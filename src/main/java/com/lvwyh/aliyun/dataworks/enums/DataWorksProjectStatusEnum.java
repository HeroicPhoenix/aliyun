package com.lvwyh.aliyun.dataworks.enums;

import com.lvwyh.aliyun.api.common.exception.BusinessException;

/**
 * 工作空间状态枚举
 */
public enum DataWorksProjectStatusEnum {

    /**
     * 正常
     */
    AVAILABLE(0, "AVAILABLE", "正常"),

    /**
     * 已删除
     */
    DELETED(1, "DELETED", "已删除"),

    /**
     * 初始化中
     */
    INITIALIZING(2, "INITIALIZING", "初始化中"),

    /**
     * 初始化失败
     */
    INIT_FAILED(3, "INIT_FAILED", "初始化失败"),

    /**
     * 手动禁用
     */
    FORBIDDEN(4, "FORBIDDEN", "手动禁用"),

    /**
     * 删除中
     */
    DELETING(5, "DELETING", "删除中"),

    /**
     * 删除失败
     */
    DEL_FAILED(6, "DEL_FAILED", "删除失败"),

    /**
     * 欠费冻结
     */
    FROZEN(7, "FROZEN", "欠费冻结"),

    /**
     * 更新中
     */
    UPDATING(8, "UPDATING", "项目更新中"),

    /**
     * 更新失败
     */
    UPDATE_FAILED(9, "UPDATE_FAILED", "项目更新失败");

    /**
     * 状态码（int）
     */
    private final Integer code;

    /**
     * 状态标识（string）
     */
    private final String name;

    /**
     * 中文描述
     */
    private final String desc;

    DataWorksProjectStatusEnum(Integer code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据 int 状态获取枚举
     */
    public static DataWorksProjectStatusEnum fromCode(Integer code) {
        if (code == null) {
            throw new BusinessException("非法DataWorks工作空间状态: " + code);
        }
        for (DataWorksProjectStatusEnum e : values()) {
            if (e.code.equals(code)) {
                return e;
            }
        }
        throw new BusinessException("非法DataWorks工作空间状态: " + code);
    }

    /**
     * 根据 string 状态获取枚举
     */
    public static DataWorksProjectStatusEnum fromName(String name) {
        if (name == null) return null;
        for (DataWorksProjectStatusEnum e : values()) {
            if (e.name.equalsIgnoreCase(name)) return e;
        }
        return null;
    }
}
