package ar.com.laboratorio.steady.pollo_corp.registro.aplication;

import org.w3c.dom.html.HTMLDocument;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientPrinterRepository;
/**
 * Use case for generating and retrieving client reports.
 * This class interacts with the ClientReportRepository to perform operations related to client reports.
 */
public class ClientReportUseCase {

    private final ClientPrinterRepository clientPrinterRepository;

    public ClientReportUseCase(ClientPrinterRepository clientReportRepository) {
        this.clientPrinterRepository = clientReportRepository;
    }

    public String getClientTextReport(String clientId) {
        return clientPrinterRepository.getClientTextReport(clientId);
    }

    public byte[] exportClientData(String clientId, String format) {
        return clientPrinterRepository.exportClientData(clientId, format);
    }

    public HTMLDocument getClientReportWeb(String clientId, String format) {
        return clientPrinterRepository.getClientReportWeb(clientId, format);
    }

}
