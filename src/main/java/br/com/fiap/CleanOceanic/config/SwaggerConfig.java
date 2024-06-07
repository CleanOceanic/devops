package br.com.fiap.CleanOceanic.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI OpenApiConfiguration() {
        return new OpenAPI()
                .info(new Info()
                        .title("CleanOceanic - API")
                        .version("1.0.0")
                        .description("")
                        .contact(new Contact()
                                .name("Kaique Santos, Marcelo Augusto , Rodrigo Batista, Thiago Martins e Vinicius Oliveira")
                                .url("https://github.com/CleanOceanic/backend"))
                        .license(new License()
                        )
                );
    }
}