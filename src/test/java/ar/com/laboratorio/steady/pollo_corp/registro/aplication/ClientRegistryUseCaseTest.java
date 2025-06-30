package ar.com.laboratorio.steady.pollo_corp.registro.aplication;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientRegistryUseCaseTest {

    private ClientRepository clientRepository;
    private ClientRegistryUseCase useCase;

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        useCase = new ClientRegistryUseCase(clientRepository);
    }

    @Test
    void crearCliente_lanzaExcepcionSiYaExiste() {
        Client client = mock(Client.class);
        Cuil cuil = new Cuil("20-12345678-3");
        when(client.getCuil()).thenReturn(cuil);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.of(client));

        assertThrows(ClientAlreadyExistsException.class, () -> useCase.crearCliente(client));
    }

    @Test
    void crearCliente_ok() {
        Client client = mock(Client.class);
        Cuil cuil = new Cuil("20-12345678-3");
        when(client.getCuil()).thenReturn(cuil);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.empty());

        useCase.crearCliente(client);

        verify(clientRepository).crearCliente(client);
    }

    @Test
    void buscarPorCuil_lanzaExcepcionSiCuilEsNull() {
        assertThrows(IllegalCUILException.class, () -> useCase.buscarPorCuil(null));
    }

    @Test
    void buscarPorCuil_ok() {
        Cuil cuil = new Cuil("20-12345678-3");
        Client client = mock(Client.class);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.of(client));

        Optional<Client> result = useCase.buscarPorCuil(cuil);

        assertTrue(result.isPresent());
        assertEquals(client, result.get());
    }

    @Test
    void eliminarCliente_lanzaExcepcionSiNoExiste() {
        Cuil cuil = new Cuil("20-12345678-3");
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> useCase.eliminarCliente(cuil));
    }

    @Test
    void eliminarCliente_ok() {
        Cuil cuil = new Cuil("20-12345678-3");
        Client client = mock(Client.class);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.of(client));

        useCase.eliminarCliente(cuil);

        verify(clientRepository).eliminarCliente(cuil);
    }

    @Test
    void buscarClientePorDni_lanzaExcepcionSiNoHayClientes() {
        when(clientRepository.buscarClientePorDni("12345678")).thenReturn(Optional.of(List.of()));

        assertThrows(ClientNotFoundException.class, () -> useCase.buscarClientePorDni("12345678"));
    }

    @Test
    void buscarClientePorDni_lanzaExcepcionSiHayMasDeUno() {
        Client c1 = mock(Client.class);
        Client c2 = mock(Client.class);
        when(clientRepository.buscarClientePorDni("12345678")).thenReturn(Optional.of(List.of(c1, c2)));

        assertThrows(ClientMoreThanOneFoundException.class, () -> useCase.buscarClientePorDni("12345678"));
    }

    @Test
    void buscarClientePorDni_ok() {
        Client c1 = mock(Client.class);
        when(clientRepository.buscarClientePorDni("12345678")).thenReturn(Optional.of(List.of(c1)));

        Optional<List<Client>> result = useCase.buscarClientePorDni("12345678");

        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
        assertEquals(c1, result.get().get(0));
    }
}
