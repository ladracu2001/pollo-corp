package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IllegalEMailExceptionTest {

    @Test
    void constructor_message() {
        IllegalEMailException ex = new IllegalEMailException("Email inválido");
        assertEquals("Email inválido", ex.getMessage());
    }

    @Test
    void constructor_messageAndCause() {
        Throwable cause = new RuntimeException("Causa");
        IllegalEMailException ex = new IllegalEMailException("Email inválido", cause);
        assertEquals("Email inválido", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    void constructor_cause() {
        Throwable cause = new RuntimeException("Causa");
        IllegalEMailException ex = new IllegalEMailException(cause);
        assertSame(cause, ex.getCause());
    }
}
