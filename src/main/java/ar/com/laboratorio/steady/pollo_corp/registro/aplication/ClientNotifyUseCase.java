package ar.com.laboratorio.steady.pollo_corp.registro.aplication;

import java.time.LocalDate;

import ar.com.laboratorio.steady.pollo_corp.registro.common.Constants;
// Import missing types or define them if not present
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.*;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientNotifyRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientStatusRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;

public class ClientNotifyUseCase {

    private final ClientNotifyRepository clientNotifyRepository;
    private final ClientStatusRepository clientStatusRepository;
    private final ClientRepository clientRepository;
    private static final int RETIREMENT_AGE = 65;

    public ClientNotifyUseCase(ClientNotifyRepository clientNotifyRepository,
                               ClientStatusRepository clientStatusRepository,
                               ClientRepository clientRepository) {
        this.clientNotifyRepository = clientNotifyRepository;
        this.clientStatusRepository = clientStatusRepository;
        this.clientRepository = clientRepository;
    }
    
    public void notifyByEmail(Client client, String subject, String message) {
        if(client == null || client.getEmail() == null) {
            throw new IllegalEMailException(Constants.EMAIL_EXCEPTION_NULL);
        }
        try {
            clientNotifyRepository.notifyByEmail(client, subject, message);            
        } catch (IllegalClientException | ClientStatusException error) {
            switch (error) {
                case IllegalEMailException e: throw new IllegalEMailException(String.format(Constants.EMAIL_EXCEPTION_INVALID, client.getEmail()) );
                case ClientStaleInformationException e: throw new ClientStaleInformationException(String.format(Constants.CLIENT_EXCEPTION_STALE, client) );
                default: throw new GeneralException(Constants.EMAIL_EXCEPTION_NOTIFICATION + error.getMessage(), error);
            }
        }
    }

    public void notifyBySms(Client client, String message) {
        if(client == null || client.getPhoneNumber() == null) {
            throw new IllegalPhoneException(Constants.EMAIL_EXCEPTION_NULL);
        }
        try {
            clientNotifyRepository.notifyBySms(client, message);            
        } catch (IllegalClientException | ClientStatusException error) {
            switch (error) {
                case IllegalPhoneException e: throw new IllegalPhoneException(String.format(Constants.PHONE_EXCEPTION_INVALID, client.getPhoneNumber()) );
                case ClientStaleInformationException e: throw new ClientStaleInformationException(String.format(Constants.CLIENT_EXCEPTION_STALE, client) );
                default: throw new GeneralException(Constants.PHONE_EXCEPTION_NOTIFICATION + error.getMessage(), error);
            }
        }
    }

    public void notifyByPush(Client client, String message) {
        try {
            clientNotifyRepository.notifyByPush(client, message);            
        } catch (ClientStatusException error) {
            switch (error) {
                case ClientNotFoundException e  : throw new ClientNotFoundException(String.format(Constants.CUIL_EXCEPTION_NOTFOUND, client.getCuil()));
                case ClientStaleInformationException e: throw new ClientStaleInformationException(String.format(Constants.CLIENT_EXCEPTION_STALE, client) );                    
                default: throw new GeneralException(Constants.PUSH_EXCEPTION_NOTIFICATION + error.getMessage(), error);
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
            throw new IllegalCUILException(String.format(Constants.CUIL_EXCEPTION_NULL, cuil));
        }
        return !clientRepository.buscarPorCuil(cuil).isEmpty();
    }
}