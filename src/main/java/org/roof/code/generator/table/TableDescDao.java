package org.roof.code.generator.table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuxin
 * @since 2020/3/16
 */
@Component
public class TableDescDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(TableDescDao.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public static final String QUERY_COLUMNS_SQL = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = :tableName AND TABLE_SCHEMA = :tableSchema";
    public static final String QUERY_TABLE_SQL = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = :tableName AND TABLE_SCHEMA = :tableSchema";


    public List<Column> queryTableColumns(String tableSchema, String tableName) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("tableName", tableName);
        params.put("tableSchema", tableSchema);
        return namedParameterJdbcTemplate.query(QUERY_COLUMNS_SQL, params, new CamelRowMapper<Column>(new Column()));
    }

    public Table queryTable(String tableSchema, String tableName) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("tableName", tableName);
        params.put("tableSchema", tableSchema);
        namedParameterJdbcTemplate.queryForList(QUERY_TABLE_SQL, params);
        return (Table) namedParameterJdbcTemplate.query(QUERY_TABLE_SQL, params, new CamelRowMapper<Table>(new Table())).get(0);
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
