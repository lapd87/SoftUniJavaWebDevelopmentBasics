package metubeV2.web.servlets;

import metubeV2.domain.models.binding.TubeUploadBindingModel;
import metubeV2.domain.models.service.TubeServiceModel;
import metubeV2.domain.models.service.UserServiceModel;
import metubeV2.service.interfacces.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static metubeV2.constants.Constants.*;

@WebServlet(TUBE_UPLOAD_URL)
public class TubeUploadServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeUploadServlet(TubeService tubeService,
                             ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher(TUBE_UPLOAD_FILE_NAME)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        TubeUploadBindingModel tubeUploadBindingModel = (TubeUploadBindingModel) req
                .getAttribute(TUBE_ENTITIES);

        TubeServiceModel tubeServiceModel = this.modelMapper
                .map(tubeUploadBindingModel, TubeServiceModel.class);

        String youTubeId = tubeUploadBindingModel
                .getYouTubeLink();

        Pattern pattern = Pattern.compile(YOUTUBE_ID_PATTERN);
        Matcher matcher = pattern.matcher(youTubeId);

        if (!matcher.find()) {
            resp.sendRedirect(TUBE_UPLOAD_URL);
            return;
        }

        youTubeId = matcher.group(1);

        tubeServiceModel.setYouTubeId(youTubeId);

        UserServiceModel userServiceModel = new UserServiceModel();

        userServiceModel.setUsername(tubeUploadBindingModel.getUploader());
        tubeServiceModel.setUploader(userServiceModel);

        this.tubeService.uploadTube(tubeServiceModel);

        resp.sendRedirect(HOME_URL);
    }
}
