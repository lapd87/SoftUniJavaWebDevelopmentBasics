package metubeV2.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static metubeV2.constants.Constants.*;

@WebFilter({INDEX_URL, REGISTER_URL, LOGIN_URL})
public class LoggedInUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession().getAttribute(PARAMETER_USER) != null) {
            resp.sendRedirect(HOME_URL);
            return;
        }

        chain.doFilter(req, resp);
    }
}
