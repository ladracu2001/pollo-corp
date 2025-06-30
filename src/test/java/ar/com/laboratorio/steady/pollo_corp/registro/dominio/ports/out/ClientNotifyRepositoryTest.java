package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyClientNotifyRepository implements ClientNotifyRepository {
    @Override
    public void notifyByEmail(Client client, String subject, String message) {
        if (client == null || client.getEmail() == null) throw new IllegalEMailException("Email inválido");
        if ("stale".equals(message)) throw new ClientStaleInformationException("Stale info");
    }

    @Override
    public void notifyBySms(Client client, String message) {
        if (client == null || client.getPhoneNumber() == null) throw new IllegalPhoneException("Teléfono inválido");
        if ("stale".equals(message)) throw new ClientStaleInformationException("Stale info");
    }

    @Override
    public void notifyByPush(Client client, String message) {
        if (client == null) throw new ClientNotFoundException("No encontrado");
        if ("stale".equals(message)) throw new ClientStaleInformationException("Stale info");
    }
}

class ClientNotifyRepositoryTest {

    private final ClientNotifyRepository repo = new DummyClientNotifyRepository();

    @Test
    void testNotifyByEmailThrowsIllegalEMailException() {
        Client client = new Client();
        assertThrows(IllegalEMailException.class, () -> repo.notifyByEmail(client, "asunto", "mensaje"));
    }

    @Test
    void testNotifyByEmailThrowsClientStaleInformationException() {
        Client client = new Client();
        client.setEmail(new ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.EMail("user", "mail.com"));
        assertThrows(ClientStaleInformationException.class, () -> repo.notifyByEmail(client, "asunto", "stale"));
    }

    @Test
    void testNotifyBySmsThrowsIllegalPhoneException() {
        Client client = new Client();
        assertThrows(IllegalPhoneException.class, () -> repo.notifyBySms(client, "mensaje"));
    }

    @Test
    void testNotifyBySmsThrowsClientStaleInformationException() {
        Client client = new Client();
        client.setPhoneNumber(new ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Phone("+54", "11", "12345678"));
        assertThrows(ClientStaleInformationException.class, () -> repo.notifyBySms(client, "stale"));
    }

    @Test
    void testNotifyByPushThrowsClientNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> repo.notifyByPush(null, "mensaje"));
    }

    @Test
    void testNotifyByPushThrowsClientStaleInformationException() {
        Client client = new Client();
        assertThrows(ClientStaleInformationException.class, () -> repo.notifyByPush(client, "stale"));
    }
}
