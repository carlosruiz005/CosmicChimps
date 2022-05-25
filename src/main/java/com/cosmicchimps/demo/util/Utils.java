package com.cosmicchimps.demo.util;

import com.cosmicchimps.demo.entity.Schedule;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Contains methods for utility.
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
public class Utils {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * *
     * Convert String to LocalDate.
     *
     * @param date
     * @return
     */
    public static LocalDate parseStringToLocalDate(String date) {
        return LocalDate.parse(date, formatter);
    }

    /**
     * *
     * Check if the date is in rage between start and end date from schedule.
     *
     * @param date
     * @param s
     * @return
     */
    public static boolean isDateInRange(String date, Schedule s) {
        LocalDate ld = parseStringToLocalDate(date);
        return ld.isAfter(s.getStartDate()) && ld.isBefore(s.getEndDate());
    }

    /**
     * *
     * Check if is date is an effective day.
     *
     * @param date
     * @param s
     * @return
     */
    public static boolean isEffectiveDay(String date, Schedule s) {
        LocalDate ld = parseStringToLocalDate(date);
        return s.getDayList().stream().anyMatch(f -> f.equals(ld.getDayOfWeek().toString().substring(0, 2)));
    }
}
