package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.mapper;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Address;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.EMail;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Phone;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.entities.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "cuil", source = "cuil", qualifiedByName = "toCuil")
    @Mapping(target = "email", source = "email", qualifiedByName = "toEMail")
    @Mapping(target = "phoneNumber", source = "phoneNumber", qualifiedByName = "toPhone")
    @Mapping(target = "address", source = "address", qualifiedByName = "toAddress")
    Client toDomain(ClientEntity entity);

    @Mapping(target = "cuil", source = "cuil", qualifiedByName = "fromCuil")
    @Mapping(target = "email", source = "email", qualifiedByName = "fromEMail")
    @Mapping(target = "phoneNumber", source = "phoneNumber", qualifiedByName = "fromPhone")
    @Mapping(target = "address", source = "address", qualifiedByName = "fromAddress")
    ClientEntity toEntity(Client client);

    // --- Métodos auxiliares robustos ---

    @Named("toCuil")
    default Cuil toCuil(String cuil) {
        try {
            return cuil != null ? new Cuil(cuil) : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Named("fromCuil")
    default String fromCuil(Cuil cuil) {
        return cuil != null ? cuil.toString() : null;
    }

    @Named("toEMail")
    default EMail toEMail(String email) {
        try {
            if (email != null && email.contains("@")) {
                String[] parts = email.split("@", 2);
                return new EMail(parts[0], parts[1]);
            }
        } catch (Exception e) {
            // Ignorar y devolver null
        }
        return null;
    }

    @Named("fromEMail")
    default String fromEMail(EMail email) {
        return email != null ? email.toString() : null;
    }

    @Named("toPhone")
    default Phone toPhone(String phone) {
        try {
            if (phone != null) {
                String[] parts = phone.split("-", 3);
                String codPais = parts.length > 0 ? parts[0] : null;
                String codCiudad = parts.length > 1 ? parts[1] : null;
                String numAbonado = parts.length > 2 ? parts[2] : null;
                return new Phone(codPais, codCiudad, numAbonado);
            }
        } catch (Exception e) {
            // Ignorar y devolver null
        }
        return null;
    }

    @Named("fromPhone")
    default String fromPhone(Phone phone) {
        return phone != null
            ? String.format("%s-%s-%s", phone.codPais(), phone.codCiudad(), phone.numAbonado())
            : null;
    }

    @Named("toAddress")
    default Address toAddress(String address) {
        try {
            if (address != null) {
                String[] parts = address.split(",", 5);
                String direccion = parts.length > 0 ? parts[0].trim() : null;
                String numero = parts.length > 1 ? parts[1].trim() : null;
                String ciudad = parts.length > 2 ? parts[2].trim() : null;
                String provincia = parts.length > 3 ? parts[3].trim() : null;
                String codigoPostal = parts.length > 4 ? parts[4].trim() : null;
                return new Address(direccion, numero, ciudad, provincia, codigoPostal);
            }
        } catch (Exception e) {
            // Ignorar y devolver null
        }
        return null;
    }

    @Named("fromAddress")
    default String fromAddress(Address address) {
        return address != null
            ? String.format("%s, %s, %s, %s, %s",
                address.direccion(),
                address.numero(),
                address.ciudad(),
                address.provincia(),
                address.codigoPostal())
            : null;
    }
}
