package exam.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 23.1.2019 г.
 * Time: 21:40 ч.
 */
public class Constants {

    public static final String WARNINGS_UNCHECKED = "unchecked";
    public static final String EMPTY_STRING = "";
    public static final String DOTS = "...";
    public static final int COLUMNS_COUNT = 5;

    public static final String PERSISTENCE_NAME = "exam";
    public static final String ID_GENERATOR = "uuid-string";
    public static final String ID_GENERATOR_STRATEGY = "org.hibernate.id.UUIDGenerator";

    public static final String ALL_URL = "/*";
    public static final String INDEX_SHORT_URL = "/";
    public static final String INDEX_NO_URL = "";

    public static final String INDEX_URL = "/index";
    public static final String LOGIN_URL = "/login";
    public static final String HOME_URL = "/home";
    public static final String REGISTER_URL = "/register";
    private static final String SCHEDULE_URL = "/schedule";
    public static final String DETAILS_URL = "/details";
    public static final String PRINT_URL = "/print";
    private static final String FAVICON_URL = "/favicon.ico";

    public static final String RESOURCE_PATH_URL = "javax.faces.resource";

    public static final List<String> VALID_URLS = Arrays.asList(INDEX_URL, LOGIN_URL, HOME_URL,
            REGISTER_URL, FAVICON_URL, SCHEDULE_URL, DETAILS_URL, PRINT_URL);

    public static final List<String> VALID_NO_USER_URLS = Arrays.asList(INDEX_URL,
            LOGIN_URL, REGISTER_URL);

    public static final String USER_ENTITIES = "users";

    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_USERNAME = "username";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_EMAIL = "email";

    public static final String DOC_ENTITIES = "documents";

    public static final String PARAMETER_TITLE = "title";
    public static final String PARAMETER_CONTENT = "content";
    public static final String CONTENT_TYPE = "longblob";

    public static final String SINGLE_RESULT = "singleResult";
    public static final String MULTIPLE_RESULT = "multipleResult";
    public static final String ALL_RESULT = "allResult";

    public static final String CACHE_MODE = "org.hibernate.cacheMode";
    public static final String CACHE_IGNORE = "IGNORE";


//    public static final String PDF_FILE_PATH = "/output/output.pdf";
//    public static final String PDF_FILE_PATH = "C:/output.pdf";



}