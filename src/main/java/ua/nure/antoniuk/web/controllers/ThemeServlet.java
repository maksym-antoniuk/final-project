package ua.nure.antoniuk.web.controllers;

import ua.nure.antoniuk.util.Attributes;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Mapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ThemeServlet", urlPatterns = Mapping.SERVLET_THEME_URL)
public class ThemeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        switch (request.getParameter(Attributes.SESSION_THEME)) {
            case Constants.THEME_RED:
                session.setAttribute(Attributes.SESSION_THEME, Constants.THEME_RED);
                break;
            case Constants.THEME_DEEP_PURPLE:
                session.setAttribute(Attributes.SESSION_THEME, Constants.THEME_DEEP_PURPLE);
                break;
            case Constants.THEME_GREEN:
                session.setAttribute(Attributes.SESSION_THEME, Constants.THEME_GREEN);
                break;
            case Constants.THEME_BLUE:
                session.setAttribute(Attributes.SESSION_THEME, Constants.THEME_BLUE);
                break;
        }
        if (session.isNew()) {
            response.sendRedirect(Mapping.SERVLET_MAIN);
        } else {
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
