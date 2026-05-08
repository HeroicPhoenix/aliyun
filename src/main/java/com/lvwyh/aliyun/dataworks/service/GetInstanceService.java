package com.lvwyh.aliyun.dataworks.service;

import com.lvwyh.aliyun.dataworks.vo.GetInstanceVO;

/**
 * DataWorks 实例详情查询服务
 */
public interface GetInstanceService {

    /**
     * 获取实例详情
     *
     * @return 返回实例详情，失败时返回 null
     */
    GetInstanceVO getInstance(Long instanceId, String projectEnv);
}
