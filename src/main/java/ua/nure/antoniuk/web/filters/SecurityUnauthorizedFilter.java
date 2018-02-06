package ua.nure.antoniuk.web.filters;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.util.Attributes;
import ua.nure.antoniuk.util.Mapping;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "SecurityUnauthorizedFilter", urlPatterns = {Mapping.SERVLET_REGISTRATION_URL, Mapping.SERVLET_LOGIN_URL})
public class SecurityUnauthorizedFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(SecurityUnauthorizedFilter.class);


    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        LOGGER.trace("filter user before do chain");
        User user = (User) request.getSession().getAttribute(Attributes.SESSION_USER);
        if (Objects.isNull(user)) {
            chain.doFilter(req, resp);
            return;
        }
        LOGGER.trace("filter user before do chain");
        HttpServletResponse response = (HttpServletResponse) resp;
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
