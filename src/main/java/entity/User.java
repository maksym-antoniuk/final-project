package entity;

import util.Role;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class User implements Serializable {

    private int id;
    private String name;
    private String lastname;
    private String password;
    private String imgPath;
    private Calendar dateReg;
    private String email;
    private Role role;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Calendar getDateReg() {
        return dateReg;
    }

    public void setDateReg(Calendar dateReg) {
        this.dateReg = dateReg;
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
        User user = (User) o;
        return getId() == user.getId() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getLastname(), user.getLastname()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getImgPath(), user.getImgPath()) &&
                Objects.equals(getDateReg(), user.getDateReg()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastname(), getPassword(), getImgPath(), getDateReg(), getEmail(), getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", dateReg=" + dateReg +
                ", email='" + email + '\'' +
                ", role=" + role.getRole() +
                '}';
    }
}
