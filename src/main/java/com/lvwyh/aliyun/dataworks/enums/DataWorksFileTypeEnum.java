package com.lvwyh.aliyun.dataworks.enums;

import com.lvwyh.aliyun.api.common.exception.BusinessException;

/**
 * 文件类型枚举
 */
public enum DataWorksFileTypeEnum {

    // =========================
    // 数据同步类节点
    // =========================

    /**
     * 离线同步节点
     */
    DI_OFFLINE_SYNC(23, "DI_OFFLINE_SYNC", "离线同步节点", "DI"),

    /**
     * 实时同步节点
     */
    RI_REALTIME_SYNC(900, "RI_REALTIME_SYNC", "实时同步节点", "RI"),

    // =========================
    // MaxCompute
    // =========================

    /**
     * ODPS SQL节点
     */
    ODPS_SQL(10, "ODPS_SQL", "ODPS SQL节点", "ODPS_SQL"),

    /**
     * ODPS Spark节点
     */
    ODPS_SPARK(225, "ODPS_SPARK", "ODPS Spark节点", "ODPS_SPARK"),

    /**
     * PyODPS 2节点
     */
    PY_ODPS(221, "PY_ODPS", "PyODPS 2节点", "PY_ODPS"),

    /**
     * PyODPS 3节点
     */
    PYODPS3(1221, "PYODPS3", "PyODPS 3节点", "PYODPS3"),

    /**
     * ODPS Script节点
     */
    ODPS_SQL_SCRIPT(24, "ODPS_SQL_SCRIPT", "ODPS Script节点", "ODPS_SQL_SCRIPT"),

    /**
     * ODPS MR节点
     */
    ODPS_MR(11, "ODPS_MR", "ODPS MR节点", "ODPS_MR"),

    /**
     * SQL组件节点
     */
    COMPONENT_SQL(1010, "COMPONENT_SQL", "SQL组件节点", "COMPONENT_SQL"),

    // =========================
    // E-MapReduce
    // =========================

    /**
     * EMR Hive节点
     */
    EMR_HIVE(227, "EMR_HIVE", "EMR Hive节点", "EMR_HIVE"),

    /**
     * EMR MR节点
     */
    EMR_MR(230, "EMR_MR", "EMR MR节点", "EMR_MR"),

    /**
     * EMR Spark SQL节点
     */
    EMR_SPARK_SQL(229, "EMR_SPARK_SQL", "EMR Spark SQL节点", "EMR_SPARK_SQL"),

    /**
     * EMR Spark节点
     */
    EMR_SPARK(228, "EMR_SPARK", "EMR Spark节点", "EMR_SPARK"),

    /**
     * EMR Shell节点
     */
    EMR_SHELL(257, "EMR_SHELL", "EMR Shell节点", "EMR_SHELL"),

    /**
     * EMR Presto节点
     */
    EMR_PRESTO(259, "EMR_PRESTO", "EMR Presto节点", "EMR_PRESTO"),

    /**
     * EMR Impala节点
     */
    EMR_IMPALA(260, "EMR_IMPALA", "EMR Impala节点", "EMR_IMPALA"),

    /**
     * EMR Spark Streaming节点
     */
    EMR_SPARK_STREAMING(264, "EMR_SPARK_STREAMING", "EMR Spark Streaming节点", "EMR_SPARK_STREAMING"),

    /**
     * EMR Kyuubi节点
     */
    EMR_KYUUBI(268, "EMR_KYUUBI", "EMR Kyuubi节点", "EMR_KYUUBI"),

    /**
     * EMR Trino节点
     */
    EMR_TRINO(267, "EMR_TRINO", "EMR Trino节点", "EMR_TRINO"),

    /**
     * EMR JAR节点
     */
    EMR_JAR(231, "EMR_JAR", "EMR JAR节点", "EMR_JAR"),

    /**
     * EMR File节点
     */
    EMR_FILE(232, "EMR_FILE", "EMR File节点", "EMR_FILE"),

    // =========================
    // CDH
    // =========================

    /**
     * CDH Hive节点
     */
    CDH_HIVE(270, "CDH_HIVE", "CDH Hive节点", "CDH_HIVE"),

