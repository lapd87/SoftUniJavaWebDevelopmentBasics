package chushka.constants;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 23.1.2019 г.
 * Time: 21:40 ч.
 */
public class Constants {

    public static final String PERSISTENCE_NAME = "chushka";

    public static final String SUPPRESS_UNCHECKED = "unchecked";

    public static final String HTML_VIEWS_RELATIVE_PATH = "views/{0}.html";

    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_DESCR = "description";
    public static final String PARAMETER_TYPE = "type";

    public static final String REPLACER_NAME = "{{" + PARAMETER_NAME + "}}";
    public static final String REPLACER_DESCR = "{{" + PARAMETER_DESCR + "}}";
    public static final String REPLACER_TYPE = "{{" + PARAMETER_TYPE + "}}";

    public static final String REPLACER_ALL = "{{allProducts}}";
    public static final String REPLACER_TYPES = "{{typeOptions}}";

    private static final String MAIN_ENTITIES = "products";

    private static final String INDEX = "index";
    public static final String INDEX_URL = "/";

    private static final String HOME = "home";
    public static final String HOME_URL = "/" + HOME;
    public static final String HOME_FILE_NAME = INDEX;

    private static final String PRODUCT_CREATE = "create";
    public static final String PRODUCT_CREATE_URL = "/" + MAIN_ENTITIES + "/" + PRODUCT_CREATE;
    public static final String PRODUCT_CREATE_FILE_NAME = MAIN_ENTITIES + "-" + PRODUCT_CREATE;

    private static final String PRODUCT_ALL = "all";
    public static final String PRODUCT_ALL_URL = "/" + MAIN_ENTITIES + "/" + PRODUCT_ALL;

    private static final String PRODUCT_DETAILS = "details";
    public static final String PRODUCT_DETAILS_URL = "/" + MAIN_ENTITIES + "/" + PRODUCT_DETAILS;
    private static final String PRODUCT_DETAILS_URL_QUERY = PRODUCT_DETAILS_URL + "?" + MAIN_ENTITIES
            + "Name=" + REPLACER_NAME;
    public static final String PRODUCT_DETAILS_FILE_NAME = MAIN_ENTITIES + "-" + PRODUCT_DETAILS;

    public static final String PRODUCTS_LIST =
            "<li><a href='" + PRODUCT_DETAILS_URL_QUERY + "'>" + REPLACER_NAME
                    + "</a></li>";
    public static final String PRODUCT_TYPE_OPTION = "<option value='%s'>%s</option>";

    public static final String QUERY_ARG_SPLITTER = "=";

    public static final String NEW_LINE = System.lineSeparator();
    public static final String EMPTY_STRING = "";
}