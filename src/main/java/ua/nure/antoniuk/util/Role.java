package ua.nure.antoniuk.util;

public enum Role {
    ADMIN("admin"), DRIVER("driver"), MANAGER("manager");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
