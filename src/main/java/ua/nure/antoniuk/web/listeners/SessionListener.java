package ua.nure.antoniuk.web.listeners;

import ua.nure.antoniuk.util.Constants;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class SessionListener implements HttpSessionListener{
    private int countUser;

    // Public constructor is required by servlet spec
    public SessionListener() {
    }

    public void sessionCreated(HttpSessionEvent se) {
        countUser++;
        se.getSession().setMaxInactiveInterval(1800);
        se.getSession().setAttribute(Constants.COUNTER, this);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        countUser--;
        se.getSession().setAttribute(Constants.COUNTER, this);
    }

    public int getCountUser() {
        return countUser;
    }
}
