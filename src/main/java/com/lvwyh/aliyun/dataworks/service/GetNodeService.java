package com.lvwyh.aliyun.dataworks.service;

import com.lvwyh.aliyun.dataworks.vo.GetNodeVO;

/**
 * DataWorks 节点详情查询服务
 */
public interface GetNodeService {

    /**
     * 获取节点详情
     *
     * @return 返回节点详情，失败时返回 null
     */
    GetNodeVO getNode(Long nodeId, String projectEnv);
}
