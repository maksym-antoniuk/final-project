package ua.nure.antoniuk.entity;

import ua.nure.antoniuk.util.Role;

import java.io.Serializable;
import java.util.Objects;

public class PotentialUser implements Serializable {

    private int id;
    private String name;
    private String lastname;
    private String email;
    private Role role;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PotentialUser that = (PotentialUser) o;
        return getId() == that.getId() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getLastname(), that.getLastname()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                getRole() == that.getRole() &&
                Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getLastname(), getEmail(), getRole(), getPhone());
    }

    @Override
    public String toString() {
        return "PotentialUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", phone='" + phone + '\'' +
                '}';
    }
}
