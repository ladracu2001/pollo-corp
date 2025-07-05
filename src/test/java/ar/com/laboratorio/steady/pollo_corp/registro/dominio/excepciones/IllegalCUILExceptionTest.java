package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IllegalCUILExceptionTest {

    @Test
    void constructor_message() {
        IllegalCUILException ex = new IllegalCUILException("CUIL inválido");
        assertEquals("CUIL inválido", ex.getMessage());
    }

    @Test
    void constructor_messageAndCause() {
        Throwable cause = new RuntimeException("Causa");
        IllegalCUILException ex = new IllegalCUILException("CUIL inválido", cause);
        assertEquals("CUIL inválido", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    void constructor_cause() {
        Throwable cause = new RuntimeException("Causa");
        IllegalCUILException ex = new IllegalCUILException(cause);
        assertSame(cause, ex.getCause());
    }
}
