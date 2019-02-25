package chushka.web.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static chushka.constants.Constants.HOME_URL;
import static chushka.constants.Constants.PRODUCT_ALL_URL;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 29.1.2019 г.
 * Time: 11:39 ч.
 */

@WebServlet(PRODUCT_ALL_URL)
public class ProductsAllServlet extends HttpServlet {

    public ProductsAllServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        resp.sendRedirect(HOME_URL);
    }
}