package ua.nure.antoniuk.services;

import javax.servlet.http.HttpServletRequest;

public class LogoutService {

    public LogoutService() {
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
