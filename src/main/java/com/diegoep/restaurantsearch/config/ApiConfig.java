package com.diegoep.restaurantsearch.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("restaurant-search")
                .pathsToMatch("/api/**")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().
                                title("Restaurant Search API - By Diego Pessoa")
                                .contact(new Contact()
                                    .name("Diego Pessoa")
                                    .email("diegopessoa12@gmail.com"))))
                .build();
    }

}
