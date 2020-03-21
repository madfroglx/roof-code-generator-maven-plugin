package org.roof.code.generator;

import org.roof.code.generator.table.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuxin
 * @since 2020/3/17
 */
@Component
public class MapperCodeGenerator extends AbstractCodeGenerator2 {

    private static final Logger CODE_LOGGER = LoggerFactory.getLogger("codeLogger");
    private static final String TEMPLATE_NAME = "temple/MapperTemplate.ftl";

    public void generate(Module module) {
        writeToFile(module, TEMPLATE_NAME,
                createOutputFile(module.getOutputDir(), module.getMapperPackage(), module.getMapperSimpleName() + ".java"));
    }
}
