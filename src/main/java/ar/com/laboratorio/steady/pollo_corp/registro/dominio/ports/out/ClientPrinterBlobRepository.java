package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

public interface ClientPrinterBlobRepository {
    /**
     * Exports the client's data to a specified format (e.g., PDF, CSV).
     * 
     * @param clientId The ID of the client whose data is exported.
     * @param format The format to which the data is exported.
     * @return A byte array containing the exported data.
     */
    byte[] exportClientData(String clientId, String format);
}
