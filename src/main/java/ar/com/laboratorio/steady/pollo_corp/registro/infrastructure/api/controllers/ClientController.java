package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.controllers;

import org.springframework.http.ResponseEntity;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientRequestDto;

public interface ClientController {

    ResponseEntity<?> crearCliente(ClientRequestDto cliente);
    ResponseEntity<?> guardarCliente(ClientRequestDto cliente);
    ResponseEntity<?> buscarClientePorDni(String dni);
    ResponseEntity<?> buscarPorCuil(String cuil);
    ResponseEntity<?> eliminarCliente(String cuil);
}
