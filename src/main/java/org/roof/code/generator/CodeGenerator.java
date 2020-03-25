package org.roof.code.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuxin
 * @since 2020/3/19
 */
@Component
public class CodeGenerator {
    @Value("${code.gen.database}")
    private String database;
    @Value("${code.gen.tables}")
    private List<String> tables;
    @Value("${code.gen.modulePackage}")
    private String modulePackage;
    @Value("${code.gen.outputDir}")
    private String outputDir;
    @Value("${code.gen.projectName}")
    private String projectName;
    @Value("${code.gen.tablePre}")
    private boolean tablePre;

    @Autowired
    private EntityCodeGenerator entityCodeGenerator;
    @Autowired
    private VoCodeGenerator voCodeGenerator;
    @Autowired
    private MapperXmlCodeGenerator mapperXmlCodeGenerator;
    @Autowired
    private MapperCodeGenerator mapperCodeGenerator;
    @Autowired
    private ServiceInterfaceCodeGenerator serviceInterfaceCodeGenerator;
    @Autowired
    private ServiceImplCodeGenerator serviceImplCodeGenerator;
    @Autowired
    private ExtMapperCodeGenerator extMapperCodeGenerator;
    @Autowired
    private ExtMapperCodeXmlGenerator extMapperCodeXmlGenerator;
    @Autowired
    private ControllerCodeGenerator controllerCodeGenerator;
    @Autowired
    private ModuleInitialization moduleInitialization;

    public void generate() {
        for (String table : tables) {
            Module module = new Module(modulePackage, database, table, outputDir);
            module.setProjectName(projectName);
            moduleInitialization.init(module);
            entityCodeGenerator.generate(module);
            voCodeGenerator.generate(module);
            mapperXmlCodeGenerator.generate(module);
            mapperCodeGenerator.generate(module);
            extMapperCodeGenerator.generate(module);
            extMapperCodeXmlGenerator.generate(module);
            serviceInterfaceCodeGenerator.generate(module);
            serviceImplCodeGenerator.generate(module);
            controllerCodeGenerator.generate(module);
        }
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public String getModulePackage() {
        return modulePackage;
    }

    public void setModulePackage(String modulePackage) {
        this.modulePackage = modulePackage;
    }

}
