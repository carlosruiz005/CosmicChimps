package com.cosmicchimps.demo.validators;

import com.cosmicchimps.demo.util.ConstraintAnnotations;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
public class NotEmptyListValidator implements ConstraintValidator<ConstraintAnnotations.NotEmptyListConstraint, List<String>> {

    @Override
    public boolean isValid(List<String> values, ConstraintValidatorContext context) {
        return values != null && !values.isEmpty();
    }
}
