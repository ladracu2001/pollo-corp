package ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalEMailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EMailTest {

    @Test
    void testValidEMail() {
        EMail email = new EMail("ladracu2001", "mail.com");
        assertEquals("ladracu2001@mail.com", email.toString());
    }

    @Test
    void testValidEMailReachLength() {
        EMail email = new EMail("ladracu012345678910111221", "mail.com");
        assertEquals("ladracu012345678910111221@mail.com", email.toString());
    }

    @Test
    void testNullUserThrowsException() {
        assertThrows(IllegalEMailException.class, () -> new EMail(null, "mail.com"));
    }

    @Test
    void testEmptyUserThrowsException() {
        assertThrows(IllegalEMailException.class, () -> new EMail("", "mail.com"));
    }

    @Test
    void testNullDomainThrowsException() {
        assertThrows(IllegalEMailException.class, () -> new EMail("juan.perez", null));
    }

    @Test
    void testEmptyDomainThrowsException() {
        assertThrows(IllegalEMailException.class, () -> new EMail("juan.perez", ""));
    }

    @Test
    void testInvalidFormatThrowsException() {
        assertThrows(IllegalEMailException.class, () -> new EMail("juan.perez", "mailcom")); // Falta el punto
        assertThrows(IllegalEMailException.class, () -> new EMail("juan.perez", "mail.c"));  // Dominio muy corto
        assertThrows(IllegalEMailException.class, () -> new EMail("juan.perez@", "mail.com")); // Usuario con arroba
        assertThrows(IllegalEMailException.class, () -> new EMail("juan.perezdelospalotesseniordelosvientos@", "mail.com")); // Usuario con mas de 25 caracteres
    }
}        