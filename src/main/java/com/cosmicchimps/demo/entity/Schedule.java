package com.cosmicchimps.demo.entity;

import com.cosmicchimps.demo.util.ConstraintAnnotations.DayNamesConstraint;
import com.cosmicchimps.demo.util.ConstraintAnnotations.NotBeforeStartDateConstraint;
import com.cosmicchimps.demo.util.ConstraintAnnotations.NotDuplicateConstraint;
import com.cosmicchimps.demo.util.ConstraintAnnotations.NotEmptyListConstraint;
import com.cosmicchimps.demo.util.ConstraintAnnotations.WeeklyStringConstraint;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
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
    @NotNull(message = "dayList can't be null.")
    @NotDuplicateConstraint
    @DayNamesConstraint
    @NotEmptyListConstraint
    private List<String> dayList;
    @NotNull
    private LocalDate created;
    private LocalDate deleted;

    public Schedule() {
    }

    public Schedule(String id, LocalDate startDate, LocalDate endDate, String frequency, List<String> dayList, LocalDate created, LocalDate deleted) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
        this.dayList = dayList;
        this.created = created;
        this.deleted = deleted;
    }

}
