package fdmc.io;

import fdmc.interfaces.InputReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.stream.Collectors;

import static fdmc.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 30.1.2019 г.
 * Time: 14:37 ч.
 */
public class HtmlViewReader implements InputReader {

    private String fileName;

    public HtmlViewReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String readLine() {
        String viewName = MessageFormat.format(HTML_VIEWS_RELATIVE_PATH, this.fileName);

        URL url = this.getClass().getClassLoader().getResource(viewName);

        File file;
        try {
            assert url != null;
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }

        String htmlContent = EMPTY_STRING;
        try {
            htmlContent = Files.lines(Paths.get(file.getAbsolutePath()))
                    .collect(Collectors.joining(NEW_LINE));
        } catch (IOException ignored) {
            ;
        }

        return htmlContent;
    }
}