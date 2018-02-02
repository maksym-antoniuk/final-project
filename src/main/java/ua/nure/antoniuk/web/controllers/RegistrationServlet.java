package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.dto.DriverRegDTO;
import ua.nure.antoniuk.dto.ManagerRegDTO;
import ua.nure.antoniuk.services.RegistrationService;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Mapping;
import ua.nure.antoniuk.util.Role;
import ua.nure.antoniuk.util.Util;

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

@WebServlet(name = "RegistrationServlet", urlPatterns = Mapping.SERVLET_REGISTRATION_URL)
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);
    private RegistrationService registrationService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> errors = registrationService.validate(request);
        HttpSession session = request.getSession();
        if (errors.isEmpty()) {
            registrationService.register(request);
            LOGGER.trace("user was register");
        } else {
            session.setAttribute(Constants.REGISTRATION_ERROR, errors);
            switch (Util.typeRole(request)) {
                case Constants.DRIVER:{
                    DriverRegDTO dto = new DriverRegDTO(request);
                    session.setAttribute("dto",dto);
                    break;
                }
                case Constants.MANAGER:{
                    ManagerRegDTO dto = new ManagerRegDTO(request);
                    session.setAttribute("dto",dto);
                    break;
                }
            }
            session.setAttribute(Constants.PARAM_ROLE,request.getParameter(Constants.PARAM_ROLE));
            session.setAttribute("regErrors",errors);
            LOGGER.trace("user was not register");
            response.sendRedirect(Mapping.SERVLET_REGISTRATION);
            return;
        }
        response.sendRedirect(Mapping.SERVLET_MAIN);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute(Constants.PARAM_ROLE) != null) {
            request.setAttribute(Constants.PARAM_ROLE,session.getAttribute(Constants.PARAM_ROLE));
            request.setAttribute("regErrors",session.getAttribute("regErrors"));
            request.setAttribute("dto",session.getAttribute("dto"));
            session.removeAttribute("dto");
            session.removeAttribute("regErrors");
            session.removeAttribute(Constants.PARAM_ROLE);
        }
        request.setAttribute("isRegister","");
        request.getRequestDispatcher(Mapping.SERVLET_MAIN).forward(request,response);
    }

    @Override
    public void init(ServletConfig config) {

        registrationService = (RegistrationService) config.getServletContext().getAttribute(Constants.SERVICE_REGISTRATION);
        LOGGER.trace("Registration servlet init");
    }
}
