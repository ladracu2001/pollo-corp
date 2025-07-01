package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.dtos;

import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.validations.Cuil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
/**
 * DTO for client registration requests.
 * This class is used to transfer client data from the API to the application layer.
 */
public class ClientRequestDto {
    
    @Cuil
    public String cuil;
    @NotNull
    @NotBlank
    @Size(min = 7, max = 11)
    public String dni;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    public String name;
    public String surname;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    public String lastName;
    public String birthDate; // ISO string
    @Email
    public String email;
    public String phoneNumber;
    public String address;
}
