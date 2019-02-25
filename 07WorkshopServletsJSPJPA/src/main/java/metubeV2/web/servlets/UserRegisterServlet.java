package metubeV2.web.servlets;

import metubeV2.domain.models.binding.UserRegisterBindingModel;
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

@WebServlet(REGISTER_URL)
public class UserRegisterServlet extends HttpServlet {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public UserRegisterServlet(UserService userService,
                               ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher(REGISTER_FILE_NAME)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        UserRegisterBindingModel userRegisterBindingModel = (UserRegisterBindingModel) req
                .getAttribute(USER_ENTITIES);

        if (!userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())) {

            req.getRequestDispatcher(REGISTER_FILE_NAME)
                    .forward(req, resp);
            return;
        }

        UserServiceModel userServiceModel = this.modelMapper
                .map(userRegisterBindingModel, UserServiceModel.class);

        this.userService.registerUser(userServiceModel);

        resp.sendRedirect(LOGIN_URL);
    }
}
