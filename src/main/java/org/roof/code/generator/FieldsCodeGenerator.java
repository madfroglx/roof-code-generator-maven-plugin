package org.roof.code.generator;

import javafx.scene.control.TextFormatter;
import org.apache.commons.lang3.StringUtils;
import org.roof.code.generator.AbstractCodeGenerator;
import org.roof.code.generator.Module;
import org.roof.code.generator.table.Column;
import org.roof.code.generator.utils.CamelCaseUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;

/**
 * @author liuxin
 * @since 2020/3/17
 */
@Component
@ConfigurationProperties(prefix = "data")
@PropertySource("classpath:data_type_mapping.properties")
public class FieldsCodeGenerator extends AbstractCodeGenerator2 {
    private static final String FIELD_TEMPLATE = "/**\n *{2}\n**/\nprivate {0} {1};\n";
    private static final String IMPORT_TEMPLATE = "import {0};\n";
    private Map<String, String> typeMapping = new HashMap<>();
    private static final String TEMPLATE_NAME = "temple/AccessMethodsTemplate.ftl";

    public String generateFields(List<Column> columns) {
        StringBuffer result = new StringBuffer();
        for (Column column : columns) {
            result.append(MessageFormat.format(FIELD_TEMPLATE, exchangeSimpleDateType(column.getDataType()),
                    CamelCaseUtils.toCamelCase(column.getColumnName()), column.getColumnComment()));
        }
        return result.toString();
    }

    public String generateImports(List<Column> columns) {
        Set<String> set = new HashSet();
        for (Column column : columns) {
            set.add(MessageFormat.format(IMPORT_TEMPLATE, exchangeDataType(column.getDataType())));
        }
        return StringUtils.join(set.toArray());
    }

    public String generateAccessMethod(List<Column> columns) {
        StringBuffer result = new StringBuffer();
        for (Column column : columns) {
            Map<String, String> dm = new HashMap<>();
            dm.put("fieldName", CamelCaseUtils.toCamelCase(column.getColumnName()));
            dm.put("fieldNameCapital", CamelCaseUtils.toCapitalizeCamelCase(column.getColumnName()));
            dm.put("dataType", exchangeSimpleDateType(column.getDataType()));
            result.append(fillTemplate(dm, TEMPLATE_NAME));
        }
        return result.toString();
    }

    private String exchangeDataType(String dateType) {
        return typeMapping.get(dateType);
    }

    private String exchangeSimpleDateType(String dateType) {
        String fullDataType = exchangeDataType(dateType);
        int index = StringUtils.lastIndexOf(fullDataType, ".");
        return StringUtils.substring(fullDataType, index + 1, StringUtils.length(fullDataType));
    }

    public Map<String, String> getTypeMapping() {
        return typeMapping;
    }

    public void setTypeMapping(Map<String, String> typeMapping) {
        this.typeMapping = typeMapping;
    }
}
