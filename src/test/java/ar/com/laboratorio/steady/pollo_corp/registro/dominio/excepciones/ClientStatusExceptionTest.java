package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientStatusExceptionTest {

    @Test
    void constructor_message() {
        ClientStatusException ex = new ClientStatusException("Estado inválido");
        assertEquals("Estado inválido", ex.getMessage());
    }

    @Test
    void constructor_messageAndCause() {
        Throwable cause = new RuntimeException("Causa");
        ClientStatusException ex = new ClientStatusException("Estado inválido", cause);
        assertEquals("Estado inválido", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    void constructor_cause() {
        Throwable cause = new RuntimeException("Causa");
        ClientStatusException ex = new ClientStatusException(cause);
        assertSame(cause, ex.getCause());
    }
}
