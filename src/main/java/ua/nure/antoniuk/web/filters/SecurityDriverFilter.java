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

@WebFilter(filterName = "SecurityDriverFilter", urlPatterns = Mapping.SERVLET_GARAGE_URL)
public class SecurityDriverFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(SecurityDriverFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        LOGGER.trace("filter driver before do chain");
        User user = (User) request.getSession().getAttribute(Attributes.SESSION_USER);
        if (Objects.isNull(user)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        if (Objects.equals(Role.DRIVER,user.getRole())) {
            chain.doFilter(req, resp);
            return;
        }
        LOGGER.trace("filter driver before do chain");
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
