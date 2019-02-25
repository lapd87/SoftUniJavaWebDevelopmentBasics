package metubeV2.web.filters;

import metubeV2.domain.models.binding.UserRegisterBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static metubeV2.constants.Constants.*;

@WebFilter(REGISTER_URL)
public class UserRegisterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getMethod().equalsIgnoreCase(METHOD_POST)) {
            UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();

            userRegisterBindingModel
                    .setUsername(req.getParameter(PARAMETER_USER));
            userRegisterBindingModel
                    .setPassword(req.getParameter(PARAMETER_PASS));
            userRegisterBindingModel
                    .setConfirmPassword(req.getParameter(PARAMETER_REPEAT_PASS));
            userRegisterBindingModel
                    .setEmail(req.getParameter(PARAMETER_EMAIL));

            req.setAttribute(USER_ENTITIES, userRegisterBindingModel);
        }

        chain.doFilter(req, resp);
    }
}
