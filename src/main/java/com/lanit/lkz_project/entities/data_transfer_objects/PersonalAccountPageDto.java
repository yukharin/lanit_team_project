package com.lanit.lkz_project.entities.data_transfer_objects;


import lombok.Data;
import org.springframework.data.domain.PageImpl;


@Data
public class PersonalAccountPageDto<Notification> {

    private TimeFilter timeFilter;

    private boolean newFilter;

    private boolean inProcessingFilter;

    private boolean approvedFilter;

    private boolean rejectedFilter;

    private int number;

    private int size;

    private PageImpl<Notification> page;

    private SortParameter sortParameter;

    private boolean reversedOrder;

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
