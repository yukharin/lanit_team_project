package com.lanit.lkz_project.entities.enums;

import org.springframework.security.core.GrantedAuthority;

import java.util.EnumSet;


public enum Role implements GrantedAuthority {

    AUTHORITY, EMPLOYEE;

    public static EnumSet<Role> roles() {
        return EnumSet.allOf(Role.class);
    }

    @Override
    public String getAuthority() {
        return this.toString();
    }
}

