package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.laboratorio.steady.pollo_corp.registro.aplication.ClientRegistryUseCase;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientRequestDto;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientResponseDto;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/clients")
public class ClientControllerImpl implements ClientController {

    private final ClientRegistryUseCase clientRegistryUseCase;

    public ClientControllerImpl(ClientRegistryUseCase clientRegistryUseCase) {
        this.clientRegistryUseCase = clientRegistryUseCase;
    }

    @GetMapping("/cuil/{cuil}")
    public ResponseEntity<ClientResponseDto> getByCuil(@PathVariable String cuil) {
        Optional<Client> clientOpt = clientRegistryUseCase.buscarPorCuil(new Cuil(cuil));
        return clientOpt
                .map(client -> ResponseEntity.ok(toDto(client)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/cuil/{cuil}")
    public ResponseEntity<Void> deleteByCuil(@PathVariable String cuil) {
        clientRegistryUseCase.eliminarCliente(new Cuil(cuil));
        return ResponseEntity.noContent().build();
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
    public ResponseEntity<ClientResponseDto> crearCliente(ClientRequestDto cliente) {
        //this.clientRegistryUseCase.crearCliente(cliente.toDomain());
        throw new UnsupportedOperationException("Unimplemented method 'crearCliente'");
    }
    @Override
    public ResponseEntity<ClientResponseDto> guardarCliente(ClientRequestDto cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardarCliente'");
    }
    @Override
    public ResponseEntity<ClientResponseDto> buscarClientePorDni(String dni) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarClientePorDni'");
    }
    @Override
    public ResponseEntity<ClientResponseDto> buscarPorCuil(String cuil) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorCuil'");
    }
    @Override
    public ResponseEntity<ClientResponseDto> eliminarCliente(String cuil) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarCliente'");
    }
}
