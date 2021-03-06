package fdmc2.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static fdmc2.constants.Constants.*;

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

        if (VALID_URLS.stream().noneMatch(url::endsWith)
                && !url.contains(RESOURCE_PATH_URL)) {
            resp.sendRedirect(INDEX_URL);
        } else {
            chain.doFilter(req, resp);
        }
    }
}
