package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

public interface ClientPrinterRepository extends ClientPrinterBlobRepository, ClientPrinterWebRepository {

    /**
     * Generates a report of the client's activity.
     * 
     * @param clientId The ID of the client for whom the report is generated.
     * @return A string containing the report details.
     */
    String getClientTextReport(String clientId);
}