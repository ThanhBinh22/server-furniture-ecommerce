package com.thesis.serverfurnitureecommerce.infrastructure.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Furniture E-Commerce API",
                version = "1.0",
                description = "API documentation for the e-commerce platform"
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Development Server")
        }
)
public class SwaggerConfig {
}
