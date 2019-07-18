package com.lanit.lkz_project.entities;

import java.util.EnumSet;
import java.util.Set;

public enum NotificationStatus {

    NEW, IN_PROCESSING, REJECTED, APPROVED;

    public static Set<NotificationStatus> statuses() {
        return EnumSet.allOf(NotificationStatus.class);
    }



}
