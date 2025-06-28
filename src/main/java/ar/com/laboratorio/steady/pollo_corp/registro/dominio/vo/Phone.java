package ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalPhoneException;

public record Phone(String codPais, String codCiudad, String numAbonado) {

    public Phone {
        // Puedes agregar validaciones aquí si lo deseas
        if (codPais == null || codPais.isEmpty()) {
            codPais = "+54"; // Código de país por defecto para Argentina
        }
        if (codCiudad == null || codCiudad.isEmpty()) {
            codCiudad = "11"; // Código de ciudad por defecto para amba
        }
        if (numAbonado == null || numAbonado.isEmpty()) {
            throw new IllegalPhoneException("El número no puede ser nulo o vacío");
        }
    }

    @Override
    public String toString() {
        return codPais + " (" + codCiudad + ")" + " " + numAbonado;
    }

}
