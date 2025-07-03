package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.adapters.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientAlreadyExistsException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientNotFoundException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalCUILException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
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
        return Optional.of(entities.stream().map(clientMapper::toDomain).toList());
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
    @Override
    public List<Client> findAll() {
     return springDataClientRepository.findAll()
                .stream()
                .map(clientMapper::toDomain)
                .toList();
    }
}
