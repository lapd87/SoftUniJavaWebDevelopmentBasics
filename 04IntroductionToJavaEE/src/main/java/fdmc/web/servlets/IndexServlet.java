package fdmc.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static fdmc.constants.Constants.HOME_URL;
import static fdmc.constants.Constants.INDEX_URL;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 29.1.2019 г.
 * Time: 11:39 ч.
 */

@WebServlet(INDEX_URL)
public class IndexServlet extends HttpServlet {

    public IndexServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        resp.sendRedirect(HOME_URL);
    }
}