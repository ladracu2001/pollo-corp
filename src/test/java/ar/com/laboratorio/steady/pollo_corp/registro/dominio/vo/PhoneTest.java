package ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalPhoneException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {

    @Test
    void testValidPhone() {
        Phone phone = new Phone("+54", "11", "12345678");
        assertEquals("+54 (11) 12345678", phone.toString());
    }

    @Test
    void testNullCodPaisDefaultsTo54() {
        Phone phone = new Phone(null, "11", "12345678");
        assertEquals("+54 (11) 12345678", phone.toString());
    }

    @Test
    void testEmptyCodPaisDefaultsTo54() {
        Phone phone = new Phone("", "11", "12345678");
        assertEquals("+54 (11) 12345678", phone.toString());
    }

    @Test
    void testNullCodCiudadDefaultsTo11() {
        Phone phone = new Phone("+54", null, "12345678");
        assertEquals("+54 (11) 12345678", phone.toString());
    }

    @Test
    void testEmptyCodCiudadDefaultsTo11() {
        Phone phone = new Phone("+54", "", "12345678");
        assertEquals("+54 (11) 12345678", phone.toString());
    }

    @Test
    void testNullNumAbonadoThrowsException() {
        assertThrows(IllegalPhoneException.class, () -> new Phone("+54", "11", null));
    }

    @Test
    void testEmptyNumAbonadoThrowsException() {
        assertThrows(IllegalPhoneException.class, () -> new Phone("+54", "11", ""));
    }
}
