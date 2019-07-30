package com.lanit.lkz_project.entities.jpa_entities;

import java.util.EnumSet;
import java.util.Set;

public enum NotificationStatus {

    NEW("Новое"), IN_PROCESSING("В работе"), REJECTED("Отклонено"), APPROVED("Одобрено");

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
