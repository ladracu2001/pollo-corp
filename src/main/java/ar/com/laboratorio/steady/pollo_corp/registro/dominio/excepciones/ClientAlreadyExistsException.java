package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

public final class ClientAlreadyExistsException extends ClientStatusException {

    public ClientAlreadyExistsException(String message) {
        super(message);
    }

    public ClientAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
