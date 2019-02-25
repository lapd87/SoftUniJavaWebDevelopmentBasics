package metubeV2.web.filters;

import metubeV2.domain.models.binding.TubeUploadBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static metubeV2.constants.Constants.*;

@WebFilter(TUBE_UPLOAD_URL)
public class TubeUploadFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getMethod().equalsIgnoreCase(METHOD_POST)) {
            TubeUploadBindingModel tubeUploadBindingModel = new TubeUploadBindingModel();

            tubeUploadBindingModel
                    .setTitle(req.getParameter(PARAMETER_TITLE));
            tubeUploadBindingModel
                    .setAuthor(req.getParameter(PARAMETER_AUTHOR));
            tubeUploadBindingModel
                    .setYouTubeLink(req.getParameter(PARAMETER_LINK));
            tubeUploadBindingModel
                    .setDescription(req.getParameter(PARAMETER_DESC));
            tubeUploadBindingModel
                    .setUploader((String) req.getSession()
                            .getAttribute(PARAMETER_USER));

            req.setAttribute(TUBE_ENTITIES, tubeUploadBindingModel);
        }

        chain.doFilter(req, resp);
    }
}
