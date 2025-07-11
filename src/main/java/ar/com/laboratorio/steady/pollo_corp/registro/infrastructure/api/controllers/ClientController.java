package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientRequestDto;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientResponseDto;

public interface ClientController {

    ResponseEntity<ClientResponseDto> crearCliente(ClientRequestDto cliente);
    ResponseEntity<ClientResponseDto> guardarCliente(ClientRequestDto cliente);
    ResponseEntity<List<ClientResponseDto>> buscarClientePorDni(String dni);
    ResponseEntity<ClientResponseDto> buscarPorCuil(String cuil);
    ResponseEntity<ClientResponseDto> eliminarCliente(String cuil);
    ResponseEntity<List<ClientResponseDto>> getAll();
}
