package ua.nure.antoniuk.util;

public abstract class Mapping {
    /**
     * This constants use for mapping servlets
     */
    public static final String SERVLET_JOURNEY = "journey";
    public static final String SERVLET_MAIN = "main";
    public static final String SERVLET_LOGIN = "login";
    public static final String SERVLET_LOGOUT = "logout";
    public static final String SERVLET_REGISTRATION = "registration";
    public static final String SERVLET_POTENTIAL_USER = "potential-user";
    public static final String SERVLET_ALL_USER = "user";
    public static final String SERVLET_PORTFOLIO = "portfolio";
    public static final String SERVLET_CAR = "car";
    public static final String SERVLET_POTENTIAL_CAR = "potential-car";
    public static final String SERVLET_CAR_JOURNEY = "car-journey";
    public static final String SERVLET_GARAGE = "garage";
    public static final String SERVLET_IMAGE = "resources/image/*";
    public static final String SERVLET_CABINET = "cabinet";
    public static final String SERVLET_FILTER_JOURNEY = "journeys/filter/*";
    public static final String SERVLET_LANGUAGE = "language";
    public static final String SERVLET_THEME = "theme";
    public static final String SERVLET_CHANGE_PASSWORD = "user/password";

    public static final String SERVLET_MAIN_URL = "/" + SERVLET_MAIN;
    public static final String SERVLET_LOGIN_URL = "/" + SERVLET_LOGIN;
    public static final String SERVLET_LOGOUT_URL = "/" + SERVLET_LOGOUT;
    public static final String SERVLET_REGISTRATION_URL = "/" + SERVLET_REGISTRATION;
    public static final String SERVLET_JOURNEY_URL = "/" + SERVLET_JOURNEY;
    public static final String SERVLET_POTENTIAL_USER_URL = "/" + SERVLET_POTENTIAL_USER;
    public static final String SERVLET_ALL_USER_URL = "/" + SERVLET_ALL_USER;
    public static final String SERVLET_PORTFOLIO_URL = "/" + SERVLET_PORTFOLIO;
    public static final String SERVLET_CAR_URL = "/" + SERVLET_CAR;
    public static final String SERVLET_POTENTIAL_CAR_URL = "/" + SERVLET_POTENTIAL_CAR;
    public static final String SERVLET_CAR_JOURNEY_URL = "/" + SERVLET_CAR_JOURNEY;
    public static final String SERVLET_GARAGE_URL = "/" + SERVLET_GARAGE;
    public static final String SERVLET_IMAGE_URL = "/" + SERVLET_IMAGE;
    public static final String SERVLET_CABINET_URL = "/" + SERVLET_CABINET;
    public static final String SERVLET_FILTER_JOURNEYS_URL = "/" + SERVLET_FILTER_JOURNEY;
    public static final String SERVLET_LANGUAGE_URL = "/" + SERVLET_LANGUAGE;
    public static final String SERVLET_THEME_URL = "/" + SERVLET_THEME;
    public static final String SERVLET_CHANGE_PASSWORD_URL = "/" + SERVLET_CHANGE_PASSWORD;
    /**
     * This constants use for mapping jsp files
     */
    public static final String JSP_INDEX = "/WEB-INF/pages/index.jsp";
    public static final String JSP_JOURNEYS = "/WEB-INF/pages/journeys.jsp";
    public static final String JSP_POTENTIAL_USERS = "/WEB-INF/pages/potential-users.jsp";
    public static final String JSP_USERS = "/WEB-INF/pages/users.jsp";
    public static final String JSP_CARS = "/WEB-INF/pages/cars.jsp";
    public static final String JSP_CABINET = "/WEB-INF/pages/cabinet.jsp";
    public static final String JSP_GARAGE = "/WEB-INF/pages/garage.jsp";
    public static final String JSP_POTENTIAL_CARS = "/WEB-INF/pages/potential-cars.jsp";


    /**
     * @exception UnsupportedOperationException
     * Because you mustn't create instance of this class
     */

    private Mapping() {
        throw new UnsupportedOperationException("You can't create instance of this class");
    }
}
