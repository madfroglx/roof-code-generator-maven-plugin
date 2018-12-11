package org.roof.code.generator;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author liuxin
 * @since 2018-12-10
 */
public abstract class AbstractCodeGenerator {
    private Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

    protected Template loadTemplate() {
        String templateName = getTemplateName();
        try {
            Resource templateResource = new ClassPathResource(templateName);
            StringTemplateLoader stringLoader = new StringTemplateLoader();
            stringLoader.putTemplate(templateName, IOUtils.toString(templateResource.getInputStream(), "UTF-8"));
            configuration.setTemplateLoader(stringLoader);
            return configuration.getTemplate(templateName, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String package2Path(String packageName) {
        return StringUtils.replaceAll(packageName, "\\.", File.separator);
    }

    protected File createOutputFile(Module module, String outputPath) {
        File dir = new File(outputPath + File.separator + package2Path(getPackage(module)));
        File target = new File(dir.getPath() + File.separator + getFilename(module) + ".java");
        return getCreateFile(dir, target);
    }

    protected File getCreateFile(File dir, File target) {
        boolean mkdirs = dir.mkdirs();
        try {
            boolean newFile = dir.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return target;
    }

    public void generate(Module module, String outputPath) {
        Template template = loadTemplate();
        File target = createOutputFile(module, outputPath);
        try (FileWriter fileWriter = new FileWriter(target)) {
            assert template != null;
            template.process(module, fileWriter);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getTemplateName();

    protected abstract String getFilename(Module module);

    protected abstract String getPackage(Module module);
}
