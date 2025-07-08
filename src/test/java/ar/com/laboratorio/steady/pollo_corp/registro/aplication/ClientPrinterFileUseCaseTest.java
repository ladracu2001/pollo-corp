package ar.com.laboratorio.steady.pollo_corp.registro.aplication;

import ar.com.laboratorio.steady.pollo_corp.registro.common.Constants;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.FileIOException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientPrinterFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientPrinterFileUseCaseTest {

    private ClientPrinterFileRepository repository;
    private ClientPrinterFileUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(ClientPrinterFileRepository.class);
        useCase = new ClientPrinterFileUseCase(repository);
    }

    @Test
    void getClientReportFile_ok() throws IOException {
        File file = new File("test.txt");
        when(repository.loadFile("123.txt")).thenReturn(file);
        assertSame(file, useCase.getClientReportFile("123", "txt"));
    }

    @Test
    void getClientReportFile_throwsFileIOException() throws IOException {
        when(repository.loadFile("123.txt")).thenThrow(new IOException("fail"));
        FileIOException ex = assertThrows(FileIOException.class, () -> useCase.getClientReportFile("123", "txt"));
        assertTrue(ex.getMessage().contains("Error loading client report file"));
    }

    @Test
    void saveClientReportFile_ok() throws IOException {
        doNothing().when(repository).save("123.txt", "contenido");
        assertDoesNotThrow(() -> useCase.saveClientReportFile("123", "txt", "contenido"));
    }

    @Test
    void saveClientReportFile_throwsFileIOException() throws IOException {
        doThrow(new IOException("fail")).when(repository).save("123.txt", "contenido");
        FileIOException ex = assertThrows(FileIOException.class, () -> useCase.saveClientReportFile("123", "txt", "contenido"));
        assertTrue(ex.getMessage().contains("Error saving client report file"));
    }

    @Test
    void deleteClientReportFile_ok() throws IOException {
        doNothing().when(repository).delete("123.txt");
        assertDoesNotThrow(() -> useCase.deleteClientReportFile("123", "txt"));
    }

    @Test
    void deleteClientReportFile_throwsFileIOException() throws IOException {
        doThrow(new IOException("fail")).when(repository).delete("123.txt");
        FileIOException ex = assertThrows(FileIOException.class, () -> useCase.deleteClientReportFile("123", "txt"));
        assertTrue(ex.getMessage().contains("Error deleting client report file"));
    }

    @Test
    void getClientsReportFile_ok() throws IOException {
        File file = new File("clients.txt");
        when(repository.loadFile("clients.txt")).thenReturn(file);
        assertSame(file, useCase.getClientsReportFile("txt"));
    }

    @Test
    void getClientsReportFile_throwsFileIOException() throws IOException {
        when(repository.loadFile("clients.txt")).thenThrow(new IOException("fail"));
        FileIOException ex = assertThrows(FileIOException.class, () -> useCase.getClientsReportFile("txt"));
        assertTrue(ex.getMessage().contains("Error loading client report file"));
    }

    @Test
    void saveClientsReportFile_ok() throws IOException {
        doNothing().when(repository).save("clients.txt", "contenido");
        assertDoesNotThrow(() -> useCase.saveClientsReportFile("txt", "contenido"));
    }

    @Test
    void saveClientsReportFile_throwsFileIOException() throws IOException {
        doThrow(new IOException("fail")).when(repository).save("clients.txt", "contenido");
        FileIOException ex = assertThrows(FileIOException.class, () -> useCase.saveClientsReportFile("txt", "contenido"));
        assertTrue(ex.getMessage().contains(Constants.FILE_EXCEPTION_SAVE));
    }

    @Test
    void existsClientReportFile_ok() {
        when(repository.exists("123.txt")).thenReturn(true);
        assertTrue(useCase.existsClientReportFile("123", "txt"));        
    }

    @Test
    void existsClientsReportFile_ok() {
        when(repository.exists("clients.txt")).thenReturn(false);
        assertFalse(useCase.existsClientsReportFile("txt"));
    }
}
