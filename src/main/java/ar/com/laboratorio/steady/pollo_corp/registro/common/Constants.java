package ar.com.laboratorio.steady.pollo_corp.registro.common;

public interface Constants {

    String EMAIL_EXCEPTION_NULL = "El cliente o su correo electrónico no pueden ser nulos";
    String EMAIL_EXCEPTION_INVALID = "El correo electrónico %s proporcionado es inválido";
    String EMAIL_EXCEPTION_NOTIFICATION = "Error al enviar la notificación por correo electrónico: ";
    String CLIENT_EXCEPTION = "Error al crear el cliente: ";
    String CLIENT_EXCEPTION_STALE = "La información del cliente %c está desactualizada";
    String CLIENT_EXCEPTION_EXIST = "El cliente %s ya existe";
    String CLIENT_EXCEPTION_NOTEXIST = "El cliente %s no existe";
    String CLIENT_EXCEPTION_MULTIEXIST = "Se encontraron múltiples clientes con el mismo DNI %s";
    String PHONE_EXCEPTION_INVALID = "El número de teléfono %s proporcionado es inválido";
    String PHONE_EXCEPTION_NOTIFICATION = "Error al enviar la notificación por SMS: ";
    String CUIL_EXCEPTION_NOTFOUND = "El cliente con CUIL %s no fue encontrado";
    String CUIL_EXCEPTION_NULL = "El CUIL %s no puede ser nulo";
    String CUIL_EXCEPTION_INVALID = "El CUIL %s proporcionado es inválido";
    String ADDRESS_EXCEPTION_INVALID = "La dirección %s proporcionada es inválida";
    String PUSH_EXCEPTION_NOTIFICATION = "Error al enviar la notificación push: ";
}
