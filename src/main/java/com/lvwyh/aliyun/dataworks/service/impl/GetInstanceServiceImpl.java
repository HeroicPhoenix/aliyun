package com.lvwyh.aliyun.dataworks.service.impl;

import com.aliyun.dataworks_public20200518.Client;
import com.aliyun.dataworks_public20200518.models.GetInstanceRequest;
import com.aliyun.dataworks_public20200518.models.GetInstanceResponse;
import com.aliyun.dataworks_public20200518.models.GetInstanceResponseBody;
import com.aliyun.teautil.models.RuntimeOptions;
import com.lvwyh.aliyun.api.common.exception.BusinessException;
import com.lvwyh.aliyun.dataworks.service.GetInstanceService;
import com.lvwyh.aliyun.dataworks.vo.GetInstanceVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DataWorks 实例详情查询服务实现
 */
@Service
public class GetInstanceServiceImpl implements GetInstanceService {

    private static final Logger log = LogManager.getLogger(GetInstanceServiceImpl.class);

    @Autowired
    private Client client;

    /**
     * 获取实例详情（V2 SDK）
     *
     * @return 返回实例详情 VO，失败时返回 null
     */
    @Override
    public GetInstanceVO getInstance(Long instanceId, String projectEnv) {

        GetInstanceRequest request = new GetInstanceRequest()
                .setInstanceId(instanceId)
                .setProjectEnv(projectEnv);

        try {
            GetInstanceResponse response = client.getInstanceWithOptions(
                    request,
                    new RuntimeOptions()
            );

            if (response == null
                    || response.getBody() == null
                    || response.getBody().getData() == null) {
                throw new BusinessException(404, "DataWorks实例不存在");
            }

            GetInstanceResponseBody.GetInstanceResponseBodyData data = response.getBody().getData();

            return new GetInstanceVO(data);

        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            }
            log.error("Get DataWorks instance failed: instanceId={}, projectEnv={}",
                    instanceId, projectEnv, e);
            throw new BusinessException("查询DataWorks实例详情失败", e);
        }
    }
}
