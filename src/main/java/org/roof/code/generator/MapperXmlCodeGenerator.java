package org.roof.code.generator;

import org.apache.commons.lang3.StringUtils;
import org.roof.code.generator.utils.CamelCaseUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuxin
 * @since 2020/3/19
 */
@Component
public class MapperXmlCodeGenerator extends AbstractCodeGenerator2 {
    private static final String TEMPLATE_NAME = "temple/MapperXmlTemplate.ftl";
    private static final String CONDITION_TEMPLATE = "\t\t<if test=\"%s != null\">\n\t\t\tand %s = #{%s}\n\t\t</if>";
    private static final String STRING_CONDITION_TEMPLATE = "\t\t<if test=\"%s != null and %s != ''\">\n\t\t\tand %s = #{%s}\n\t\t</if>";
    public static final String STRING_UPDATE_IGNORE_NULL_SET_TEMPLATE = "\t\t<if test=\"%s != null and %s != ''\">\n\t\t\t%s=#{%s}, \n\t\t</if>";
    public static final String UPDATE_IGNORE_NULL_SET_TEMPLATE = "\t\t<if test=\"%s != null\">\n\t\t\t%s=#{%s}, \n\t\t</if>";

    public void generate(Module module) {
        String mapperFullName = module.getMapperFullName();
        Map<String, String> dataModel = new HashMap<>();
        dataModel.put("mapperFullName", mapperFullName);
        List<String> columnNames = new ArrayList<>(), columnNamesAs = new ArrayList<>(),
                vals = new ArrayList<>(), conditions = new ArrayList<>(), updateSet = new ArrayList<>(),
                updateIgnoreNullSet = new ArrayList<>();
        module.getColumns().forEach(m -> {
            String columnName = m.getColumnName();
            columnNames.add(columnName);
            String fieldName = CamelCaseUtils.toCamelCase(columnName);
            columnNamesAs.add(columnName + " as " + fieldName);
            vals.add("#{" + fieldName + "}");
            if (m.getDataType().equals("varchar")) {
                conditions.add(String.format(STRING_CONDITION_TEMPLATE, fieldName, fieldName, columnName, fieldName));
                if (!StringUtils.equalsAnyIgnoreCase("PRI", m.getColumnKey())) {
                    updateIgnoreNullSet.add(String.format(STRING_UPDATE_IGNORE_NULL_SET_TEMPLATE, fieldName, fieldName, columnName, fieldName));
                }
            } else {
                conditions.add(String.format(CONDITION_TEMPLATE, fieldName, columnName, fieldName));
                if (!StringUtils.equalsAnyIgnoreCase("PRI", m.getColumnKey())) {
                    updateIgnoreNullSet.add(String.format(UPDATE_IGNORE_NULL_SET_TEMPLATE, fieldName, columnName, fieldName));
                }
            }
            if (!StringUtils.equalsAnyIgnoreCase("PRI", m.getColumnKey())) {
                updateSet.add(columnName + "=#{" + fieldName + "}");
            }
        });
        dataModel.put("columns", StringUtils.join(columnNames, ","));
        dataModel.put("columnsAs", StringUtils.join(columnNamesAs, ","));
        dataModel.put("vals", StringUtils.join(vals, ","));
        dataModel.put("conds", StringUtils.join(conditions, "\n"));
        dataModel.put("entitySimpleName", module.getEntitySimpleName());
        dataModel.put("entityFullName", module.getEntityFullName());
        dataModel.put("tableName", module.getTableName());
        dataModel.put("idCond", "id=#{id}");
        dataModel.put("updateSet", StringUtils.join(updateSet, ","));
        dataModel.put("updateIgnoreNullSet", StringUtils.join(updateIgnoreNullSet, "\n"));
        writeToFile(dataModel, TEMPLATE_NAME,
                createOutputFile(module.getOutputDir(), module.getMapperPackage(), module.getMapperSimpleName() + ".xml"));
    }
}