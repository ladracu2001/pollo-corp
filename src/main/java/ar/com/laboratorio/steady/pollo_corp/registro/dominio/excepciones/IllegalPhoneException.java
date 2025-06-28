package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

public final class IllegalPhoneException extends IllegalClientException {

    public IllegalPhoneException(String message) {
        super(message);
    }

    public IllegalPhoneException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPhoneException(Throwable cause) {
        super(cause);
    }

}
