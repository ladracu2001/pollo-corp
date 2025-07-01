package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.validations;

public class CuilValidator implements jakarta.validation.ConstraintValidator<Cuil, String> {

    @Override
    public boolean isValid(String cuil, jakarta.validation.ConstraintValidatorContext context) {
        return (cuil == null || cuil.isEmpty()|| !cuil.matches("\\d{2}-\\d{8}-\\d")); // CUIL must match the format XX-XXXXXXXX-X
    }

}
