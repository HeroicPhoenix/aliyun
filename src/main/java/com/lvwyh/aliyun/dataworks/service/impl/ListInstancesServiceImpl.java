package com.lvwyh.aliyun.dataworks.service.impl;

import com.aliyun.dataworks_public20200518.Client;
import com.aliyun.dataworks_public20200518.models.ListInstancesRequest;
import com.aliyun.dataworks_public20200518.models.ListInstancesResponse;
import com.aliyun.dataworks_public20200518.models.ListInstancesResponseBody;
import com.aliyun.teautil.models.RuntimeOptions;
import com.lvwyh.aliyun.api.common.exception.BusinessException;
import com.lvwyh.aliyun.util.PageResult;
import com.lvwyh.aliyun.dataworks.service.ListInstancesService;
import com.lvwyh.aliyun.dataworks.vo.ListInstancesVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * DataWorks 实例列表查询服务实现
 */
@Service
public class ListInstancesServiceImpl implements ListInstancesService {

    private static final Logger log = LogManager.getLogger(ListInstancesServiceImpl.class);

    @Autowired
    private Client client;

    @Override
    public PageResult<ListInstancesVO> listInstances(
            Integer pageNumber,
            Integer pageSize,
            Long projectId,
            String projectEnv,
            Long nodeId,
            String status
    ) {

        ListInstancesRequest request = new ListInstancesRequest()
                .setProjectId(projectId)
                .setProjectEnv(projectEnv)
                .setPageNumber(pageNumber)
                .setPageSize(pageSize);
        if (nodeId != null) {
            request.setNodeId(nodeId);
        }
        if (status != null && !status.trim().isEmpty()) {
            request.setStatus(status);
        }

        try {
            ListInstancesResponse response = client.listInstancesWithOptions(
                    request,
                    new RuntimeOptions()
            );

            if (response == null
                    || response.getBody() == null
                    || response.getBody().getData() == null) {
                return new PageResult<ListInstancesVO>(
                        0L,
                        pageNumber == null ? 1 : pageNumber,
                        pageSize == null ? 10 : pageSize,
                        new ArrayList<ListInstancesVO>()
                );
            }

            ListInstancesResponseBody.ListInstancesResponseBodyData data = response.getBody().getData();

            List<ListInstancesVO> list = new ArrayList<ListInstancesVO>();

            if (data.getInstances() != null) {
                for (ListInstancesResponseBody.ListInstancesResponseBodyDataInstances item : data.getInstances()) {
                    list.add(new ListInstancesVO(item));
                }
            }

            return new PageResult<ListInstancesVO>(
                    data.getTotalCount() == null ? 0L : data.getTotalCount().longValue(),
                    data.getPageNumber() == null ? (pageNumber == null ? 1 : pageNumber) : data.getPageNumber(),
                    data.getPageSize() == null ? (pageSize == null ? 10 : pageSize) : data.getPageSize(),
                    list
            );

        } catch (Exception e) {
            log.error("List DataWorks instances failed: pageNumber={}, pageSize={}, projectId={}, projectEnv={}, nodeId={}, status={}",
                    pageNumber, pageSize, projectId, projectEnv, nodeId, status, e);
            throw new BusinessException("查询DataWorks实例列表失败", e);
        }
    }
}
