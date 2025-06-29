package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Address;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.EMail;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Phone;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.entities.ClientEntity;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final SpringDataClientRepository springDataClientRepository;
    // Implement the methods defined in the ClientRepository interface
    // This class will interact with the database using Spring Data JPA or any other ORM framework
    public ClientRepositoryImpl(SpringDataClientRepository springDataClientRepository) {
        this.springDataClientRepository = springDataClientRepository;
    }

    @Override
    public void crearCliente(Client client) {
        // Validaciones y mapeo de Client a ClientEntity
        // Lanza excepciones de dominio según corresponda
        // Ejemplo:
        // if (cliente.getCuil() == null) throw new IllegalCUILException(...);
        // ...
        // springDataClientRepository.save(clientEntity);
    }

    @Override
    public void guardarCliente(Client client) {
        // Similar a crearCliente, pero actualiza un cliente existente
    }
    @Override
    public Optional<List<Client>> buscarClientePorDni(String dni) {
        List<ClientEntity> entities = springDataClientRepository.findByDni(dni);
        // Mapear ClientEntity a Client
        // ...
        return Optional.of(entities.stream().map(this::toDomain).collect(Collectors.toList()));
    }
    @Override
    public Optional<Client> buscarPorCuil(Cuil cuil) {
       return springDataClientRepository.findByCuil(cuil.toString())
                .map(this::toDomain);
    }
    @Override
    public void eliminarCliente(Cuil cuil){
          // Buscar y eliminar el cliente, lanzar excepciones si corresponde
    }
    // Métodos de mapeo entre ClientEntity y Client
    private Client toDomain(ClientEntity entity) {
        // Email: separar en usuario y dominio
        EMail email = null;
        if (entity.getEmail() != null && entity.getEmail().contains("@")) {
            String[] parts = entity.getEmail().split("@", 2);
            email = new EMail(parts[0], parts[1]);
        }
        // Phone: separar en codPais-codCiudad-numAbonado (ejemplo: "+54-11-12345678")
        Phone phone = null;
        if (entity.getPhoneNumber() != null) {
            String[] parts = entity.getPhoneNumber().split("-", 3);
            String codPais = parts.length > 0 ? parts[0] : null;
            String codCiudad = parts.length > 1 ? parts[1] : null;
            String numAbonado = parts.length > 2 ? parts[2] : null;
            phone = new Phone(codPais, codCiudad, numAbonado);
        }
        // Address: separar en direccion, numero, ciudad, provincia, codigoPostal
        Address address = null;
        if (entity.getAddress() != null) {
            String[] parts = entity.getAddress().split(",", 5);
            String direccion = parts.length > 0 ? parts[0].trim() : null;
            String numero = parts.length > 1 ? parts[1].trim() : null;
            String ciudad = parts.length > 2 ? parts[2].trim() : null;
            String provincia = parts.length > 3 ? parts[3].trim() : null;
            String codigoPostal = parts.length > 4 ? parts[4].trim() : null;
            address = new Address(direccion, numero, ciudad, provincia, codigoPostal);
        }
        return new Client(
            entity.getDni(),
            new Cuil(entity.getCuil() != null ? entity.getCuil() : null),
            entity.getName(),
            entity.getSurname(),
            entity.getLastName(),
            entity.getBirthDate(),
            email,
            phone,
            address
        );
    }
}
