package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Address;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Phone;

public interface ClientRegistedValidatorRepository {

    boolean isClientRegistered(String dni);
    boolean isEmailRegistered(String email);
    boolean isPhoneNumberRegistered(Phone phoneNumber);
    boolean isAddressRegistered(Address address);
    boolean isCuilRegistered(Cuil cuil);
}
