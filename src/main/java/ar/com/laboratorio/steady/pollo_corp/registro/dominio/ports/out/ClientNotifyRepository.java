package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.*;

/**
 * Interfaz de repositorio para notificar a los clientes a través de varios canales.
 * Proporciona métodos para enviar notificaciones por correo electrónico, SMS y notificaciones push.
 */
public interface ClientNotifyRepository {
    /**
     * Envía una notificación por correo electrónico al cliente especificado.
     *
     * @param client  el cliente a notificar
     * @param subject el asunto del correo electrónico
     * @param message el cuerpo del correo electrónico
     * @throws IllegalEMailException si el correo electrónico es nulo o inválido
     * @throws ClientStaleInformationException si la información del cliente está desactualizada
     * @throws RuntimeException para otros errores inesperados
     */
    void notifyByEmail(Client client, String subject, String message);

    /**
     * Envía una notificación por SMS al cliente especificado.
     *
     * @param client  el cliente a notificar
     * @param message el contenido del mensaje SMS
     * @throws IllegalPhoneException si el número de teléfono es nulo o inválido
     * @throws ClientStaleInformationException si la información del cliente está desactualizada
     * @throws RuntimeException para otros errores inesperados
     */
    void notifyBySms(Client client, String message);

    /**
     * Envía una notificación push al cliente especificado.
     *
     * @param client  el cliente a notificar
     * @param message el contenido del mensaje de la notificación push
     * @throws ClientNotFoundException si el cliente no existe
     * @throws ClientStaleInformationException si la información del cliente está desactualizada
     * @throws RuntimeException para otros errores inesperados
     */
    void notifyByPush(Client client, String message);
}