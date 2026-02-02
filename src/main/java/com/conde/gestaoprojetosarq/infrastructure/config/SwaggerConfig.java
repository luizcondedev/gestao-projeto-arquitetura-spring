package com.conde.gestaoprojetosarq.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestão de Projetos de Arquitetura")
                        .version("1.0")
                        .description("Sistema de gestão voltado para arquitetos, " +
                                "desenvolvido para organizar projetos, prazos e recursos.")
                        .contact(new Contact()
                                .name("Luiz Conde")
                                .email("luizconde.deev@gmail.com")));
    }
}