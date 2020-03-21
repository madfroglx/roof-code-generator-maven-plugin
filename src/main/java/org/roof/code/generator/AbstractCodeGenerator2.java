package org.roof.code.generator;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @author liuxin
 * @since 2018-12-10
 */
public abstract class AbstractCodeGenerator2 {
    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractCodeGenerator2.class);
    private Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

    protected Template loadTemplate(String templateName) {
        try {
            Resource templateResource = new ClassPathResource(templateName);
            StringTemplateLoader stringLoader = new StringTemplateLoader();
            stringLoader.putTemplate(templateName, IOUtils.toString(templateResource.getInputStream(), "UTF-8"));
            configuration.setTemplateLoader(stringLoader);
            return configuration.getTemplate(templateName, "UTF-8");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    protected String package2Path(String packageName) {
        return StringUtils.replaceAll(packageName, "\\.", File.separator);
    }

    protected File createOutputFile(String outputPath, String packageName, String fileName) {
        File dir = new File(outputPath + File.separator + package2Path(packageName));
        File target = new File(dir.getPath() + File.separator + fileName);
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

    protected String fillTemplate(Object dataModel, String templateName) {
        Template template = loadTemplate(templateName);
        assert template != null;
        try (StringWriter stringWriter = new StringWriter()) {
            template.process(dataModel, stringWriter);
            return stringWriter.toString();
        } catch (TemplateException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    protected void writeToFile(Object dataModel, String templateName, File file) {
        Template template = loadTemplate(templateName);
        assert template != null;
        try (FileWriter fileWriter = new FileWriter(file)) {
            template.process(dataModel, fileWriter);
        } catch (TemplateException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
