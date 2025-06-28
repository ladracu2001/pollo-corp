package ar.com.laboratorio.steady.pollo_corp.registro.aplication;

import java.util.List;
import java.util.Optional;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientAlreadyExistsException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientMoreThanOneFoundException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientNotFoundException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientStaleInformationException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalAddressException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalCUILException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalClientException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalEMailException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalPhoneException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;

public class ClientRegistryUseCase implements ClientRepository {

    private final ClientRepository clientRepository;

    public ClientRegistryUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void crearCliente(Client cliente) {
        if(clientRepository.buscarPorCuil(cliente.getCuil()).isPresent()) {
            throw new ClientAlreadyExistsException(String.format("El cliente %s ya existe", cliente.getCuil()));
        }
        try{
            clientRepository.crearCliente(cliente);
        }catch(IllegalClientException error){
            switch (error) {
                case IllegalCUILException e:
                    throw new IllegalCUILException("El CUIL proporcionado es inválido");
                case IllegalAddressException e:
                    throw new IllegalAddressException("La dirección proporcionada es inválida");
                case IllegalEMailException e:
                    throw new IllegalEMailException("El correo electrónico proporcionado es inválido");
                case IllegalPhoneException e:
                    throw new IllegalPhoneException("El número de teléfono proporcionado es inválido");
                default:
                    throw new RuntimeException("Error al crear el cliente: " + error.getMessage(), error);
            }
        }
    }

    @Override
    public void guardarCliente(Client cliente) {
        if (cliente == null || cliente.getCuil() == null) {
            throw new IllegalCUILException("El cliente o su CUIL no pueden ser nulos");
        }
        if (!clientRepository.buscarPorCuil(cliente.getCuil()).isEmpty()) {
            throw new ClientNotFoundException(String.format("El cliente no existe %s", cliente.getCuil()));
        }
        try {
            clientRepository.guardarCliente(cliente);
        }catch(IllegalClientException error){
            switch (error) {
                case IllegalCUILException e:
                    throw new IllegalCUILException("El CUIL proporcionado es inválido");
                case IllegalAddressException e:
                    throw new IllegalAddressException("La dirección proporcionada es inválida");
                case IllegalEMailException e:
                    throw new IllegalEMailException("El correo electrónico proporcionado es inválido");
                case IllegalPhoneException e:
                    throw new IllegalPhoneException("El número de teléfono proporcionado es inválido");
                case ClientStaleInformationException e:
                    throw new ClientStaleInformationException("La información del cliente está desactualizada");
                default:
                    throw new RuntimeException("Error al crear el cliente: " + error.getMessage(), error);
            }
        }
    }

    @Override
    public Optional<List<Client>> buscarClientePorDni(String dni) {
    
        Optional<List<Client>> clientes = clientRepository.buscarClientePorDni(dni);
        if(clientes.isEmpty() || clientes.get().isEmpty()) {
            throw new ClientNotFoundException(String.format("No se encontraron clientes con el DNI %s proporcionado", dni));
        }
        if (clientes.get().size() > 1) {
            throw new ClientMoreThanOneFoundException(String.format("Se encontraron múltiples clientes con el mismo DNI %s", dni));
        }
        return clientes;
    }

    @Override
    public Optional<Client> buscarPorCuil(Cuil cuil) {
        if (cuil == null) {
            throw new IllegalCUILException("El CUIL no puede ser nulo");
        }
        return clientRepository.buscarPorCuil(cuil);
    }

    @Override
    public void eliminarCliente(Cuil cuil) {
        if (cuil == null) {
            throw new IllegalCUILException("El CUIL no puede ser nulo");
        }
        if(!clientRepository.buscarPorCuil(cuil).isPresent()) {
            throw new ClientNotFoundException(String.format("El cliente con el CUIL %s proporcionado no existe", cuil.toString()));
        }
        clientRepository.eliminarCliente(cuil);
    }
}
