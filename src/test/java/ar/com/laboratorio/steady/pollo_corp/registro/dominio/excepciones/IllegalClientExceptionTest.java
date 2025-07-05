package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IllegalClientExceptionTest {

    @Test
    void constructor_message() {
        IllegalClientException ex = new IllegalClientException("Error de cliente");
        assertEquals("Error de cliente", ex.getMessage());
    }

    @Test
    void constructor_messageAndCause() {
        Throwable cause = new RuntimeException("Causa");
        IllegalClientException ex = new IllegalClientException("Error de cliente", cause);
        assertEquals("Error de cliente", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    void constructor_cause() {
        Throwable cause = new RuntimeException("Causa");
        IllegalClientException ex = new IllegalClientException(cause);
        assertSame(cause, ex.getCause());
    }
}
