package com.lvwyh.aliyun.maxcompute.service.impl;

import com.aliyun.odps.Column;
import com.aliyun.odps.Odps;
import com.aliyun.odps.Table;
import com.aliyun.odps.TableSchema;
import com.lvwyh.aliyun.api.common.exception.BusinessException;
import com.lvwyh.aliyun.maxcompute.config.MaxComputeProperties;
import com.lvwyh.aliyun.maxcompute.service.MaxComputeMetadataExportService;
import com.lvwyh.aliyun.maxcompute.vo.ExportMaxComputeMetadataVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class MaxComputeMetadataExportServiceImpl implements MaxComputeMetadataExportService {

    private static final Logger log = LogManager.getLogger(MaxComputeMetadataExportServiceImpl.class);

    private static final String CSV_HEADER = "project_name,table_name,table_comment,table_type,is_partition_column,column_name,column_type,column_comment,column_position";

    private final Odps odps;

    private final MaxComputeProperties properties;

    public MaxComputeMetadataExportServiceImpl(Odps maxComputeOdps, MaxComputeProperties properties) {
        this.odps = maxComputeOdps;
        this.properties = properties;
    }

    @Override
    public ExportMaxComputeMetadataVO exportMetadata(String projectName, String fileName) {
        validateConfig();
        validateProjectName(projectName);
        String finalFileName = buildFileName(fileName);
        Path exportPath = buildExportPath(finalFileName);

        int tableCount = 0;
        int columnCount = 0;

        try {
            Files.createDirectories(exportPath.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(exportPath, StandardCharsets.UTF_8)) {
                writer.write(CSV_HEADER);
                writer.newLine();

                Iterator<Table> iterator = odps.tables().iterator(projectName);
                while (iterator.hasNext()) {
                    Table table = iterator.next();
                    tableCount++;
                    TableSchema schema = table.getSchema();
                    columnCount += writeColumns(writer, table, schema.getColumns(), false);
                    columnCount += writeColumns(writer, table, schema.getPartitionColumns(), true);
                }
            }
            return new ExportMaxComputeMetadataVO(
                    projectName,
                    finalFileName,
                    exportPath.toAbsolutePath().toString(),
                    tableCount,
                    columnCount
            );
        } catch (Exception e) {
            log.error("Export MaxCompute metadata failed: projectName={}, filePath={}",
                    projectName, exportPath.toAbsolutePath(), e);
            throw new BusinessException("导出MaxCompute表字段CSV失败", e);
        }
    }

    private int writeColumns(BufferedWriter writer, Table table, List<Column> columns, boolean partitionColumn) throws IOException {
        if (columns == null || columns.isEmpty()) {
            return 0;
        }

        int columnCount = 0;
        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            writer.write(toCsvLine(
                    table.getProject(),
                    table.getName(),
                    table.getComment(),
                    getTableType(table),
                    String.valueOf(partitionColumn),
                    column.getName(),
                    column.getTypeInfo() == null ? "" : column.getTypeInfo().toString(),
                    column.getComment(),
                    String.valueOf(i + 1)
            ));
            writer.newLine();
            columnCount++;
        }
        return columnCount;
    }

    private String getTableType(Table table) {
        if (table.isVirtualView()) {
            return "VIRTUAL_VIEW";
        }
        if (table.isMaterializedView()) {
            return "MATERIALIZED_VIEW";
        }
        if (table.isExternalTable()) {
            return "EXTERNAL_TABLE";
        }
        return "TABLE";
    }

    private String toCsvLine(String... values) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                builder.append(',');
            }
            builder.append(escapeCsv(values[i]));
        }
        return builder.toString();
    }

    private String escapeCsv(String value) {
        if (value == null) {
            return "";
        }
        String escaped = value.replace("\"", "\"\"");
        if (escaped.contains(",") || escaped.contains("\"") || escaped.contains("\n") || escaped.contains("\r")) {
            return "\"" + escaped + "\"";
        }
        return escaped;
    }

    private String buildFileName(String fileName) {
        if (StringUtils.hasText(fileName)) {
            return fileName;
        }
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "maxcompute_metadata_" + timestamp + ".csv";
    }

    private Path buildExportPath(String fileName) {
        return Paths.get(properties.getExportDir()).resolve(fileName).normalize();
    }

    private void validateConfig() {
        if (!StringUtils.hasText(properties.getAccessKeyId())) {
            throw new BusinessException("MaxCompute accessKeyId未配置");
        }
        if (!StringUtils.hasText(properties.getAccessKeySecret())) {
            throw new BusinessException("MaxCompute accessKeySecret未配置");
        }
        if (!StringUtils.hasText(properties.getEndpoint())) {
            throw new BusinessException("MaxCompute endpoint未配置");
        }
        if (!StringUtils.hasText(properties.getExportDir())) {
            throw new BusinessException("MaxCompute exportDir未配置");
        }
    }

    private void validateProjectName(String projectName) {
        if (!StringUtils.hasText(projectName)) {
            throw new BusinessException("MaxCompute projectName不能为空");
        }
    }
}
