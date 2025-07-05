package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IllegalPhoneExceptionTest {

    @Test
    void constructor_message() {
        IllegalPhoneException ex = new IllegalPhoneException("Teléfono inválido");
        assertEquals("Teléfono inválido", ex.getMessage());
    }

    @Test
    void constructor_messageAndCause() {
        Throwable cause = new RuntimeException("Causa");
        IllegalPhoneException ex = new IllegalPhoneException("Teléfono inválido", cause);
        assertEquals("Teléfono inválido", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    void constructor_cause() {
        Throwable cause = new RuntimeException("Causa");
        IllegalPhoneException ex = new IllegalPhoneException(cause);
        assertSame(cause, ex.getCause());
    }
}
