package ar.com.laboratorio.steady.pollo_corp.registro.aplication;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out.ClientPrinterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;
import org.w3c.dom.html.HTMLDocument;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientReportUseCaseTest {

    private ClientPrinterRepository clientPrinterRepository;
    private ClientReportUseCase useCase;

    @BeforeEach
    void setUp() {
        clientPrinterRepository = mock(ClientPrinterRepository.class);
        useCase = new ClientReportUseCase(clientPrinterRepository);
    }

    @Test
    void getClientTextReport_returnsReport() {
        when(clientPrinterRepository.getClientTextReport("1")).thenReturn("report");
        String result = useCase.getClientTextReport("1");
        assertEquals("report", result);
    }

    @Test
    void getClientTextReportAsText_returnsText() {
        Text text = mock(Text.class);
        when(clientPrinterRepository.getClientTextReportAsText("1")).thenReturn(text);
        Text result = useCase.getClientTextReportAsText("1");
        assertEquals(text, result);
    }

    @Test
    void exportClientData_returnsBytes() {
        byte[] data = new byte[]{1, 2, 3};
        when(clientPrinterRepository.exportClientData("1", "pdf")).thenReturn(data);
        byte[] result = useCase.exportClientData("1", "pdf");
        assertArrayEquals(data, result);
    }

    @Test
    void getClientReportWeb_returnsHtmlDocument() {
        HTMLDocument doc = mock(HTMLDocument.class);
        when(clientPrinterRepository.getClientReportWeb("1", "html")).thenReturn(doc);
        HTMLDocument result = useCase.getClientReportWeb("1", "html");
        assertEquals(doc, result);
    }

    @Test
    void getClientReportFile_returnsFile() throws Exception {
        File file = mock(File.class);
        when(clientPrinterRepository.getClientReportFile("1", "pdf")).thenReturn(file);
        File result = useCase.getClientReportFile("1", "pdf");
        assertEquals(file, result);
    }
}
