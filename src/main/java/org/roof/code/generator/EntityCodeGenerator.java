package org.roof.code.generator;

import org.roof.code.generator.table.Column;
import org.roof.code.generator.table.TableDescDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.lang.model.element.NestingKind;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuxin
 * @since 2020/3/17
 */
@Component
public class EntityCodeGenerator extends AbstractCodeGenerator2 {

    private static final Logger CODE_LOGGER = LoggerFactory.getLogger("codeLogger");

    private static final String TEMPLATE_NAME = "temple/EntityTemplate.ftl";
    @Autowired
    private FieldsCodeGenerator fieldsCodeGenerator;

    public void generate(Module module) {
        String entityPackage = module.getEntityPackage();
        List<Column> columns = module.getColumns();
        String imports = fieldsCodeGenerator.generateImports(columns);
        String fields = fieldsCodeGenerator.generateFields(columns);
        String accessMethods = fieldsCodeGenerator.generateAccessMethod(columns);
        Map<String, String> dataModel = new HashMap<>();
        dataModel.put("entityPackage", entityPackage);
        dataModel.put("imports", imports);
        dataModel.put("fields", fields);
        dataModel.put("accessMethods", accessMethods);
        dataModel.put("entitySimpleName", module.getEntitySimpleName());
        dataModel.put("tableComment", module.getTable().getTableComment());
        writeToFile(dataModel, TEMPLATE_NAME,
                createOutputFile(module.getOutputDir(), entityPackage, module.getEntitySimpleName() + ".java"));
    }
}
