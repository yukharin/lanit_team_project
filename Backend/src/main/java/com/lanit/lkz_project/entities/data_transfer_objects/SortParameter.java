package com.lanit.lkz_project.entities.data_transfer_objects;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.EnumSet;
import java.util.Set;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum SortParameter {

    BY_DATE_RECEIVED("dateReceived", "по дате получения"), BY_DATE_RESPONSE("dateResponse", "по дате ответа"),
    BY_STATUS("status", "по статусу"), BY_ORGANIZATION("organization", "по организации");

    private String value;
    private String message;

    SortParameter(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public static Set<SortParameter> sortParameters() {
        return EnumSet.allOf(SortParameter.class);
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
