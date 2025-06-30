package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.entities.ClientEntity;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    ClientEntity toEntity(Client client);
    Client toDomain(ClientEntity clientEntity);
}
