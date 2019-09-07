package com.lanit.lkz_project.entities.data_transfer_objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.EnumSet;
import java.util.Set;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum TimeFilter {

    NO_FILTER("Выберите промежуток времени"), THREE_DAYS(3, "3 дня"), TEN_DAYS(10, "10 дней"),
    THIRTY_DAYS(30, "30 дней");

    private int days;
    private String message;

    TimeFilter(int days, String message) {
        this.days = days;
        this.message = message;
    }

    TimeFilter(String message) {
        this.message = message;
    }


    public static Set<TimeFilter> timeFilters() {
        return EnumSet.allOf(TimeFilter.class);
    }

    public int getDays() {
        return days;
    }

    public String getMessage() {
        return message;
    }
}
