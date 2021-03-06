package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.services.LogoutService;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Mapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = Mapping.SERVLET_LOGOUT_URL)
public class LogoutServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LogoutServlet.class);
    private LogoutService logoutService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logoutService.logout(request);
        response.sendRedirect(Mapping.SERVLET_MAIN);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        logoutService = (LogoutService) config.getServletContext().getAttribute(Constants.SERVICE_LOGOUT);
        LOGGER.trace("Logout Service init");
    }
}
