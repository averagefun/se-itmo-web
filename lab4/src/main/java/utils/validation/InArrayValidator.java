package utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.stream.DoubleStream;

public class InArrayValidator implements ConstraintValidator<InArray, Integer> {
    protected double[] array;

    @Override
    public void initialize(InArray inArray) {
        this.array = inArray.array();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && DoubleStream.of(array).anyMatch(x -> x == value);
    }
}