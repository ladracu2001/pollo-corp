package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IllegalAddressExceptionTest {

    @Test
    void constructor_message() {
        IllegalAddressException ex = new IllegalAddressException("Dirección inválida");
        assertEquals("Dirección inválida", ex.getMessage());
    }

    @Test
    void constructor_messageAndCause() {
        Throwable cause = new RuntimeException("Causa");
        IllegalAddressException ex = new IllegalAddressException("Dirección inválida", cause);
        assertEquals("Dirección inválida", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    void constructor_cause() {
        Throwable cause = new RuntimeException("Causa");
        IllegalAddressException ex = new IllegalAddressException(cause);
        assertSame(cause, ex.getCause());
    }
}
