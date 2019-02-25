package metubeV2.web.filters;

import metubeV2.domain.models.binding.UserLoginBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static metubeV2.constants.Constants.*;

@WebFilter(LOGIN_URL)
public class UserLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getMethod().equalsIgnoreCase(METHOD_POST)) {
            UserLoginBindingModel userLoginBindingModel = new UserLoginBindingModel();

            userLoginBindingModel
                    .setUsername(req.getParameter(PARAMETER_USER));
            userLoginBindingModel
                    .setPassword(req.getParameter(PARAMETER_PASS));

            req.setAttribute(USER_ENTITIES, userLoginBindingModel);
        }

        chain.doFilter(req, resp);
    }
}
