package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.repository;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientAlreadyExistsException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientNotFoundException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalCUILException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Address;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.EMail;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Phone;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.entities.ClientEntity;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.mapper.ClientMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientRepositoryImplTest {

    private SpringDataClientRepository springDataClientRepository;
    private ClientMapper clientMapper;
    private ClientRepositoryImpl clientRepository;

    @BeforeEach
    void setUp() {
        springDataClientRepository = mock(SpringDataClientRepository.class);
        clientMapper = mock(ClientMapper.class);
        clientRepository = new ClientRepositoryImpl(springDataClientRepository, clientMapper);
         // Stubs generales para la mayoría de los tests
    when(clientMapper.toEntity(any(Client.class))).thenReturn(buildEntity());
    when(clientMapper.toDomain(any(ClientEntity.class))).thenReturn(buildClient());
    }

    @Test
    void testCrearClienteOk() {
        Client client = buildClient();
        when(springDataClientRepository.existsByCuil(client.getCuil().toString())).thenReturn(false);

        clientRepository.crearCliente(client);

        ArgumentCaptor<ClientEntity> captor = ArgumentCaptor.forClass(ClientEntity.class);
        verify(springDataClientRepository).save(captor.capture());
        assertEquals(client.getDni(), captor.getValue().getDni());
    }

    @Test
    void testCrearClienteYaExiste() {
        Client client = buildClient();
        when(springDataClientRepository.existsByCuil(client.getCuil().toString())).thenReturn(true);

        assertThrows(ClientAlreadyExistsException.class, () -> clientRepository.crearCliente(client));
    }

    @Test
    void testGuardarClienteOk() {
        Client client = buildClient();
        when(springDataClientRepository.existsByCuil(client.getCuil().toString())).thenReturn(true);

        clientRepository.guardarCliente(client);

        verify(springDataClientRepository).save(any(ClientEntity.class));
    }

    @Test
    void testGuardarClienteNoExiste() {
        Client client = buildClient();
        when(springDataClientRepository.existsByCuil(client.getCuil().toString())).thenReturn(false);

        assertThrows(ClientNotFoundException.class, () -> clientRepository.guardarCliente(client));
    }

    @Test
    void testBuscarClientePorDni() {
        ClientEntity entity = buildEntity();
        when(springDataClientRepository.findByDni("12345678")).thenReturn(List.of(entity));

        Optional<List<Client>> result = clientRepository.buscarClientePorDni("12345678");

        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
        assertEquals("12345678", result.get().get(0).getDni());
    }

    @Test
    void testBuscarPorCuil() {
        ClientEntity entity = buildEntity();
        when(springDataClientRepository.findByCuil("20-12345678-3")).thenReturn(Optional.of(entity));

        Optional<Client> result = clientRepository.buscarPorCuil(new Cuil("20-12345678-3"));

        assertTrue(result.isPresent());
        assertEquals("12345678", result.get().getDni());
    }

    @Test
    void testEliminarClienteOk() {
        ClientEntity entity = buildEntity();
        when(springDataClientRepository.findByCuil("20-12345678-3")).thenReturn(Optional.of(entity));

        clientRepository.eliminarCliente(new Cuil("20-12345678-3"));

        verify(springDataClientRepository).delete(entity);
    }

    @Test
    void testEliminarClienteNoExiste() {
        when(springDataClientRepository.findByCuil("20-00000000-0")).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientRepository.eliminarCliente(new Cuil("20-00000000-0")));
    }

    private Client buildClient() {
        return new Client(
            new Cuil("20-12345678-3"),
                "12345678",
                "Juan",
                "Pérez",
                "Gómez",
                LocalDate.of(1990, 1, 1),
                new EMail("juan.perez", "mail.com"),
                new Phone("+54", "11", "12345678"),
                new Address("Calle Falsa", "123", "Ciudad", "Provincia", "1000")
        );
    }

    private ClientEntity buildEntity() {
        return new ClientEntity(
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
    }
}
