package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

import java.io.File;

import org.w3c.dom.Text;
import org.w3c.dom.html.HTMLDocument;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.GeneralException;

public interface ClientPrinterRepository {

    /**
     * Generates a report of the client's activity.
     * 
     * @param clientId The ID of the client for whom the report is generated.
     * @return A string containing the report details.
     */
    String getClientTextReport(String clientId);
    /**
     * Retrieves the client's report in text format.
     * 
     * @param clientId The ID of the client for whom the report is retrieved.
     * @return A Text object containing the client's report in text format.
     */
    Text getClientTextReportAsText(String clientId);
    /**
     * Exports the client's data to a specified format (e.g., PDF, CSV).
     * 
     * @param clientId The ID of the client whose data is exported.
     * @param format The format to which the data is exported.
     * @return A byte array containing the exported data.
     */
    byte[] exportClientData(String clientId, String format);
    /**
     * Retrieves a detailed report of the client in HTML format.
     * 
     * @param clientId The ID of the client for whom the report is retrieved.
     * @param format The format of the report (e.g., "HTML").
     * @return An HTMLDocument containing the client's report.
     */
    HTMLDocument getClientReportWeb(String clientId, String format);
    /**
     * Retrieves a file containing the client's report in the specified format.
     * 
     * @param clientId The ID of the client for whom the report is retrieved.
     * @param format The format of the report (e.g., "PDF", "CSV").
     * @return A File object representing the client's report file.
     * @throws Exception If an error occurs while retrieving the report file.
     */
    File getClientReportFile(String clientId, String format) throws GeneralException;
    
}
