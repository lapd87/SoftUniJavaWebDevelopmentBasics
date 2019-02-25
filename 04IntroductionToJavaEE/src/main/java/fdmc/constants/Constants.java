package fdmc.constants;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 23.1.2019 г.
 * Time: 21:40 ч.
 */
public class Constants {

    public static final String SUPPRESS_UNCHECKED = "unchecked";

    public static final String HTML_VIEWS_RELATIVE_PATH = "views/{0}.html";

    public static final String SESSION_STORAGE_CATS = "cats";

    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_BREED = "breed";
    public static final String PARAMETER_COLOR = "color";
    public static final String PARAMETER_AGE = "age";

    public static final String REPLACER_NAME = "{{" + PARAMETER_NAME + "}}";
    public static final String REPLACER_BREED = "{{" + PARAMETER_BREED + "}}";
    public static final String REPLACER_COLOR = "{{" + PARAMETER_COLOR + "}}";
    public static final String REPLACER_AGE = "{{" + PARAMETER_AGE + "}}";

    public static final String REPLACER_ALL = "{{cats}}";

    private static final String INDEX = "index";
    public static final String INDEX_URL = "/";

    private static final String HOME = "home";
    public static final String HOME_URL = "/" + HOME;
    public static final String HOME_FILE_NAME = INDEX;

    private static final String CAT_CREATE = "create";
    public static final String CAT_CREATE_URL = "/cats/" + CAT_CREATE;
    public static final String CAT_CREATE_FILE_NAME = "cat-create";

    private static final String CAT_ALL = "all";
    public static final String CAT_ALL_URL = "/cats/" + CAT_ALL;
    public static final String CAT_ALL_FILE_NAME = "cat-all";
    public static final String CAT_NO_ALL_FILE_NAME = "cat-no-all";

    private static final String CAT_PROFILE = "profile";
    public static final String CAT_PROFILE_URL = "/cats/" + CAT_PROFILE;
    public static final String CAT_PROFILE_URL_QUERY = CAT_PROFILE_URL + "?catName=" + REPLACER_NAME;
    public static final String CAT_PROFILE_FILE_NAME = "cat-profile";
    public static final String CAT_NO_PROFILE_FILE_NAME = "cat-no-profile";
    public static final String CAT_PROFILE_LIST = "<li><a href='" + CAT_PROFILE_URL_QUERY + "'>" + REPLACER_NAME
            + "</a></li>";

    public static final String QUERY_ARG_SPLITTER = "=";

    public static final String NEW_LINE = System.lineSeparator();
    public static final String EMPTY_STRING = "";
}