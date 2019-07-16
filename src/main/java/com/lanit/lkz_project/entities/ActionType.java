package com.lanit.lkz_project.entities;


import java.util.EnumSet;
import java.util.Set;

public enum ActionType {

    SEND_TO_PROCESSING, APPROVE, REJECT;

    public static Set<ActionType> types() {
        return EnumSet.allOf(ActionType.class);
    }
}

