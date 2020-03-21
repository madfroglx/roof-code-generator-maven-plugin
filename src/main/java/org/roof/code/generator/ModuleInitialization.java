package org.roof.code.generator;

import org.roof.code.generator.table.Column;
import org.roof.code.generator.table.Table;
import org.roof.code.generator.table.TableDescDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuxin
 * @since 2020/3/19
 */
@Component
public class ModuleInitialization {
    @Autowired
    private TableDescDao tableDescDao;

    public void init(Module module) {
        List<Column> columns = tableDescDao.queryTableColumns(module.getDatabase(), module.getTableName());
        Table table = tableDescDao.queryTable(module.getDatabase(), module.getTableName());
        module.setColumns(columns);
        module.setTableName(table.getTableName());
        module.setTable(table);
    }
}
