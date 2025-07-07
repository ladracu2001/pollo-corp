package ar.com.laboratorio.steady.pollo_corp.registro.aplication;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientPrinterFileRepository;

public class ClientPrinterFileUseCase {

    private final ClientPrinterFileRepository clientPrinterFileRepository;

    public ClientPrinterFileUseCase(ClientPrinterFileRepository clientPrinterFileRepository) {
        this.clientPrinterFileRepository = clientPrinterFileRepository;
    }

    public void openClientReport(String clientId, String format) {
        try {
            clientPrinterFileRepository.getClientReportFile(clientId, format);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving client report file", e);
        }
    }
}
