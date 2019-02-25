package metubeV2.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static metubeV2.constants.Constants.*;

@WebFilter({HOME_URL, PROFILE_URL, TUBE_ALL_URL})
public class GuestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession().getAttribute(PARAMETER_USER) == null) {
            resp.sendRedirect(LOGIN_URL);
            return;
        }

        chain.doFilter(req, resp);
    }
}
