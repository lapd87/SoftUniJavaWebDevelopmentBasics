package metubeV2.web.servlets;

import metubeV2.domain.models.service.TubeServiceModel;
import metubeV2.domain.models.view.TubeDetailsViewModel;
import metubeV2.service.interfacces.TubeService;
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

@WebServlet(TUBE_DETAILS_ALL_URL)
public class TubeDetailsServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeDetailsServlet(TubeService tubeService,
                              ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String[] urlParams = req.getRequestURI()
                .split(URL_SPLITTER);
        String tubeId = urlParams[urlParams.length - 1];

        Optional<TubeServiceModel> tubeServiceModel = this.tubeService
                .findTubeById(tubeId);

        if (tubeServiceModel.isEmpty()) {

            req.getRequestDispatcher(INDEX_FILE_NAME)
                    .forward(req, resp);
            return;
        }

        TubeServiceModel tsm = tubeServiceModel.get();

        tsm.setViews(tsm.getViews() + 1);

        this.tubeService.updateTube(tsm);

        TubeDetailsViewModel tubeDetailsViewModel = this.modelMapper
                .map(tubeServiceModel.get(), TubeDetailsViewModel.class);

        req.setAttribute(TUBE_ENTITIES, null);
        req.setAttribute(TUBE_ENTITIES, tubeDetailsViewModel);

        req.getRequestDispatcher(TUBE_DETAILS_FILE_NAME)
                .forward(req, resp);
    }
}
