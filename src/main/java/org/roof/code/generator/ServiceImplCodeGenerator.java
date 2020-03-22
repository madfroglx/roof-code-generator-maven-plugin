package org.roof.code.generator;

import org.springframework.stereotype.Component;

/**
 * @author liuxin
 * @since 2018-12-10
 */
@Component
public class ServiceImplCodeGenerator extends AbstractCodeGenerator {

    private static final String TEMPLATE_NAME = "temple/ServiceImplTemplate.ftl";

    @Override
    public void generate(Module module) {
        writeToFile(module, TEMPLATE_NAME,
                createOutputFile(module.getOutputDir(), module.getServiceImplPackage(), module.getServiceImplSimpleName() + ".java"));
    }

}
