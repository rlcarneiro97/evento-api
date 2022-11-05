package com.eventoapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfiguration {
    
    @Bean
    public OpenAPI custonOpenApi(){
        return new OpenAPI()
        .components(new Components())
        .info(new Info().title("Evento API")
        .description("API REST de eventos"));
    }

}
