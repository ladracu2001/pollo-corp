package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyClientStatusRepository implements ClientStatusRepository {

    @Override
    public boolean isClientActive(Cuil cuil) {
        return cuil != null && "20-12345678-3".equals(cuil.valor());
    }

    @Override
    public boolean isValidClient(Cuil cuil) {
        return cuil != null && cuil.valor().startsWith("20-");
    }
}

class ClientStatusRepositoryTest {

    private final ClientStatusRepository repo = new DummyClientStatusRepository();

    @Test
    void testIsClientActive() {
        assertTrue(repo.isClientActive(new Cuil("20-12345678-3")));
        assertFalse(repo.isClientActive(new Cuil("27-87654321-5")));
    }

    @Test
    void testIsValidClient() {
        assertTrue(repo.isValidClient(new Cuil("20-12345678-3")));
        assertFalse(repo.isValidClient(new Cuil("27-87654321-5")));
    }

    @Test
    void testNullCuil() {
        assertFalse(repo.isClientActive(null));
        assertFalse(repo.isValidClient(null));
    }
}
