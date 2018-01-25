package ua.nure.antoniuk.dto;

import ua.nure.antoniuk.entity.PotentialUser;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.StringUtil;
import ua.nure.antoniuk.util.Util;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class ManagerRegDTO {

    private String name;
    private String surname;
    private String email;
    private String phone;

    public ManagerRegDTO(HttpServletRequest request) {
        name = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_MANAGER_NAME));
        surname = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_MANAGER_SURNAME));
        email = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_MANAGER_EMAIL));
        phone = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_MANAGER_PHONE));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerRegDTO that = (ManagerRegDTO) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getSurname(), that.getSurname()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getSurname(), getEmail(), getPhone());
    }

    @Override
    public String toString() {
        return "ManagerRegDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public PotentialUser extractPotentialUser() {
        PotentialUser user = new PotentialUser();
        user.setName(name);
        user.setLastname(surname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(Util.getRole(Constants.MANAGER_ROLE));
        return user;
    }
}
