package metubeV2.web.servlets;

import metubeV2.domain.models.service.UserServiceModel;
import metubeV2.domain.models.view.UserHomeViewModel;
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

@WebServlet(HOME_URL)
public class HomeServlet extends HttpServlet {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public HomeServlet(UserService userService,
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

        UserHomeViewModel userHomeViewModel = this.modelMapper
                .map(userServiceModel.get(), UserHomeViewModel.class);

        req.setAttribute(USER_ENTITIES, null);
        req.setAttribute(USER_ENTITIES, userHomeViewModel);


        req.getRequestDispatcher(HOME_FILE_NAME)
                .forward(req, resp);
    }
}
