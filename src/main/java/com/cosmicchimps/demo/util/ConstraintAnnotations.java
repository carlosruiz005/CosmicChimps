package com.cosmicchimps.demo.util;

import com.cosmicchimps.demo.validators.DateRangeValidator;
import com.cosmicchimps.demo.validators.DayNamesValidator;
import com.cosmicchimps.demo.validators.NotDuplicateValidator;
import com.cosmicchimps.demo.validators.NotEmptyListValidator;
import com.cosmicchimps.demo.validators.WeeklyStringValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
public class ConstraintAnnotations {

    @Constraint(validatedBy = DateRangeValidator.class)
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NotBeforeStartDateConstraint {

        String message() default "endDate cannot be before startDate";

        String startDate();

        String endDate();

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    @Constraint(validatedBy = NotEmptyListValidator.class)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NotEmptyListConstraint {

        String message() default "Empty is not allowed";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    @Constraint(validatedBy = NotDuplicateValidator.class)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NotDuplicateConstraint {

        String message() default "dayList cannot have duplicated acronyms";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    @Constraint(validatedBy = DayNamesValidator.class)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DayNamesConstraint {

        String message() default "Day Names Acronyms when there is availability (`[SU, MO, TU, WE, TH, FR, SA]`)";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    @Constraint(validatedBy = WeeklyStringValidator.class)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface WeeklyStringConstraint {

        String message() default "Will always be `WEEKLY`";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }
}
