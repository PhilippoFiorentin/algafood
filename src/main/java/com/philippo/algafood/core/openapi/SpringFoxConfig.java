package com.philippo.algafood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.philippo.algafood.api"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
                .apiInfo(apiInfo())
                .tags(new Tag("Cities", "Manage cities"));
    }

    private List<Response> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Internal server error")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("The resource does not have representation that could be accepted by the consumer")
                        .build()
        );
    }

    private List<Response> globalPostPutResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Invalid request (client error)")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("The resource does not have representation that could be accepted by the consumer")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                        .description("Request rejected because the body is in an unsupported format")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Internal server error")
                        .build()
        );
    }

    private List<Response> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Internal server error")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Invalid request (client error)")
                        .build()
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("AlgaFood API")
                .description("Open API for costumers and restaurants")
                .version("1")
                .contact(new Contact("AlgaWorks", "http://www.algaworks.com", "contato@algaworks.com"))
                .build();
    }
}
