package ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalCUILException;

public record Cuil(String valor) {

    public Cuil {
        if (valor == null || valor.isBlank()) {
            throw new IllegalCUILException("El CUIL no puede estar vacío");
        }

        if (!valor.matches("\\d{2}-\\d{8}-\\d")) {
            throw new IllegalCUILException("Formato inválido de CUIL: " + valor);
        }
    }

    @Override
    public String toString() {
        return valor;
    }
}
