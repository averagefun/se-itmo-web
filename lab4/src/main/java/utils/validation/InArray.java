package utils.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({FIELD, PARAMETER, TYPE_USE, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InArrayValidator.class)
public @interface InArray {
    String message();

    double[] array();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}