package com.lvwyh.aliyun.dataworks.service;

/**
 * DataWorks 触发式节点运行服务
 */
public interface RunTriggerNodeService {

    /**
     * 运行触发式节点
     *
     * @return 成功返回 true，否则返回 false
     */
    boolean runTriggerNode(Long nodeId, Long projectId, Long cycleTime, Long bizDate);
}