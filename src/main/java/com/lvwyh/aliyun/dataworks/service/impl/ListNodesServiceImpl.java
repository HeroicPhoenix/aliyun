package com.lvwyh.aliyun.dataworks.service.impl;

import com.aliyun.dataworks_public20200518.Client;
import com.aliyun.dataworks_public20200518.models.ListNodesRequest;
import com.aliyun.dataworks_public20200518.models.ListNodesResponse;
import com.aliyun.dataworks_public20200518.models.ListNodesResponseBody;
import com.aliyun.teautil.models.RuntimeOptions;
import com.lvwyh.aliyun.api.common.exception.BusinessException;
import com.lvwyh.aliyun.util.PageResult;
import com.lvwyh.aliyun.dataworks.service.ListNodesService;
import com.lvwyh.aliyun.dataworks.vo.ListNodesVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * DataWorks 节点列表查询服务实现
 */
@Service
public class ListNodesServiceImpl implements ListNodesService {

    private static final Logger log = LogManager.getLogger(ListNodesServiceImpl.class);

    @Autowired
    private Client client;

    /**
     * 查询节点列表（仅前4个必选参数）
     *
     * @param projectId   工作空间 ID
     * @param projectEnv  环境：DEV / PROD
     * @param pageNumber  页码
     * @param pageSize    每页条数
     * @return 返回节点分页结果，失败时返回空分页
     */
    public PageResult<ListNodesVO> listNodes(Integer pageNumber, Integer pageSize, Long projectId, String projectEnv) {
        return listNodes(pageNumber, pageSize, projectId, projectEnv, null, null, null, null, null);
    }

    /**
     * 查询节点列表（完整参数）
     *
     * @param projectId     工作空间 ID
     * @param projectEnv    环境：DEV / PROD
     * @param pageNumber    页码
     * @param pageSize      每页条数
     * @param owner         负责人（可选）
     * @param bizName       业务流程名称（可选）
     * @param programType   节点类型（可选）
     * @param nodeName      节点名称（可选）
     * @param schedulerType 调度类型（可选）
     * @return 返回节点分页结果，失败时返回空分页
     */
    @Override
    public PageResult<ListNodesVO> listNodes(
            Integer pageNumber,
            Integer pageSize,
            Long projectId,
            String projectEnv,
            String owner,
            String bizName,
            String programType,
            String nodeName,
            String schedulerType) {

        ListNodesRequest request = new ListNodesRequest()
                .setProjectId(projectId)
                .setProjectEnv(projectEnv)
                .setPageNumber(pageNumber)
                .setPageSize(pageSize);
        if (owner != null && !owner.trim().isEmpty()) {
            request.setOwner(owner);
        }
        if (bizName != null && !bizName.trim().isEmpty()) {
            request.setBizName(bizName);
        }
        if (programType != null && !programType.trim().isEmpty()) {
            request.setProgramType(programType);
        }
        if (nodeName != null && !nodeName.trim().isEmpty()) {
            request.setNodeName(nodeName);
        }
        if (schedulerType != null && !schedulerType.trim().isEmpty()) {
            request.setSchedulerType(schedulerType);
        }

        try {
            ListNodesResponse response = client.listNodesWithOptions(
                    request,
                    new RuntimeOptions()
            );

            if (response == null
                    || response.getBody() == null
                    || response.getBody().getData() == null) {
                return new PageResult<ListNodesVO>(
                        0L,
                        pageNumber == null ? 1 : pageNumber,
                        pageSize == null ? 10 : pageSize,
                        new ArrayList<ListNodesVO>()
                );
            }

            ListNodesResponseBody.ListNodesResponseBodyData data = response.getBody().getData();

            List<ListNodesVO> list = new ArrayList<ListNodesVO>();

            if (data.getNodes() != null) {
                for (ListNodesResponseBody.ListNodesResponseBodyDataNodes item : data.getNodes()) {
                    list.add(new ListNodesVO(item));
                }
            }

            return new PageResult<ListNodesVO>(
                    data.getTotalCount() == null ? 0L : data.getTotalCount().longValue(),
                    data.getPageNumber() == null ? (pageNumber == null ? 1 : pageNumber) : data.getPageNumber(),
                    data.getPageSize() == null ? (pageSize == null ? 10 : pageSize) : data.getPageSize(),
                    list
            );

        } catch (Exception e) {
            log.error("List DataWorks nodes failed: pageNumber={}, pageSize={}, projectId={}, projectEnv={}",
                    pageNumber, pageSize, projectId, projectEnv, e);
            throw new BusinessException("查询DataWorks节点列表失败", e);
        }
    }
}