    /**
     * CDH Spark节点
     */
    CDH_SPARK(271, "CDH_SPARK", "CDH Spark节点", "CDH_SPARK"),

    /**
     * CDH MR节点
     */
    CDH_MR(273, "CDH_MR", "CDH MR节点", "CDH_MR"),

    /**
     * CDH Presto节点
     */
    CDH_PRESTO(278, "CDH_PRESTO", "CDH Presto节点", "CDH_PRESTO"),

    /**
     * CDH Impala节点
     */
    CDH_IMPALA(279, "CDH_IMPALA", "CDH Impala节点", "CDH_IMPALA"),

    /**
     * CDH Spark SQL节点
     */
    CDH_SPARK_SQL(272, "CDH_SPARK_SQL", "CDH Spark SQL节点", "CDH_SPARK_SQL"),

    // =========================
    // Hologres / ClickHouse / PAI
    // =========================

    /**
     * Hologres SQL节点
     */
    HOLOGRES_SQL(1093, "HOLOGRES_SQL", "Hologres SQL节点", "HOLOGRES_SQL"),

    /**
     * 一键MaxCompute表结构同步节点
     */
    HOLOGRES_SYNC_DDL(1094, "HOLOGRES_SYNC_DDL", "一键MaxCompute表结构同步节点", "HOLOGRES_SYNC_DDL"),

    /**
     * 一键MaxCompute数据同步节点
     */
    HOLOGRES_SYNC_DATA(1095, "HOLOGRES_SYNC_DATA", "一键MaxCompute数据同步节点", "HOLOGRES_SYNC_DATA"),

    /**
     * ClickHouse SQL节点
     */
    CLICK_SQL(1301, "CLICK_SQL", "ClickHouse SQL节点", "CLICK_SQL"),

    /**
     * PAI Designer节点
     */
    PAI_STUDIO(1117, "PAI_STUDIO", "PAI Designer节点", "PAI_STUDIO"),

    /**
     * PAI DLC节点
     */
    PAI_DLC(1119, "PAI_DLC", "PAI DLC节点", "PAI_DLC"),

    // =========================
    // 数据库
    // =========================

    /**
     * MySQL节点
     */
    MYSQL(1000125, "MYSQL", "MySQL节点", null),

    /**
     * SQL Server节点
     */
    SQL_SERVER(10001, "SQL_SERVER", "SQL Server节点", null),

    /**
     * Oracle节点
     */
    ORACLE(10002, "ORACLE", "Oracle节点", null),

    /**
     * PostgreSQL节点
     */
    POSTGRESQL(10003, "POSTGRESQL", "PostgreSQL节点", null),

    /**
     * StarRocks节点
     */
    STARROCKS(10004, "STARROCKS", "StarRocks节点", null),

    /**
     * DRDS节点
     */
    DRDS(10005, "DRDS", "DRDS节点", null),

    /**
     * PolarDB MySQL节点
     */
    POLARDB_MYSQL(10006, "POLARDB_MYSQL", "PolarDB MySQL节点", null),

    /**
     * PolarDB PostgreSQL节点
     */
    POLARDB_POSTGRESQL(10007, "POLARDB_POSTGRESQL", "PolarDB PostgreSQL节点", null),

    /**
     * Doris节点
     */
    DORIS(10008, "DORIS", "Doris节点", null),

    /**
     * MariaDB节点
     */
    MARIADB(10009, "MARIADB", "MariaDB节点", null),

    /**
     * SelectDB节点
     */
    SELECTDB(10010, "SELECTDB", "SelectDB节点", null),

    /**
     * Redshift节点
     */
    REDSHIFT(10011, "REDSHIFT", "Redshift节点", null),

    /**
     * SAP HANA节点
     */
    SAP_HANA(10012, "SAP_HANA", "SAP HANA节点", null),

    /**
     * Vertica节点
     */
    VERTICA(10013, "VERTICA", "Vertica节点", null),

    /**
     * DM（达梦）节点
     */
    DM(10014, "DM", "DM（达梦）节点", null),

    /**
     * KingbaseES（人大金仓）节点
     */
    KINGBASE_ES(10015, "KINGBASE_ES", "KingbaseES（人大金仓）节点", null),

