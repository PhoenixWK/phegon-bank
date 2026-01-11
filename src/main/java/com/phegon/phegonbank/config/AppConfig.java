package com.phegon.phegonbank.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class AppConfig {

    /*
        * Configures the Thymeleaf template engine with a class loader template resolver.
        * Returns the configured engine for use by the application (typically by ViewResolver).
        *
        * SpringTemplateEngine templateEngine = new SpringTemplateEngine(); --> creates a new instance of the Thymeleaf template engine.
        * ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver(); --> Creates a template resolver, which tells Thymeleaf where to find your HTML templates.
        * templateResolver.setPrefix("templates/"); -->Sets the directory path where your templates are stored, relative to the classpath
        * templateResolver.setSuffix(".html"); --> Sets the file extension of templates to .html
        * templateResolver.setCharacterEncoding("UTF-8"); --> Ensures that the template files are read and rendered using UTF-8 encoding
        * templateEngine.setTemplateResolver(templateResolver); --> This tells SpringTemplateEngine how to find and load templates for rendering.

     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }

    /*
        This function declares a ModelMapper bean for object mapping.
        modelMapper.getConfiguration(); --> Accesses the internal configuration object of ModelMapper to fine-tune how mapping works.
            .setFieldMatchingEnabled(true) --> Enables field matching — allows ModelMapper to map fields directly (even if there are no getters/setters).
            .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE) --> Sets the access level to PRIVATE, allowing ModelMapper to access private fields directly.
            .setMatchingStrategy(MatchingStrategies.STANDARD); --> ets the matching strategy to STANDARD, which balances between flexibility and strictness:
                It matches fields by name (case-sensitive).
                Allows nested mapping (e.g., user.address.street ↔ userDTO.addressStreet).
                Ignores minor naming differences.
    */
    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        return modelMapper;
    }

}
