package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.exceptions;

import ar.com.laboratorio.steady.pollo_corp.registro.common.Constants;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalHandlerExceptionTest {

    private final GlobalHandlerException handler = new GlobalHandlerException();

    @SuppressWarnings("null")
    @Test
    void handleClientNotFoundException() {
        ClientNotFoundException ex = new ClientNotFoundException("No existe");
        ResponseEntity<Map<String, String>> response = handler.handleClientNotFoundException(ex);
        assertEquals(404, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("No existe", response.getBody().get("error"));
    }

    @SuppressWarnings("null")
    @Test
    void handleClientAlreadyExistsException() {
        ClientAlreadyExistsException ex = new ClientAlreadyExistsException("Ya existe");
        ResponseEntity<Map<String, String>> response = handler.handleClientAlreadyExistsException(ex);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(Constants.CLIENT_EXCEPTION_EXIST + "Ya existe", response.getBody().get("error"));
    }

    @SuppressWarnings("null")
    @Test
    void handleClientStaleInformationException() {
        ClientStaleInformationException ex = new ClientStaleInformationException("Stale");
        ResponseEntity<Map<String, String>> response = handler.handleClientStaleInformationException(ex);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(Constants.CLIENT_EXCEPTION_STALE + "Stale", response.getBody().get("error"));
    }

    @SuppressWarnings("null")
    @Test
    void handleIllegalCUILException() {
        IllegalCUILException ex = new IllegalCUILException("CUIL inválido");
        ResponseEntity<Map<String, String>> response = handler.handleIllegalCUILException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Constants.CUIL_EXCEPTION_INVALID + "CUIL inválido", response.getBody().get("error"));
    }

    @SuppressWarnings("null")
    @Test
    void handleIllegalAddressException() {
        IllegalAddressException ex = new IllegalAddressException("Dirección inválida");
        ResponseEntity<Map<String, String>> response = handler.handleIllegalAddressException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Constants.ADDRESS_EXCEPTION_INVALID + "Dirección inválida", response.getBody().get("error"));
    }

    @SuppressWarnings("null")
    @Test
    void handleIllegalEMailException() {
        IllegalEMailException ex = new IllegalEMailException("Email inválido");
        ResponseEntity<Map<String, String>> response = handler.handleIllegalEMailException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Constants.EMAIL_EXCEPTION_INVALID + "Email inválido", response.getBody().get("error"));
    }

    @SuppressWarnings("null")
    @Test
    void handleIllegalPhoneException() {
        IllegalPhoneException ex = new IllegalPhoneException("Teléfono inválido");
        ResponseEntity<Map<String, String>> response = handler.handleIllegalPhoneException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Constants.PHONE_EXCEPTION_INVALID + "Teléfono inválido", response.getBody().get("error"));
    }

    @SuppressWarnings("null")
    @Test
    void handleClientMoreThanOneFoundException() {
        ClientMoreThanOneFoundException ex = new ClientMoreThanOneFoundException("Multiples");
        ResponseEntity<Map<String, String>> response = handler.handleClientMoreThanOneFoundException(ex);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(Constants.CLIENT_EXCEPTION_MULTIEXIST + "Multiples", response.getBody().get("error"));
    }

    @SuppressWarnings("null")
    @Test
    void handleGeneralException() {
        GeneralException ex = new GeneralException("Error general");
        ResponseEntity<Map<String, String>> response = handler.handleGeneralException(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(Constants.INTERNAL_EXCEPTION_NOTIFICATION + "Error general", response.getBody().get("error"));
    }
}
