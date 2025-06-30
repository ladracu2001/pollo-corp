package ar.com.laboratorio.steady.pollo_corp.registro.dominio.aplication;

import ar.com.laboratorio.steady.pollo_corp.registro.aplication.ClientNotifyUseCase;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.*;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientNotifyRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientStatusRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.EMail;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientNotifyUseCaseTest {

    private ClientNotifyRepository notifyRepo;
    private ClientStatusRepository statusRepo;
    private ClientRepository clientRepo;
    private ClientNotifyUseCase useCase;

    @BeforeEach
    void setUp() {
        notifyRepo = mock(ClientNotifyRepository.class);
        statusRepo = mock(ClientStatusRepository.class);
        clientRepo = mock(ClientRepository.class);
        useCase = new ClientNotifyUseCase(notifyRepo, statusRepo, clientRepo);
    }

    @Test
    void notifyByEmail_ok() {
        Client client = buildClient();
        useCase.notifyByEmail(client, "subject", "msg");
        verify(notifyRepo).notifyByEmail(client, "subject", "msg");
    }

    @Test
    void notifyByEmail_nullClient_throws() {
        assertThrows(IllegalEMailException.class, () -> useCase.notifyByEmail(null, "subject", "msg"));
    }

    @Test
    void notifyByEmail_nullEmail_throws() {
        Client client = buildClient();
        when(client.getEmail()).thenReturn(null);
        assertThrows(IllegalEMailException.class, () -> useCase.notifyByEmail(client, "subject", "msg"));
    }

    @Test
    void notifyByEmail_illegalEmailException() {
        Client client = buildClient();
        doThrow(new IllegalEMailException("bad")).when(notifyRepo).notifyByEmail(any(), any(), any());
        assertThrows(IllegalEMailException.class, () -> useCase.notifyByEmail(client, "subject", "msg"));
    }

    @Test
    void notifyBySms_ok() {
        Client client = buildClient();
        useCase.notifyBySms(client, "msg");
        verify(notifyRepo).notifyBySms(client, "msg");
    }

    @Test
    void notifyBySms_nullClient_throws() {
        assertThrows(IllegalPhoneException.class, () -> useCase.notifyBySms(null, "msg"));
    }

    @Test
    void notifyBySms_nullPhone_throws() {
        Client client = buildClient();
        when(client.getPhoneNumber()).thenReturn(null);
        assertThrows(IllegalPhoneException.class, () -> useCase.notifyBySms(client, "msg"));
    }

    @Test
    void notifyBySms_illegalPhoneException() {
        Client client = buildClient();
        doThrow(new IllegalPhoneException("bad")).when(notifyRepo).notifyBySms(any(), any());
        assertThrows(IllegalPhoneException.class, () -> useCase.notifyBySms(client, "msg"));
    }

    @Test
    void notifyByPush_ok() {
        Client client = buildClient();
        useCase.notifyByPush(client, "msg");
        verify(notifyRepo).notifyByPush(client, "msg");
    }

    @Test
    void notifyByPush_clientNotFoundException() {
        Client client = buildClient();
        doThrow(new ClientNotFoundException("not found")).when(notifyRepo).notifyByPush(any(), any());
        assertThrows(ClientNotFoundException.class, () -> useCase.notifyByPush(client, "msg"));
    }

    @Test
    void isClientActive_trueIfValidAndYoung() {
        Cuil cuil = new Cuil("20-12345678-3");
        Client client = buildClientWithBirthYear(LocalDate.now().getYear() - 30);
        when(statusRepo.isValidClient(cuil)).thenReturn(true);
        when(clientRepo.buscarPorCuil(cuil)).thenReturn(Optional.of(client));
        assertTrue(useCase.isClientActive(cuil));
    }

    @Test
    void isClientActive_falseIfOld() {
        Cuil cuil = new Cuil("20-12345678-3");
        Client client = buildClientWithBirthYear(LocalDate.now().getYear() - 70);
        when(statusRepo.isValidClient(cuil)).thenReturn(true);
        when(clientRepo.buscarPorCuil(cuil)).thenReturn(Optional.of(client));
        assertFalse(useCase.isClientActive(cuil));
    }

    @Test
    void isClientActive_falseIfNotValid() {
        Cuil cuil = new Cuil("20-12345678-3");
        when(statusRepo.isValidClient(cuil)).thenReturn(false);
        assertFalse(useCase.isClientActive(cuil));
    }

    @Test
    void isValidClient_nullCuil_throws() {
        assertThrows(IllegalCUILException.class, () -> useCase.isValidClient(null));
    }

    @Test
    void isValidClient_falseIfNotFound() {
        Cuil cuil = new Cuil("20-12345678-3");
        when(clientRepo.buscarPorCuil(cuil)).thenReturn(Optional.empty());
        assertFalse(useCase.isValidClient(cuil));
    }

    @Test
    void isValidClient_trueIfFound() {
        Cuil cuil = new Cuil("20-12345678-3");
        Client client = buildClient();
        when(clientRepo.buscarPorCuil(cuil)).thenReturn(Optional.of(client));
        assertTrue(useCase.isValidClient(cuil));
    }

    // Helpers
    private Client buildClient() {
        Client client = mock(Client.class);
        when(client.getEmail()).thenReturn(new EMail("juan", "mail.com"));
        when(client.getPhoneNumber()).thenReturn(new Phone("+54", "11", "12345678"));
        when(client.getCuil()).thenReturn(new Cuil("20-12345678-3"));
        when(client.getBirthDate()).thenReturn(LocalDate.of(1990, 1, 1));
        return client;
    }

    private Client buildClientWithBirthYear(int year) {
        Client client = buildClient();
        when(client.getBirthDate()).thenReturn(LocalDate.of(year, 1, 1));
        return client;
    }
}
