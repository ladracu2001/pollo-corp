package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.configurations;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Client API")
                        .version("1.0.0")
                        .description("API for Clients")
                        .contact(new Contact()
                                .name("Lautaro Maccio")
                                .email("ladracu2001@gmail.com")));
    }
}
