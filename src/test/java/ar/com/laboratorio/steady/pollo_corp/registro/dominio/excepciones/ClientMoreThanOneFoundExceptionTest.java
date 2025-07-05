package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientMoreThanOneFoundExceptionTest {

    @Test
    void constructor_message() {
        ClientMoreThanOneFoundException ex = new ClientMoreThanOneFoundException("Más de un cliente encontrado");
        assertEquals("Más de un cliente encontrado", ex.getMessage());
    }

    @Test
    void constructor_messageAndCause() {
        Throwable cause = new RuntimeException("Causa");
        ClientMoreThanOneFoundException ex = new ClientMoreThanOneFoundException("Más de un cliente encontrado", cause);
        assertEquals("Más de un cliente encontrado", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    void constructor_cause() {
        Throwable cause = new RuntimeException("Causa");
        ClientMoreThanOneFoundException ex = new ClientMoreThanOneFoundException(cause);
        assertSame(cause, ex.getCause());
    }
}
