package com.cosmicchimps.demo.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class NewSchedule {

    private LocalDate startDate;
    private LocalDate endDate;
    private String frequency;
    private List<String> dayList;
}
