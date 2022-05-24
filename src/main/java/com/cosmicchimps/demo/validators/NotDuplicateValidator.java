package com.cosmicchimps.demo.validators;

import com.cosmicchimps.demo.util.ConstraintAnnotations;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
public class NotDuplicateValidator implements ConstraintValidator<ConstraintAnnotations.NotDuplicateConstraint, List<String>> {

    @Override
    public boolean isValid(List<String> values, ConstraintValidatorContext context) {
        Set<String> items = new HashSet<>();
        if (values != null) {
            Set<String> d = values.stream().filter(v -> !items.add(v)).collect(Collectors.toSet());
            if (d.size() == 0) {
                return true;
            }
        }
        return false;
    }
}
