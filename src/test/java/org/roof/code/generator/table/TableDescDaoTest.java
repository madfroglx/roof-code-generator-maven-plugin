package org.roof.code.generator.table;

import org.junit.jupiter.api.Test;
import org.roof.code.generator.FieldsCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author liuxin
 * @since 2020/3/17
 */
@SpringBootTest
public class TableDescDaoTest {
    @Autowired
    private TableDescDao tableDescDao;
    @Autowired
    private FieldsCodeGenerator fieldsCodeGenerator;

    @Test
    public void queryTableColumns() {
        List<Column> h_atlas = tableDescDao.queryTableColumns("spiderman", "h_picture");
        System.out.println(fieldsCodeGenerator.generateImports(h_atlas));
        System.out.println(fieldsCodeGenerator.generateFields(h_atlas));
    }
}