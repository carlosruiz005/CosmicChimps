package com.cosmicchimps.demo.entity;

import com.cosmicchimps.demo.util.ConstraintAnnotations.DayNamesConstraint;
import com.cosmicchimps.demo.util.ConstraintAnnotations.NotBeforeStartDateConstraint;
import com.cosmicchimps.demo.util.ConstraintAnnotations.NotDuplicateConstraint;
import com.cosmicchimps.demo.util.ConstraintAnnotations.NotEmptyListConstraint;
import com.cosmicchimps.demo.util.ConstraintAnnotations.WeeklyStringConstraint;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "schedules")
@NotBeforeStartDateConstraint(startDate = "startDate", endDate = "endDate")
public class Schedule {

    @Id
    private String id;
    @NotNull(message = "startDate can't be null.")
    private LocalDate startDate;
    @NotNull(message = "endDate can't be null.")
    private LocalDate endDate;
    @NotNull(message = "frequency can't be null.")
    @NotEmpty(message = "frequency can't be blank.")
    @WeeklyStringConstraint
    private String frequency;
    @NotNull
    @Min(value = 1, message = "Min interval is 1")
    private Integer interval;
    @NotNull(message = "dayList can't be null.")
    @NotDuplicateConstraint
    @DayNamesConstraint
    @NotEmptyListConstraint
    private List<String> dayList;
    @NotNull
    private LocalDate created;
    private LocalDate deleted;

}
