package org.roof.code.generator.mojo;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.roof.code.generator.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

/**
 * @author liuxin
 * @since 2018-12-10
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES,
        requiresDependencyResolution = ResolutionScope.TEST)
public class CodeGenerateMojo extends AbstractMojo {
    /**
     * Maven Project.
     */
    @Parameter(property = "project", required = true, readonly = true)
    private MavenProject project;

    /**
     * Output Directory.
     */
    @Parameter(property = "code.generator.outputDirectory",
            defaultValue = "${project.basedir}/src/main/java", required = true)
    private File outputDirectory;

    /**
     * Output Directory.
     */
    @Parameter(property = "code.generator.outputDirectory",
            defaultValue = "${project.build.directory}/code-template", required = true)
    private File templateDirectory;

    /**
     * Location of the configuration file.
     */
    @Parameter(property = "code.generator.configurationFile",
            defaultValue = "${project.basedir}/src/main/resources/CodeGeneratorConfig.properties", required = true)
    private File configurationFile;

    public void execute() throws MojoExecutionException, MojoFailureException {
        Resource resource = new FileSystemResource(configurationFile);
        try (InputStream in = resource.getInputStream()) {
            Properties properties = new Properties();
            properties.load(in);
            String entitiesStr = properties.getProperty("codeGenerator.entities");
            String projectName = properties.getProperty("codeGenerator.projectName");
            String[] entityFullNames = StringUtils.split(entitiesStr, ",");
            for (String entityFullName : entityFullNames) {
                Module module = new Module(entityFullName);
                module.setProjectName(projectName);
                new ServiceInterfaceCodeGenerator().generate(module, outputDirectory.getPath());
                new ServiceImplCodeGenerator().generate(module, outputDirectory.getPath());
                new ControllerCodeGenerator().generate(module, outputDirectory.getPath());
                new ExtMapperCodeGenerator().generate(module, outputDirectory.getPath());
                new ExtMapperCodeXmlGenerator().generate(module, outputDirectory.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
