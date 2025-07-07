package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

import java.io.File;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.GeneralException;

public interface ClientPrinterFileRepository {
    /**
     * Retrieves a file containing the client's report in the specified format.
     * 
     * @param clientId The ID of the client for whom the report is retrieved.
     * @param format The format of the report (e.g., "PDF", "CSV").
     * @return A File object representing the client's report file.
     * @throws Exception If an error occurs while retrieving the report file.
     */
    File getClientReportFile(String clientId, String format) throws GeneralException;
    /**
     * Saves a client's report file in the specified format.
     * 
     * @param clientId The ID of the client for whom the report is saved.
     * @param format The format of the report (e.g., "PDF", "CSV").
     * @param file The File object containing the report data to be saved.
     * @throws GeneralException If an error occurs while saving the report file.
     */
    void saveClientReportFile(String clientId, String format, File file) throws GeneralException;
    /**
     * Deletes a client's report file in the specified format.
     * 
     * @param clientId The ID of the client for whom the report file is deleted.
     * @param format The format of the report (e.g., "PDF", "CSV").
     * @throws GeneralException If an error occurs while deleting the report file.
     */
    void deleteClientReportFile(String clientId, String format) throws GeneralException;
    /**
     * Retrieves a file containing the clients' report in the specified format.
     * 
     * @param format The format of the report (e.g., "PDF", "CSV").
     * @return A File object representing the clients' report file.
     * @throws GeneralException If an error occurs while retrieving the report file.
     */
    File getClientsReportFile(String format) throws GeneralException;
    /**
     * Saves a clients' report file in the specified format.
     * 
     * @param format The format of the report (e.g., "PDF", "CSV").
     * @param file The File object containing the report data to be saved.
     * @throws GeneralException If an error occurs while saving the report file.
     */
    void saveClientsReportFile(String format, File file) throws GeneralException;
    /*
     * Exists methods to check if a report file exists for a specific client.
     * @param clientId The ID of the client for whom the report file existence is checked.
     * @param format The format of the report (e.g., "PDF", "CSV
     */
    boolean existsClientReportFile(String clientId, String format) throws GeneralException;
    /*
     * Exists methods to check if a report file exists for all clients.
     * @param clientId The ID of the client for whom the report file existence is checked.
     * @param format The format of the report (e.g., "PDF", "CSV
     */
    boolean existsClientsReportFile(String format) throws GeneralException;
}
