package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

public final class IllegalCUILException extends IllegalClientException {

    public IllegalCUILException(String message) {
        super(message);
    }

    public IllegalCUILException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCUILException(Throwable cause) {
        super(cause);
    }

}
