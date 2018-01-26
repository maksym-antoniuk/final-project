package ua.nure.antoniuk.web.listeners;

import ua.nure.antoniuk.db.dao.*;
import ua.nure.antoniuk.db.dao.mysql.*;
import ua.nure.antoniuk.db.transaction.TransactionManager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.services.*;
import ua.nure.antoniuk.util.Constants;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
    private final static Logger LOGGER = Logger.getLogger(ServletContextListener.class);

    // Public constructor is required by servlet spec
    public ServletContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        initLog4j(sce.getServletContext());
        TransactionManager transactionManager = new TransactionManager(getDataSource());

        UserDAO userDAO = new UserDAOImpl();
        PotentialCarDAO potentialCarDAO = new PotentialCarDAOImpl();
        PotentialUserDAO potentialUserDAO = new PotentialUserDAOImpl();
        JourneyDAO journeyDAO = new JourneyDAOImpl();
        CarDAO carDAO = new CarDAOImpl();

        CarService carService = new CarService(transactionManager, potentialCarDAO, carDAO);
        UserService userService = new UserService(transactionManager, potentialUserDAO, potentialCarDAO, userDAO, carDAO);
        RegistrationService registrationService = new RegistrationService(userService);
        LoginService loginService = new LoginService(userService);
        JourneyService journeyService = new JourneyService(journeyDAO, transactionManager);
        LogoutService logoutService = new LogoutService();

        sce.getServletContext().setAttribute(Constants.SERVICE_LOGOUT, logoutService);
        sce.getServletContext().setAttribute(Constants.SERVICE_JOURNEY, journeyService);
        sce.getServletContext().setAttribute(Constants.SERVICE_LOGIN, loginService);
        sce.getServletContext().setAttribute(Constants.SERVICE_CAR, carService);
        sce.getServletContext().setAttribute(Constants.SERVICE_USER, userService);
        sce.getServletContext().setAttribute(Constants.SERVICE_REGISTRATION, registrationService);

        LOGGER.trace("init context");
    }

    private DataSource getDataSource() {
        DataSource ds;
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/appname");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return ds;
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void initLog4j(ServletContext sc) {
        System.out.println("Log4JInitServlet is initializing log4j");
        String log4jLocation = sc.getInitParameter("log4j-properties-location");
        System.out.println(log4jLocation);

        if (log4jLocation == null) {
            System.err.println("*** No log4j-properties-location init param, so initializing log4j with BasicConfigurator");
            BasicConfigurator.configure();
        } else {
            String log4jProp = sc.getRealPath("WEB-INF/log4j.properties");
            //String log4jProp = webAppPath + log4jLocation;
            File properties = new File(log4jProp);
            if (properties.exists()) {
                System.out.println("Initializing log4j with: " + log4jProp);
                PropertyConfigurator.configure(log4jProp);
            } else {
                System.err.println("*** " + log4jProp + " file not found, so initializing log4j with BasicConfigurator");
                BasicConfigurator.configure();
            }
        }
    }

}
