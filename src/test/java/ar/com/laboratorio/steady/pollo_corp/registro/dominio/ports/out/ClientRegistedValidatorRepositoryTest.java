package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Address;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Phone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyClientRegistedValidatorRepository implements ClientRegistedValidatorRepository {

    @Override
    public boolean isClientRegistered(String dni) {
        return "12345678".equals(dni);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return "test@mail.com".equals(email);
    }

    @Override
    public boolean isPhoneNumberRegistered(Phone phoneNumber) {
        return phoneNumber != null && "+54".equals(phoneNumber.codPais()) && "11".equals(phoneNumber.codCiudad()) && "12345678".equals(phoneNumber.numAbonado());
    }

    @Override
    public boolean isAddressRegistered(Address address) {
        return address != null && "Calle Falsa".equals(address.direccion());
    }

    @Override
    public boolean isCuilRegistered(Cuil cuil) {
        return cuil != null && "20-12345678-3".equals(cuil.valor());
    }
}

class ClientRegistedValidatorRepositoryTest {

    private final ClientRegistedValidatorRepository repo = new DummyClientRegistedValidatorRepository();

    @Test
    void testIsClientRegistered() {
        assertTrue(repo.isClientRegistered("12345678"));
        assertFalse(repo.isClientRegistered("87654321"));
    }

    @Test
    void testIsEmailRegistered() {
        assertTrue(repo.isEmailRegistered("test@mail.com"));
        assertFalse(repo.isEmailRegistered("otro@mail.com"));
    }

    @Test
    void testIsPhoneNumberRegistered() {
        Phone phone = new Phone("+54", "11", "12345678");
        assertTrue(repo.isPhoneNumberRegistered(phone));
        assertFalse(repo.isPhoneNumberRegistered(new Phone("+54", "11", "00000000")));
    }

    @Test
    void testIsAddressRegistered() {
        Address address = new Address("Calle Falsa", "123", "Ciudad", "Provincia", "1000");
        assertTrue(repo.isAddressRegistered(address));
        Address other = new Address("Otra Calle", "456", "Ciudad", "Provincia", "1000");
        assertFalse(repo.isAddressRegistered(other));
    }

    @Test
    void testIsCuilRegistered() {
        Cuil cuil = new Cuil("20-12345678-3");
        assertTrue(repo.isCuilRegistered(cuil));
        assertFalse(repo.isCuilRegistered(new Cuil("27-87654321-5")));
    }
}
