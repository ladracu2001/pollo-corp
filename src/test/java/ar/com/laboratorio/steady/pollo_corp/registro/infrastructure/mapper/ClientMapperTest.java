package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.mapper;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Address;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.EMail;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Phone;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.entities.ClientEntity;
import org.junit.jupiter.api.Test;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientRequestDto;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientResponseDto;
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

    @Test
    void testToDtoFromRequest() {
        ClientRequestDto dto = new ClientRequestDto();
        dto.setCuil("20-12345678-3");
        dto.setDni("12345678");
        dto.setName("Juan");
        dto.setSurname("Pérez");
        dto.setLastName("García");
        dto.setBirthDate("1990-01-01");
        dto.setEmail("juan@mail.com");
        dto.setPhoneNumber("+54-11-12345678");
        dto.setAddress("Calle Falsa, 123, CABA, Buenos Aires, 1000");

        Client client = mapper.toDto(dto);

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
    void testFromDtoToResponse() {
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

        ClientResponseDto dto = mapper.fromDto(client);

        assertThat(dto).isNotNull();
        assertThat(dto.getCuil()).isEqualTo("20-12345678-3");
        assertThat(dto.getDni()).isEqualTo("12345678");
        assertThat(dto.getName()).isEqualTo("Juan");
        assertThat(dto.getSurname()).isEqualTo("Pérez");
        assertThat(dto.getLastName()).isEqualTo("García");
        assertThat(dto.getBirthDate()).isEqualTo("1990-01-01");
        assertThat(dto.getEmail()).isEqualTo("juan@mail.com");
        assertThat(dto.getPhoneNumber()).isEqualTo("+54-11-12345678");
        assertThat(dto.getAddress()).isEqualTo("Calle Falsa, 123, CABA, Buenos Aires, 1000");
    }

    @Test
    void testToDtoFromRequestNullSafe() {
        assertThat(mapper.toDto((ClientRequestDto) null)).isNull();
    }

    @Test
    void testFromDtoToResponseNullSafe() {
        assertThat(mapper.fromDto((Client) null)).isNull();
    }

    @Test
    void testToCuil_nullAndInvalid() {
        assertThat(mapper.toCuil(null)).isNull();
        assertThat(mapper.toCuil("")).isNull(); // Suponiendo que new Cuil("") lanza excepción
    }

    @Test
    void testToDtoCuil_nullAndInvalid() {
        assertThat(mapper.toDtoCuil(null)).isNull();
        assertThat(mapper.toDtoCuil("")).isNull();
    }

    @Test
    void testToEMail_nullAndInvalid() {
        assertThat(mapper.toEMail(null)).isNull();
        assertThat(mapper.toEMail("sinarroba")).isNull();
    }

    @Test
    void testToDtoEMail_nullAndInvalid() {
        assertThat(mapper.toDtoEMail(null)).isNull();
        assertThat(mapper.toDtoEMail("sinarroba")).isNull();
    }

    @Test
    void testToPhone_nullAndInvalid() {
        assertThat(mapper.toPhone(null)).isNull();
        assertThat(mapper.toPhone("")).isNull(); // Devuelve Phone con campos null
        assertThat(mapper.toPhone("solo-uno")).isNull(); // Devuelve Phone con algunos campos null
    }

    @Test
    void testToDtoPhone_nullAndInvalid() {
        assertThat(mapper.toDtoPhone(null)).isNull();
        assertThat(mapper.toDtoPhone("")).isNull();
        assertThat(mapper.toDtoPhone("solo-uno")).isNull();
    }

    @Test
    void testToAddress_nullAndInvalid() {
        assertThat(mapper.toAddress(null)).isNull();
        assertThat(mapper.toAddress("")).isNull(); // Devuelve Address con campos null
    }

    @Test
    void testToDtoAddress_nullAndInvalid() {
        assertThat(mapper.toDtoAddress(null)).isNull();
        assertThat(mapper.toDtoAddress("")).isNull();
    }

    @Test
    void testToDtoLocalDate_nullAndInvalid() {
        assertThat(mapper.toDtoLocalDate(null)).isNull();
        assertThat(mapper.toDtoLocalDate("fecha-invalida")).isNull();
    }

    @Test
    void testFromDtoCuil_null() {
        assertThat(mapper.fromDtoCuil(null)).isNull();
    }

    @Test
    void testFromCuil_null() {
        assertThat(mapper.fromCuil(null)).isNull();
    }

    @Test
    void testFromDtoEMail_null() {
        assertThat(mapper.fromDtoEMail(null)).isNull();
    }

    @Test
    void testFromEMail_null() {
        assertThat(mapper.fromEMail(null)).isNull();
    }

    @Test
    void testFromDtoPhone_null() {
        assertThat(mapper.fromDtoPhone(null)).isNull();
    }

    @Test
    void testFromPhone_null() {
        assertThat(mapper.fromPhone(null)).isNull();
    }

    @Test
    void testFromDtoAddress_null() {
        assertThat(mapper.fromDtoAddress(null)).isNull();
    }

    @Test
    void testFromAddress_null() {
        assertThat(mapper.fromAddress(null)).isNull();
    }

    @Test
    void testFromDtoLocalDate_null() {
        assertThat(mapper.fromDtoLocalDate(null)).isNull();
    }
}
