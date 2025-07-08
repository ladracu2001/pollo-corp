package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import ar.com.laboratorio.steady.pollo_corp.registro.aplication.ClientRegistryUseCase;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.adapters.reports.FileAdapter;

@Configuration
public class ApiConfiguration {

    @Bean
    public ClientRegistryUseCase clientRegistryUseCase(ClientRepository clientRepository) {
        return new ClientRegistryUseCase(clientRepository);
    }
    @Bean
    public FileAdapter fileAdapter(@Value("${file.base-directory:/tmp}") String baseDirectoryPath) {
        return new FileAdapter(baseDirectoryPath);
    }
}
