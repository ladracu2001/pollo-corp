package ar.com.laboratorio.steady.pollo_corp.registro.aplication;

import java.io.File;
import java.io.IOException;

import ar.com.laboratorio.steady.pollo_corp.registro.common.Constants;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.FileIOException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientPrinterFileRepository;

public class ClientPrinterFileUseCase {

    private final ClientPrinterFileRepository clientPrinterFileRepository;

    public ClientPrinterFileUseCase(ClientPrinterFileRepository clientPrinterFileRepository) {
        this.clientPrinterFileRepository = clientPrinterFileRepository;
    }

    public File getClientReportFile(String clientId, String format) throws FileIOException {
        try {
            return clientPrinterFileRepository.loadFile(clientId+ "." + format);            
        } catch (IOException e) {
            throw new FileIOException(Constants.FILE_EXCEPTION_LOAD + e.getMessage(), e);
        }
    }
    
    public void saveClientReportFile(String clientId, String format, String content) throws FileIOException {
        try {
            clientPrinterFileRepository.save(clientId + "." + format, content);            
        } catch (IOException e) {
            throw new FileIOException(Constants.FILE_EXCEPTION_SAVE + e.getMessage(), e);
        }
    }

    
    public void deleteClientReportFile(String clientId, String format) throws FileIOException {
        try {
            clientPrinterFileRepository.delete(clientId + "." + format);            
        } catch (IOException e) {
            throw new FileIOException(Constants.FILE_EXCEPTION_DELETE + e.getMessage(), e);
        }
    }
    
    public File getClientsReportFile(String format) throws FileIOException {
        try {
            return clientPrinterFileRepository.loadFile(Constants.FILE_NAME_CLIENTS  + "." +  format);            
        } catch (IOException e) {
            throw new FileIOException(Constants.FILE_EXCEPTION_LOAD + e.getMessage(), e);
        }
    }
    public void saveClientsReportFile(String format, String content) throws FileIOException {
        try {
            clientPrinterFileRepository.save(Constants.FILE_NAME_CLIENTS  + "." +  format, content);            
        } catch (IOException e) {
            throw new FileIOException(Constants.FILE_EXCEPTION_SAVE + e.getMessage(), e);
        }
    }
    
    public boolean existsClientReportFile(String clientId, String format) {
        return clientPrinterFileRepository.exists(clientId + "." + format);
    }
    
    public boolean existsClientsReportFile(String format) {
        return clientPrinterFileRepository.exists(Constants.FILE_NAME_CLIENTS  + "." +  format);
    }
}
