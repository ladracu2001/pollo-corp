package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

public final class IllegalAddressException extends IllegalClientException{

    public IllegalAddressException(String message) {
        super(message);
    }

    public IllegalAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAddressException(Throwable cause) {
        super(cause);
    }

}
