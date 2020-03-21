package org.roof.code.generator;

import org.springframework.stereotype.Component;

/**
 * @author liuxin
 * @since 2018-12-10
 */
@Component
public class ExtMapperCodeGenerator extends AbstractCodeGenerator2 {

    private static final String TEMPLATE_NAME = "temple/MapperExtTemplate.ftl";

    public void generate(Module module) {
        writeToFile(module, TEMPLATE_NAME,
                createOutputFile(module.getOutputDir(), module.getMapperExtPackage(), module.getMapperExtSimpleName() + ".java"));
    }
}
