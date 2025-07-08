package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

import java.io.IOException;

//import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.FileIOException;

public interface ClientPrinterFileRepository {
    /**
     * Saves the content to a file with the specified filename.
     *
     * @param filename the name of the file to save
     * @param content  the content to write to the file
     * @throws IOException if an I/O error occurs
     */
    void save(String filename, String content) throws IOException;
    /**
     * Loads the content from a file with the specified filename.
     *
     * @param filename the name of the file to load
     * @return the content of the file
     * @throws IOException if an I/O error occurs
     */
    String load(String filename) throws IOException;
    /**
     * Deletes the file with the specified filename.
     *
     * @param filename the name of the file to delete
     * @throws IOException if an I/O error occurs
     */
    void delete(String filename) throws IOException;
    /**
     * Checks if a file with the specified filename exists.
     *
     * @param filename the name of the file to check
     * @return true if the file exists, false otherwise
     */
    boolean exists(String filename);

}
