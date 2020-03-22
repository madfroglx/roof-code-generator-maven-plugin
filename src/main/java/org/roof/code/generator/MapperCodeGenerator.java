package org.roof.code.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author liuxin
 * @since 2020/3/17
 */
@Component
public class MapperCodeGenerator extends AbstractCodeGenerator {

    private static final Logger CODE_LOGGER = LoggerFactory.getLogger("codeLogger");
    private static final String TEMPLATE_NAME = "temple/MapperTemplate.ftl";

    @Override
    public void generate(Module module) {
        writeToFile(module, TEMPLATE_NAME,
                createOutputFile(module.getOutputDir(), module.getMapperPackage(), module.getMapperSimpleName() + ".java"));
    }
}
