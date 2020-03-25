package org.roof.code.generator;

import org.apache.commons.lang3.StringUtils;
import org.roof.code.generator.table.Column;
import org.roof.code.generator.table.Table;
import org.roof.code.generator.utils.CamelCaseUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author liuxin
 * @since 2018-12-10
 */
public class Module {

    private String[] segments;
    private String outputDir;
    private String database;
    private String projectName;

    private String modulePackage; //项目包名 如 com.roof.spiderman
    private String tableName; //生成的表名

    private String entityPackage; //实体类包名com.roof.spiderman.picture.entity
    private String entityFullName;//类全名 如:com.roof.spiderman.picture.entity.Picture
    private String entitySimpleName; // 类名 如:Picture
    private String entityVariateName;// 变量名 如:picture

    private Table table;
    private List<Column> columns; //数据库列
    private boolean tablePre; //是否包含表名前缀

    private String mapperPackage;
    private String mapperFullName;
    private String mapperSimpleName;
    private String mapperVariateName;


    private String mapperExtPackage;
    private String mapperExtFullName;
    private String mapperExtSimpleName;
    private String mapperExtVariateName;


    private String serviceInterfacePackage;
    private String serviceInterfaceFullName;
    private String serviceInterfaceSimpleName;
    private String serviceInterfaceVariateName;

    private String serviceImplPackage;
    private String serviceImplFullName;
    private String serviceImplSimpleName;

    private String controllerPackage;
    private String controllerFullName;
    private String controllerSimpleName;


    public Module(String modulePackage, String database, String tableName, String outputDir) {
        this.modulePackage = modulePackage;
        this.tableName = tableName;
        this.outputDir = outputDir;
        this.database = database;
        init();
    }

    private void init() {
        segments = modulePackage.split("\\.");
        createEntityName();
        createMapper();
        createServiceInterfaceName();
        createServiceImplName();
        createControllerName();
    }

    private void createControllerName() {
        controllerSimpleName = entitySimpleName + "Controller";
        controllerPackage = modulePackage + "." + entityVariateName + ".controller";
        controllerFullName = controllerPackage + "." + controllerSimpleName;
    }

    private void createServiceImplName() {
        serviceImplSimpleName = serviceInterfaceSimpleName + "Impl";
        serviceImplPackage = serviceInterfacePackage;
        serviceImplFullName = serviceInterfaceFullName + "Impl";
    }

    private void createServiceInterfaceName() {
        serviceInterfaceSimpleName = entitySimpleName + "Service";
        serviceInterfacePackage = modulePackage + "." + entityVariateName + ".service";
        serviceInterfaceFullName = serviceInterfacePackage + "." + serviceInterfaceSimpleName;
        serviceInterfaceVariateName = StringUtils.uncapitalize(serviceInterfaceSimpleName);
    }

    private void createMapper() {
        mapperSimpleName = entitySimpleName + "Mapper";
        mapperExtSimpleName = entitySimpleName + "ExtMapper";
        mapperPackage = modulePackage + "." + entityVariateName + ".mapper";
        mapperExtPackage = mapperPackage;
        mapperFullName = mapperPackage + "." + mapperSimpleName;
        mapperExtFullName = mapperExtPackage + "." + mapperExtSimpleName;
        mapperVariateName = StringUtils.uncapitalize(mapperSimpleName);
        mapperExtVariateName = StringUtils.uncapitalize(mapperExtSimpleName);
    }

    private void createEntityName() {
        if (tablePre) {
            entitySimpleName =CamelCaseUtils.toCapitalizeCamelCase(StringUtils.substring(tableName,
                    StringUtils.indexOf(tableName, "_") + 1,
                    tableName.length()));
        } else {
            entitySimpleName = CamelCaseUtils.toCapitalizeCamelCase((tableName));
        }
        entityVariateName = StringUtils.uncapitalize(entitySimpleName);
        entityPackage = modulePackage + "." + entityVariateName + ".entity";
        entityFullName = entityPackage + "." + entitySimpleName;
    }

    public String getEntityFullName() {
        return entityFullName;
    }

    public void setEntityFullName(String entityFullName) {
        this.entityFullName = entityFullName;
    }

    public String getEntitySimpleName() {
        return entitySimpleName;
    }

    public void setEntitySimpleName(String entitySimpleName) {
        this.entitySimpleName = entitySimpleName;
    }

    public String getMapperFullName() {
        return mapperFullName;
    }

    public void setMapperFullName(String mapperFullName) {
        this.mapperFullName = mapperFullName;
    }

    public String getMapperSimpleName() {
        return mapperSimpleName;
    }

    public void setMapperSimpleName(String mapperSimpleName) {
        this.mapperSimpleName = mapperSimpleName;
    }

    public String getControllerFullName() {
        return controllerFullName;
    }

    public void setControllerFullName(String controllerFullName) {
        this.controllerFullName = controllerFullName;
    }

    public String getControllerSimpleName() {
        return controllerSimpleName;
    }

    public void setControllerSimpleName(String controllerSimpleName) {
        this.controllerSimpleName = controllerSimpleName;
    }

