package com.lvwyh.aliyun.maxcompute.service;

import com.lvwyh.aliyun.maxcompute.vo.ExportMaxComputeTableDataVO;
import com.lvwyh.aliyun.maxcompute.vo.MaxComputeTableSchemaVO;

public interface MaxComputeTableService {

    MaxComputeTableSchemaVO getTableSchema(String projectName, String tableName);

    ExportMaxComputeTableDataVO exportTableData(String projectName, String tableName, Integer pageNum,
                                                Integer pageSize, String fileName);
}
