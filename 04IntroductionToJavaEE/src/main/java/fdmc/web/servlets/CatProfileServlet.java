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

@WebServlet(CAT_PROFILE_URL)
public class CatProfileServlet extends HttpServlet {

    private InputReader inputReaderMatch;
    private InputReader inputReaderNoMatch;

    public CatProfileServlet() {
        this.inputReaderMatch = new HtmlViewReader(CAT_PROFILE_FILE_NAME);
        this.inputReaderNoMatch = new HtmlViewReader(CAT_NO_PROFILE_FILE_NAME);
    }

    @Override
    @SuppressWarnings(SUPPRESS_UNCHECKED)
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        Map<String, Cat> allCats = (Map<String, Cat>) req.getSession()
                .getAttribute(SESSION_STORAGE_CATS);

        String catName = req.getQueryString()
                .split(QUERY_ARG_SPLITTER)[1];

        String html = inputReaderMatch.readLine();

        try {
            Cat wantedCat = allCats.get(catName);

            html = html.replace(REPLACER_NAME, wantedCat.getName())
                    .replace(REPLACER_BREED, wantedCat.getBreed())
                    .replace(REPLACER_COLOR, wantedCat.getColor())
                    .replace(REPLACER_AGE, String.valueOf(wantedCat.getAge()));

        } catch (NullPointerException npe) {
            html = inputReaderNoMatch.readLine();
            html = html.replace(REPLACER_NAME, catName);
        }

        PrintWriter writer = resp.getWriter();

        writer.println(html);
    }
}