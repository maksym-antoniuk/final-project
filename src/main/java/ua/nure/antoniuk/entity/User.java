package ua.nure.antoniuk.entity;

import ua.nure.antoniuk.util.Role;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
    private String phone;
    private float salary;
    private Role role;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
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

    public String getDateReg() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateReg.getTime());
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
                Float.compare(user.getSalary(), getSalary()) == 0 &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getLastname(), user.getLastname()) &&
                Objects.equals(getImgPath(), user.getImgPath()) &&
                Objects.equals(getDateReg(), user.getDateReg()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPhone(), user.getPhone()) &&
                getRole() == user.getRole();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getLastname(), getImgPath(), getDateReg(), getEmail(), getPhone(), getSalary(), getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", dateReg=" + dateReg +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                ", role=" + role.getRole() +
                '}';
    }
}
