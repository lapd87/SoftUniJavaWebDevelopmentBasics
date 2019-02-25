package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.interfaces.InputReader;
import fdmc.io.HtmlViewReader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static fdmc.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 29.1.2019 г.
 * Time: 11:39 ч.
 */

@WebServlet(CAT_ALL_URL)
public class CatAllServlet extends HttpServlet {

    private InputReader inputReaderMatch;
    private InputReader inputReaderNoMatch;

    public CatAllServlet() {
        this.inputReaderMatch = new HtmlViewReader(CAT_ALL_FILE_NAME);
        this.inputReaderNoMatch = new HtmlViewReader(CAT_NO_ALL_FILE_NAME);
    }

    @Override
    @SuppressWarnings(SUPPRESS_UNCHECKED)
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        Map<String, Cat> allCats = (Map<String, Cat>) req.getSession()
                .getAttribute(SESSION_STORAGE_CATS);

        String html = inputReaderMatch.readLine();


        if (allCats == null || allCats.size() <= 0) {
            html = inputReaderNoMatch.readLine();
        } else {

            StringBuilder catsListHtml = new StringBuilder();

            for (Cat cat : allCats.values()) {
                catsListHtml.append(CAT_PROFILE_LIST
                        .replace(REPLACER_NAME, cat.getName()));
            }

            html = html.replace(REPLACER_ALL, catsListHtml);
        }

        PrintWriter writer = resp.getWriter();

        writer.println(html);
    }
}