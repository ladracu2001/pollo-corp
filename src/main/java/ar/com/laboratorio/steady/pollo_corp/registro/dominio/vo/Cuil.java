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

        if (!esValido(valor)) {
            throw new IllegalCUILException("CUIL inválido según dígito verificador");
        }
    }

    private static boolean esValido(String cuil) {
        // Eliminar guiones
        String digits = cuil.replaceAll("-", "");
        if (digits.length() != 11 || !digits.matches("\\d+")) return false;

        int[] pesos = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
        int suma = 0;

        for (int i = 0; i < 10; i++) {
            suma += Character.getNumericValue(digits.charAt(i)) * pesos[i];
        }

        int resto = suma % 11;
        int verificadorCalculado = resto == 0 ? 0 : resto == 1 ? 9 : 11 - resto;
        int verificadorReal = Character.getNumericValue(digits.charAt(10));

        return verificadorCalculado == verificadorReal;
    }
    @Override
    public String toString() {
        return valor;
    }
}
