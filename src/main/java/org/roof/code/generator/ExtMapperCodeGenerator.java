package org.roof.code.generator;

/**
 * @author liuxin
 * @since 2018-12-10
 */
public class ExtMapperCodeGenerator extends AbstractCodeGenerator {

    private static final String TEMPLATE_NAME = "temple/MapperExtTemplate.ftl";


    @Override
    protected String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Override
    protected String getFilename(Module module) {
        return module.getMapperExtSimpleName();
    }

    @Override
    protected String getPackage(Module module) {
        return module.getMapperExtPackage();
    }


}
