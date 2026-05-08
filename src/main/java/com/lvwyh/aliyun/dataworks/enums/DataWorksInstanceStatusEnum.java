package com.lvwyh.aliyun.dataworks.enums;

import com.lvwyh.aliyun.api.common.exception.BusinessException;

/**
 * DataWorks 实例状态枚举
 */
public enum DataWorksInstanceStatusEnum {

    /**
     * 未运行
     */
    NOT_RUN("NOT_RUN", "NOT_RUN", "未运行"),

    /**
     * 等待时间。等待定时时间到来
     */
    WAIT_TIME("WAIT_TIME", "WAIT_TIME", "等待时间"),

    /**
     * 等待资源。已经下发到执行引擎，在等待资源排队调度执行
     */
    WAIT_RESOURCE("WAIT_RESOURCE", "WAIT_RESOURCE", "等待资源"),

    /**
     * 运行中
     */
    RUNNING("RUNNING", "RUNNING", "运行中"),

    /**
     * 运行失败
     */
    FAILURE("FAILURE", "FAILURE", "运行失败"),

    /**
     * 运行成功
     */
    SUCCESS("SUCCESS", "SUCCESS", "运行成功"),

    /**
     * 校验中
     */
    CHECKING("CHECKING", "CHECKING", "校验中"),

    /**
     * 条件检测中
     */
    CHECKING_CONDITION("CHECKING_CONDITION", "CHECKING_CONDITION", "条件检测中"),

    /**
     * 等待触发
     */
    WAIT_TRIGGER("WAIT_TRIGGER", "WAIT_TRIGGER", "等待触发");

    /**
     * 状态编码（接口返回值）
     */
    private final String code;

    /**
     * 状态描述
     */
    private final String name;

    private final String desc;

    DataWorksInstanceStatusEnum(String code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    // ========================
    // 标准解析方法（对齐你现有风格）
    // ========================

    /**
     * 根据 code 获取枚举
     */
    public static DataWorksInstanceStatusEnum fromCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new BusinessException("非法DataWorks实例状态: " + code);
        }
        for (DataWorksInstanceStatusEnum item : values()) {
            if (item.code.equalsIgnoreCase(code.trim())) {
                return item;
            }
        }
        throw new BusinessException("非法DataWorks实例状态: " + code);
    }

    /**
     * 根据枚举名获取（兜底）
     */
    public static DataWorksInstanceStatusEnum fromName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        for (DataWorksInstanceStatusEnum item : values()) {
            if (item.name().equalsIgnoreCase(name.trim())) {
                return item;
            }
        }
        return null;
    }

    /**
     * 根据 code 获取描述
     */
    public static String getDescByCode(String code) {
        DataWorksInstanceStatusEnum item = fromCode(code);
        return item.getDesc();
    }

    @Override
    public String toString() {
        return "InstanceStatusEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
