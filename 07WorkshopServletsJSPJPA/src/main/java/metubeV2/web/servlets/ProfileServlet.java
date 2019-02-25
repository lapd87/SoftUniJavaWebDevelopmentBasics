package metubeV2.web.servlets;

import metubeV2.domain.models.service.UserServiceModel;
import metubeV2.domain.models.view.UserProfileViewModel;
import metubeV2.service.interfacces.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static metubeV2.constants.Constants.*;

@WebServlet(PROFILE_URL)
public class ProfileServlet extends HttpServlet {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public ProfileServlet(UserService userService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        Optional<UserServiceModel> userServiceModel = this.userService
                .findUserByUsername((String) req.getSession()
                        .getAttribute(PARAMETER_USER));

        if (userServiceModel.isEmpty()) {

            req.getRequestDispatcher(INDEX_FILE_NAME)
                    .forward(req, resp);
            return;
        }

        UserProfileViewModel userProfileViewModel = this.modelMapper
                .map(userServiceModel.get(), UserProfileViewModel.class);

        req.setAttribute(USER_ENTITIES, null);
        req.setAttribute(USER_ENTITIES, userProfileViewModel);

        req.getRequestDispatcher(PROFILE_FILE_NAME)
                .forward(req, resp);
    }
}
