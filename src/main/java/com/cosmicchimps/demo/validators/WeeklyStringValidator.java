package com.cosmicchimps.demo.validators;

import com.cosmicchimps.demo.util.ConstraintAnnotations;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
public class WeeklyStringValidator implements ConstraintValidator<ConstraintAnnotations.WeeklyStringConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("WEEKLY");
    }
}
