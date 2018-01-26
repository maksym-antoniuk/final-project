package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.entity.PotentialUser;
import ua.nure.antoniuk.services.CarService;
import ua.nure.antoniuk.services.UserService;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Mapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "PotentialUserServlet", urlPatterns = Mapping.SERVLET_POTENTIAL_USER_URL)
public class PotentialUserServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(PotentialUserServlet.class);
    private UserService userService;
    private CarService carService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.trace("Potential user servlet POST");
        LOGGER.trace(request.getParameter("add"));
        if (!Objects.isNull(request.getParameter("add"))) {
            PotentialUser potentialUser = userService.getPotentialUserById(Integer.parseInt(request.getParameter("add")));
            PotentialCar potentialCar = carService.getPotentialCarByIdDriver(potentialUser.getId());

        }
        request.getParameter("cancel");
        response.sendRedirect(Mapping.SERVLET_POTENTIAL_USER);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Constants.POTENTIAL_MANAGERS, userService.getPotentialManagers());
        request.setAttribute(Constants.POTENTIAL_DRIVERS, userService.getPotentialDrivers());
        request.getRequestDispatcher(Mapping.JSP_POTENTIAL_USERS).forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute(Constants.SERVICE_USER);
        carService = (CarService) config.getServletContext().getAttribute(Constants.SERVICE_CAR);
        LOGGER.trace("Potential User Servlet init");
    }
}
