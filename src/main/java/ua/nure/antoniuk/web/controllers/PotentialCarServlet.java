package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.services.CarService;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "PotentialCarServlet", urlPatterns = Mapping.SERVLET_POTENTIAL_CAR_URL)
public class PotentialCarServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(PotentialCarServlet.class);
    private CarService carService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error;
        if (!Objects.isNull(request.getParameter("add"))) {
            if (Util.isMatch("\\d+", request.getParameter("add"))) {
                PotentialCar potentialCar = carService.getPotentialCar(Integer.parseInt(request.getParameter("add")));
                error = carService.acceptCar(Integer.parseInt(request.getParameter("add")));
                ((Map<HttpSession, User>) request.getServletContext().getAttribute(Constants.ONLINE_USERS)).forEach((k, v) -> {
                    if (v.getId() == potentialCar.getIdUser()) {
                        k.setAttribute(Attributes.SESSION_CARS, carService.getDriversCars(potentialCar.getIdUser()));
                    }
                });
            } else {
                error = Constants.INVALID_FORMAT;
            }
            request.getSession().setAttribute(Attributes.SESSION_ERROR_ACCEPT_POTENTIAL_CAR, error);
        } else if (!Objects.isNull(request.getParameter("remove"))) {
            if (Util.isMatch("\\d+", request.getParameter("remove"))) {
                error = carService.removePotentialCar(Integer.parseInt(request.getParameter("remove")));
            } else {
                error = Constants.INVALID_FORMAT;
            }
            request.getSession().setAttribute(Attributes.SESSION_ERROR_REMOVE_POTENTIAL_CAR, error);
        }
        response.sendRedirect(Mapping.SERVLET_POTENTIAL_CAR);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Attributes.REQUEST_POTENTIAL_CARS, carService.getPotentialCars());
        request.setAttribute(Attributes.SESSION_ERROR_ACCEPT_POTENTIAL_CAR, request.getSession().getAttribute(Attributes.SESSION_ERROR_ACCEPT_POTENTIAL_CAR));
        request.setAttribute(Attributes.SESSION_ERROR_REMOVE_POTENTIAL_CAR, request.getSession().getAttribute(Attributes.SESSION_ERROR_REMOVE_POTENTIAL_CAR));
        request.getSession().removeAttribute(Attributes.SESSION_ERROR_ACCEPT_POTENTIAL_CAR);
        request.getSession().removeAttribute(Attributes.SESSION_ERROR_REMOVE_POTENTIAL_CAR);
        request.getRequestDispatcher(Mapping.JSP_POTENTIAL_CARS).forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        carService = (CarService) config.getServletContext().getAttribute(Constants.SERVICE_CAR);
        LOGGER.trace("Potential Car Servlet init");
    }
}
