package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.*;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DummyClientRepository implements ClientRepository {
    private final Map<String, Client> db = new HashMap<>();

    @Override
    public void crearCliente(Client cliente) {
        if (cliente == null || cliente.getCuil() == null) throw new IllegalCUILException("CUIL nulo");
        if (db.containsKey(cliente.getCuil().toString())) throw new ClientAlreadyExistsException("Ya existe");
        db.put(cliente.getCuil().toString(), cliente);
    }

    @Override
    public void guardarCliente(Client cliente) {
        if (cliente == null || cliente.getCuil() == null) throw new IllegalCUILException("CUIL nulo");
        if (!db.containsKey(cliente.getCuil().toString())) throw new ClientNotFoundException("No existe");
        db.put(cliente.getCuil().toString(), cliente);
    }

    @Override
    public Optional<List<Client>> buscarClientePorDni(String dni) {
        List<Client> result = db.values().stream().filter(c -> dni.equals(c.getDni())).toList();
        if (result.isEmpty()) throw new ClientNotFoundException("No encontrado");
        if (result.size() > 1) throw new ClientMoreThanOneFoundException("Más de uno");
        return Optional.of(result);
    }

    @Override
    public Optional<Client> buscarPorCuil(Cuil cuil) {
        if (cuil == null) throw new IllegalCUILException("CUIL nulo");
        return Optional.ofNullable(db.get(cuil.toString()));
    }

    @Override
    public void eliminarCliente(Cuil cuil) {
        if (cuil == null) throw new IllegalCUILException("CUIL nulo");
        if (!db.containsKey(cuil.toString())) throw new ClientNotFoundException("No existe");
        db.remove(cuil.toString());
    }

    @Override
    public List<Client> findAll() {
        return new ArrayList<>(db.values());
    }
}

class ClientRepositoryTest {

    private final ClientRepository repo = new DummyClientRepository();

    private Client buildClient(String dni, String cuil) {
        return new Client(
            new Cuil(cuil),
                dni,
                "Juan",
                "Pérez",
                "Gómez",
                java.time.LocalDate.of(1990, 1, 1),
                null, null, null
        );
    }

    @Test
    void testCrearYBuscarCliente() {
        Client client = buildClient("12345678", "20-12345678-3");
        repo.crearCliente(client);
        Optional<Client> found = repo.buscarPorCuil(new Cuil("20-12345678-3"));
        assertTrue(found.isPresent());
        assertEquals("12345678", found.get().getDni());
    }

    @Test
    void testCrearClienteYaExistenteLanzaExcepcion() {
        Client client = buildClient("12345678", "20-12345678-3");
        repo.crearCliente(client);
        assertThrows(ClientAlreadyExistsException.class, () -> repo.crearCliente(client));
    }

    @Test
    void testGuardarClienteNoExistenteLanzaExcepcion() {
        Client client = buildClient("12345678", "20-12345678-3");
        assertThrows(ClientNotFoundException.class, () -> repo.guardarCliente(client));
    }

    @Test
    void testEliminarCliente() {
        Client client = buildClient("12345678", "20-12345678-3");
        repo.crearCliente(client);
        repo.eliminarCliente(new Cuil("20-12345678-3"));
        assertTrue(repo.buscarPorCuil(new Cuil("20-12345678-3")).isEmpty());
    }

    @Test
    void testEliminarClienteNoExistenteLanzaExcepcion() {
        assertThrows(ClientNotFoundException.class, () -> repo.eliminarCliente(new Cuil("20-00000000-0")));
    }

    @Test
    void testBuscarClientePorDni() {
        Client client = buildClient("12345678", "20-12345678-3");
        repo.crearCliente(client);
        Optional<List<Client>> result = repo.buscarClientePorDni("12345678");
        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
    }

    @Test
    void testBuscarClientePorDniNoExistenteLanzaExcepcion() {
        assertThrows(ClientNotFoundException.class, () -> repo.buscarClientePorDni("99999999"));
    }

    @Test
    void testFindAll() {
        Client client1 = buildClient("12345678", "20-12345678-3");
        Client client2 = buildClient("87654321", "20-87654321-3");
        repo.crearCliente(client1);
        repo.crearCliente(client2);

        List<Client> all = repo.findAll();
        assertEquals(2, all.size());
        assertTrue(all.stream().anyMatch(c -> c.getCuil().toString().equals("20-12345678-3")));
        assertTrue(all.stream().anyMatch(c -> c.getCuil().toString().equals("20-87654321-3")));
    }
}
