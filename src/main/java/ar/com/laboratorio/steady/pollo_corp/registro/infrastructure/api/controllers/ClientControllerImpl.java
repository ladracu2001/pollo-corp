package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.laboratorio.steady.pollo_corp.registro.aplication.ClientRegistryUseCase;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientRequestDto;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientResponseDto;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.mapper.ClientDtoMapper;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/clients")
public class ClientControllerImpl implements ClientController {

    private final ClientRegistryUseCase clientRegistryUseCase;
    private final ClientDtoMapper clientDtoMapper;
    
    public ClientControllerImpl(ClientRegistryUseCase clientRegistryUseCase, ClientDtoMapper clientDtoMapper) {
        this.clientRegistryUseCase = clientRegistryUseCase;
        this.clientDtoMapper = clientDtoMapper;
    }
    @Override
    @GetMapping("/clients")
    public ResponseEntity<List<ClientResponseDto>> getAll() {
        List<Client> clients = this.clientRegistryUseCase.obtenerTodosLosClientes();                
        List<ClientResponseDto> clientDtos = clients.stream()
                .map(this.clientDtoMapper::toDto).toList();
        return ResponseEntity.ok(clientDtos);
    }
    
    @Override
    @PostMapping("/cliente/{cliente}")
    public ResponseEntity<ClientResponseDto> crearCliente(@Valid @PathVariable ClientRequestDto cliente) {
        this.clientRegistryUseCase.crearCliente(this.clientDtoMapper.toDomain(cliente));
        return ResponseEntity.ok(this.clientDtoMapper.toDto(this.clientDtoMapper.toDomain(cliente)));
    }
    @Override
    @PatchMapping("/cliente/{cliente}")
    public ResponseEntity<ClientResponseDto> guardarCliente(@Valid @PathVariable ClientRequestDto cliente) {
        this.clientRegistryUseCase.crearCliente(this.clientDtoMapper.toDomain(cliente));
        return ResponseEntity.ok(this.clientDtoMapper.toDto(this.clientDtoMapper.toDomain(cliente)));
    }
    @Override
    @GetMapping("/dni/{dni}")
    public ResponseEntity<List<ClientResponseDto>> buscarClientePorDni(@Valid @PathVariable String dni) {
        Optional<List<Client>> clientOpt = clientRegistryUseCase.buscarClientePorDni(dni);
        if (clientOpt.isPresent() && !clientOpt.get().isEmpty()) {
            List<ClientResponseDto> dtos = clientOpt.get().stream()
                .map(this.clientDtoMapper::toDto)
                .toList();
            return ResponseEntity.ok(dtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Override
    @GetMapping("/cuil/{cuil}")
    public ResponseEntity<ClientResponseDto> buscarPorCuil(@Valid @PathVariable String cuil) {
        
        Optional<Client> clientOpt = clientRegistryUseCase.buscarPorCuil(new Cuil(cuil));
        return clientOpt
                .map(client -> ResponseEntity.ok(this.clientDtoMapper.toDto(client)))
                .orElse(ResponseEntity.notFound().build());
    }
    @Override
    @DeleteMapping("/cuil/{cuil}")
    public ResponseEntity<ClientResponseDto> eliminarCliente(@Valid @PathVariable String cuil) {
        clientRegistryUseCase.eliminarCliente(new Cuil(cuil));
        return ResponseEntity.noContent().build();
    }
}
