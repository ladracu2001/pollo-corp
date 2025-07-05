package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneralExceptionTest {

    @Test
    void constructor_message() {
        GeneralException ex = new GeneralException("Error general");
        assertEquals("Error general", ex.getMessage());
    }

    @Test
    void constructor_messageAndCause() {
        Throwable cause = new RuntimeException("Causa");
        GeneralException ex = new GeneralException("Error general", cause);
        assertEquals("Error general", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    void constructor_cause() {
        Throwable cause = new RuntimeException("Causa");
        GeneralException ex = new GeneralException(cause);
        assertSame(cause, ex.getCause());
    }
}
