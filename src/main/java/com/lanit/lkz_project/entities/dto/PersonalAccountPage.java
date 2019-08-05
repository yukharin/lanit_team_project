package com.lanit.lkz_project.entities.dto;


import lombok.Data;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;


@Data
public class PersonalAccountPage<Notification> {


    private static final int DEFAULT_TOTAL_ELEMENTS = 0;

    private static final int DEFAULT_PAGE_NUMBER = 1;

    private static final int DEFAULT_PAGE_SIZE = 10;

    private TimeFilter timeFilter = TimeFilter.NO_FILTER;

    private boolean newFilter = false;

    private boolean inProcessingFilter = false;

    private boolean approvedFilter = false;

    private boolean rejectedFilter = false;

    private int number = DEFAULT_PAGE_NUMBER;

    private int size = DEFAULT_PAGE_SIZE;

    private SortParameter sortParameter = SortParameter.BY_DATE_RECEIVED;

    private boolean reversedOrder = false;

    private PageImpl<Notification> page = new PageImpl<>(Collections.emptyList(),
            PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE), DEFAULT_TOTAL_ELEMENTS);

    public enum TimeFilter {
        THREE_DAYS(3, "3 дня"), TEN_DAYS(10, "10 дней"),
        THIRTY_DAYS(30, "30 дней"), NO_FILTER("Выберите промежуток времени");

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

    public enum SortParameter {

        BY_DATE_RECEIVED("dateReceived", "по дате получения"), BY_DATE_RESPONSE("dateResponse", "по дате ответа"),
        BY_STATUS("status", "по статусу"), BY_ORGANIZATION("organization", "по организации");

        private String value;
        private String message;

        SortParameter(String value, String message) {
            this.value = value;
            this.message = message;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


}
