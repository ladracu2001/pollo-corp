package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

public final class IllegalEMailException extends IllegalClientException {

    public IllegalEMailException(String message) {
        super(message);
    }

    public IllegalEMailException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalEMailException(Throwable cause) {
        super(cause);
    }

}
