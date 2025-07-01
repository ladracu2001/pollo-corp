package ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalEMailException;

public record EMail(String userMail, String domain) {

    public EMail {
        if (userMail == null || userMail.isEmpty()) {
            throw new IllegalEMailException("El usuario del correo electrónico no puede ser nulo o vacío");
        }
        if (domain == null || domain.isEmpty()) {
            throw new IllegalEMailException("El dominio del correo electrónico no puede ser nulo o vacío");
        }
        if(!(userMail+"@"+domain).matches("^[\\w-.]+@([\\w-]+\\.)+{25}[\\w-]{2,4}$")){
            throw new IllegalEMailException("El formato del correo electrónico es inválido");
        }
    }

    @Override
    public String toString() {
        return String.format("%s@%s", userMail, domain);
    }

}
