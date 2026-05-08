package com.lvwyh.aliyun.dataworks.service.impl;

import com.aliyun.dataworks_public20200518.Client;
import com.aliyun.dataworks_public20200518.models.GetNodeRequest;
import com.aliyun.dataworks_public20200518.models.GetNodeResponse;
import com.aliyun.dataworks_public20200518.models.GetNodeResponseBody;
import com.aliyun.teautil.models.RuntimeOptions;
import com.lvwyh.aliyun.api.common.exception.BusinessException;
import com.lvwyh.aliyun.dataworks.service.GetNodeService;
import com.lvwyh.aliyun.dataworks.vo.GetNodeVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DataWorks 节点详情查询服务实现
 */
@Service
public class GetNodeServiceImpl implements GetNodeService {

    private static final Logger log = LogManager.getLogger(GetNodeServiceImpl.class);

    @Autowired
    private Client client;

    /**
     * 获取节点详情（V2 SDK）
     *
     * @return 返回节点详情 VO，失败时返回 null
     */
    @Override
    public GetNodeVO getNode(Long nodeId, String projectEnv) {

        GetNodeRequest request = new GetNodeRequest()
                .setNodeId(nodeId)
                .setProjectEnv(projectEnv);

        try {
            GetNodeResponse response = client.getNodeWithOptions(
                    request,
                    new RuntimeOptions()
            );

            if (response == null
                    || response.getBody() == null
                    || response.getBody().getData() == null) {
                throw new BusinessException(404, "DataWorks节点不存在");
            }

            GetNodeResponseBody.GetNodeResponseBodyData data = response.getBody().getData();

            return new GetNodeVO(data);

        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            }
            log.error("Get DataWorks node failed: nodeId={}, projectEnv={}",
                    nodeId, projectEnv, e);
            throw new BusinessException("查询DataWorks节点详情失败", e);
        }
    }
}
