package com.cosmicchimps.demo.util;

import com.cosmicchimps.demo.entity.Schedule;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Contains methods for utility.
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
public class Utils {

    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * *
     * Convert String to LocalDate.
     *
     * @param date
     * @return
     */
    public static LocalDate parseStringToLocalDate(String date) {
        return LocalDate.parse(date, FORMATTER);
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

    /**
     * *
     * Check if the date is an effective week.
     *
     * @param date
     * @param s
     * @return
     */
    public static boolean isWeekIntervalEffective(String date, Schedule s) {
        LocalDate ld = parseStringToLocalDate(date);
        List<Integer> semanasEfectivas = new ArrayList<>();
        int numberOfWeekStartDate = getWeekNumer(setFirstDayOfWeek(s.getStartDate()));
        int numberOfWeekEndDate = getWeekNumer(setFirstDayOfWeek(s.getEndDate()));
        int numberOfWeekOfDate = getWeekNumer(setFirstDayOfWeek(ld));
        for (int i = numberOfWeekStartDate; i < numberOfWeekEndDate; i = i + s.getInterval()) {
            semanasEfectivas.add(i);
        }
        return semanasEfectivas.stream().anyMatch(se -> se == numberOfWeekOfDate);
    }

    private static int getWeekNumer(LocalDate date) {
        return date.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
    }

    private static LocalDate setFirstDayOfWeek(LocalDate date) {
        return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }
}
