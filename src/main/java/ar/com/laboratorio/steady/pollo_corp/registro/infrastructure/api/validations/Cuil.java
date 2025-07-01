package ar.com.laboratorio.steady.pollo_corp.registro.infrastructure.api.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Documented
@Constraint(
    validatedBy = CuilValidator.class
)
@Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.PARAMETER})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Cuil {
    String message() default "Invalid CUIL format. Expected format: XX-XXXXXXXX-X";

    Class<?>[] groups() default {};

    Class<? extends jakarta.validation.Payload>[] payload() default {};
}
