package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

public sealed class ClientStatusException extends IllegalClientException permits
        ClientAlreadyExistsException,
        ClientNotFoundException,
        ClientStaleInformationException, ClientMoreThanOneFoundException{

    public ClientStatusException(String message) {
        super(message); 
    }
    public ClientStatusException(String message, Throwable cause) {
        super(message, cause);
    }
    public ClientStatusException(Throwable cause) {
        super(cause);
    }
}
