package com.philippo.algafood.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MultipleValidator implements ConstraintValidator<Multiple, Number> {

    private int multipleNumber;

    @Override
    public void initialize(Multiple constraintAnnotation) {
        this.multipleNumber = constraintAnnotation.number();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;

        if (value != null){
            var decimalValue = BigDecimal.valueOf(value.doubleValue());
            var decimalMultiple = BigDecimal.valueOf(this.multipleNumber);
            var rest = decimalValue.remainder(decimalMultiple);

            valid = BigDecimal.ZERO.compareTo(rest) == 0;
        }

        return valid;
    }
}
