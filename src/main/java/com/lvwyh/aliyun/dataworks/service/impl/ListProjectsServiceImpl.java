package com.lvwyh.aliyun.dataworks.service.impl;

import com.aliyun.dataworks_public20200518.Client;
import com.aliyun.dataworks_public20200518.models.ListProjectsRequest;
import com.aliyun.dataworks_public20200518.models.ListProjectsResponse;
import com.aliyun.dataworks_public20200518.models.ListProjectsResponseBody;
import com.aliyun.teautil.models.RuntimeOptions;
import com.lvwyh.aliyun.api.common.exception.BusinessException;
import com.lvwyh.aliyun.util.PageResult;
import com.lvwyh.aliyun.dataworks.service.ListProjectsService;
import com.lvwyh.aliyun.dataworks.vo.ListProjectsVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * DataWorks 工作空间列表查询服务实现
 */
@Service
public class ListProjectsServiceImpl implements ListProjectsService {

    private static final Logger log = LogManager.getLogger(ListProjectsServiceImpl.class);

    @Autowired
    private Client client;

    /**
     * 查询工作空间列表（V2 SDK）
     *
     * @return 返回分页结果
     */
    @Override
    public PageResult<ListProjectsVO> listProjects(Integer pageNumber, Integer pageSize) {

        ListProjectsRequest request = new ListProjectsRequest()
                .setPageNumber(pageNumber);
        if (pageSize != null) {
            request.setPageSize(pageSize);
        }

        try {
            ListProjectsResponse response = client.listProjectsWithOptions(
                    request,
                    new RuntimeOptions()
            );

            if (response == null
                    || response.getBody() == null
                    || response.getBody().getPageResult() == null) {
                return new PageResult<ListProjectsVO>(
                        0L,
                        pageNumber == null ? 1 : pageNumber,
                        pageSize == null ? 10 : pageSize,
                        new ArrayList<ListProjectsVO>()
                );
            }

            ListProjectsResponseBody.ListProjectsResponseBodyPageResult pageResult =
                    response.getBody().getPageResult();

            List<ListProjectsVO> list = new ArrayList<ListProjectsVO>();

            if (pageResult.getProjectList() != null) {
                for (ListProjectsResponseBody.ListProjectsResponseBodyPageResultProjectList project
                        : pageResult.getProjectList()) {
                    list.add(new ListProjectsVO(project));
                }
            }

            return new PageResult<ListProjectsVO>(
                    pageResult.getTotalCount() == null ? 0L : pageResult.getTotalCount().longValue(),
                    pageResult.getPageNumber(),
                    pageResult.getPageSize(),
                    list
            );

        } catch (Exception e) {
            log.error("List DataWorks projects failed: pageNumber={}, pageSize={}",
                    pageNumber, pageSize, e);
            throw new BusinessException("查询DataWorks工作空间列表失败", e);
        }
    }
}
