package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.builder.Filter;
import ua.nure.antoniuk.db.builder.FilterJourney;
import ua.nure.antoniuk.dto.JourneyDTO;
import ua.nure.antoniuk.dto.JourneyDisplayDTO;
import ua.nure.antoniuk.entity.Journey;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.services.JourneyService;
import ua.nure.antoniuk.util.*;
import ua.nure.antoniuk.util.validators.JourneyValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "JourneyServlet", urlPatterns = Mapping.SERVLET_JOURNEY_URL)
public class JourneyServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(JourneyServlet.class);

    private JourneyValidator journeyValidator;
    private JourneyService journeyService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> errors = null;
        if (!Objects.isNull(request.getParameter("add"))) {
            if (((User) request.getSession().getAttribute(Attributes.SESSION_USER)).getRole().equals(Role.DRIVER)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            JourneyDTO journeyDTO = new JourneyDTO().setFromRequest(request);
            errors = journeyValidator.validate(journeyDTO);
            if (errors.isEmpty()) {
                Journey journey = journeyDTO.extract();
                journey.setIdManager(((User)request.getSession().getAttribute(Constants.SESSION_USER)).getId());
                journeyService.create(journey);
                LOGGER.trace("create journey");
                errors.put("Good!", "Journey was added");
            } else {
                request.getSession().setAttribute(Attributes.SESSION_CREATE_JOURNEY_DTO, journeyDTO);
            }
        } else if (!Objects.isNull(request.getParameter("cancel"))) {
            if (((User) request.getSession().getAttribute(Attributes.SESSION_USER)).getRole().equals(Role.DRIVER)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            errors = new HashMap<>();
            if (Util.isMatch("\\d+", request.getParameter("cancel"))) {
                journeyService.cancel(Integer.parseInt(request.getParameter("cancel")));
                errors.put("Good!", "Journey was canceled");
            } else {
                errors.put("Danger!", Constants.INVALID_FORMAT);
            }
        } else if (!Objects.isNull(request.getParameter("confirm"))) {
            if (!((User) request.getSession().getAttribute(Attributes.SESSION_USER)).getRole().equals(Role.DRIVER)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            errors = new HashMap<>();
            if (Util.isMatch("\\d+", request.getParameter("confirm"))) {
                journeyService.confirm(Integer.parseInt(request.getParameter("confirm")), (User) request.getSession().getAttribute(Constants.SESSION_USER));
                errors.put("Good!", "Journey was confirmed");
            } else {
                errors.put("Danger!", Constants.INVALID_FORMAT);
            }
        }
        request.getSession().setAttribute(Attributes.SESSION_ERROR_CREATE_JOURNEY, errors);
        response.sendRedirect(Mapping.SERVLET_JOURNEY);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setAttribute("journeys", journeyService.getJourneys());
        List<JourneyDisplayDTO> journeyDisplayDTO = journeyService.getJourneys((FilterJourney) request.getSession().getAttribute(Attributes.SESSION_FILTER_JOURNEY));
        request.setAttribute("journeys", journeyDisplayDTO);
        request.setAttribute(Attributes.SESSION_ERROR_CREATE_JOURNEY, request.getSession().getAttribute(Attributes.SESSION_ERROR_CREATE_JOURNEY));
        request.setAttribute(Attributes.SESSION_CREATE_JOURNEY_DTO, request.getSession().getAttribute(Attributes.SESSION_CREATE_JOURNEY_DTO));
        request.getSession().removeAttribute(Attributes.SESSION_CREATE_JOURNEY_DTO);
        request.getSession().removeAttribute(Attributes.SESSION_ERROR_CREATE_JOURNEY);
        request.getRequestDispatcher(Mapping.JSP_JOURNEYS).forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        journeyService = (JourneyService) config.getServletContext().getAttribute(Constants.SERVICE_JOURNEY);
        journeyValidator = new JourneyValidator();
        LOGGER.trace("Journey Servlet init");
    }
}
