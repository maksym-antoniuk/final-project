package ua.nure.antoniuk.web.filters;

import org.apache.log4j.Logger;
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

@WebFilter(filterName = "SecurityAuthorizedFilter", urlPatterns = {Mapping.SERVLET_LOGOUT_URL, Mapping.SERVLET_JOURNEY_URL,
                        Mapping.SERVLET_FILTER_JOURNEYS_URL, Mapping.SERVLET_CAR_JOURNEY_URL, Mapping.SERVLET_IMAGE_URL, Mapping.SERVLET_CABINET_URL})
public class SecurityAuthorizedFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(SecurityAuthorizedFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        LOGGER.trace("filter authorized before do chain");
        User user = (User) request.getSession().getAttribute(Attributes.SESSION_USER);
        if (Objects.isNull(user)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else  {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
