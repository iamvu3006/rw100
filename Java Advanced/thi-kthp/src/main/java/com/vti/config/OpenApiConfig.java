package com.vti.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Application API")
                        .version("1.0")
                        .description("API Documentation")
                        .contact(new Contact()
                                .name("VTI")
                                .email("contact@vti.com")
                                .url("https://vti.com.vn")));
    }
}