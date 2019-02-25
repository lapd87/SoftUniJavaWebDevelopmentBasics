package metubeV2.web.servlets;

import metubeV2.domain.models.binding.UserLoginBindingModel;
import metubeV2.domain.models.service.UserServiceModel;
import metubeV2.service.interfacces.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static metubeV2.constants.Constants.*;

@WebServlet(LOGIN_URL)
public class UserLoginServlet extends HttpServlet {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public UserLoginServlet(UserService userService,
                            ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher(LOGIN_FILE_PATH)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        UserLoginBindingModel userLoginBindingModel = (UserLoginBindingModel) req
                .getAttribute(USER_ENTITIES);

        UserServiceModel userServiceModel = this.modelMapper
                .map(userLoginBindingModel,
                        UserServiceModel.class);

        boolean isLogged = this.userService
                .loginUser(userServiceModel);

        if (!isLogged) {
            req.getRequestDispatcher(LOGIN_FILE_PATH)
                    .forward(req, resp);
            return;
        }

        req.getSession().setAttribute(PARAMETER_USER,
                userLoginBindingModel.getUsername());

        resp.sendRedirect(HOME_URL);
    }
}
