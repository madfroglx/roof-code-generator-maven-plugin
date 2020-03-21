package org.roof.code.generator.table;

import org.apache.commons.beanutils.PropertyUtils;
import org.roof.code.generator.utils.CamelCaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.lang.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author liuxin
 * @since 2020/3/17
 */
public class CamelRowMapper<T> implements RowMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(CamelRowMapper.class);

    private Class type;

    public CamelRowMapper(T t) {
        this.type = t.getClass();
    }

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        Object r;
        try {
            r = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
        for (int i = 1; i <= columnCount; i++) {
            String column = JdbcUtils.lookupColumnName(rsmd, i);
            String columnKey = getColumnKey(column);
            Object columnValue = getColumnValue(rs, i);
            String name = CamelCaseUtils.toCamelCase(columnKey);
            String value = columnValue == null ? null : columnValue.toString();
            if (value == null) {
                continue;
            }
            try {
                PropertyUtils.setProperty(r, name, value);
            } catch (NoSuchMethodException e) {
                LOGGER.debug(e.getMessage());
            } catch (IllegalAccessException | InvocationTargetException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return (T) r;
    }

    protected String getColumnKey(String columnName) {
        return columnName;
    }

    @Nullable
    protected Object getColumnValue(ResultSet rs, int index) throws SQLException {
        return JdbcUtils.getResultSetValue(rs, index);
    }
}
