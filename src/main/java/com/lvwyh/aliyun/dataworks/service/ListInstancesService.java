package com.lvwyh.aliyun.dataworks.service;

import com.lvwyh.aliyun.util.PageResult;
import com.lvwyh.aliyun.dataworks.vo.ListInstancesVO;

/**
 * DataWorks 实例列表查询服务
 */
public interface ListInstancesService {

    /**
     * 查询实例列表
     *
     * @return 返回实例分页结果，失败时返回空分页
     */
    PageResult<ListInstancesVO> listInstances(
            Integer pageNumber,
            Integer pageSize,
            Long projectId,
            String projectEnv,
            Long nodeId,
            String status
    );
}
