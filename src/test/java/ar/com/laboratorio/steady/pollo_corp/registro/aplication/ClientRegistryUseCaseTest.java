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

    @Test
    void guardarCliente_lanzaExcepcionSiClienteEsNull() {
        assertThrows(IllegalCUILException.class, () -> useCase.guardarCliente(null));
    }

    @Test
    void guardarCliente_lanzaExcepcionSiCuilEsNull() {
        Client client = mock(Client.class);
        when(client.getCuil()).thenReturn(null);
        assertThrows(IllegalCUILException.class, () -> useCase.guardarCliente(client));
    }

    @Test
    void guardarCliente_lanzaExcepcionSiNoExiste() {
        Client client = mock(Client.class);
        Cuil cuil = new Cuil("20-12345678-3");
        when(client.getCuil()).thenReturn(cuil);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.of(client));
        assertThrows(ClientNotFoundException.class, () -> useCase.guardarCliente(client));
    }

    @Test
    void guardarCliente_ok() {
        Client client = mock(Client.class);
        Cuil cuil = new Cuil("20-12345678-3");
        when(client.getCuil()).thenReturn(cuil);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.empty());
        useCase.guardarCliente(client);
        verify(clientRepository).guardarCliente(client);
    }

    @Test
    void obtenerTodosLosClientes_ok() {
        Client c1 = mock(Client.class);
        Client c2 = mock(Client.class);
        when(clientRepository.findAll()).thenReturn(List.of(c1, c2));
        List<Client> result = useCase.obtenerTodosLosClientes();
        assertEquals(2, result.size());
        assertTrue(result.contains(c1));
        assertTrue(result.contains(c2));
    }

    @Test
    void crearCliente_lanzaIllegalCUILException() {
        Client client = mock(Client.class);
        Cuil cuil = new Cuil("20-12345678-3");
        when(client.getCuil()).thenReturn(cuil);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.empty());
        doThrow(new IllegalCUILException("CUIL inválido")).when(clientRepository).crearCliente(client);

        assertThrows(IllegalCUILException.class, () -> useCase.crearCliente(client));
    }

    @Test
    void crearCliente_lanzaIllegalAddressException() {
        Client client = mock(Client.class);
        Cuil cuil = new Cuil("20-12345678-3");
        when(client.getCuil()).thenReturn(cuil);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.empty());
        doThrow(new IllegalAddressException("Dirección inválida")).when(clientRepository).crearCliente(client);

        assertThrows(IllegalAddressException.class, () -> useCase.crearCliente(client));
    }

    @Test
    void crearCliente_lanzaIllegalEMailException() {
        Client client = mock(Client.class);
        Cuil cuil = new Cuil("20-12345678-3");
        when(client.getCuil()).thenReturn(cuil);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.empty());
        doThrow(new IllegalEMailException("Email inválido")).when(clientRepository).crearCliente(client);

        assertThrows(IllegalEMailException.class, () -> useCase.crearCliente(client));
    }

    @Test
    void crearCliente_lanzaIllegalPhoneException() {
        Client client = mock(Client.class);
        Cuil cuil = new Cuil("20-12345678-3");
        when(client.getCuil()).thenReturn(cuil);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.empty());
        doThrow(new IllegalPhoneException("Teléfono inválido")).when(clientRepository).crearCliente(client);

        assertThrows(IllegalPhoneException.class, () -> useCase.crearCliente(client));
    }

    @Test
    void crearCliente_lanzaGeneralException() {
        Client client = mock(Client.class);
        Cuil cuil = new Cuil("20-12345678-3");
        when(client.getCuil()).thenReturn(cuil);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.empty());
        doThrow(new IllegalClientException("Otro error")).when(clientRepository).crearCliente(client);

        assertThrows(GeneralException.class, () -> useCase.crearCliente(client));
    }

    @Test
    void guardarCliente_lanzaIllegalCUILExceptionPorRepositorio() {
        Client client = mock(Client.class);
        Cuil cuil = new Cuil("20-12345678-3");
        when(client.getCuil()).thenReturn(cuil);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.empty());
        doThrow(new IllegalCUILException("CUIL inválido")).when(clientRepository).guardarCliente(client);

        assertThrows(IllegalCUILException.class, () -> useCase.guardarCliente(client));
    }

    @Test
    void guardarCliente_lanzaIllegalAddressExceptionPorRepositorio() {
        Client client = mock(Client.class);
        Cuil cuil = new Cuil("20-12345678-3");
        when(client.getCuil()).thenReturn(cuil);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.empty());
        doThrow(new IllegalAddressException("Dirección inválida")).when(clientRepository).guardarCliente(client);

        assertThrows(IllegalAddressException.class, () -> useCase.guardarCliente(client));
    }

    @Test
    void guardarCliente_lanzaIllegalEMailExceptionPorRepositorio() {
        Client client = mock(Client.class);
        Cuil cuil = new Cuil("20-12345678-3");
        when(client.getCuil()).thenReturn(cuil);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.empty());
        doThrow(new IllegalEMailException("Email inválido")).when(clientRepository).guardarCliente(client);

        assertThrows(IllegalEMailException.class, () -> useCase.guardarCliente(client));
    }

    @Test
    void guardarCliente_lanzaIllegalPhoneExceptionPorRepositorio() {
        Client client = mock(Client.class);
        Cuil cuil = new Cuil("20-12345678-3");
        when(client.getCuil()).thenReturn(cuil);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.empty());
        doThrow(new IllegalPhoneException("Teléfono inválido")).when(clientRepository).guardarCliente(client);

        assertThrows(IllegalPhoneException.class, () -> useCase.guardarCliente(client));
    }

    @Test
    void guardarCliente_lanzaGeneralExceptionPorRepositorio() {
        Client client = mock(Client.class);
        Cuil cuil = new Cuil("20-12345678-3");
        when(client.getCuil()).thenReturn(cuil);
        when(clientRepository.buscarPorCuil(cuil)).thenReturn(Optional.empty());
        doThrow(new IllegalClientException("Otro error")).when(clientRepository).guardarCliente(client);

        assertThrows(GeneralException.class, () -> useCase.guardarCliente(client));
    }
}
