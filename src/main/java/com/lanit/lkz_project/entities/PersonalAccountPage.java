package com.lanit.lkz_project.entities;


import lombok.Data;
import org.springframework.data.domain.PageImpl;

@Data
public class PersonalAccountPage<T> {

    private static final int DEFAULT_PAGE_NUMBER = 0;

    private static final int DEFAULT_PAGE_SIZE = 10;

    private PageImpl<T> page;

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
