package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.adapters.repository;

import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.entities.ClientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJpaTest
class SpringDataClientRepositoryTest {

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
    private SpringDataClientRepository repository;

    @Test
    void testSaveAndFindByDni() {
        ClientEntity entity = new ClientEntity(
            "20-12345678-3",
            "12345678",
            "Juan",
            "Pérez",
            "Gómez",
            LocalDate.of(1990, 1, 1),
            "juan.perez@mail.com",
            "+54-11-12345678",
            "Calle Falsa, 123, Ciudad, Provincia, 1000"
        );
        repository.save(entity);

        List<ClientEntity> found = repository.findByDni("12345678");
        assertFalse(found.isEmpty());
        assertEquals("Juan", found.get(0).getName());
    }

    @Test
    void testFindByCuil() {
        ClientEntity entity = new ClientEntity(
            "20-87654321-0",
            "87654321",
            "Ana",
            "López",
            "Martínez",
            LocalDate.of(1985, 5, 15),
            "ana.lopez@mail.com",
            "+54-11-87654321",
            "Otra Calle, 456, Ciudad, Provincia, 2000"
        );
        repository.save(entity);
    
        ClientEntity found = repository.findByCuil("20-87654321-0").orElse(null);
        assertNotNull(found);
        assertEquals("Ana", found.getName());
    }
    
    @Test
    void testExistsByCuil() {
        ClientEntity entity = new ClientEntity(
            "20-11111111-1",
            "11111111",
            "Carlos",
            "Sosa",
            "Fernández",
            LocalDate.of(1970, 3, 10),
            "carlos.sosa@mail.com",
            "+54-11-11111111",
            "Calle Uno, 789, Ciudad, Provincia, 3000"
        );
        repository.save(entity);
    
        assertTrue(repository.existsByCuil("20-11111111-1"));
        assertFalse(repository.existsByCuil("20-00000000-0"));
    }
    
    @Test
    void testDeleteByCuil() {
        ClientEntity entity = new ClientEntity(
            "20-22222222-2",
            "22222222",
            "Lucía",
            "García",
            "Pérez",
            LocalDate.of(1995, 7, 20),
            "lucia.garcia@mail.com",
            "+54-11-22222222",
            "Calle Dos, 321, Ciudad, Provincia, 4000"
        );
        repository.save(entity);
    
        assertTrue(repository.existsByCuil("20-22222222-2"));
        repository.deleteById("20-22222222-2");
        assertFalse(repository.existsByCuil("20-22222222-2"));
    }
}
