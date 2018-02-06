package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Mapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet", urlPatterns = Mapping.SERVLET_MAIN_URL)
public class MainServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(MainServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.trace("Main Servlet POST");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Constants.REGISTRATION_ERROR, request.getSession().getAttribute(Constants.REGISTRATION_ERROR));
        request.getSession().removeAttribute(Constants.REGISTRATION_ERROR);
        request.getRequestDispatcher(Mapping.JSP_INDEX).forward(request, response);
        LOGGER.trace("Main Servlet GET");
    }
}
