package com.lvwyh.aliyun.dataworks.enums;

import com.lvwyh.aliyun.api.common.exception.BusinessException;

/**
 * 调度类型枚举
 */
public enum DataWorksSchedulerTypeEnum {

    /**
     * 正常调度任务
     */
    NORMAL("NORMAL", "NORMAL", "正常调度任务"),

    /**
     * 手动任务，不会被日常调度
     */
    MANUAL("MANUAL", "MANUAL", "手动任务，不会被日常调度"),

    /**
     * 暂停任务
     */
    PAUSE("PAUSE", "PAUSE", "暂停任务"),

    /**
     * 空跑任务，被日常调度，但启动调度时直接被置为成功
     */
    SKIP("SKIP", "SKIP", "空跑任务，被日常调度，但启动调度时直接被置为成功");

    /**
     * 枚举编码
     */
    private final String code;

    /**
     * 枚举说明
     */
    private final String name;

    private final String desc;

    DataWorksSchedulerTypeEnum(String code, String name, String desc) {
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

    public static DataWorksSchedulerTypeEnum fromCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new BusinessException("非法DataWorks调度类型: " + code);
        }
        for (DataWorksSchedulerTypeEnum item : values()) {
            if (item.code.equalsIgnoreCase(code.trim())) {
                return item;
            }
        }
        throw new BusinessException("非法DataWorks调度类型: " + code);
    }
}
