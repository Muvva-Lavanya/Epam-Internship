package com.epam.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(info = @Info(title = "REST API", version = "1.0",
        contact = @Contact(name = "Muvva Lavanya", email = "Lavanya_Muvva@epam.com")),
        security = {@SecurityRequirement(name = "basicAuth"), @SecurityRequirement(name = "bearerToken")}
)
        @SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
        @SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class OpenApiConfig {
}