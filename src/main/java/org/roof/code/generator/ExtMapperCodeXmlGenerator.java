package org.roof.code.generator;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author liuxin
 * @since 2018-12-10
 */
@Component
public class ExtMapperCodeXmlGenerator extends AbstractCodeGenerator2 {

    private static final String TEMPLATE_NAME = "temple/MapperExtXmlTemplate.ftl";

    public void generate(Module module) {
        writeToFile(module, TEMPLATE_NAME,
                createOutputFile(module.getOutputDir(), module.getMapperExtPackage(), module.getMapperExtSimpleName() + ".xml"));
    }
}
