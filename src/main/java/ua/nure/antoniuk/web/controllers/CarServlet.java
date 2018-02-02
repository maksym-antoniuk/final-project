package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.services.CarService;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Mapping;
import ua.nure.antoniuk.util.Parameters;
import ua.nure.antoniuk.util.Util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "CarServlet", urlPatterns = Mapping.SERVLET_CAR_URL)
public class CarServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CarServlet.class);
    private CarService carService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error;
        if (!Objects.isNull(request.getParameter(Parameters.CHANGE_CAR_STATUS))) {
            if (Util.isMatch("\\d+", request.getParameter(Parameters.CHANGE_CAR_STATUS))) {
                error = carService.changeStatusCar(Integer.parseInt(request.getParameter(Parameters.CHANGE_CAR_STATUS)), ((User) request.getSession().getAttribute(Constants.SESSION_USER)).getId());
            } else {
                error = Constants.INVALID_FORMAT;
            }
        }
        response.sendRedirect(Mapping.SERVLET_GARAGE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Constants.ALL_CARS, carService.getAllCars());
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        carService = (CarService) config.getServletContext().getAttribute(Constants.SERVICE_CAR);
        LOGGER.trace("Car Servlet init");
    }
}
