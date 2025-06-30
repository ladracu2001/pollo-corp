package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientAlreadyExistsException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientNotFoundException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalCUILException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Address;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.EMail;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Phone;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.entities.ClientEntity;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.mapper.ClientMapper;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final SpringDataClientRepository springDataClientRepository;
    private final ClientMapper clientMapper;
    // Implement the methods defined in the ClientRepository interface
    // This class will interact with the database using Spring Data JPA or any other ORM framework
    public ClientRepositoryImpl(SpringDataClientRepository springDataClientRepository, ClientMapper clientMapper) {
        this.springDataClientRepository = springDataClientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public void crearCliente(Client client) {
        if (client == null) throw new IllegalArgumentException("El cliente no puede ser nulo");
        if (client.getCuil() == null) throw new IllegalCUILException("El CUIL no puede ser nulo");
        if (springDataClientRepository.existsByCuil(client.getCuil().toString())) {
            throw new ClientAlreadyExistsException("Ya existe un cliente con ese CUIL");
        }
        ClientEntity entity = clientMapper.toEntity(client);
        springDataClientRepository.save(entity);
    }

    @Override
    public void guardarCliente(Client client) {
        if (client == null) throw new IllegalArgumentException("El cliente no puede ser nulo");
        if (client.getCuil() == null) throw new IllegalCUILException("El CUIL no puede ser nulo");
        if (!springDataClientRepository.existsByCuil(client.getCuil().toString())) {
            throw new ClientNotFoundException("No existe un cliente con ese CUIL");
        }
        ClientEntity entity = clientMapper.toEntity(client);
        springDataClientRepository.save(entity);
    }
    @Override
    public Optional<List<Client>> buscarClientePorDni(String dni) {
        List<ClientEntity> entities = springDataClientRepository.findByDni(dni);
        // Mapear ClientEntity a Client
        // ...
        return Optional.of(entities.stream().map(clientMapper::toDomain).collect(Collectors.toList()));
    }
    @Override
    public Optional<Client> buscarPorCuil(Cuil cuil) {
       return springDataClientRepository.findByCuil(cuil.toString())
                .map(clientMapper::toDomain);
    }
    @Override
    public void eliminarCliente(Cuil cuil){
        if (cuil == null) throw new IllegalCUILException("El CUIL no puede ser nulo");
        Optional<ClientEntity> entityOpt = springDataClientRepository.findByCuil(cuil.toString());
        if (entityOpt.isEmpty()) {
            throw new ClientNotFoundException("No existe un cliente con ese CUIL");
        }
        springDataClientRepository.delete(entityOpt.get());
    }
    /*
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
            new Cuil(entity.getCuil() != null ? entity.getCuil() : null),
            entity.getDni(),
            entity.getName(),
            entity.getSurname(),
            entity.getLastName(),
            entity.getBirthDate(),
            email,
            phone,
            address
        );
    }
    
    private ClientEntity toEntity(Client client) {
    String email = client.getEmail() != null ? client.getEmail().toString() : null;
    String phone = client.getPhoneNumber() != null
        ? String.format("%s-%s-%s", client.getPhoneNumber().codPais(), client.getPhoneNumber().codCiudad(), client.getPhoneNumber().numAbonado())
        : null;
    String address = client.getAddress() != null
        ? String.format("%s, %s, %s, %s, %s",
            client.getAddress().direccion(),
            client.getAddress().numero(),
            client.getAddress().ciudad(),
            client.getAddress().provincia(),
            client.getAddress().codigoPostal())
        : null;
    return new ClientEntity(
        client.getCuil() != null ? client.getCuil().toString() : null,
        client.getDni(),
        client.getName(),
        client.getSurname(),
        client.getLastName(),
        client.getBirthDate(),
        email,
        phone,
        address
    );
    }*/
}
