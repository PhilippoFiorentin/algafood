package com.philippo.algafood.core.validation;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;

public class ValueZeroIncludesDescriptionValidator implements ConstraintValidator<ValueZeroIncludesDescription, Object> {

    private String valueField;
    private String descriptionField;
    private String mandatoryDescription;

    @Override
    public void initialize(ValueZeroIncludesDescription constraint) {
        this.valueField = constraint.valueField();
        this.descriptionField = constraint.descriptionField();
        this.mandatoryDescription = constraint.mandatoryDescription();
    }

    @Override
    public boolean isValid(Object objectValidation, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;

        try {
            BigDecimal value = (BigDecimal) BeanUtils.getPropertyDescriptor(objectValidation.getClass(), valueField)
                    .getReadMethod().invoke(objectValidation);
            String description = (String) BeanUtils.getPropertyDescriptor(objectValidation.getClass(), descriptionField)
                    .getReadMethod().invoke(objectValidation);

            if (value != null && description != null && BigDecimal.ZERO.compareTo(value) == 0) {
                valid = description.toLowerCase().contains(this.mandatoryDescription.toLowerCase());

            }

            return valid;

        } catch (Exception e){
            throw new ValidationException(e);
        }

    }
}
