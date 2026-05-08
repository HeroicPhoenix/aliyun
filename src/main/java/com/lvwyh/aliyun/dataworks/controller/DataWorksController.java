package com.lvwyh.aliyun.dataworks.controller;

import com.lvwyh.aliyun.util.PageResult;
import com.lvwyh.aliyun.dataworks.ao.*;
import com.lvwyh.aliyun.api.common.response.ApiResponse;
import com.lvwyh.aliyun.dataworks.service.*;
import com.lvwyh.aliyun.dataworks.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "DataWorks管理")
@Validated
@RestController
@RequestMapping("/api/dataworks")
public class DataWorksController {

    private static final Logger log = LogManager.getLogger(DataWorksController.class);

    @Autowired
    private ListProjectsService listProjectsService;

    @Autowired
    private ListNodesService listNodesService;

    @Autowired
    private GetNodeService getNodeService;

    @Autowired
    private RunTriggerNodeService runTriggerNodeService;

    @Autowired
    private GetInstanceService getInstanceService;

    @Autowired
    private ListInstancesService listInstancesService;

    /**
     * 查询工作空间列表（分页）
     */
    @Operation(summary = "查询工作空间列表（分页）", description = "分页查询 DataWorks 工作空间列表")
    @PostMapping("/projects/page")
    public ApiResponse<PageResult<ListProjectsVO>> listProjects(@RequestBody @Valid ListProjectsAO ao) {

        log.info("List DataWorks projects request: pageNumber={}, pageSize={}",
                ao.getPageNumber(), ao.getPageSize());

        PageResult<ListProjectsVO> page =
                listProjectsService.listProjects(ao.getPageNumber(), ao.getPageSize());

        log.info("List DataWorks projects success: total={}, pageNumber={}, pageSize={}",
                page.getTotal(), page.getPageNum(), page.getPageSize());

        return ApiResponse.success("查询成功", page);
    }

    /**
     * 查询节点列表（分页）
     */
    @Operation(summary = "查询节点列表（分页）", description = "分页查询 DataWorks 节点列表")
    @PostMapping("/nodes/page")
    public ApiResponse<PageResult<ListNodesVO>> listNodes(@RequestBody @Valid ListNodesAO ao) {

        log.info("List DataWorks nodes request: pageNumber={}, pageSize={}, projectId={}, projectEnv={}, owner={}, bizName={}, programType={}, nodeName={}, schedulerType={}",
                ao.getPageNumber(),
                ao.getPageSize(),
                ao.getProjectId(),
                ao.getProjectEnv(),
                ao.getOwner(),
                ao.getBizName(),
                ao.getProgramType(),
                ao.getNodeName(),
                ao.getSchedulerType());

        PageResult<ListNodesVO> page =
                listNodesService.listNodes(
                        ao.getPageNumber(),
                        ao.getPageSize(),
                        ao.getProjectId(),
                        ao.getProjectEnv(),
                        ao.getOwner(),
                        ao.getBizName(),
                        ao.getProgramType(),
                        ao.getNodeName(),
                        ao.getSchedulerType()
                );

        log.info("List DataWorks nodes success: pageNumber={}, pageSize={}, projectId={}, total={}",
                page.getPageNum(), page.getPageSize(), ao.getProjectId(), page.getTotal());

        return ApiResponse.success("查询成功", page);
    }

    /**
     * 查询节点详情
     */
    @Operation(summary = "查询节点详情", description = "根据节点ID和环境查询节点详情")
    @PostMapping("/node/get")
    public ApiResponse<GetNodeVO> getNode(@RequestBody @Valid GetNodeAO ao) {

        log.info("Get DataWorks node request: nodeId={}, projectEnv={}", ao.getNodeId(), ao.getProjectEnv());

        GetNodeVO vo = getNodeService.getNode(ao.getNodeId(), ao.getProjectEnv());

        log.info("Get DataWorks node success: nodeId={}, nodeName={}, projectId={}",
                vo.getNodeId(), vo.getNodeName(), vo.getProjectId());

        return ApiResponse.success("查询成功", vo);
    }

    /**
     * 运行触发式节点
     */
    @Operation(summary = "运行触发式节点", description = "根据节点ID、工作空间ID、cycleTime、bizDate 运行触发式节点")
    @PostMapping("/node/runTrigger")
    public ApiResponse<Boolean> runTriggerNode(@RequestBody @Valid RunTriggerNodeAO ao) {

        log.info("Run DataWorks trigger node request: projectId={}, nodeId={}, cycleTime={}, bizDate={}",
                ao.getProjectId(), ao.getNodeId(), ao.getCycleTime(), ao.getBizDate());

        boolean success = runTriggerNodeService.runTriggerNode(
                ao.getNodeId(),
                ao.getProjectId(),
                ao.getCycleTime(),
                ao.getBizDate()
        );

        log.info("Run DataWorks trigger node success: projectId={}, nodeId={}, success={}",
                ao.getProjectId(), ao.getNodeId(), success);

        return ApiResponse.success("执行成功", success);
    }

    /**
     * 查询实例详情
     */
    @Operation(summary = "查询实例详情", description = "根据实例ID和环境查询实例详情")
    @PostMapping("/instance/get")
    public ApiResponse<GetInstanceVO> getInstance(@RequestBody @Valid GetInstanceAO ao) {

        log.info("Get DataWorks instance request: instanceId={}, projectEnv={}",
                ao.getInstanceId(), ao.getProjectEnv());

        GetInstanceVO vo = getInstanceService.getInstance(ao.getInstanceId(), ao.getProjectEnv());

        log.info("Get DataWorks instance success: instanceId={}, status={}, nodeId={}, nodeName={}",
                vo.getInstanceId(), vo.getStatus(), vo.getNodeId(), vo.getNodeName());

        return ApiResponse.success("查询成功", vo);
    }

    /**
     * 查询实例列表（分页）
     */
    @Operation(summary = "查询实例列表（分页）", description = "分页查询 DataWorks 实例列表")
    @PostMapping("/instances/page")
    public ApiResponse<PageResult<ListInstancesVO>> listInstances(@RequestBody @Valid ListInstancesAO ao) {

        log.info("List DataWorks instances request: pageNumber={}, pageSize={}, projectId={}, projectEnv={}, nodeId={}, status={}",
                ao.getPageNumber(),
                ao.getPageSize(),
                ao.getProjectId(),
                ao.getProjectEnv(),
                ao.getNodeId(),
                ao.getStatus());

        PageResult<ListInstancesVO> page =
                listInstancesService.listInstances(
                        ao.getPageNumber(),
                        ao.getPageSize(),
                        ao.getProjectId(),
                        ao.getProjectEnv(),
                        ao.getNodeId(),
                        ao.getStatus()
                );

        log.info("List DataWorks instances success: pageNumber={}, pageSize={}, projectId={}, total={}",
                page.getPageNum(), page.getPageSize(), ao.getProjectId(), page.getTotal());

        return ApiResponse.success("查询成功", page);
    }
}
