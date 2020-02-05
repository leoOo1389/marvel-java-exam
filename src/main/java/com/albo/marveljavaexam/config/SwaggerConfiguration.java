package com.albo.marveljavaexam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static springfox.documentation.builders.PathSelectors.ant;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public static final String SECURITY_SCHEMA_OAUTH2 = "header";

    private static final String APPLICATION_BASIC_AUTH_SCHEME = "basic";

    /**
     * Configuration of OAuth2 autentication on Swagger
     */
    @Bean
    public Docket statusAPI() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any())
                .paths(PathSelectors.any())
                .paths(not(ant("/error")))
                .build()
                .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Marvel REST API")
                .description("REST API to know marvel sync status")
                .contact(new Contact("Leobardo Vargas", "", "leovargasf@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}