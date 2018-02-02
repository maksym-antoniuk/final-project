package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.services.CarService;
import ua.nure.antoniuk.services.JourneyService;
import ua.nure.antoniuk.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "CarJourneyServlet", urlPatterns = Mapping.SERVLET_CAR_JOURNEY_URL)
public class CarJourneyServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CarJourneyServlet.class);
    private JourneyService journeyService;
    private CarService carService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error;
        if (!Objects.isNull(request.getParameter(Parameters.ADD_CAR_TO_JOURNEY))) {
            if (!Util.isMatch("\\d+", request.getParameter("journeyid")) || !Util.isMatch("\\d+", request.getParameter("carid"))) {
                error = "Incorect data";
            } else {
                LOGGER.trace("journey " + request.getParameter("journeyid") + " car " + request.getParameter("carid"));
                error = journeyService.addCarToJourney(Integer.parseInt(request.getParameter("journeyid")), Integer.parseInt(request.getParameter("carid")));
            }
            request.getSession().setAttribute(Attributes.SESSION_ERROR_ADD_CAR_TO_JOURNEY, error);
            LOGGER.error(error);
            response.sendRedirect(Mapping.SERVLET_CAR_JOURNEY);
        }
        if (!Objects.isNull(request.getParameter(Parameters.ACCEPT_CAR_TO_JOURNEY))) {
            if (!Util.isMatch("\\d+", request.getParameter("journeyid")) || !Util.isMatch("\\d+", request.getParameter("carid"))) {
                LOGGER.trace("journey BAD " + request.getParameter("journeyid") + " car " + request.getParameter("carid"));
                error = "Incorect data";
            } else {
                LOGGER.trace("journey " + request.getParameter("journeyid") + " car " + request.getParameter("carid"));
                error = journeyService.acceptToJourney(Integer.parseInt(request.getParameter("journeyid")), Integer.parseInt(request.getParameter("carid")));
            }
            request.getSession().setAttribute(Attributes.SESSION_ERROR_ACCEPT_CAR_TO_JOURNEY, error);
            LOGGER.error(error);
            response.sendRedirect(Mapping.SERVLET_CAR_JOURNEY);
        }
        LOGGER.trace("POST ACR_JOURNEY");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Objects.isNull(request.getParameter("journey"))) {
            LOGGER.trace("REQUEST " + request.getParameter("journey"));
            response.setContentType("application/json");
            LOGGER.trace(StringUtil.toArrayJsonCars(carService.getJourneyCars(Integer.parseInt(request.getParameter("journey"))),"cars"));
            response.getWriter().print(StringUtil.toArrayJsonCars(carService.getJourneyCars(Integer.parseInt(request.getParameter("journey"))),"cars"));
        } else {
            HttpSession session = request.getSession();
            request.setAttribute(Attributes.SESSION_ERROR_ADD_CAR_TO_JOURNEY, session.getAttribute(Attributes.SESSION_ERROR_ADD_CAR_TO_JOURNEY));
            request.setAttribute(Attributes.SESSION_ERROR_ACCEPT_CAR_TO_JOURNEY, session.getAttribute(Attributes.SESSION_ERROR_ACCEPT_CAR_TO_JOURNEY));
            session.removeAttribute(Attributes.SESSION_ERROR_ADD_CAR_TO_JOURNEY);
            session.removeAttribute(Attributes.SESSION_ERROR_ACCEPT_CAR_TO_JOURNEY);
            request.getRequestDispatcher(Mapping.SERVLET_JOURNEY).forward(request, response);
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        journeyService = (JourneyService) config.getServletContext().getAttribute(Constants.SERVICE_JOURNEY);
        carService = (CarService) config.getServletContext().getAttribute(Constants.SERVICE_CAR);
        LOGGER.trace("Car Journey Servlet init");
    }
}
