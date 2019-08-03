package com.lanit.lkz_project.entities.dto;


import com.lanit.lkz_project.entities.jpa_entities.Notification;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.PostConstruct;


@Data
@ToString
@Component
public class PersonalAccountPage<T> {


    private static final int DEFAULT_TOTAL_ELEMENTS = 0;

    private int DEFAULT_PAGE_NUMBER = 1;

    @Value("${default_page_size}")
    private int DEFAULT_PAGE_SIZE;

    private TimeFilter timeFilter = TimeFilter.NO_FILTER;

    private boolean newFilter = false;

    private boolean inProcessingFilter = false;

    private boolean approvedFilter = false;

    private boolean rejectedFilter = false;

    private int number = DEFAULT_PAGE_NUMBER;

    private int size = DEFAULT_PAGE_SIZE;

    private SortParameter sortParameter = SortParameter.BY_DATE_RECEIVED;

    private boolean reversedOrder = false;

    private PageImpl<T> page;

    public enum TimeFilter {
        THREE_DAYS(3, "3 дня"), TEN_DAYS(10, "10 дней"), THIRTY_DAYS(30, "30 дней"), NO_FILTER("Выберите промежуток времени");

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

    @ModelAttribute
    public static PersonalAccountPage<Notification> getAccountPage() {
        PersonalAccountPage page = new PersonalAccountPage<>();
        System.err.println("PAGE: " + page);
        return page;
    }

    @PostConstruct
    public void test() {
        System.err.println("Construction:  " + this);
    }


}
