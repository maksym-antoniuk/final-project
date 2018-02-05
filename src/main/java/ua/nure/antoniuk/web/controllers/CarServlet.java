package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.dto.CarDTO;
import ua.nure.antoniuk.entity.Car;
import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.services.CarService;
import ua.nure.antoniuk.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "CarServlet", urlPatterns = Mapping.SERVLET_CAR_URL)
public class CarServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CarServlet.class);
    private CarService carService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Objects.isNull(request.getParameter(Parameters.CHANGE_CAR_STATUS))) {
            String error;
            if (Util.isMatch("\\d+", request.getParameter(Parameters.CHANGE_CAR_STATUS))) {
                error = carService.changeStatusCar(Integer.parseInt(request.getParameter(Parameters.CHANGE_CAR_STATUS)), ((User) request.getSession().getAttribute(Constants.SESSION_USER)).getId());
            } else {
                error = Constants.INVALID_FORMAT;
            }
            request.getSession().setAttribute(Attributes.SESSION_ERROR_STATUS_CAR, error);
            response.sendRedirect(Mapping.SERVLET_GARAGE);
        } else if (!Objects.isNull(request.getParameter(Parameters.CREATE_CAR))) {
            CarDTO carDTO = new CarDTO(request);
            Map<String, String> errors = carService.validateCreateCar(carDTO);
            if (errors.isEmpty()) {
                PotentialCar car = carDTO.toPotentialCar();
                car.setIdUser(((User) request.getSession().getAttribute(Attributes.SESSION_USER)).getId());
                carService.create(car);
                request.getSession().setAttribute("goodCreate", "as");
            } else {
                request.getSession().setAttribute(Attributes.SESSION_DTO_CAR, carDTO);
                request.getSession().setAttribute(Attributes.SESSION_ERROR_CREATE_CAR, errors);
            }
            response.sendRedirect(Mapping.SERVLET_GARAGE);
        } else if (!Objects.isNull(request.getParameter(Parameters.DELETE_CAR))) {
            String error;
            if (Util.isMatch("\\d+", request.getParameter(Parameters.DELETE_CAR))) {
                error = carService.deleteCar(Integer.parseInt(request.getParameter(Parameters.DELETE_CAR)));
            } else {
                error = Constants.INVALID_FORMAT;
            }
            request.getSession().setAttribute(Attributes.SESSION_ERROR_DELETE_CAR, error);
            response.sendRedirect(Mapping.SERVLET_CAR);
        } else if (!Objects.isNull(request.getParameter(Parameters.EDIT_CAR))) {
                CarDTO carDTO = new CarDTO(request);
                Map<String, String> errors = carService.validateEditCar(carDTO);
                if (errors.isEmpty()) {
                    Car car = carDTO.toCar();
                    car.setId(Integer.parseInt(request.getParameter(Parameters.EDIT_CAR)));
                    carService.update(car);
                    request.getSession().setAttribute("goodEdit", "as");
                } else {
                    request.getSession().setAttribute(Attributes.SESSION_ERROR_EDIT_CAR, errors);
                }
            response.sendRedirect(Mapping.SERVLET_CAR);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Constants.ALL_CARS, carService.getAllCars());
        request.setAttribute(Attributes.SESSION_ERROR_EDIT_CAR, request.getSession().getAttribute(Attributes.SESSION_ERROR_EDIT_CAR));
        request.setAttribute("goodEdit", request.getSession().getAttribute("goodEdit"));
        request.getSession().removeAttribute("goodEdit");
        request.getSession().removeAttribute(Attributes.SESSION_ERROR_EDIT_CAR);
        request.getRequestDispatcher(Mapping.JSP_CARS).forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        carService = (CarService) config.getServletContext().getAttribute(Constants.SERVICE_CAR);
        LOGGER.trace("Car Servlet init");
    }
}
