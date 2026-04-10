package com.ejemplo.demo.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Workshop Spring Boot")
                        .version("1.0")
                        .description("API demo para laboratorio de Spring Boot")
                        .contact(new Contact()
                                .name("Jose Mario")
                                .email("Jrosalesp16@miumg.edu.gt")));
    }
}
