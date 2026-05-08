package com.lvwyh.aliyun.dataworks.service;

import com.lvwyh.aliyun.util.PageResult;
import com.lvwyh.aliyun.dataworks.vo.ListProjectsVO;

/**
 * DataWorks 工作空间列表查询服务
 */
public interface ListProjectsService {

    /**
     * 查询工作空间列表
     *
     * @return 返回工作空间列表，失败时返回空列表
     */
    PageResult<ListProjectsVO> listProjects(Integer pageNumber, Integer pageSize);
}