    /**
     * OceanBase节点
     */
    OCEANBASE(10016, "OCEANBASE", "OceanBase节点", null),

    /**
     * DB2节点
     */
    DB2(10017, "DB2", "DB2节点", null),

    /**
     * GBase 8a节点
     */
    GBASE_8A(10018, "GBASE_8A", "GBase 8a节点", null),

    /**
     * Data Lake Analytics节点
     */
    DATA_LAKE_ANALYTICS(1000023, "DATA_LAKE_ANALYTICS", "Data Lake Analytics节点", null),

    // =========================
    // 通用类节点
    // =========================

    /**
     * 虚拟节点
     */
    VIRTUAL(99, "VIRTUAL", "虚拟节点", "VIRTUAL"),

    /**
     * HTTP触发器节点
     */
    SCHEDULER_TRIGGER(1114, "SCHEDULER_TRIGGER", "HTTP触发器节点", "SCHEDULER_TRIGGER"),

    /**
     * OSS对象检查节点
     */
    OSS_INSPECT(239, "OSS_INSPECT", "OSS对象检查节点", "OSS_INSPECT"),

    /**
     * FTP Check节点
     */
    FTP_CHECK(1320, "FTP_CHECK", "FTP Check节点", "FTP_CHECK"),

    /**
     * Check节点
     */
    CHECK_NODE(241, "CHECK_NODE", "Check节点", "CHECK_NODE"),

    /**
     * 数据质量监控节点
     */
    DATA_QUALITY_MONITOR(1333, "DATA_QUALITY_MONITOR", "数据质量监控节点", "DATA_QUALITY_MONITOR"),

    /**
     * 数据对比节点
     */
    DATA_SYNCHRONIZATION_QUALITY_CHECK(1331, "DATA_SYNCHRONIZATION_QUALITY_CHECK", "数据对比节点", "DATA_SYNCHRONIZATION_QUALITY_CHECK"),

    /**
     * 赋值节点
     */
    CONTROLLER_ASSIGNMENT(1100, "CONTROLLER_ASSIGNMENT", "赋值节点", "CONTROLLER_ASSIGNMENT"),

    /**
     * 参数节点
     */
    PARAM_HUB(1115, "PARAM_HUB", "参数节点", "PARAM_HUB"),

    /**
     * for-each节点
     */
    CONTROLLER_TRAVERSE(1106, "CONTROLLER_TRAVERSE", "for-each节点", "CONTROLLER_TRAVERSE"),

    /**
     * do-while节点
     */
    CONTROLLER_CYCLE(1103, "CONTROLLER_CYCLE", "do-while节点", "CONTROLLER_CYCLE"),

    /**
     * 分支节点
     */
    CONTROLLER_BRANCH(1101, "CONTROLLER_BRANCH", "分支节点", "CONTROLLER_BRANCH"),

    /**
     * 归并节点
     */
    CONTROLLER_JOIN(1102, "CONTROLLER_JOIN", "归并节点", "CONTROLLER_JOIN"),

    /**
     * Shell节点
     */
    DIDE_SHELL(6, "DIDE_SHELL", "Shell节点", "DIDE_SHELL"),

    /**
     * 函数计算节点
     */
    FUNCTION_COMPUTE(1330, "FUNCTION_COMPUTE", "函数计算节点", "FUNCTION_COMPUTE"),

    /**
     * 数据推送节点
     */
    DATA_PUSH(1332, "DATA_PUSH", "数据推送节点", "DATA_PUSH");

    /**
     * 节点编码
     */
    private final Integer code;

    /**
     * 节点名称说明
     */
    private final String name;

    private final String desc;

    /**
     * TaskType
     */
    private final String taskType;

    DataWorksFileTypeEnum(Integer code, String name, String desc, String taskType) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.taskType = taskType;
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

    public String getTaskType() {
        return taskType;
    }

    public static DataWorksFileTypeEnum fromCode(Integer code) {
        if (code == null) {
            throw new BusinessException("非法DataWorks文件类型: " + code);
        }
        for (DataWorksFileTypeEnum item : values()) {
            if (item.code.equals(code)) {
                return item;
            }
        }
        throw new BusinessException("非法DataWorks文件类型: " + code);
    }
}
