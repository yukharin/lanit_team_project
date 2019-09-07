package com.lanit.lkz_project.entities.enums;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.EnumSet;
import java.util.Set;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum ActionType {

    SEND_TO_PROCESSING("Отправить в обработку"), APPROVE("Одобрить"), REJECT("Отклонить"), CREATE("Создать");

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

