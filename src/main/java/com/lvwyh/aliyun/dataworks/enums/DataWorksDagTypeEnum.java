package com.lvwyh.aliyun.dataworks.enums;

import com.lvwyh.aliyun.api.common.exception.BusinessException;

/**
 * DataWorks 工作流类型枚举
 */
public enum DataWorksDagTypeEnum {

    /**
     * 日常调度工作流
     */
    DAILY("DAILY", "DAILY", "日常调度工作流"),

    /**
     * 手动任务工作流
     */
    MANUAL("MANUAL", "MANUAL", "手动任务工作流"),

    /**
     * 冒烟测试工作流
     */
    SMOKE_TEST("SMOKE_TEST", "SMOKE_TEST", "冒烟测试工作流"),

    /**
     * 补数据工作流
     */
    SUPPLY_DATA("SUPPLY_DATA", "SUPPLY_DATA", "补数据工作流"),

    /**
     * 手动运行的业务流程（IDE运行）
     */
    MANUAL_FLOW("MANUAL_FLOW", "MANUAL_FLOW", "手动运行业务流程"),

    /**
     * 手动业务流程工作流
     */
    BUSINESS_PROCESS_DAG("BUSINESS_PROCESS_DAG", "BUSINESS_PROCESS_DAG", "手动业务流程工作流");

    /**
     * 类型编码（接口返回值）
     */
    private final String code;

    /**
     * 类型描述
     */
    private final String name;

    private final String desc;

    DataWorksDagTypeEnum(String code, String name, String desc) {
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
    // 标准解析方法（统一风格）
    // ========================

    /**
     * 根据 code 获取枚举
     */
    public static DataWorksDagTypeEnum fromCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new BusinessException("非法DataWorks工作流类型: " + code);
        }
        for (DataWorksDagTypeEnum item : values()) {
            if (item.code.equalsIgnoreCase(code.trim())) {
                return item;
            }
        }
        throw new BusinessException("非法DataWorks工作流类型: " + code);
    }

    /**
     * 根据枚举名获取（兜底）
     */
    public static DataWorksDagTypeEnum fromName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        for (DataWorksDagTypeEnum item : values()) {
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
        DataWorksDagTypeEnum item = fromCode(code);
        return item.getDesc();
    }

    @Override
    public String toString() {
        return "DagTypeEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
