package com.cosmicchimps.demo.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
@Getter
@Setter
@ToString
public class NewSchedule {

    private LocalDate startDate;
    private LocalDate endDate;
    private String frequency;
    private List<String> dayList;

    public NewSchedule() {
    }

    public NewSchedule(LocalDate startDate, LocalDate endDate, String frequency, List<String> dayList) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
        this.dayList = dayList;
    }

}
