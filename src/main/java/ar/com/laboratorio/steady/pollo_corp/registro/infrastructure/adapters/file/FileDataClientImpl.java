package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.adapters.file;

import java.io.File;

import org.w3c.dom.Text;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.GeneralException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientPrinterRepository;

public class FileDataClientImpl implements ClientPrinterRepository {
    @Override
    public String getClientTextReport(String clientId) {
        // Puedes obtener los datos del cliente aquí
    String html = """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Reporte de Cliente</title>
        </head>
        <body>
            <h1>Reporte de Cliente</h1>
            <p>ID: %s</p>
            <p>Formato: %s</p>
            <!-- Agrega más datos del cliente aquí -->
        </body>
        </html>
        """.formatted(clientId, "HTML");
    return html;
    }

    @Override
    public Text getClientTextReportAsText(String clientId) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = builder.newDocument();
            String report = "Reporte de Cliente\nID: " + clientId + "\nFormato: TEXTO\n";
            Text textNode = doc.createTextNode(report);
            return textNode;
        } catch (ParserConfigurationException e) {
            return null;
        }
    }

    @Override
    public byte[] exportClientData(String clientId, String format) {
        String data;
        if ("html".equalsIgnoreCase(format)) {
            data = getClientTextReport(clientId);
        } else {
            data = "Reporte de Cliente\nID: " + clientId + "\nFormato: " + format + "\n";
        }
        return data.getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }

    @Override
    public HTMLDocument getClientReportWeb(String clientId, String format) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = builder.newDocument();

            org.w3c.dom.Element html = doc.createElement("html");
            doc.appendChild(html);

            org.w3c.dom.Element body = doc.createElement("body");
            html.appendChild(body);

            org.w3c.dom.Element h1 = doc.createElement("h1");
            h1.setTextContent("Reporte de Cliente");
            body.appendChild(h1);

            org.w3c.dom.Element p = doc.createElement("p");
            p.setTextContent("ID: " + clientId);
            body.appendChild(p);

            // ...agrega más nodos según necesites...

            // Si necesitas específicamente HTMLDocument, deberías usar una librería como jsoup.
            return (HTMLDocument) doc;
        } catch (ParserConfigurationException e) {
            return null;
        }
    }

    @Override
    public File getClientReportFile(String clientId, String format) throws GeneralException {
         try {
            // Define el nombre y la ubicación temporal del archivo
            String fileName = "client_report_" + clientId + "." + ("html".equalsIgnoreCase(format) ? "html" : "txt");
            File tempFile = File.createTempFile(fileName, null);

            // Genera el contenido según el formato
            String content;
            if ("html".equalsIgnoreCase(format)) {
                content = getClientTextReport(clientId);
            } else {
                content = "Reporte de Cliente\nID: " + clientId + "\nFormato: " + format + "\n";
            }

            // Escribe el contenido al archivo
            try (java.io.FileWriter writer = new java.io.FileWriter(tempFile)) {
                writer.write(content);
            }

            return tempFile;
        } catch (Exception e) {
            throw new GeneralException("No se pudo generar el archivo de reporte del cliente", e);
        }
    }
}
