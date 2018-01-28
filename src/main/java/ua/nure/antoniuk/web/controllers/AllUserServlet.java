package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.services.UserService;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Mapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AllUserServlet", urlPatterns = Mapping.SERVLET_ALL_USER_URL)
public class AllUserServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AllUserServlet.class);
    private UserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Constants.ALL_USERS, userService.getUsers());
        request.getRequestDispatcher(Mapping.JSP_USERS).forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute(Constants.SERVICE_USER);
        LOGGER.trace("All User Servlet init");
    }
}
