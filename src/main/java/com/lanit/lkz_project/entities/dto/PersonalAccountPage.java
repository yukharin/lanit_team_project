package com.lanit.lkz_project.entities.dto;


import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;


@Data
public class PersonalAccountPage<T> {

    private static final int DEFAULT_TOTAL_ELEMENTS = 0;

    private static final int DEFAULT_PAGE_NUMBER = 0;

    private static final int DEFAULT_PAGE_SIZE = 10;

    private JsonPageImpl<T> page = new JsonPageImpl<>(Collections.emptyList(),
            PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE), DEFAULT_TOTAL_ELEMENTS);

    private TimeFilter timeFilter = TimeFilter.NO_FILTER;

    private boolean newFilter = false;

    private boolean inProcessingFilter = false;

    private boolean approvedFilter = false;

    private boolean rejectedFilter = false;


    public enum TimeFilter {
        THREE_DAYS(3, "3 дня"), TEN_DAYS(10, "10 дней"), THIRTY_DAYS(30, "30 дней"), NO_FILTER("Выберите срок предоставления ответа");

        private int days;
        private String message;

        TimeFilter(int days, String message) {
            this.days = days;
            this.message = message;
        }

        TimeFilter(String message) {
            this.message = message;
        }

        public int days() {
            return days;
        }

        public String message() {
            return message;
        }
    }

}
