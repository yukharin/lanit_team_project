package com.lanit.lkz_project.entities;

import java.util.EnumSet;
import java.util.Set;

public enum NotificationStatus {

    НОВОЕ, В_РАБОТЕ, ОТКЛОНЕНО, ОДОБРЕНО;

    public static Set<NotificationStatus> types() {
        return EnumSet.allOf(NotificationStatus.class);
    }




}
