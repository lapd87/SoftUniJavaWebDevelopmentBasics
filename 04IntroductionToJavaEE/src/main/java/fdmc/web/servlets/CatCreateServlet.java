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
import java.util.LinkedHashMap;
import java.util.Map;

import static fdmc.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 29.1.2019 г.
 * Time: 11:39 ч.
 */

@WebServlet(CAT_CREATE_URL)
public class CatCreateServlet extends HttpServlet {

    private InputReader inputReader;

    public CatCreateServlet() {
        this.inputReader = new HtmlViewReader(CAT_CREATE_FILE_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        PrintWriter writer = resp.getWriter();
        writer.println(this.inputReader.readLine());
    }

    @Override
    @SuppressWarnings(SUPPRESS_UNCHECKED)
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        Cat cat = new Cat();
        cat.setName(req.getParameter(PARAMETER_NAME));
        cat.setBreed(req.getParameter(PARAMETER_BREED));
        cat.setColor(req.getParameter(PARAMETER_COLOR));
        cat.setAge(Integer.parseInt(req.getParameter(PARAMETER_AGE)));

        Map<String, Cat> allCats = (Map<String, Cat>) req.getSession()
                .getAttribute(SESSION_STORAGE_CATS);

        if (allCats == null) {
            req.getSession()
                    .setAttribute(SESSION_STORAGE_CATS,
                            new LinkedHashMap<>());
            allCats = (Map<String, Cat>) req.getSession()
                    .getAttribute(SESSION_STORAGE_CATS);
        }

        allCats.put(cat.getName(), cat);

        resp.sendRedirect(CAT_PROFILE_URL_QUERY
                .replace(REPLACER_NAME, cat.getName()));
    }
}