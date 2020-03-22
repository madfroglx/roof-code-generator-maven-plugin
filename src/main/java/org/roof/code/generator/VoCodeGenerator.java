package org.roof.code.generator;

import org.springframework.stereotype.Component;

/**
 * @author liuxin
 * @since 2018-12-10
 */
@Component
public class VoCodeGenerator extends AbstractCodeGenerator {

    private static final String TEMPLATE_NAME = "temple/VoTemplate.ftl";

    @Override
    public void generate(Module module) {
        writeToFile(module, TEMPLATE_NAME,
                createOutputFile(module.getOutputDir(), module.getEntityPackage(), module.getEntitySimpleName() + "Vo.java"));
    }
}
