package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.services.LoginService;
import ua.nure.antoniuk.util.Constants;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    private LoginService loginService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> errors = loginService.validate(request);
        HttpSession session = request.getSession();
        if (errors.isEmpty()) {
            LOGGER.trace("GOOD LOGIN");
            session.setAttribute(Constants.SESSION_USER, loginService.getByEmail(request));
            response.sendRedirect("journey");
        } else {
            LOGGER.trace("BAD LOGIN");
            response.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        loginService = (LoginService) config.getServletContext().getAttribute(Constants.SERVICE_LOGIN);
        LOGGER.trace("Login servlet init");
    }
}
