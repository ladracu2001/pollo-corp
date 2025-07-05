package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientStaleInformationExceptionTest {

    @Test
    void constructor_message() {
        ClientStaleInformationException ex = new ClientStaleInformationException("Información desactualizada");
        assertEquals("Información desactualizada", ex.getMessage());
    }

    @Test
    void constructor_messageAndCause() {
        Throwable cause = new RuntimeException("Causa");
        ClientStaleInformationException ex = new ClientStaleInformationException("Información desactualizada", cause);
        assertEquals("Información desactualizada", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    void constructor_cause() {
        Throwable cause = new RuntimeException("Causa");
        ClientStaleInformationException ex = new ClientStaleInformationException(cause);
        assertSame(cause, ex.getCause());
    }
}
