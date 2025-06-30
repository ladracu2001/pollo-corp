package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.entities.ClientEntity;

public interface SpringDataClientRepository extends JpaRepository<ClientEntity, String> {

    Optional<ClientEntity> findByCuil(String cuil);

    List<ClientEntity> findByDni(String dni);

    boolean existsByCuil(String cuil);
}
