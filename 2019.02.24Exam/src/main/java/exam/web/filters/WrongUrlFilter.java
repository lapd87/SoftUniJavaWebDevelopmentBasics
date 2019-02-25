package exam.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static exam.constants.Constants.*;

@WebFilter(ALL_URL)
public class WrongUrlFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String url = req.getRequestURL().toString();

        if (url.contains(RESOURCE_PATH_URL)) {
            chain.doFilter(req, resp);
            return;
        }

        if (VALID_URLS.stream().noneMatch(url::endsWith)) {
            resp.sendRedirect(INDEX_URL);
            return;
        }

        HttpSession session = req.getSession();

        if (session.getAttribute(PARAMETER_USERNAME) == null
                && VALID_NO_USER_URLS.stream().noneMatch(url::endsWith)) {
            resp.sendRedirect(INDEX_URL);
        } else {
            chain.doFilter(req, resp);
        }
    }
}
