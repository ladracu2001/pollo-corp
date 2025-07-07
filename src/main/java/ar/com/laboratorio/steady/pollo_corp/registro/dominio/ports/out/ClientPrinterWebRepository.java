package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

import org.w3c.dom.html.HTMLDocument;

public interface ClientPrinterWebRepository {
    /**
     * Retrieves a detailed report of the client in HTML format.
     * 
     * @param clientId The ID of the client for whom the report is retrieved.
     * @param format The format of the report (e.g., "HTML").
     * @return An HTMLDocument containing the client's report.
     */
    HTMLDocument getClientReportWeb(String clientId, String format);
}
