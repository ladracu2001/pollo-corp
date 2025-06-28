package ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalAddressException;

public record Address(String direccion, String numero, String ciudad, String provincia, String codigoPostal) {

    public Address {
        if(direccion == null || direccion.isEmpty()) {
            throw new IllegalAddressException("La dirección no puede ser nula o vacía");
        }
        if(numero == null || numero.isEmpty()) {
            numero = "S/N"; // Asignar un valor por defecto si el número es nulo o vacío
        }
        if(ciudad == null || ciudad.isEmpty()) {
            throw new IllegalAddressException("La ciudad no puede ser nula o vacía");
        }
        if(provincia == null || provincia.isEmpty()) {
            throw new IllegalAddressException("La provincia no puede ser nula o vacía");
        }
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", direccion, numero, ciudad, provincia, codigoPostal);
    }

}
