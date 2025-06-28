package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

public final class ClientMoreThanOneFoundException extends ClientStatusException {

    public ClientMoreThanOneFoundException(String message) {
        super(message);
    }

    public ClientMoreThanOneFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientMoreThanOneFoundException(Throwable cause) {
        super(cause);
    }

}
