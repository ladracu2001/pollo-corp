package ar.com.laboratorio.steady.pollo_corp.registro.aplication;

import java.util.List;
import java.util.Optional;

import ar.com.laboratorio.steady.pollo_corp.registro.common.Constants;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientAlreadyExistsException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientMoreThanOneFoundException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientNotFoundException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientStaleInformationException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientStatusException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.GeneralException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalAddressException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalClientException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalCUILException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalEMailException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalPhoneException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientRepository;

public class ClientRegistryUseCase{

    private final ClientRepository clientRepository;

    public ClientRegistryUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void crearCliente(Client cliente) {
        if(clientRepository.buscarPorCuil(cliente.getCuil()).isPresent()) {
            throw new ClientAlreadyExistsException(String.format(Constants.CLIENT_EXCEPTION_EXIST, cliente.getCuil()));
        }
        try{
            clientRepository.crearCliente(cliente);
        }catch(IllegalClientException error){
            switch (error) {
                case IllegalCUILException e:
                    throw new IllegalCUILException(String.format(Constants.CUIL_EXCEPTION_INVALID, cliente.getCuil()));
                case IllegalAddressException e:
                    throw new IllegalAddressException(String.format(Constants.ADDRESS_EXCEPTION_INVALID, cliente.getAddress()));
                case IllegalEMailException e:
                    throw new IllegalEMailException(String.format(Constants.EMAIL_EXCEPTION_INVALID, cliente.getEmail()));
                case IllegalPhoneException e:
                    throw new IllegalPhoneException(String.format(Constants.PHONE_EXCEPTION_INVALID, cliente.getPhoneNumber()));
                default:
                    throw new GeneralException(Constants.CLIENT_EXCEPTION + error.getMessage(), error);
            }
        }
    }

    public void guardarCliente(Client cliente) {
        if (cliente == null || cliente.getCuil() == null) {
            throw new IllegalCUILException(Constants.CUIL_EXCEPTION_NULL);
        }
        if (!clientRepository.buscarPorCuil(cliente.getCuil()).isEmpty()) {
            throw new ClientNotFoundException(String.format(Constants.CLIENT_EXCEPTION_NOTEXIST, cliente.getCuil()));
        }
        try {
            clientRepository.guardarCliente(cliente);
        }catch(IllegalClientException | ClientStatusException error){
            switch (error) {
                case IllegalCUILException e:
                    throw new IllegalCUILException(String.format(Constants.CUIL_EXCEPTION_INVALID, cliente.getCuil()));
                case IllegalAddressException e:
                    throw new IllegalAddressException(String.format(Constants.ADDRESS_EXCEPTION_INVALID, cliente.getAddress()));
                case IllegalEMailException e:
                    throw new IllegalEMailException(String.format(Constants.EMAIL_EXCEPTION_INVALID, cliente.getEmail()));
                case IllegalPhoneException e:
                    throw new IllegalPhoneException(String.format(Constants.PHONE_EXCEPTION_INVALID, cliente.getPhoneNumber()));
                case ClientStaleInformationException e:
                    throw new ClientStaleInformationException(Constants.CLIENT_EXCEPTION_STALE);
                default:
                    throw new GeneralException(Constants.CLIENT_EXCEPTION + error.getMessage(), error);
            }
        }
    }

    public Optional<List<Client>> buscarClientePorDni(String dni) {
    
        Optional<List<Client>> clientes = clientRepository.buscarClientePorDni(dni);
        if(clientes.isEmpty() || clientes.get().isEmpty()) {
            throw new ClientNotFoundException(String.format(Constants.CLIENT_EXCEPTION_NOTEXIST, dni));
        }
        if (clientes.get().size() > 1) {
            throw new ClientMoreThanOneFoundException(String.format(Constants.CLIENT_EXCEPTION_MULTIEXIST, dni));
        }
        return clientes;
    }

    public Optional<Client> buscarPorCuil(Cuil cuil) {
        if (cuil == null) {
            throw new IllegalCUILException(String.format(Constants.CUIL_EXCEPTION_NULL, cuil));
        }
        return clientRepository.buscarPorCuil(cuil);
    }

    public void eliminarCliente(Cuil cuil) {
        if (cuil == null) {
            throw new IllegalCUILException(String.format(Constants.CUIL_EXCEPTION_NULL, cuil));
        }
        if(!clientRepository.buscarPorCuil(cuil).isPresent()) {
            throw new ClientNotFoundException(String.format(Constants.CUIL_EXCEPTION_NOTFOUND, cuil.toString()));
        }
        clientRepository.eliminarCliente(cuil);
    }
    public List<Client> obtenerTodosLosClientes() {
        return clientRepository.findAll(); // O el método correspondiente en tu puerto
    }
}