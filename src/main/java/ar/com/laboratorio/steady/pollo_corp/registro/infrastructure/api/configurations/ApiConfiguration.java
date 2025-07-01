package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.com.laboratorio.steady.pollo_corp.registro.aplication.ClientRegistryUseCase;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientRepository;

@Configuration
public class ApiConfiguration {

    @Bean
    public ClientRegistryUseCase clientRegistryUseCase(ClientRepository clientRepository) {
        return new ClientRegistryUseCase(clientRepository);
    }
}
