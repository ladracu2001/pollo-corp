package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientNotFoundExceptionTest {

    @Test
    void constructor_message() {
        ClientNotFoundException ex = new ClientNotFoundException("No existe");
        assertEquals("No existe", ex.getMessage());
    }

    @Test
    void constructor_messageAndCause() {
        Throwable cause = new RuntimeException("Causa");
        ClientNotFoundException ex = new ClientNotFoundException("No existe", cause);
        assertEquals("No existe", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    void constructor_cause() {
        Throwable cause = new RuntimeException("Causa");
        ClientNotFoundException ex = new ClientNotFoundException(cause);
        assertSame(cause, ex.getCause());
    }
}
