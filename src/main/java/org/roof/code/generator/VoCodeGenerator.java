package org.roof.code.generator;

/**
 * @author liuxin
 * @since 2018-12-10
 */
public class VoCodeGenerator extends AbstractCodeGenerator {

    private static final String TEMPLATE_NAME = "temple/VoTemplate.ftl";


    @Override
    protected String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Override
    protected String getFilename(Module module) {
        return module.getEntitySimpleName() + "Vo";
    }

    @Override
    protected String getPackage(Module module) {
        return module.getEntityPackage();
    }


}
