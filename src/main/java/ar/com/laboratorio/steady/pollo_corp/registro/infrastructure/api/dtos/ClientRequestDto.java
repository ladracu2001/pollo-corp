package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos;

import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.validations.Cuil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * DTO for client registration requests.
 * This class is used to transfer client data from the API to the application layer.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDto {
    
    @Cuil
    private String cuil;
    @NotNull
    @NotBlank
    @Size(min = 7, max = 11)
    private String dni;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    private String surname;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;
    private String birthDate; // ISO string
    @Email
    private String email;
    private String phoneNumber;
    private String address;
}
