package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.controllers;

import ar.com.laboratorio.steady.pollo_corp.registro.aplication.ClientRegistryUseCase;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientRequestDto;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientResponseDto;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.mapper.ClientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientControllerImplTest {

    private ClientRegistryUseCase useCase;
    private ClientMapper mapper;
    private ClientControllerImpl controller;

    @BeforeEach
    void setUp() {
        useCase = mock(ClientRegistryUseCase.class);
        mapper = mock(ClientMapper.class);
        controller = new ClientControllerImpl(useCase, mapper);
    }

    @Test
    void getAll_returnsList() {
        Client client = mock(Client.class);
        ClientResponseDto dto = mock(ClientResponseDto.class);
        when(useCase.obtenerTodosLosClientes()).thenReturn(List.of(client));
        when(mapper.fromDto(client)).thenReturn(dto);

        ResponseEntity<List<ClientResponseDto>> response = controller.getAll();

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody() != null ? response.getBody().size() : 0);
        assertSame(dto, response.getBody() != null ? response.getBody().get(0) : null);
    }

    @Test
    void crearCliente_returnsCreatedClient() {
        ClientRequestDto req = mock(ClientRequestDto.class);
        Client client = mock(Client.class);
        ClientResponseDto dto = mock(ClientResponseDto.class);

        when(mapper.toDto(req)).thenReturn(client);
        when(mapper.fromDto(client)).thenReturn(dto);

        ResponseEntity<ClientResponseDto> response = controller.crearCliente(req);

        verify(useCase).crearCliente(client);
        assertEquals(200, response.getStatusCode().value());
        assertSame(dto, response.getBody());
    }

    @Test
    void guardarCliente_returnsUpdatedClient() {
        ClientRequestDto req = mock(ClientRequestDto.class);
        Client client = mock(Client.class);
        ClientResponseDto dto = mock(ClientResponseDto.class);

        when(mapper.toDto(req)).thenReturn(client);
        when(mapper.fromDto(client)).thenReturn(dto);

        ResponseEntity<ClientResponseDto> response = controller.guardarCliente(req);

        verify(useCase).crearCliente(client);
        assertEquals(200, response.getStatusCode().value());
        assertSame(dto, response.getBody());
    }

    @Test
    void buscarClientePorDni_found() {
        String dni = "12345678";
        Client client = mock(Client.class);
        ClientResponseDto dto = mock(ClientResponseDto.class);

        when(useCase.buscarClientePorDni(dni)).thenReturn(Optional.of(List.of(client)));
        when(mapper.fromDto(client)).thenReturn(dto);

        ResponseEntity<List<ClientResponseDto>> response = controller.buscarClientePorDni(dni);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertSame(dto, response.getBody().get(0));
    }

    @Test
    void buscarClientePorDni_notFound() {
        String dni = "12345678";
        when(useCase.buscarClientePorDni(dni)).thenReturn(Optional.empty());

        ResponseEntity<List<ClientResponseDto>> response = controller.buscarClientePorDni(dni);

        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void buscarPorCuil_found() {
        String cuilStr = "20-12345678-3";
        Cuil cuil = new Cuil(cuilStr);
        Client client = mock(Client.class);
        ClientResponseDto dto = mock(ClientResponseDto.class);

        when(useCase.buscarPorCuil(cuil)).thenReturn(Optional.of(client));
        when(mapper.fromDto(client)).thenReturn(dto);

        ResponseEntity<ClientResponseDto> response = controller.buscarPorCuil(cuilStr);

        assertEquals(200, response.getStatusCodeValue());
        assertSame(dto, response.getBody());
    }

    @Test
    void buscarPorCuil_notFound() {
        String cuilStr = "20-12345678-3";
        Cuil cuil = new Cuil(cuilStr);

        when(useCase.buscarPorCuil(cuil)).thenReturn(Optional.empty());

        ResponseEntity<ClientResponseDto> response = controller.buscarPorCuil(cuilStr);

        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void eliminarCliente_returnsNoContent() {
        String cuilStr = "20-12345678-3";
        ResponseEntity<ClientResponseDto> response = controller.eliminarCliente(cuilStr);

        verify(useCase).eliminarCliente(new Cuil(cuilStr));
        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
    }
}
