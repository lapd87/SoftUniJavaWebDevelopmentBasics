package exam.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static exam.constants.Constants.*;

@WebFilter({REGISTER_URL, LOGIN_URL, INDEX_URL})
public class LoggedInUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if (session.getAttribute(PARAMETER_USERNAME) != null) {
            resp.sendRedirect(HOME_URL);
        } else {
            chain.doFilter(req, resp);
        }
    }
}
