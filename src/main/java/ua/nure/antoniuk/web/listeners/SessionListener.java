package ua.nure.antoniuk.web.listeners;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.util.Attributes;
import ua.nure.antoniuk.util.Constants;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener()
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    private static final Logger LOGGER = Logger.getLogger(SessionListener.class);
    private AtomicInteger countUser;
    private static final String MONITOR = "MONITOR";
    Map<HttpSession, User> onlineUsers;

    // Public constructor is required by servlet spec
    public SessionListener() {
        countUser = new AtomicInteger();
    }

    public void sessionCreated(HttpSessionEvent se) {
        synchronized (MONITOR) {
            if (Objects.isNull(onlineUsers)) {
                onlineUsers = (Map<HttpSession, User>) se.getSession().getServletContext().getAttribute(Constants.ONLINE_USERS);
            }
        }
        se.getSession().setAttribute(Attributes.SESSION_LOCALE, "en");
        se.getSession().setMaxInactiveInterval(1800);
        LOGGER.trace("new SESSION=" + se.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        if (!Objects.isNull(se.getSession().getAttribute(Constants.SESSION_USER))) {
            //countUser.decrementAndGet();
        }
    }

    public int getCountUser() {
        return countUser.get();
    }


    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        if (Constants.SESSION_USER.equals(httpSessionBindingEvent.getName())) {
            countUser.incrementAndGet();
            httpSessionBindingEvent.getSession().setAttribute(Constants.COUNTER, this);
            onlineUsers.put(httpSessionBindingEvent.getSession(), (User) httpSessionBindingEvent.getValue());
            LOGGER.trace("user added to session");
        }
        LOGGER.trace("added to session");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        if (Constants.SESSION_USER.equals(httpSessionBindingEvent.getName())) {
            onlineUsers.remove(httpSessionBindingEvent.getSession());
            countUser.decrementAndGet();
            LOGGER.trace("user removed from session");
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        if (Constants.SESSION_USER.equals(httpSessionBindingEvent.getName())) {
            //onlineUsers.remove(httpSessionBindingEvent.getValue());
            //countUser.decrementAndGet();
            LOGGER.trace("user replaced in session");
        }
    }
}
