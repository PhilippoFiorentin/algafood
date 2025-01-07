package com.philippo.algafood.core.openapi;

import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.v1.model.*;
import com.philippo.algafood.api.v1.openapi.model.*;
import com.philippo.algafood.api.v2.model.CityModelV2;
import com.philippo.algafood.api.v2.model.KitchenModelV2;
import com.philippo.algafood.api.v2.openapi.model.CitiesModelV2OpenApi;
import com.philippo.algafood.api.v2.openapi.model.KitchensModelV2OpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket apiDocketV1() {

        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.OAS_30)
                .groupName("V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.philippo.algafood.api"))
                .paths(PathSelectors.ant("/v1/**"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
                .additionalModels(typeResolver.resolve(Problem.class))
                .ignoredParameterTypes(ServletWebRequest.class, URL.class, URI.class, URLStreamHandler.class,
                        Resource.class, File.class, InputStream.class)
                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                .directModelSubstitute(Link.class, LinksModelOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class, KitchenModel.class),
                                KitchensModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class, RestaurantOrderSummaryModel.class),
                        RestaurantOrdersSummaryModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, CityModel.class),
                        CitiesModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, StateModel.class),
                        StatesModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, PaymentMethodModel.class),
                        PaymentMethodsModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, GroupModel.class),
                        GroupsModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, PermissionModel.class),
                        PermissionsModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, ProductModel.class),
                        ProductsModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, UserModel.class),
                        UsersModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, BasicRestaurantModel.class),
                        BasicRestaurantsModelOpenApi.class))
                .apiInfo(apiInfoV1())
                .tags(
                        new Tag("Cities", "Manage cities"),
                        new Tag("Groups", "Manage groups"),
                        new Tag("Kitchens", "Manage kitchens"),
                        new Tag("Payment methods", "Manage payment methods"),
                        new Tag("Restaurant orders", "Manage restaurant orders"),
                        new Tag("Restaurants", "Manage restaurants"),
                        new Tag("States", "Manage states"),
                        new Tag("Products", "Manage products"),
                        new Tag("Users", "Manage users"),
                        new Tag("Statistics", "Manage statistics"),
                        new Tag("Permissions", "Manage permissions")
                );
    }

    @Bean
    public Docket apiDocketV2() {

        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.OAS_30)
                .groupName("V2")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.philippo.algafood.api"))
                .paths(PathSelectors.ant("/v2/**"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
                .additionalModels(typeResolver.resolve(Problem.class))
                .ignoredParameterTypes(ServletWebRequest.class, URL.class, URI.class, URLStreamHandler.class,
                        Resource.class, File.class, InputStream.class)
                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                .directModelSubstitute(Link.class, LinksModelOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class, KitchenModelV2.class),
                        KitchensModelV2OpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, CityModelV2.class),
                        CitiesModelV2OpenApi.class))
                .apiInfo(apiInfoV2())
                .tags(
                        new Tag("Cities", "Manage cities"),
                        new Tag("Kitchens", "Manage kitchens")
                );
    }

    @Bean
    public JacksonModuleRegistrar springFoxJacksonConfig() {
        return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
    }

    private List<Response> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Internal server error")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getProblemModelReference())
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
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getProblemModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("The resource does not have representation that could be accepted by the consumer")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                        .description("Request rejected because the body is in an unsupported format")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getProblemModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Internal server error")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getProblemModelReference())
                        .build()
        );
    }

    private List<Response> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Internal server error")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getProblemModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Invalid request (client error)")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getProblemModelReference())
                        .build()
        );
    }

    private ApiInfo apiInfoV1() {
        return new ApiInfoBuilder()
                .title("AlgaFood API")
                .description("Open API for costumers and restaurants")
                .version("2")
                .contact(new Contact("AlgaWorks", "http://www.algaworks.com", "contato@algaworks.com"))
                .build();
    }

    private ApiInfo apiInfoV2() {
        return new ApiInfoBuilder()
                .title("AlgaFood API")
                .description("Open API for costumers and restaurants")
                .version("1")
                .contact(new Contact("AlgaWorks", "http://www.algaworks.com", "contato@algaworks.com"))
                .build();
    }

    private Consumer<RepresentationBuilder> getProblemModelReference() {
        return r -> r.model(m -> m.name("Problema")
                .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                        q -> q.name("Problem").namespace("com.philippo.algafood.api.exceptionhandler")))));
    }
}
