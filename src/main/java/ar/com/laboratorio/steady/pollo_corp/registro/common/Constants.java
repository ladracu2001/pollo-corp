package ar.com.laboratorio.steady.pollo_corp.registro.common;

public class Constants {

    private Constants() {
        // Prevent instantiation
    }
    public static final String EMAIL_EXCEPTION_NULL = "El cliente o su correo electrónico no pueden ser nulos";
    public static final String EMAIL_EXCEPTION_INVALID = "El correo electrónico %s proporcionado es inválido";
    public static final String EMAIL_EXCEPTION_NOTIFICATION = "Error al enviar la notificación por correo electrónico: ";
    public static final String CLIENT_EXCEPTION = "Error al crear el cliente: ";
    public static final String CLIENT_EXCEPTION_STALE = "La información del cliente %c está desactualizada";
    public static final String CLIENT_EXCEPTION_EXIST = "El cliente %s ya existe";
    public static final String CLIENT_EXCEPTION_NOTEXIST = "El cliente %s no existe";
    public static final String CLIENT_EXCEPTION_MULTIEXIST = "Se encontraron múltiples clientes con el mismo DNI %s";
    public static final String PHONE_EXCEPTION_INVALID = "El número de teléfono %s proporcionado es inválido";
    public static final String PHONE_EXCEPTION_NOTIFICATION = "Error al enviar la notificación por SMS: ";
    public static final String CUIL_EXCEPTION_NOTFOUND = "El cliente con CUIL %s no fue encontrado";
    public static final String CUIL_EXCEPTION_NULL = "El CUIL %s no puede ser nulo";
    public static final String CUIL_EXCEPTION_INVALID = "El CUIL %s proporcionado es inválido";
    public static final String ADDRESS_EXCEPTION_INVALID = "La dirección %s proporcionada es inválida";
    public static final String PUSH_EXCEPTION_NOTIFICATION = "Error al enviar la notificación push: ";
}
