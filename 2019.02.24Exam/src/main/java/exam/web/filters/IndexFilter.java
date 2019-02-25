package exam.web.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static exam.constants.Constants.*;

@WebFilter({INDEX_SHORT_URL, INDEX_NO_URL})
public class IndexFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException {

        HttpServletResponse resp = (HttpServletResponse) response;

        resp.sendRedirect(INDEX_URL);
    }
}
