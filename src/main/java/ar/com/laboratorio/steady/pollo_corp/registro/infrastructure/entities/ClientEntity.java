package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "client", schema = "pollo_corp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    @Id
    @Column(name = "cuil", nullable = false, unique = true, length = 20)
    @NotNull(message = "El CUIL no puede ser nulo")
    @Size(max = 20, message = "El CUIL no puede tener más de 20 caracteres")
    private String cuil;

    @Column(name = "dni", length = 20, nullable = false)
    @NotNull(message = "El DNI no puede ser nulo")
    @Size(max = 20, message = "El DNI no puede tener más de 20 caracteres")
    private String dni;

    @Column(name = "name", length = 50, nullable = false)
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String name;

    @Column(name = "surname", length = 50)
    @Size(max = 50, message = "El apellido no puede tener más de 50 caracteres")
    private String surname;

    @Column(name = "lastName", length = 50, nullable = false)
    @NotNull(message = "El segundo apellido no puede ser nulo")
    @Size(max = 50, message = "El segundo apellido no puede tener más de 50 caracteres")
    private String lastName;

    @Column(name = "birthDate", nullable = false)
    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    private LocalDate birthDate;

    @Column(name = "email", length = 75)
    @Size(max = 75, message = "El email no puede tener más de 75 caracteres")
    private String email;

    @Column(name = "phoneNumber", length = 50)
    @Size(max = 50, message = "El teléfono no puede tener más de 50 caracteres")
    private String phoneNumber;

    @Column(name = "address", length = 255)
    @Size(max = 255, message = "La dirección no puede tener más de 255 caracteres")
    private String address;
}