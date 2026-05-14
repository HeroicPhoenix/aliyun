package com.lvwyh.aliyun.maxcompute.controller;

import com.lvwyh.aliyun.api.common.response.ApiResponse;
import com.lvwyh.aliyun.maxcompute.ao.ExportMaxComputeMetadataAO;
import com.lvwyh.aliyun.maxcompute.ao.ExportMaxComputeTableDataAO;
import com.lvwyh.aliyun.maxcompute.ao.GetMaxComputeTableSchemaAO;
import com.lvwyh.aliyun.maxcompute.service.MaxComputeMetadataExportService;
import com.lvwyh.aliyun.maxcompute.service.MaxComputeTableService;
import com.lvwyh.aliyun.maxcompute.vo.ExportMaxComputeMetadataVO;
import com.lvwyh.aliyun.maxcompute.vo.ExportMaxComputeTableDataVO;
import com.lvwyh.aliyun.maxcompute.vo.MaxComputeTableSchemaVO;
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

    @Autowired
    private MaxComputeTableService maxComputeTableService;

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

    @Operation(summary = "查询表结构", description = "查询MaxCompute指定项目空间下指定表的结构")
    @PostMapping("/table/schema")
    public ApiResponse<MaxComputeTableSchemaVO> getTableSchema(@RequestBody @Valid GetMaxComputeTableSchemaAO ao) {

        log.info("Get MaxCompute table schema request: projectName={}, tableName={}",
                ao.getProjectName(), ao.getTableName());

        MaxComputeTableSchemaVO vo = maxComputeTableService.getTableSchema(
                ao.getProjectName(),
                ao.getTableName()
        );

        log.info("Get MaxCompute table schema success: projectName={}, tableName={}, columnCount={}, partitionColumnCount={}",
                vo.getProjectName(), vo.getTableName(), vo.getColumnCount(), vo.getPartitionColumnCount());

        return ApiResponse.success("查询成功", vo);
    }

    @Operation(summary = "分页查询并导出表数据", description = "分页查询MaxCompute指定表数据，结果写入CSV文件并打印到控制台")
    @PostMapping("/table/data/export")
    public ApiResponse<ExportMaxComputeTableDataVO> exportTableData(@RequestBody @Valid ExportMaxComputeTableDataAO ao) {

        log.info("Export MaxCompute table data request: projectName={}, tableName={}, pageNum={}, pageSize={}, fileName={}",
                ao.getProjectName(), ao.getTableName(), ao.getPageNum(), ao.getPageSize(), ao.getFileName());

        ExportMaxComputeTableDataVO vo = maxComputeTableService.exportTableData(
                ao.getProjectName(),
                ao.getTableName(),
                ao.getPageNum(),
                ao.getPageSize(),
                ao.getFileName()
        );

        log.info("Export MaxCompute table data success: projectName={}, tableName={}, pageNum={}, pageSize={}, rowCount={}, fileName={}",
                vo.getProjectName(), vo.getTableName(), vo.getPageNum(), vo.getPageSize(), vo.getRowCount(), vo.getFileName());

        return ApiResponse.success("查询导出成功", vo);
    }
}
