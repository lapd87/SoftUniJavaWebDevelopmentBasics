package fdmc.web.servlets;

import fdmc.interfaces.InputReader;
import fdmc.io.HtmlViewReader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static fdmc.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 29.1.2019 г.
 * Time: 11:39 ч.
 */

@WebServlet(HOME_URL)
public class HomeServlet extends HttpServlet {

    private InputReader inputReader;

    public HomeServlet() {
        this.inputReader = new HtmlViewReader(HOME_FILE_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        PrintWriter writer = resp.getWriter();

        writer.println(this.inputReader.readLine());
    }
}