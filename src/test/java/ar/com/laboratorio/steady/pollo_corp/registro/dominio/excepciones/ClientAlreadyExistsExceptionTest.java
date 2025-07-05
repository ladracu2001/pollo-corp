package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientAlreadyExistsExceptionTest {

    @Test
    void constructor_message() {
        ClientAlreadyExistsException ex = new ClientAlreadyExistsException("Ya existe");
        assertEquals("Ya existe", ex.getMessage());
    }

    @Test
    void constructor_messageAndCause() {
        Throwable cause = new RuntimeException("Causa");
        ClientAlreadyExistsException ex = new ClientAlreadyExistsException("Ya existe", cause);
        assertEquals("Ya existe", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    void constructor_cause() {
        Throwable cause = new RuntimeException("Causa");
        ClientAlreadyExistsException ex = new ClientAlreadyExistsException(cause);
        assertSame(cause, ex.getCause());
    }
}
