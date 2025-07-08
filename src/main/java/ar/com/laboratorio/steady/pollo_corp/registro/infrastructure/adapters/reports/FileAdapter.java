// src/main/java/com/tuempresa/tuproducto/adapters/out/filesystem/FileAdapter.java
package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.adapters.reports;


import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.FileIOException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientPrinterFileRepository;

import java.io.IOException;
import java.nio.file.*;

public class FileAdapter implements ClientPrinterFileRepository {

    private final Path baseDirectory;

    public FileAdapter(String baseDirectoryPath) {
        this.baseDirectory = Paths.get(baseDirectoryPath);
    }

    @Override
    public void save(String filename, String content) throws IOException {
        Path filePath = baseDirectory.resolve(filename);
        Files.writeString(filePath, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Override
    public String load(String filename) throws IOException {
        Path filePath = baseDirectory.resolve(filename);
        return Files.readString(filePath);
    }

    @Override
    public void delete(String filename) throws IOException {
        Path filePath = baseDirectory.resolve(filename);
        Files.deleteIfExists(filePath);
    }

    @Override
    public boolean exists(String filename) {
        Path filePath = baseDirectory.resolve(filename);
        return Files.exists(filePath);
    }
}