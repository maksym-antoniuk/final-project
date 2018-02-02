package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.dto.PortfolioDTO;
import ua.nure.antoniuk.services.UserService;
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
import java.util.Objects;

@WebServlet(name = "PortfolioServlet", urlPatterns = Mapping.SERVLET_PORTFOLIO_URL)
public class PortfolioServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(PortfolioServlet.class);
    private UserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Objects.isNull(request.getParameter("userId")) && Util.isMatch("\\d", request.getParameter("userId"))) {
            LOGGER.trace(request.getParameter("userId"));
            PortfolioDTO portfolioDTO = userService.getPortfolio(Integer.parseInt(request.getParameter("userId")));
            response.setContentType("application/json");
            response.getWriter().write(portfolioDTO.toJson());
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute(Constants.SERVICE_USER);
    }
}
