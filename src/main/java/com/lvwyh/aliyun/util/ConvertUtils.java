package com.lvwyh.aliyun.util;

import com.lvwyh.aliyun.util.PageResult;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 通用对象转换工具类
 *
 * 适用于：
 * 1. Entity -> AO
 * 2. Entity -> VO
 * 3. 任意 List<T> -> List<R>
 */
public final class ConvertUtils {

    private ConvertUtils() {
        // 禁止实例化
    }

    /**
     * 通用 List 转换方法
     *
     * @param list   原始列表
     * @param mapper 转换函数
     * @param <T>    原类型
     * @param <R>    目标类型
     * @return 转换后的列表
     */
    public static <T, R> List<R> convertList(
            List<T> list,
            Function<T, R> mapper) {

        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }

        return list.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public static <T, R> PageResult<R> convertPage(
            PageResult<T> page,
            Function<T, R> mapper) {

        if (page == null) {
            return null;
        }

        List<R> newList = convertList(page.getList(), mapper);

        return new PageResult<>(
                page.getTotal(),
                page.getPageNum(),
                page.getPageSize(),
                newList
        );
    }

    /**
     * 下划线转驼峰
     * 例如：access_id → accessId
     */
    public static String snakeToCamel(String key) {
        if (!StringUtils.hasText(key) || !key.contains("_")) {
            return key;
        }

        String[] parts = key.split("_");
        StringBuilder sb = new StringBuilder(parts[0]);

        for (int i = 1; i < parts.length; i++) {
            if (!StringUtils.hasText(parts[i])) {
                continue;
            }
            sb.append(parts[i].substring(0, 1).toUpperCase())
                    .append(parts[i].substring(1));
        }

        return sb.toString();
    }
}
