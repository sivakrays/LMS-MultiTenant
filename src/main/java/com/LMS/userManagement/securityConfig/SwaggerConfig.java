package com.LMS.userManagement.securityConfig;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Arrays;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openApi = new OpenAPI();
        openApi.info(
                new Info()
                        .title("LMS API")
                        .version("1.0")
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

        return openApi;
    }
}
