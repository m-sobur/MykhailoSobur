package com.epam.spring.homework3.quiz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.PathSelectors;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value(value = "${swagger.base.package}")
    private String swaggerBasePackage;

    @Bean
    public Docket apiUser() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerBasePackage))
                .paths(PathSelectors.ant("/user/**"))
                .build();
    }

    @Bean
    public Docket apiQuiz() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("quiz")
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerBasePackage))
                .paths(PathSelectors.ant("/quiz/**"))
                .build();
    }

    @Bean
    public Docket apiGame() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("game")
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerBasePackage))
                .paths(PathSelectors.ant("/game/**"))
                .build();
    }

    @Bean
    public Docket apiQuestion() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("question")
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerBasePackage))
                .paths(PathSelectors.ant("/question/**"))
                .build();
    }

    @Bean
    public Docket apiAnswerVariant() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("answer variant")
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerBasePackage))
                .paths(PathSelectors.ant("/answerVariant/**"))
                .build();
    }


    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }
}