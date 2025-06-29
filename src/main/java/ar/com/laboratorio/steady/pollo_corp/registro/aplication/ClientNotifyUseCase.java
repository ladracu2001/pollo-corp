package ar.com.laboratorio.steady.pollo_corp.registro.aplication;

import java.time.LocalDate;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientNotFoundException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientStaleInformationException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientStatusException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalCUILException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalClientException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalEMailException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalPhoneException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientNotifyRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientStatusRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;

public class ClientNotifyUseCase {

    private final ClientNotifyRepository clientNotifyRepository;
    private final ClientStatusRepository clientStatusRepository;
    private final ClientRepository clientRepository;
    private final int RETIREMENT_AGE = 65;

    public ClientNotifyUseCase(ClientNotifyRepository clientNotifyRepository,
                               ClientStatusRepository clientStatusRepository,
                               ClientRepository clientRepository) {
        this.clientNotifyRepository = clientNotifyRepository;
        this.clientStatusRepository = clientStatusRepository;
        this.clientRepository = clientRepository;
    }
    
    public void notifyByEmail(Client client, String subject, String message) {
        if(client == null || client.getEmail() == null) {
            throw new IllegalEMailException("El cliente o su correo electrónico no pueden ser nulos");
        }
        try {
            clientNotifyRepository.notifyByEmail(client, subject, message);            
        } catch (IllegalClientException error) {
            switch (error) {
                case IllegalEMailException e: throw new IllegalEMailException(String.format("El correo electrónico %s proporcionado es inválido", client.getEmail()) );
                case ClientStaleInformationException e: throw new ClientStaleInformationException(String.format("La información del cliente %c está desactualizada", client) );
                default: throw new RuntimeException("Error al enviar la notificación por correo electrónico: " + error.getMessage(), error);
            }
        }
    }

    public void notifyBySms(Client client, String message) {
        if(client == null || client.getPhoneNumber() == null) {
            throw new IllegalPhoneException("El cliente o su número de teléfono no pueden ser nulos");
        }
        try {
            clientNotifyRepository.notifyBySms(client, message);            
        } catch (IllegalClientException error) {
            switch (error) {
                case IllegalPhoneException e: throw new IllegalPhoneException(String.format("El número de teléfono %s proporcionado es inválido", client.getPhoneNumber()) );
                case ClientStaleInformationException e: throw new ClientStaleInformationException(String.format("La información del cliente %c está desactualizada", client) );
                default: throw new RuntimeException("Error al enviar la notificación por SMS: " + error.getMessage(), error);
            }
        }
    }

    public void notifyByPush(Client client, String message) {
        try {
            clientNotifyRepository.notifyByPush(client, message);            
        } catch (ClientStatusException error) {
            switch (error) {
                case ClientNotFoundException e  : throw new ClientNotFoundException(String.format("El cliente con CUIL %s no fue encontrado", client.getCuil()));
                case ClientStaleInformationException e: throw new ClientStaleInformationException(String.format("La información del cliente %c está desactualizada", client) );                    
                default: throw new RuntimeException("Error al enviar la notificación push: " + error.getMessage(), error);
            }
        }
    }

    public boolean isClientActive(Cuil cuil) {
        if(clientStatusRepository.isValidClient(cuil)) {            
            return clientRepository.buscarPorCuil(cuil).map(client -> 
            { 
                int edad = LocalDate.now().getYear() - client.getBirthDate().getYear();
                return edad < RETIREMENT_AGE;
            }).orElse(false);           
        }
        return false; // El cliente no es activo o no existe
    }

    public boolean isValidClient(Cuil cuil) {
        if(cuil == null) {
            throw new IllegalCUILException(String.format("El CUIL %s no puede ser nulo", cuil));
        }
        if(clientRepository.buscarPorCuil(cuil).isEmpty()) {
            return false; // El cliente no existe
        }
        return true; // El cliente existe y es válido
    }
}