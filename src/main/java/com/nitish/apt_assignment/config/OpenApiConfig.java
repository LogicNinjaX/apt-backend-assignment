package com.nitish.apt_assignment.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "APT Assignment",
                version = "1.0",
                description = "REST API for managing orders and broadcasting real-time updates via WebSocket",
                contact = @Contact(
                        name = "Nitish Sahni",
                        email = "nitishsahni9565@gmail.com"
                )
        )
)
public class OpenApiConfig {
}
