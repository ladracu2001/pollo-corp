package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.mapper;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Address;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.EMail;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Phone;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientRequestDto;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos.ClientResponseDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ClientDtoMapperTest {

    private final ClientDtoMapper mapper = ClientDtoMapper.INSTANCE;

    @Test
    void testToDomain() {
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

        Client client = mapper.toDomain(dto);

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
    void testToDto() {
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

        ClientResponseDto dto = mapper.toDto(client);

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
    void testNullSafeMapping() {
        assertThat(mapper.toDomain(null)).isNull();
        assertThat(mapper.toDto(null)).isNull();
    }
    @Test
    void testMetodosAuxiliares() {
        assertThat(mapper.fromAddress(new Address("Calle Falsa", "123", "CABA", "Buenos Aires", "1000"))).isEqualTo("Calle Falsa, 123, CABA, Buenos Aires, 1000");
        assertThat(mapper.fromPhone(new Phone("+54", "11", "12345678"))).isEqualTo("+54-11-12345678");
        assertThat(mapper.fromCuil(new Cuil("20-12345678-3"))).isEqualTo("20-12345678-3");
        assertThat(mapper.fromEMail(new EMail("juan", "mail.com"))).isEqualTo("juan@mail.com");
        assertThat(mapper.fromLocalDate(LocalDate.of(1990, 1, 1))).isEqualTo("1990-01-01");
        assertThat(mapper.toAddress("Calle Falsa, 123, CABA, Buenos Aires, 1000")).isEqualTo(new Address("Calle Falsa", "123", "CABA", "Buenos Aires", "1000"));
        assertThat(mapper.toPhone("+54-11-12345678")).isEqualTo(new Phone("+54", "11", "12345678"));
        assertThat(mapper.toCuil("20-12345678-3")).isEqualTo(new Cuil("20-12345678-3"));
        assertThat(mapper.toEMail("juan@mail.com")).isEqualTo(new EMail("juan", "mail.com"));
        assertThat(mapper.toLocalDate("1990-01-01")).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(mapper.toLocalDate("invalid-date")).isNull(); // Verifica manejo de fechas inválidas
        assertThat(mapper.toEMail("invalid-email")).isNull(); // Verifica manejo de emails inválidos
        assertThat(mapper.toPhone("invalid-phone")).isNull(); // Verifica manejo de teléfonos inválidos
        assertThat(mapper.toCuil("invalid-cuil")).isNull(); // Verifica manejo de CUIL inválidos
        assertThat(mapper.toAddress("invalid-address")).isNull(); // Verifica manejo de direcciones inválidas
    } 
}