    public String[] getSegments() {
        return segments;
    }

    public void setSegments(String[] segments) {
        this.segments = segments;
    }

    public String getModulePackage() {
        return modulePackage;
    }

    public void setModulePackage(String modulePackage) {
        this.modulePackage = modulePackage;
    }

    public String getServiceInterfaceFullName() {
        return serviceInterfaceFullName;
    }

    public void setServiceInterfaceFullName(String serviceInterfaceFullName) {
        this.serviceInterfaceFullName = serviceInterfaceFullName;
    }

    public String getServiceInterfaceSimpleName() {
        return serviceInterfaceSimpleName;
    }

    public void setServiceInterfaceSimpleName(String serviceInterfaceSimpleName) {
        this.serviceInterfaceSimpleName = serviceInterfaceSimpleName;
    }

    public String getServiceImplFullName() {
        return serviceImplFullName;
    }

    public void setServiceImplFullName(String serviceImplFullName) {
        this.serviceImplFullName = serviceImplFullName;
    }

    public String getServiceImplSimpleName() {
        return serviceImplSimpleName;
    }

    public void setServiceImplSimpleName(String serviceImplSimpleName) {
        this.serviceImplSimpleName = serviceImplSimpleName;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getServiceInterfacePackage() {
        return serviceInterfacePackage;
    }

    public void setServiceInterfacePackage(String serviceInterfacePackage) {
        this.serviceInterfacePackage = serviceInterfacePackage;
    }

    public String getServiceImplPackage() {
        return serviceImplPackage;
    }

    public void setServiceImplPackage(String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getEntityVariateName() {
        return entityVariateName;
    }

    public void setEntityVariateName(String entityVariateName) {
        this.entityVariateName = entityVariateName;
    }

    public String getMapperVariateName() {
        return mapperVariateName;
    }

    public void setMapperVariateName(String mapperVariateName) {
        this.mapperVariateName = mapperVariateName;
    }

    public String getMapperExtPackage() {
        return mapperExtPackage;
    }

    public void setMapperExtPackage(String mapperExtPackage) {
        this.mapperExtPackage = mapperExtPackage;
    }

    public String getMapperExtFullName() {
        return mapperExtFullName;
    }

    public void setMapperExtFullName(String mapperExtFullName) {
        this.mapperExtFullName = mapperExtFullName;
    }

    public String getMapperExtSimpleName() {
        return mapperExtSimpleName;
    }

    public void setMapperExtSimpleName(String mapperExtSimpleName) {
        this.mapperExtSimpleName = mapperExtSimpleName;
    }

    public String getMapperExtVariateName() {
        return mapperExtVariateName;
    }

    public void setMapperExtVariateName(String mapperExtVariateName) {
        this.mapperExtVariateName = mapperExtVariateName;
    }

    public String getServiceInterfaceVariateName() {
        return serviceInterfaceVariateName;
    }

    public void setServiceInterfaceVariateName(String serviceInterfaceVariateName) {
        this.serviceInterfaceVariateName = serviceInterfaceVariateName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public boolean isTablePre() {
        return tablePre;
    }

    public void setTablePre(boolean tablePre) {
        this.tablePre = tablePre;
    }

    @Override
    public String toString() {
        return "Module{" +
                "segments=" + Arrays.toString(segments) +
                ", modulePackage='" + modulePackage + '\'' +
                ", tableName='" + tableName + '\'' +
                ", entityPackage='" + entityPackage + '\'' +
                ", entityFullName='" + entityFullName + '\'' +
                ", entitySimpleName='" + entitySimpleName + '\'' +
                ", entityVariateName='" + entityVariateName + '\'' +
                ", mapperPackage='" + mapperPackage + '\'' +
                ", mapperFullName='" + mapperFullName + '\'' +
                ", mapperSimpleName='" + mapperSimpleName + '\'' +
                ", mapperVariateName='" + mapperVariateName + '\'' +
                ", mapperExtPackage='" + mapperExtPackage + '\'' +
                ", mapperExtFullName='" + mapperExtFullName + '\'' +
                ", mapperExtSimpleName='" + mapperExtSimpleName + '\'' +
                ", mapperExtVariateName='" + mapperExtVariateName + '\'' +
                ", serviceInterfacePackage='" + serviceInterfacePackage + '\'' +
                ", serviceInterfaceFullName='" + serviceInterfaceFullName + '\'' +
                ", serviceInterfaceSimpleName='" + serviceInterfaceSimpleName + '\'' +
                ", serviceInterfaceVariateName='" + serviceInterfaceVariateName + '\'' +
                ", serviceImplPackage='" + serviceImplPackage + '\'' +
                ", serviceImplFullName='" + serviceImplFullName + '\'' +
                ", serviceImplSimpleName='" + serviceImplSimpleName + '\'' +
                ", controllerPackage='" + controllerPackage + '\'' +
                ", controllerFullName='" + controllerFullName + '\'' +
                ", controllerSimpleName='" + controllerSimpleName + '\'' +
                '}';
    }


}
