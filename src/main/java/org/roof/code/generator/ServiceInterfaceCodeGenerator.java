package org.roof.code.generator;

/**
 * @author liuxin
 * @since 2018-12-10
 */
public class ServiceInterfaceCodeGenerator extends AbstractCodeGenerator {

    private static final String TEMPLATE_NAME = "temple/ServiceInterfaceTemplate.ftl";


    @Override
    protected String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Override
    protected String getFilename(Module module) {
        return module.getServiceInterfaceSimpleName();
    }

    @Override
    protected String getPackage(Module module) {
        return module.getServiceInterfacePackage();
    }


}
