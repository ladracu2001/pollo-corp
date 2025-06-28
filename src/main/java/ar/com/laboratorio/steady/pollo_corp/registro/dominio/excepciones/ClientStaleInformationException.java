package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

public final class ClientStaleInformationException extends ClientStatusException {

    public ClientStaleInformationException(String message) {
        super(message);
    }

    public ClientStaleInformationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientStaleInformationException(Throwable cause) {
        super(cause);
    }

}
