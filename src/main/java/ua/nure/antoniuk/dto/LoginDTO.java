package ua.nure.antoniuk.dto;

import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.StringUtil;
import ua.nure.antoniuk.util.Util;

import javax.servlet.http.HttpServletRequest;

public class LoginDTO {

    private String email;
    private String password;

    public LoginDTO(HttpServletRequest request) {
        email = request.getParameter(Constants.PARAM_LOGIN_EMAIL);
        password = request.getParameter(Constants.PARAM_LOGIN_PASSWORD);
    }

    public LoginDTO(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User extractUser() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(StringUtil.MD5(password));
        return user;
    }
}
