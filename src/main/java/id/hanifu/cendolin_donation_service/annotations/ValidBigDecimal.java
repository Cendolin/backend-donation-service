package id.hanifu.cendolin_donation_service.annotations;

import id.hanifu.cendolin_donation_service.validators.BigDecimalValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BigDecimalValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBigDecimal {
    String message() default "Invalid BigDecimal value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String minValue() default "0";
    String maxValue() default "100000000000000000000000";
}
