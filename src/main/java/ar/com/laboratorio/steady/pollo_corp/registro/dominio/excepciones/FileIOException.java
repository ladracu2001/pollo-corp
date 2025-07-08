package ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones;

import java.io.IOException;

public class FileIOException extends IOException {

    public FileIOException(String message) {
        super(message);
    }

    public FileIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileIOException(Throwable cause) {
        super(cause);
    }
}
