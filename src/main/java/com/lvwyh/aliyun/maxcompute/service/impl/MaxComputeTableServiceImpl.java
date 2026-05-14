package com.lvwyh.aliyun.maxcompute.service.impl;

import com.aliyun.odps.Column;
import com.aliyun.odps.Instance;
import com.aliyun.odps.Odps;
import com.aliyun.odps.Table;
import com.aliyun.odps.TableSchema;
import com.aliyun.odps.data.Record;
import com.aliyun.odps.data.ResultSet;
import com.aliyun.odps.task.SQLTask;
import com.lvwyh.aliyun.api.common.exception.BusinessException;
import com.lvwyh.aliyun.maxcompute.config.MaxComputeProperties;
import com.lvwyh.aliyun.maxcompute.service.MaxComputeTableService;
import com.lvwyh.aliyun.maxcompute.vo.ExportMaxComputeTableDataVO;
import com.lvwyh.aliyun.maxcompute.vo.MaxComputeColumnVO;
import com.lvwyh.aliyun.maxcompute.vo.MaxComputeTableSchemaVO;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class MaxComputeTableServiceImpl implements MaxComputeTableService {

    private static final Logger log = LogManager.getLogger(MaxComputeTableServiceImpl.class);

    private final Odps odps;

    private final MaxComputeProperties properties;

    public MaxComputeTableServiceImpl(Odps maxComputeOdps, MaxComputeProperties properties) {
        this.odps = maxComputeOdps;
        this.properties = properties;
    }

    @Override
    public MaxComputeTableSchemaVO getTableSchema(String projectName, String tableName) {
        validateConfig();
        try {
            Table table = getTable(projectName, tableName);
            TableSchema schema = table.getSchema();

            List<MaxComputeColumnVO> columns = convertColumns(schema.getColumns(), false);
            List<MaxComputeColumnVO> partitionColumns = convertColumns(schema.getPartitionColumns(), true);

            MaxComputeTableSchemaVO vo = new MaxComputeTableSchemaVO();
            vo.setProjectName(projectName);
            vo.setTableName(tableName);
            vo.setTableComment(table.getComment());
            vo.setTableType(getTableType(table));
            vo.setOwner(table.getOwner());
            vo.setCreatedTime(table.getCreatedTime());
            vo.setRecordNum(table.getRecordNum());
            vo.setSize(table.getSize());
            vo.setColumnCount(columns.size());
            vo.setPartitionColumnCount(partitionColumns.size());
            vo.setColumns(columns);
            vo.setPartitionColumns(partitionColumns);
            return vo;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Get MaxCompute table schema failed: projectName={}, tableName={}",
                    projectName, tableName, e);
            throw new BusinessException("查询MaxCompute表结构失败", e);
        }
    }

    @Override
    public ExportMaxComputeTableDataVO exportTableData(String projectName, String tableName, Integer pageNum,
                                                       Integer pageSize, String fileName) {
        validateConfig();
        int finalPageNum = pageNum == null ? 1 : pageNum;
        int finalPageSize = pageSize == null ? 100 : pageSize;
        String finalFileName = buildTableDataFileName(projectName, tableName, finalPageNum, finalPageSize, fileName);
        Path exportPath = buildExportPath(finalFileName);
        try {
            Table table = getTable(projectName, tableName);
            List<String> columnNames = getDataColumnNames(table.getSchema());
            String sql = buildPagedSelectSql(projectName, tableName, columnNames, finalPageNum, finalPageSize);
            Files.createDirectories(exportPath.getParent());

            long rowCount = 0L;
            try (BufferedWriter writer = Files.newBufferedWriter(exportPath, StandardCharsets.UTF_8)) {
                String headerLine = toCsvLine(columnNames);
                writer.write(headerLine);
                writer.newLine();
                log.info("MaxCompute query result header: {}", headerLine);

                Instance instance = SQLTask.run(odps, projectName, sql,
                        "query_" + tableName, Collections.<String, String>emptyMap(), Collections.<String, String>emptyMap());
                instance.waitForSuccess();
                ResultSet resultSet = SQLTask.getResultSet(instance);
                while (resultSet.hasNext()) {
                    String rowLine = toCsvLine(convertRecordValues(resultSet.next()));
                    writer.write(rowLine);
                    writer.newLine();
                    rowCount++;
                    log.info("MaxCompute query result row: {}", rowLine);
                }
            }

            return new ExportMaxComputeTableDataVO(
                    projectName,
                    tableName,
                    finalFileName,
                    exportPath.toAbsolutePath().toString(),
                    finalPageNum,
                    finalPageSize,
                    columnNames.size(),
                    rowCount
            );
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Export MaxCompute table data failed: projectName={}, tableName={}, filePath={}",
                    projectName, tableName, exportPath.toAbsolutePath(), e);
            throw new BusinessException("导出MaxCompute表数据失败", e);
        }
    }

    private Table getTable(String projectName, String tableName) throws Exception {
        if (!odps.tables().exists(projectName, tableName)) {
            throw new BusinessException("MaxCompute表不存在");
        }
        return odps.tables().get(projectName, tableName);
    }

    private List<MaxComputeColumnVO> convertColumns(List<Column> columns, boolean partitionColumn) {
        List<MaxComputeColumnVO> result = new ArrayList<MaxComputeColumnVO>();
        if (columns == null) {
            return result;
        }
        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            result.add(new MaxComputeColumnVO(
                    column.getName(),
                    column.getTypeInfo() == null ? "" : column.getTypeInfo().toString(),
                    column.getComment(),
                    column.isNullable(),
                    partitionColumn,
                    i + 1
            ));
        }
        return result;
    }

    private List<String> getDataColumnNames(TableSchema schema) {
        List<String> columnNames = new ArrayList<String>();
        appendColumnNames(columnNames, schema.getColumns());
        appendColumnNames(columnNames, schema.getPartitionColumns());
        return columnNames;
    }

    private void appendColumnNames(List<String> columnNames, List<Column> columns) {
        if (columns == null) {
            return;
        }
        for (Column column : columns) {
            columnNames.add(column.getName());
        }
    }

    private List<String> convertRecordValues(Record record) {
        List<String> values = new ArrayList<String>();
        Object[] array = record.toArray();
        if (array == null) {
            return values;
        }
        for (Object value : array) {
            values.add(value == null ? "" : String.valueOf(value));
        }
        return values;
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

    private String buildPagedSelectSql(String projectName, String tableName, List<String> columnNames,
                                       int pageNum, int pageSize) {
        if (columnNames.isEmpty()) {
            throw new BusinessException("MaxCompute表没有可查询字段");
        }
        long offset = (long) (pageNum - 1) * pageSize;
        return "SELECT * FROM " + projectName + "." + tableName
                + " ORDER BY " + columnNames.get(0)
                + " LIMIT " + pageSize
                + " OFFSET " + offset;
    }

    private String buildTableDataFileName(String projectName, String tableName, int pageNum, int pageSize, String fileName) {
        if (StringUtils.hasText(fileName)) {
            return fileName;
        }
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "maxcompute_table_data_" + projectName + "_" + tableName
                + "_p" + pageNum + "_s" + pageSize + "_" + timestamp + ".csv";
    }

    private Path buildExportPath(String fileName) {
        return Paths.get(properties.getExportDir()).resolve(fileName).normalize();
    }

    private String toCsvLine(List<String> values) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) {
                builder.append(',');
            }
            builder.append(escapeCsv(values.get(i)));
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
}
