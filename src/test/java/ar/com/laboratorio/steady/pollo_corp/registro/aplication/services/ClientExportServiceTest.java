package ar.com.laboratorio.steady.pollo_corp.registro.aplication.services;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientPrinterFileRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.adapters.repository.SpringDataClientRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.entities.ClientEntity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

@Testcontainers
@SpringBootTest
public class ClientExportServiceTest {

    @SuppressWarnings("resource")
    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.datasource.driver-class-name", mysql::getDriverClassName);
    }

    @Autowired
    private SpringDataClientRepository clientRepository;
    @Autowired
    private ClientExportService exportService;
    @Autowired
    private ClientPrinterFileRepository fileRepository;

    @AfterEach
    void cleanUpFiles() throws Exception {
        // Obtén el directorio base desde tu FileAdapter
        // Si FileAdapter tiene un getter, úsalo. Si no, usa la ruta configurada.
        Path filePath = Path.of(System.getProperty("user.dir"), "tmp", "clients.csv");
        Files.deleteIfExists(filePath);
        // Si usas otro directorio, ajusta la ruta según tu configuración
    }
    
    @Test
    void exportAllClientsToFile_withRealData() {
        // Guardar datos reales en la base
        ClientEntity entity = new ClientEntity(
                "20-12345678-3",
                "12345678",
                "Juan",
                "Pérez",
                "García",
                LocalDate.of(1990, 1, 1),
                "juan@mail.com",
                "+54-11-12345678",
                "Calle Falsa, 123, CABA, Buenos Aires, 1000"
        );
        clientRepository.save(entity);

        // Ejecutar el método real
        assertDoesNotThrow(() -> exportService.exportAllClientsToFile("csv"));

        // Aquí podrías verificar que el archivo fue creado, por ejemplo:
        assertTrue(fileRepository.exists("clients.csv"));
        // O leer el archivo y verificar el contenido si tu implementación lo permite
    }
}
