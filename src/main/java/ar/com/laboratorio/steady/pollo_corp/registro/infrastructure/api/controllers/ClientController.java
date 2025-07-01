package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.laboratorio.steady.pollo_corp.registro.aplication.ClientRegistryUseCase;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientResponseDto;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/clients")
public class ClientController {
    // Aquí puedes definir los endpoints para manejar las operaciones de clientes
    // Por ejemplo, crear, actualizar, eliminar y obtener clientes

    // Ejemplo de un endpoint para obtener todos los clientes
    // @GetMapping
    // public List<ClientResponseDto> getAllClients() {
    //     return clientService.getAllClients();
    // }

    // Otros métodos para manejar las operaciones de clientes irían aquí
    private final ClientRegistryUseCase clientRegistryUseCase;

    public ClientController(ClientRegistryUseCase clientRegistryUseCase) {
        this.clientRegistryUseCase = clientRegistryUseCase;
    }
    @GetMapping("/cuil")
    public ResponseEntity<ClientResponseDto> getByCuil(@PathVariable String cuil) {
        Optional<Client> clientOpt = clientRegistryUseCase.buscarPorCuil(new Cuil(cuil));
        return clientOpt
                .map(client -> ResponseEntity.ok(toDto(client)))
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{cuil}")
    public ResponseEntity<Void> deleteByCuil(@PathVariable String cuil) {
        clientRegistryUseCase.eliminarCliente(new Cuil(cuil));
        return ResponseEntity.noContent().build();
    }
     // --- Mapper manual simple (puedes usar MapStruct si prefieres) ---
    private ClientResponseDto toDto(Client client) {
        ClientResponseDto dto = new ClientResponseDto();
        dto.cuil = client.getCuil() != null ? client.getCuil().toString() : null;
        dto.dni = client.getDni();
        dto.name = client.getName();
        dto.surname = client.getSurname();
        dto.lastName = client.getLastName();
        dto.birthDate = client.getBirthDate() != null ? client.getBirthDate().toString() : null;
        dto.email = client.getEmail() != null ? client.getEmail().toString() : null;
        dto.phoneNumber = client.getPhoneNumber() != null ? client.getPhoneNumber().toString() : null;
        dto.address = client.getAddress() != null ? client.getAddress().toString() : null;
        return dto;
    }
}
