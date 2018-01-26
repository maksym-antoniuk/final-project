package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.services.JourneyService;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Mapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "JourneyServlet", urlPatterns = Mapping.SERVLET_JOURNEY_URL)
public class JourneyServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(JourneyServlet.class);

    private JourneyService journeyService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("journeys", journeyService.getJourneys());
        request.getRequestDispatcher(Mapping.JSP_JOURNEYS).forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        journeyService = (JourneyService) config.getServletContext().getAttribute(Constants.SERVICE_JOURNEY);
        LOGGER.trace("Journey Servlet init");
    }
}
