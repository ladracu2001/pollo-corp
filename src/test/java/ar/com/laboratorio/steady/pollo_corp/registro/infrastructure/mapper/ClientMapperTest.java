package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.mapper;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Address;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.EMail;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Phone;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.entities.ClientEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ClientMapperTest {

    private final ClientMapper mapper = ClientMapper.INSTANCE;

    @Test
    void testToDomain() {
        ClientEntity entity = new ClientEntity();
        entity.setCuil("20-12345678-3");
        entity.setDni("12345678");
        entity.setName("Juan");
        entity.setSurname("Pérez");
        entity.setLastName("García");
        entity.setBirthDate(LocalDate.of(1990, 1, 1));
        entity.setEmail("juan@mail.com");
        entity.setPhoneNumber("+54-11-12345678");
        entity.setAddress("Calle Falsa, 123, CABA, Buenos Aires, 1000");

        Client client = mapper.toDomain(entity);

        assertThat(client).isNotNull();
        assertThat(client.getCuil()).isEqualTo(new Cuil("20-12345678-3"));
        assertThat(client.getDni()).isEqualTo("12345678");
        assertThat(client.getName()).isEqualTo("Juan");
        assertThat(client.getSurname()).isEqualTo("Pérez");
        assertThat(client.getLastName()).isEqualTo("García");
        assertThat(client.getBirthDate()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(client.getEmail()).isEqualTo(new EMail("juan", "mail.com"));
        assertThat(client.getPhoneNumber()).isEqualTo(new Phone("+54", "11", "12345678"));
        assertThat(client.getAddress()).isEqualTo(new Address("Calle Falsa", "123", "CABA", "Buenos Aires", "1000"));
    }

    @Test
    void testToEntity() {
        Client client = new Client(
                new Cuil("20-12345678-3"),
                "12345678",
                "Juan",
                "Pérez",
                "García",
                LocalDate.of(1990, 1, 1),
                new EMail("juan", "mail.com"),
                new Phone("+54", "11", "12345678"),
                new Address("Calle Falsa", "123", "CABA", "Buenos Aires", "1000")
        );

        ClientEntity entity = mapper.toEntity(client);

        assertThat(entity).isNotNull();
        assertThat(entity.getCuil()).isEqualTo("20-12345678-3");
        assertThat(entity.getDni()).isEqualTo("12345678");
        assertThat(entity.getName()).isEqualTo("Juan");
        assertThat(entity.getSurname()).isEqualTo("Pérez");
        assertThat(entity.getLastName()).isEqualTo("García");
        assertThat(entity.getBirthDate()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(entity.getEmail()).isEqualTo("juan@mail.com");
        assertThat(entity.getPhoneNumber()).isEqualTo("+54-11-12345678");
        assertThat(entity.getAddress()).isEqualTo("Calle Falsa, 123, CABA, Buenos Aires, 1000");
    }

    @Test
    void testNullSafeMapping() {
        assertThat(mapper.toDomain(null)).isNull();
        assertThat(mapper.toEntity(null)).isNull();
    }
}
