package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.dto.DriverRegDTO;
import ua.nure.antoniuk.dto.ManagerRegDTO;
import ua.nure.antoniuk.services.RegistrationService;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Role;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);
    private RegistrationService registrationService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> errors = registrationService.validate(request);
        HttpSession session = request.getSession();
        if (errors.isEmpty()) {
            registrationService.register(request);
            LOGGER.trace("user was register");
        } else {
            session.setAttribute(Constants.REGISTRATION_ERROR, errors);
            session.setAttribute(Constants.REGISTRATION_DTO_MANAGER, new ManagerRegDTO(request));
            session.setAttribute(Constants.REGISTRATION_DTO_DRIVER, new DriverRegDTO(request));
            LOGGER.trace("user was not register");
        }
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        registrationService = (RegistrationService) config.getServletContext().getAttribute(Constants.SERVICE_REGISTRATION);
        LOGGER.trace("Registration servlet init");
    }
}
