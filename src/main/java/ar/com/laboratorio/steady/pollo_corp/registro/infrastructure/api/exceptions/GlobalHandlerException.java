package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException extends Exception{

    private static final long serialVersionUID = 1L;

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleClientNotFoundException(ClientNotFoundException e) {
        return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
    }
    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleClientAlreadyExistsException(ClientAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", "Client already exists: " + e.getMessage()));
    }
    @ExceptionHandler(ClientStaleInformationException.class)
    public ResponseEntity<Map<String, String>> handleClientStaleInformationException(ClientStaleInformationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", "Stale information: " + e.getMessage()));
    }
    @ExceptionHandler(IllegalCUILException.class)
    public ResponseEntity<Map<String, String>> handleIllegalCUILException(IllegalCUILException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", "Invalid CUIL: " + e.getMessage()));
    }
    @ExceptionHandler(IllegalAddressException.class)
    public ResponseEntity<Map<String, String>> handleIllegalAddressException(IllegalAddressException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", "Invalid address: " + e.getMessage()));
    }
    @ExceptionHandler(IllegalEMailException.class)
    public ResponseEntity<Map<String, String>> handleIllegalEMailException(IllegalEMailException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", "Invalid email: " + e.getMessage()));
    }
    @ExceptionHandler(IllegalPhoneException.class)
    public ResponseEntity<Map<String, String>> handleIllegalPhoneException(IllegalPhoneException e) {    
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", "Invalid phone number: " + e.getMessage()));
    }
    @ExceptionHandler(ClientMoreThanOneFoundException.class)
    public ResponseEntity<Map<String, String>> handleClientMoreThanOneFoundException(ClientMoreThanOneFoundException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", "More than one client found: " + e.getMessage()));
    }
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(GeneralException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "An unexpected error occurred: " + e.getMessage()));
    }
}
