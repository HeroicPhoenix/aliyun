package com.lvwyh.aliyun.maxcompute.controller;

import com.lvwyh.aliyun.api.common.response.ApiResponse;
import com.lvwyh.aliyun.maxcompute.ao.ExportMaxComputeMetadataAO;
import com.lvwyh.aliyun.maxcompute.service.MaxComputeMetadataExportService;
import com.lvwyh.aliyun.maxcompute.vo.ExportMaxComputeMetadataVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "MaxCompute元数据")
@Validated
@RestController
@RequestMapping("/api/maxcompute")
public class MaxComputeController {

    private static final Logger log = LogManager.getLogger(MaxComputeController.class);

    @Autowired
    private MaxComputeMetadataExportService maxComputeMetadataExportService;

    @Operation(summary = "导出表字段CSV", description = "读取MaxCompute项目下全部表和字段，并导出为CSV文件")
    @PostMapping("/metadata/export")
    public ApiResponse<ExportMaxComputeMetadataVO> exportMetadata(@RequestBody @Valid ExportMaxComputeMetadataAO ao) {

        log.info("Export MaxCompute metadata request: projectName={}, fileName={}",
                ao.getProjectName(), ao.getFileName());

        ExportMaxComputeMetadataVO vo = maxComputeMetadataExportService.exportMetadata(
                ao.getProjectName(),
                ao.getFileName()
        );

        log.info("Export MaxCompute metadata success: projectName={}, fileName={}, tableCount={}, columnCount={}",
                vo.getProjectName(), vo.getFileName(), vo.getTableCount(), vo.getColumnCount());

        return ApiResponse.success("导出成功", vo);
    }
}
