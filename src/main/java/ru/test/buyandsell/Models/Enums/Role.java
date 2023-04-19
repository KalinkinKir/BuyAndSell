package ru.test.buyandsell.Models.Enums;

import org.springframework.security.core.GrantedAuthority;

//енам с ролями
public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
