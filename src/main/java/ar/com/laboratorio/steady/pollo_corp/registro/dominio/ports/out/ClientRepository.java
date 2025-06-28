package ar.com.laboratorio.steady.pollo_corp.registro.dominio.ports.out;

import java.util.List;
import java.util.Optional;

import ar.com.laboratorio.steady.pollo_corp.registro.dominio.Client;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientAlreadyExistsException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientMoreThanOneFoundException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientNotFoundException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.ClientStaleInformationException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalAddressException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalCUILException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalEMailException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.excepciones.IllegalPhoneException;
import ar.com.laboratorio.steady.pollo_corp.registro.dominio.vo.Cuil;

public interface ClientRepository {
    /**
     * Crea un nuevo cliente.
     *
     * @param cliente El cliente a crear.
     * @throws IllegalCUILException Si el CUIL del cliente es nulo o inválido.
     * @throws IllegalAddressException Si la dirección del cliente es inválida.
     * @throws IllegalEMailException Si el correo electrónico del cliente es inválido.
     * @throws IllegalPhoneException Si el número de teléfono del cliente es inválido.
     * @throws ClientAlreadyExistsException Si el cliente ya existe.
     */
    void crearCliente(Client cliente);
     /**
     * Actualiza la información de un cliente existente.
     *
     * @param cliente El cliente con la información actualizada.
     * @throws IllegalCUILException Si el CUIL del cliente es nulo o inválido.
     * @throws IllegalAddressException Si la dirección del cliente es inválida.
     * @throws IllegalEMailException Si el correo electrónico del cliente es inválido.
     * @throws IllegalPhoneException Si el número de teléfono del cliente es inválido.
     * @throws ClientNotFoundException Si el cliente con el CUIL proporcionado no existe.
     * @throws ClientStaleInformationException Si la información del cliente está desactualizada.
     */
    void guardarCliente(Client cliente);
    /**
     * Busca un cliente por su DNI.
     *
     * @param dni El DNI del cliente a buscar.
     * @return Una lista de clientes que coinciden con el DNI proporcionado.
     * @throws ClientNotFoundException Si no se encuentran clientes con el DNI proporcionado.
     * @throws ClientMoreThanOneFoundException Si se encuentran múltiples clientes con el mismo DNI.
     */
    Optional<List<Client>> buscarClientePorDni(String dni);
    /**
     * Busca un cliente por su CUIL.
     *
     * @param cuil El CUIL del cliente a buscar.
     * @return Un cliente que coincide con el CUIL proporcionado, o vacío si no se encuentra.
     * @throws IllegalCUILException Si el CUIL es nulo.
     */
    Optional<Client> buscarPorCuil(Cuil cuil);
    /**
     * Elimina un cliente por su CUIL.
     *
     * @param cuil El CUIL del cliente a eliminar.
     * @throws IllegalCUILException Si el CUIL es nulo.
     * @throws ClientNotFoundException Si el cliente con el CUIL proporcionado no existe.
     */
    void eliminarCliente(Cuil cuil);
}
