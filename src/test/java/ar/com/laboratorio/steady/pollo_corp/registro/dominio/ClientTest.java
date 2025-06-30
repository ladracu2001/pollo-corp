package ar.com.laboratorio.steady.pollo_corp.registro.dominio;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.*;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testChangeEmailThrowsExceptionWhenNull() {
        Client client = new Client();
        assertThrows(IllegalEMailException.class, () -> client.changeEmail(null));
    }

    @Test
    void testChangePhoneNumberThrowsExceptionWhenNull() {
        Client client = new Client();
        assertThrows(IllegalPhoneException.class, () -> client.changePhoneNumber(null));
    }

    @Test
    void testChangeAddressThrowsExceptionWhenNull() {
        Client client = new Client();
        assertThrows(IllegalAddressException.class, () -> client.changeAddress(null));
    }

    @Test
    void testValidateBirthDateThrowsExceptionWhenNull() {
        Client client = new Client();
        assertThrows(IllegalArgumentException.class, () -> client.validateBirthDate(null));
    }

    @Test
    void testValidateBirthDateThrowsExceptionWhenFuture() {
        Client client = new Client();
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assertThrows(IllegalArgumentException.class, () -> client.validateBirthDate(futureDate));
    }

    @Test
    void testToStringNotNull() {
        Client client = new Client(
            new Cuil("20-12345678-3"),
                "12345678",
                "Juan",
                "Pérez",
                "Gómez",
                LocalDate.of(1990, 1, 1),
                new EMail("ladracu2001", "mail.com"),
                new Phone("+54", "11", "12345678"),
                new Address("Calle Falsa", "123", "Ciudad", "Provincia", "1000")
        );
        assertNotNull(client.toString());
    }
}