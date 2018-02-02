package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.services.CarService;
import ua.nure.antoniuk.util.Attributes;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Mapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GarageServlet", urlPatterns = Mapping.SERVLET_GARAGE_URL)
public class GarageServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(GarageServlet.class);
    private CarService carService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Attributes.REQUEST_GARAGE_CARS, carService.getGarage(((User)request.getSession().getAttribute(Constants.SESSION_USER)).getId()));
        request.getRequestDispatcher(Mapping.JSP_GARAGE).forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        carService = (CarService) config.getServletContext().getAttribute(Constants.SERVICE_CAR);
    }
}
