package com.plukash.testuserservice.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Value("${springdoc.swagger-ui.server}")
    private String serverUrl;

    @Bean
    public OpenAPI apiInfo() {
        var server = new Server();
        server.setUrl(serverUrl);
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addServersItem(server)
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.APIKEY)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("")
                        .description("Test-user-service app")
                        .version("0.1"));
    }
}
