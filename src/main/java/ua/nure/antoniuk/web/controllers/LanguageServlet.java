package ua.nure.antoniuk.web.controllers;

import ua.nure.antoniuk.util.Attributes;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Mapping;
import ua.nure.antoniuk.util.Parameters;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LanguageServlet", urlPatterns = Mapping.SERVLET_LANGUAGE_URL)
public class LanguageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (!Objects.isNull(request.getParameter("lang"))) {
            if (Constants.LOCALE_EN.equals(request.getParameter(Parameters.LANG))){
                session.setAttribute(Attributes.SESSION_LOCALE, Constants.LOCALE_EN);
            } else if(Constants.LOCALE_RU.equals(request.getParameter(Parameters.LANG))) {
                session.setAttribute(Attributes.SESSION_LOCALE, Constants.LOCALE_RU);
            }
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
