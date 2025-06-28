package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

public sealed class IllegalClientException extends RuntimeException permits IllegalAddressException, IllegalEMailException, IllegalPhoneException, IllegalCUILException, ClientStatusException {
    public IllegalClientException(String message) {
        super(message);
    }

    public IllegalClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalClientException(Throwable cause) {
        super(cause);
    }
}