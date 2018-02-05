package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.builder.FilterJourney;
import ua.nure.antoniuk.services.FiltrationService;
import ua.nure.antoniuk.util.Attributes;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Mapping;
import ua.nure.antoniuk.util.Util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FilterJourneyServlet", urlPatterns = Mapping.SERVLET_FILTER_JOURNEYS_URL)
public class FilterJourneyServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(FilterJourneyServlet.class);
    private FiltrationService filtrationService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Util.isMatch(request.getContextPath() + Mapping.SERVLET_FILTER_JOURNEYS_URL.replace("/*","") + "/[a-z]+", request.getRequestURI())) {
            String paramFilter = request.getRequestURI().replace((request.getContextPath() + Mapping.SERVLET_FILTER_JOURNEYS_URL.replace("*","")), "");
            filtrationService.setParameter(paramFilter, (FilterJourney) request.getSession().getAttribute(Attributes.SESSION_FILTER_JOURNEY));
        }
        response.sendRedirect(request.getContextPath() + Mapping.SERVLET_JOURNEY_URL);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        filtrationService = (FiltrationService) config.getServletContext().getAttribute(Constants.SERVICE_FILTRATION);
        LOGGER.trace("Filtration servlet init");
    }
}
