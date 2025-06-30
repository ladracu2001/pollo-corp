package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;
import org.w3c.dom.html.HTMLDocument;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DummyClientPrinterRepository implements ClientPrinterRepository {

    @Override
    public String getClientTextReport(String clientId) {
        return "Reporte para " + clientId;
    }

    @Override
    public Text getClientTextReportAsText(String clientId) {
        return new org.apache.xerces.dom.TextImpl(null, "Reporte para " + clientId);
    }

    @Override
    public byte[] exportClientData(String clientId, String format) {
        return ("Exportando " + clientId + " en " + format).getBytes();
    }

    @Override
    public HTMLDocument getClientReportWeb(String clientId, String format) {
        return null; // Simulación simple
    }

    @Override
    public File getClientReportFile(String clientId, String format) {
        return new File("dummy_" + clientId + "." + format.toLowerCase());
    }
}

class ClientPrinterRepositoryTest {

    private final ClientPrinterRepository repo = new DummyClientPrinterRepository();

    @Test
    void testGetClientTextReport() {
        String result = repo.getClientTextReport("123");
        assertTrue(result.contains("123"));
    }

    @Test
    void testGetClientTextReportAsText() {
        Text text = repo.getClientTextReportAsText("123");
        assertNotNull(text);
        assertTrue(text.getData().contains("123"));
    }

    @Test
    void testExportClientData() {
        byte[] data = repo.exportClientData("123", "PDF");
        assertNotNull(data);
        assertTrue(new String(data).contains("PDF"));
    }

    @Test
    void testGetClientReportWeb() {
        HTMLDocument doc = repo.getClientReportWeb("123", "HTML");
        assertNull(doc); // Dummy implementation returns null
    }

    @Test
    void testGetClientReportFile() throws Exception {
        File file = repo.getClientReportFile("123", "PDF");
        assertNotNull(file);
        assertTrue(file.getName().endsWith(".pdf"));
    }
}
