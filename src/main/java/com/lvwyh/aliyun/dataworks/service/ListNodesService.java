package com.lvwyh.aliyun.dataworks.service;

import com.lvwyh.aliyun.util.PageResult;
import com.lvwyh.aliyun.dataworks.vo.ListNodesVO;

/**
 * DataWorks 节点列表查询服务
 */
public interface ListNodesService {

    /**
     * 查询节点列表
     *
     * @return 返回节点列表，失败时返回空列表
     */
    PageResult<ListNodesVO> listNodes(Integer pageNumber, Integer pageSize, Long projectId, String projectEnv);
    PageResult<ListNodesVO> listNodes(Integer pageNumber, Integer pageSize, Long projectId, String projectEnv, String owner, String bizName, String programType, String nodeName, String schedulerType);
}
