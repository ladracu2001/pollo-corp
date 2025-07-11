package ar.com.laboratorio.steady.pollo_corp.registro.aplication.services;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientPrinterFileRepository;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.FileIOException;
import java.util.List;

public class ClientExportService {

    private final ClientRepository clientRepository;
    private final ClientPrinterFileRepository fileRepository;

    public ClientExportService(ClientRepository clientRepository, ClientPrinterFileRepository fileRepository) {
        this.clientRepository = clientRepository;
        this.fileRepository = fileRepository;
    }

    public void exportAllClientsToFile(String format) throws FileIOException {
        List<Client> clients = clientRepository.findAll();
        // Aquí conviertes la lista a texto (CSV, JSON, etc.)
        StringBuilder sb = new StringBuilder();
        sb.append("cuil,dni,name,surname,lastName,birthDate,email,phoneNumber,address\n");
        clients.forEach(c -> {
            // Asume que tienes getters apropiados en tu entidad o dominio
             sb.append(c.getCuil()).append(",")
               .append(c.getDni()).append(",")
               .append(c.getName()).append(",")
               .append(c.getSurname()).append(",")
               .append(c.getLastName()).append(",")
               .append(c.getBirthDate()).append(",")
               .append(c.getEmail()).append(",")
               .append(c.getPhoneNumber()).append(",")
               .append(c.getAddress()).append("\n");
        });
        try {
            fileRepository.save("clients." + format, sb.toString());
        } catch (Exception e) {
            throw new FileIOException("No se pudo exportar los clientes", e);
        }
    }
}
