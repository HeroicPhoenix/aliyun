package com.lvwyh.aliyun.dataworks.enums;

import com.lvwyh.aliyun.api.common.exception.BusinessException;

/**
 * DataWorks 实例调度类型枚举
 */
public enum DataWorksTaskTypeEnum {

    /**
     * 正常调度任务
     */
    NORMAL("NORMAL", "NORMAL", "正常调度任务"),

    /**
     * 手动任务
     */
    MANUAL("MANUAL", "MANUAL", "手动任务"),

    /**
     * 冻结任务
     */
    PAUSE("PAUSE", "PAUSE", "冻结任务"),

    /**
     * 空跑任务
     */
    SKIP("SKIP", "SKIP", "空跑任务"),

    /**
     * 临时工作流未选择任务
     */
    SKIP_UNCHOOSE("SKIP_UNCHOOSE", "SKIP_UNCHOOSE", "临时工作流未选择任务"),

    /**
     * 未到周期任务
     */
    SKIP_CYCLE("SKIP_CYCLE", "SKIP_CYCLE", "未到运行周期任务"),

    /**
     * 分支未选中任务
     */
    CONDITION_UNCHOOSE("CONDITION_UNCHOOSE", "CONDITION_UNCHOOSE", "分支未选中任务"),

    /**
     * 已过期周期实例
     */
    REALTIME_DEPRECATED("REALTIME_DEPRECATED", "REALTIME_DEPRECATED", "已过期周期实例");

    /**
     * 类型编码（接口返回值）
     */
    private final String code;

    /**
     * 类型描述
     */
    private final String name;

    private final String desc;

    DataWorksTaskTypeEnum(String code, String name, String desc) {
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
    public static DataWorksTaskTypeEnum fromCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new BusinessException("非法DataWorks任务类型: " + code);
        }
        for (DataWorksTaskTypeEnum item : values()) {
            if (item.code.equalsIgnoreCase(code.trim())) {
                return item;
            }
        }
        throw new BusinessException("非法DataWorks任务类型: " + code);
    }

    /**
     * 根据枚举名获取（兜底）
     */
    public static DataWorksTaskTypeEnum fromName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        for (DataWorksTaskTypeEnum item : values()) {
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
        DataWorksTaskTypeEnum item = fromCode(code);
        return item.getDesc();
    }

    @Override
    public String toString() {
        return "TaskTypeEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
