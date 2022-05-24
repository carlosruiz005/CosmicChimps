package com.cosmicchimps.demo.validators;

import com.cosmicchimps.demo.enums.DayEnum;
import com.cosmicchimps.demo.util.ConstraintAnnotations;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
public class DayNamesValidator implements ConstraintValidator<ConstraintAnnotations.DayNamesConstraint, List<String>> {

    @Override
    public boolean isValid(List<String> values, ConstraintValidatorContext context) {
        return values.stream().allMatch(v -> {
            try {
                DayEnum.valueOf(v);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        });
    }

}
