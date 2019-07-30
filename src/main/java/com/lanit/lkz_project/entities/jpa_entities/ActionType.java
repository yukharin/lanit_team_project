package com.lanit.lkz_project.entities.jpa_entities;


import java.util.EnumSet;
import java.util.Set;

public enum ActionType {

    SEND_TO_PROCESSING("Отправить в обработку"), APPROVE("Одобрить"), REJECT("Отклонить");

    private String message;

    ActionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static Set<ActionType> types() {
        return EnumSet.allOf(ActionType.class);
    }
}

