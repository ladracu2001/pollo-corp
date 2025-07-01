package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDto {
    private String cuil;
    private String dni;
    private String name;
    private String surname;
    private String lastName;
    private String birthDate;
    private String email;
    private String phoneNumber;
    private String address;
}
