package com.lvwyh.aliyun.dataworks.service.impl;

import com.aliyun.dataworks_public20200518.Client;
import com.aliyun.dataworks_public20200518.models.RunTriggerNodeRequest;
import com.aliyun.dataworks_public20200518.models.RunTriggerNodeResponse;
import com.aliyun.teautil.models.RuntimeOptions;
import com.lvwyh.aliyun.api.common.exception.BusinessException;
import com.lvwyh.aliyun.dataworks.service.RunTriggerNodeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DataWorks 触发式节点运行服务实现
 */
@Service
public class RunTriggerNodeServiceImpl implements RunTriggerNodeService {

    private static final Logger log = LogManager.getLogger(RunTriggerNodeServiceImpl.class);

    @Autowired
    private Client client;

    /**
     * 运行触发式节点（V2 SDK）
     *
     * @return 成功返回 true，否则返回 false
     */
    @Override
    public boolean runTriggerNode(Long nodeId, Long projectId, Long cycleTime, Long bizDate) {
        RunTriggerNodeRequest request = new RunTriggerNodeRequest()
                .setNodeId(nodeId)
                .setCycleTime(cycleTime)
                .setBizDate(bizDate)
                .setAppId(projectId);

        try {
            RunTriggerNodeResponse response = client.runTriggerNodeWithOptions(
                    request,
                    new RuntimeOptions()
            );

            boolean success = response != null
                    && response.getBody() != null
                    && Boolean.TRUE.equals(response.getBody().getSuccess());
            if (!success) {
                throw new BusinessException("DataWorks触发式节点运行失败");
            }
            return true;

        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            }
            log.error("Run DataWorks trigger node failed: nodeId={}, projectId={}, cycleTime={}, bizDate={}",
                    nodeId, projectId, cycleTime, bizDate, e);
            throw new BusinessException("运行DataWorks触发式节点失败", e);
        }
    }
}
