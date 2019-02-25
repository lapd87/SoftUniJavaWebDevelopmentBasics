package metubeV2.web.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static metubeV2.constants.Constants.INDEX_URL;
import static metubeV2.constants.Constants.LOGOUT_URL;

@WebServlet(LOGOUT_URL)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws  IOException {

        req.getSession().invalidate();

        resp.sendRedirect(INDEX_URL);
    }
}
