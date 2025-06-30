package ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalCUILException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuilTest {

    @Test
    void testValidCuil() {
        Cuil cuil = new Cuil("20-12345678-3");
        assertEquals("20-12345678-3", cuil.toString());
    }

    @Test
    void testNullCuilThrowsException() {
        assertThrows(IllegalCUILException.class, () -> new Cuil(null));
    }

    @Test
    void testEmptyCuilThrowsException() {
        assertThrows(IllegalCUILException.class, () -> new Cuil(""));
    }

    @Test
    void testInvalidFormatThrowsException() {
        assertThrows(IllegalCUILException.class, () -> new Cuil("20123456783")); // Sin guiones
        assertThrows(IllegalCUILException.class, () -> new Cuil("20-1234567-3")); // Menos dígitos
        assertThrows(IllegalCUILException.class, () -> new Cuil("20-abcdefgh-3")); // Letras en lugar de dígitos
    }
}
