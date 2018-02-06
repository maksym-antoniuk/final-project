package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.services.UserService;
import ua.nure.antoniuk.util.Attributes;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Mapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CabinetServlet", urlPatterns = Mapping.SERVLET_CABINET_URL)
public class CabinetServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CabinetServlet.class);
    private UserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Attributes.SESSION_ERROR_CHANGE_PASSWORD, request.getSession().getAttribute(Attributes.SESSION_ERROR_CHANGE_PASSWORD));
        request.getSession().removeAttribute(Attributes.SESSION_ERROR_CHANGE_PASSWORD);
        request.setAttribute(Attributes.REQUEST_PORTFOLIO, userService.getPortfolio(((User)request.getSession().getAttribute(Attributes.SESSION_USER)).getId()));
        request.getRequestDispatcher(Mapping.JSP_CABINET).forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute(Constants.SERVICE_USER);
        LOGGER.trace("Cabinet Servlet init");
    }
}
