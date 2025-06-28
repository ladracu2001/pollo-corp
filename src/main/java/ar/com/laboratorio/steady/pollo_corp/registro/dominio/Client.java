package ar.com.laboratorio.steady.pollo_corp.registro.dominio;

import java.time.LocalDate;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalAddressException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalEMailException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalPhoneException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Address;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.EMail;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private String dni;
    private Cuil cuil;
    private String name;
    private String surname;
    private String lastName;
    private LocalDate birthDate;
    private EMail email;
    private Phone phoneNumber;
    private Address address;

    public void changeEmail(EMail email) {
        if (email == null) {
            throw new IllegalEMailException("El correo electrónico no puede ser nulo");
        }
        this.email = email;
    }
    public void changePhoneNumber(Phone phoneNumber) {
        if (phoneNumber == null) {
            throw new IllegalPhoneException("El número de teléfono no puede ser nulo");
        }
        this.phoneNumber = phoneNumber;
    }
    public void changeAddress(Address address) {
        if (address == null) {
            throw new IllegalAddressException("La dirección no puede ser nula");
        }
        this.address = address;
    }
    public void validateBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }
        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura");
        }
        this.birthDate = birthDate;
    }
    @Override
    public String toString() {
        return "Client{" +
                "dni='" + dni + '\'' +
                ", cuil='" + (cuil != null ? cuil.toString() : null) + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + (email != null ? email.toString() : null) + '\'' +
                ", phoneNumber='" + (phoneNumber != null ? phoneNumber.toString() : null) + '\'' +
                ", address='" + (address != null ? address.toString() : null)  + '\'' +
                '}';
    }
}
