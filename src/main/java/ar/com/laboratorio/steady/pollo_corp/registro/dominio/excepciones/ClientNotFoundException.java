package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

public final class ClientNotFoundException extends ClientStatusException {

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotFoundException(Throwable cause) {
        super(cause);
    }

}
