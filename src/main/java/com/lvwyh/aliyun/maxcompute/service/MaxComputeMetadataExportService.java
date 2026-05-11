package com.lvwyh.aliyun.maxcompute.service;

import com.lvwyh.aliyun.maxcompute.vo.ExportMaxComputeMetadataVO;

public interface MaxComputeMetadataExportService {

    ExportMaxComputeMetadataVO exportMetadata(String projectName, String fileName);
}
