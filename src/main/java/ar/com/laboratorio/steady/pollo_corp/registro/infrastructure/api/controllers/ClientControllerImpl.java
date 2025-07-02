package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.laboratorio.steady.pollo_corp.registro.aplication.ClientRegistryUseCase;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientRequestDto;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientResponseDto;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.mapper.ClientDtoMapper;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
     // --- Mapper manual simple (puedes usar MapStruct si prefieres) ---
    private ClientResponseDto toDto(Client client) {
        ClientResponseDto dto = new ClientResponseDto();
        dto.setCuil(client.getCuil() != null ? client.getCuil().toString() : null);
        dto.setDni(client.getDni());
        dto.setName(client.getName());
        dto.setSurname(client.getSurname());
        dto.setLastName(client.getLastName());
        dto.setBirthDate(client.getBirthDate() != null ? client.getBirthDate().toString() : null);
        dto.setEmail(client.getEmail() != null ? client.getEmail().toString() : null);
        dto.setPhoneNumber(client.getPhoneNumber() != null ? client.getPhoneNumber().toString() : null);
        dto.setAddress(client.getAddress() != null ? client.getAddress().toString() : null);
        return dto;
    }
    @Override
    @PostMapping("/cliente/{cliente}")
    public ResponseEntity<ClientResponseDto> crearCliente(ClientRequestDto cliente) {
        this.clientRegistryUseCase.crearCliente(this.clientDtoMapper.toDomain(cliente));
        return ResponseEntity.ok(toDto(this.clientDtoMapper.toDomain(cliente)));
    }
    @Override
    @PatchMapping("/cliente/{cliente}")
    public ResponseEntity<ClientResponseDto> guardarCliente(ClientRequestDto cliente) {
        this.clientRegistryUseCase.crearCliente(this.clientDtoMapper.toDomain(cliente));
        return ResponseEntity.ok(toDto(this.clientDtoMapper.toDomain(cliente)));
    }
    @Override
    @GetMapping("/dni/{dni}")
    public ResponseEntity<ClientResponseDto> buscarClientePorDni(String dni) {
        Optional<List<Client>> clientOpt = clientRegistryUseCase.buscarClientePorDni(dni);
        return clientOpt
                .map(clients -> ResponseEntity.ok(toDto(clients.get(0)))) // Asumiendo que solo se devuelve un cliente
                .orElse(ResponseEntity.notFound().build());
    }
    @Override
    @GetMapping("/cuil/{cuil}")
    public ResponseEntity<ClientResponseDto> buscarPorCuil(String cuil) {
        
        Optional<Client> clientOpt = clientRegistryUseCase.buscarPorCuil(new Cuil(cuil));
        return clientOpt
                .map(client -> ResponseEntity.ok(toDto(client)))
                .orElse(ResponseEntity.notFound().build());
    }
    @Override
    @DeleteMapping("/cuil/{cuil}")
    public ResponseEntity<ClientResponseDto> eliminarCliente(String cuil) {
        clientRegistryUseCase.eliminarCliente(new Cuil(cuil));
        return ResponseEntity.noContent().build();
    }
}
