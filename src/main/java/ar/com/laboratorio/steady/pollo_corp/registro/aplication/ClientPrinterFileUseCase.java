package ar.com.laboratorio.steady.pollo_corp.registro.aplication;

import java.io.File;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.FileIOException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientPrinterFileRepository;

public class ClientPrinterFileUseCase {

    private final ClientPrinterFileRepository clientPrinterFileRepository;

    public ClientPrinterFileUseCase(ClientPrinterFileRepository clientPrinterFileRepository) {
        this.clientPrinterFileRepository = clientPrinterFileRepository;
    }

    public File getClientReportFile(String clientId, String format) throws FileIOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClientReportFile'");
    }

    
    public void saveClientReportFile(String clientId, String format, File file) throws FileIOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveClientReportFile'");
    }

    
    public void deleteClientReportFile(String clientId, String format) throws FileIOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteClientReportFile'");
    }

    
    public File getClientsReportFile(String format) throws FileIOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClientsReportFile'");
    }

    
    public void saveClientsReportFile(String format, File file) throws FileIOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveClientsReportFile'");
    }

    
    public boolean existsClientReportFile(String clientId, String format) throws FileIOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsClientReportFile'");
    }

    
    public boolean existsClientsReportFile(String format) throws FileIOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsClientsReportFile'");
    }
}
