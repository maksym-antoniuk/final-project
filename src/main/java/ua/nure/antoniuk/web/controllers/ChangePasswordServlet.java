package ua.nure.antoniuk.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.services.UserService;
import ua.nure.antoniuk.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "ChangePasswordServlet", urlPatterns = Mapping.SERVLET_CHANGE_PASSWORD_URL)
public class ChangePasswordServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ChangePasswordServlet.class);
    private UserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = null;
        if (!Objects.isNull(request.getParameter(Parameters.CHANGE_PASSWORD))) {
            if (Objects.equals(request.getParameter(Parameters.CHANGE_PASSWORD), request.getParameter(Parameters.CHANGE_PASSWORD_CONFIRM))) {
                if (Util.isMatch("[a-zA-Z0-9]{3,16}", request.getParameter(Parameters.CHANGE_PASSWORD))) {
                    User user = (User) request.getSession().getAttribute(Attributes.SESSION_USER);
                    user.setPassword(StringUtil.MD5(request.getParameter(Parameters.CHANGE_PASSWORD)));
                    userService.updateUser(user);
                    result = "";
                } else {
                    result = Constants.INVALID_FORMAT;
                }
            } else {
                result = "Passwords not equals";
            }
        }
        request.getSession().setAttribute(Attributes.SESSION_ERROR_CHANGE_PASSWORD, result);
        response.sendRedirect(request.getContextPath() + Mapping.SERVLET_CABINET_URL);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute(Constants.SERVICE_USER);
        LOGGER.trace("Change password servlet init");
    }
}
