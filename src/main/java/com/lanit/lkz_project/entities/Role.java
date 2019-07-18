package com.lanit.lkz_project.entities;

import java.util.EnumSet;


public enum Role {

    AUTHORITY, EMPLOYEE;

    public static EnumSet<Role> roles() {
        return EnumSet.allOf(Role.class);
    }

}

