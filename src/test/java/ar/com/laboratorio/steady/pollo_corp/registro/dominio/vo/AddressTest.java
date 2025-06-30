package ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalAddressException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void testValidAddress() {
        Address address = new Address("Calle Falsa", "123", "Ciudad", "Provincia", "1000");
        assertEquals("Calle Falsa, 123, Ciudad, Provincia, 1000", address.toString());
    }

    @Test
    void testNullDireccionThrowsException() {
        assertThrows(IllegalAddressException.class, () ->
                new Address(null, "123", "Ciudad", "Provincia", "1000"));
    }

    @Test
    void testEmptyDireccionThrowsException() {
        assertThrows(IllegalAddressException.class, () ->
                new Address("", "123", "Ciudad", "Provincia", "1000"));
    }

    @Test
    void testNullCiudadThrowsException() {
        assertThrows(IllegalAddressException.class, () ->
                new Address("Calle Falsa", "123", null, "Provincia", "1000"));
    }

    @Test
    void testEmptyCiudadThrowsException() {
        assertThrows(IllegalAddressException.class, () ->
                new Address("Calle Falsa", "123", "", "Provincia", "1000"));
    }

    @Test
    void testNullProvinciaThrowsException() {
        assertThrows(IllegalAddressException.class, () ->
                new Address("Calle Falsa", "123", "Ciudad", null, "1000"));
    }

    @Test
    void testEmptyProvinciaThrowsException() {
        assertThrows(IllegalAddressException.class, () ->
                new Address("Calle Falsa", "123", "Ciudad", "", "1000"));
    }

    @Test
    void testNullNumeroDefaultsToSN() {
        Address address = new Address("Calle Falsa", null, "Ciudad", "Provincia", "1000");
        assertTrue(address.toString().contains("S/N"));
    }

    @Test
    void testEmptyNumeroDefaultsToSN() {
        Address address = new Address("Calle Falsa", "", "Ciudad", "Provincia", "1000");
        assertTrue(address.toString().contains("S/N"));
    }
}