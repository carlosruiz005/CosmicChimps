package com.cosmicchimps.demo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Contains methods for utility.
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
public class Utils {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parseStringToLocalDate(String date) {
        return LocalDate.parse(date, formatter);
    }
}
