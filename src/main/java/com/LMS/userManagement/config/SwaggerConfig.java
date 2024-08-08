package com.LMS.userManagement.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Value("${lms.openapi.dev-url}")
    private String developmentUrl;

    @Value("${lms.openapi.local-url}")
    private String localUrl;

    @Bean
    public OpenAPI customOpenAPI() {

        OpenAPI openApi = new OpenAPI();
        openApi.info(
                new Info()
                        .title("LMS API")
                        .version("3.0")
                        .description("Learning Management System")
        );

        openApi.components(
                new Components().addSecuritySchemes("bearer-jwt",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER).name("Authorization"))
        );

        openApi.addSecurityItem(
                new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write"))
        );
        openApi.servers(Collections.singletonList(
                new Server().url(developmentUrl).description("Server URL in Development environment")
        ));

        return openApi;
    }

}
