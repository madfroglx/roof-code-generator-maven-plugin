package org.roof.code.generator;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author liuxin
 * @since 2018-12-10
 */
public class Module {

    private String projectName;
    private String[] segments;

    private String modulePackage;

    private String entityPackage;
    private String entityFullName;
    private String entitySimpleName;
    private String entityVariateName;

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


    public Module(String entityFullName) {
        this.entityFullName = entityFullName;
        init();
    }

    private void init() {
        segments = entityFullName.split("\\.");
        createModulePackage();
        createEntityName();
        createMapper();
        createServiceInterfaceName();
        createServiceImplName();
        createControllerName();
    }

    private void createControllerName() {
        controllerSimpleName = entitySimpleName + "Controller";
        controllerPackage = modulePackage + ".controller";
        controllerFullName = controllerPackage + "." + controllerSimpleName;
    }

    private void createServiceImplName() {
        serviceImplSimpleName = serviceInterfaceSimpleName + "Impl";
        serviceImplPackage = serviceInterfacePackage;
        serviceImplFullName = serviceInterfaceFullName + "Impl";
    }

    private void createServiceInterfaceName() {
        serviceInterfaceSimpleName = entitySimpleName + "Service";
        serviceInterfacePackage = modulePackage + ".service";
        serviceInterfaceFullName = serviceInterfacePackage + "." + serviceInterfaceSimpleName;
        serviceInterfaceVariateName = StringUtils.uncapitalize(serviceInterfaceSimpleName);
    }

    private void createMapper() {
        mapperSimpleName = entitySimpleName + "Mapper";
        mapperExtSimpleName = entitySimpleName + "ExtMapper";
        mapperPackage = modulePackage + ".mapper";
        mapperExtPackage = mapperPackage;
        mapperFullName = mapperPackage + "." + mapperSimpleName;
        mapperExtFullName = mapperExtPackage + "." + mapperExtSimpleName;
        mapperVariateName = StringUtils.uncapitalize(mapperSimpleName);
        mapperExtVariateName = StringUtils.uncapitalize(mapperExtSimpleName);
    }

    private void createEntityName() {
        entitySimpleName = segments[segments.length - 1];
        entityPackage = modulePackage + "entity";
        entityVariateName = StringUtils.uncapitalize(entitySimpleName);
    }

    private void createModulePackage() {
        String[] modulePackageSegments = ArrayUtils.subarray(segments, 0, segments.length - 2);
        modulePackage = StringUtils.join(modulePackageSegments, ".");
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
