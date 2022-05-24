package com.cosmicchimps.demo.enums;

/**
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
public enum DayEnum {
    SU("SUNDAY"), MO("MONDAY"), TU("TUESDAY"), WE("WEDNESDAY"), TH("THURSDAY"), FR("FRIDAY"), SA("SATURDAY");
    private String dayName;

    private DayEnum(String dayName) {
        this.dayName = dayName;
    }

    private String getDayName() {
        return this.getDayName();
    }
}
