package id.hanifu.cendolin_donation_service.validators;

import id.hanifu.cendolin_donation_service.annotations.ValidBigDecimal;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class BigDecimalValidator
        implements ConstraintValidator<ValidBigDecimal, BigDecimal> {
    private String minValue;
    private String maxValue;

    @Override
    public void initialize(ValidBigDecimal constraintAnnotation) {
        this.minValue = constraintAnnotation.minValue();
        this.maxValue = constraintAnnotation.maxValue();
    }

    @Override
    public boolean isValid(
            BigDecimal value,
            ConstraintValidatorContext context
    ) {
        if (value == null) {
            return false;
        }
        try {
            BigDecimal min = new BigDecimal(minValue);
            BigDecimal max = new BigDecimal(maxValue);
            return value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
        } catch (NumberFormatException e) {
            return false; // invalid min/max strings
        }
    }
}
