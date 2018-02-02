package ua.nure.antoniuk.dto;

public class PortfolioDTO {
    private String username;
    private String surname;
    private String email;
    private String phone;
    private String role;
    private String days;

    public PortfolioDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String toJson() {
        return "{" +
                "\"username\":\"" + username + "\"," +
                "\"surname\":\"" + surname + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"phone\":\"" + phone + "\"," +
                "\"role\":\"" + role + "\"," +
                "\"days\":\"" + days + "\"}";
    }

    public void setUnnamed() {
        setUsername("Unknown");
        setSurname("Unknown");
        setEmail("Unknown");
        setPhone("Unknown");
        setRole("Unknown");
        setDays("?");
    }
}