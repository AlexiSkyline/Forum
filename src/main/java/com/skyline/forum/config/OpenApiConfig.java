package com.skyline.forum.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Alexiskyline",
                        email = "ilegal_sprite@hotmail.com",
                        url = "https://github.com/AlexiSkyline"
                ),
                description = "Welcome to the Forum Application API Documentation. This API allows you to interact with a community forum where users can ask questions, post answers, and engage in discussions on various topics",
                title = "Forum Application API"
        ),
        servers = {
                @Server(
                        description = "Development server",
                        url = "http://localhost:8080"
                )
        }
)
@SecurityScheme(
        name = "JWT Bearer Token",
        description = "Authentication using JSON Web Tokens (JWT). Include the JWT token in the 'Authorization' header with the 'Bearer' scheme.",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
