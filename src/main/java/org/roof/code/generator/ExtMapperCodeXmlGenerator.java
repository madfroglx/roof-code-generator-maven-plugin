package org.roof.code.generator;

import java.io.File;
import java.io.IOException;

/**
 * @author liuxin
 * @since 2018-12-10
 */
public class ExtMapperCodeXmlGenerator extends AbstractCodeGenerator {

    private static final String TEMPLATE_NAME = "temple/MapperExtXmlTemplate.ftl";


    @Override
    protected String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Override
    protected String getFilename(Module module) {
        return module.getMapperExtSimpleName();
    }

    @Override
    protected File createOutputFile(Module module, String outputPath) {
        File dir = new File(outputPath + File.separator + package2Path(getPackage(module)));
        File target = new File(dir.getPath() + File.separator + getFilename(module) + ".xml");
        return getCreateFile(dir, target);
    }

    @Override
    protected String getPackage(Module module) {
        return module.getMapperExtPackage();
    }


}
