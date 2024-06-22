package com.libraryapp.entity.enums;

public enum Role {
    USER("User"),
    ADMIN("Admin");

    private final String roleName;

    Role(String role) {
        this.roleName = role;
    }

    public String getRoleName() {
        return roleName;
    }
}
