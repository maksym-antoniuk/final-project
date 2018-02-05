package ua.nure.antoniuk.web.filters;

import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.util.Attributes;
import ua.nure.antoniuk.util.Mapping;
import ua.nure.antoniuk.util.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {Mapping.SERVLET_JOURNEY_URL, Mapping.SERVLET_FILTER_JOURNEYS_URL, Mapping.SERVLET_CABINET_URL,
Mapping.SERVLET_IMAGE_URL, Mapping.SERVLET_CAR_URL, Mapping.SERVLET_PORTFOLIO_URL, Mapping.SERVLET_ALL_USER_URL, Mapping.SERVLET_LOGOUT_URL, Mapping.SERVLET_POTENTIAL_USER_URL})
public class SecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        User user = (User) request.getSession().getAttribute(Attributes.SESSION_USER);
        if (user != null && Role.ADMIN == user.getRole()) {
            chain.doFilter(req, resp);
            return;
        }
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
