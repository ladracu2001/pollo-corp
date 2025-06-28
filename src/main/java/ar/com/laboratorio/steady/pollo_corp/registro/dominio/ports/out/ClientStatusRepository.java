package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;

public interface ClientStatusRepository {

    boolean isClientActive(Cuil cuil);
    boolean isValidClient(Cuil cuil);
}
