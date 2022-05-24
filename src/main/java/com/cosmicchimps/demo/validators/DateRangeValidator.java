package com.cosmicchimps.demo.validators;

import com.cosmicchimps.demo.util.ConstraintAnnotations;
import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

/**
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
public class DateRangeValidator implements ConstraintValidator<ConstraintAnnotations.NotBeforeStartDateConstraint, Object> {

    private String startDate;
    private String endDate;

    public void initialize(ConstraintAnnotations.NotBeforeStartDateConstraint constraintAnnotation) {
        this.startDate = constraintAnnotation.startDate();
        this.endDate = constraintAnnotation.endDate();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            LocalDate startDate = (LocalDate) new BeanWrapperImpl(value).getPropertyValue(this.startDate);
            LocalDate endDate = (LocalDate) new BeanWrapperImpl(value).getPropertyValue(this.endDate);
            return startDate.isBefore(endDate);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
