package com.lanit.lkz_project.entities.enums;

import java.util.EnumSet;
import java.util.Set;

public enum NotificationStatus {

    NEW("Новое"), IN_PROCESSING("В обработке"), REJECTED("Отклонено"), APPROVED("Одобрено");

    private String message;

    NotificationStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static Set<NotificationStatus> statuses() {
        return EnumSet.allOf(NotificationStatus.class);
    }


}
