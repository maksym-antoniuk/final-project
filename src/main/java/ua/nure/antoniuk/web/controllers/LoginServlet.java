package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.builder.FilterJourney;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.services.CarService;
import ua.nure.antoniuk.services.LoginService;
import ua.nure.antoniuk.util.Attributes;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Mapping;
import ua.nure.antoniuk.util.Role;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LoginServlet", urlPatterns = Mapping.SERVLET_LOGIN_URL)
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    private LoginService loginService;
    private CarService carService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> errors = loginService.validate(request);

        HttpSession session = request.getSession();
        if (errors.isEmpty()) {
            LOGGER.trace("GOOD LOGIN");
            User user = loginService.getByEmail(request);
            session.setAttribute(Constants.SESSION_USER, user);
            session.setAttribute(Attributes.SESSION_FILTER_JOURNEY, new FilterJourney(user.getRole(), user.getId()));
            if (((User) session.getAttribute(Attributes.SESSION_USER)).getRole().equals(Role.DRIVER)) {
                session.setAttribute(Attributes.SESSION_CARS, carService.getDriversCars(((User) session.getAttribute(Attributes.SESSION_USER)).getId()));
            }
            response.sendRedirect(Mapping.SERVLET_JOURNEY);
        } else {
            LOGGER.trace("BAD LOGIN");
            session.setAttribute("loginErrors",errors);
            response.sendRedirect(Mapping.SERVLET_LOGIN);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("loginErrors") != null){
            request.setAttribute("loginErrors",session.getAttribute("loginErrors"));
            session.removeAttribute("loginErrors");
        }
        request.setAttribute("isLogin","");
        request.getRequestDispatcher(Mapping.SERVLET_MAIN).forward(request,response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        loginService = (LoginService) config.getServletContext().getAttribute(Constants.SERVICE_LOGIN);
        carService = (CarService) config.getServletContext().getAttribute(Constants.SERVICE_CAR);
        LOGGER.trace("Login servlet init");
    }

}

