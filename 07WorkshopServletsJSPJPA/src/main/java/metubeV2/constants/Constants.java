package metubeV2.constants;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 23.1.2019 г.
 * Time: 21:40 ч.
 */
public class Constants {

    private static final String EMPTY_STRING = "";

    public static final String PERSISTENCE_NAME = "metubeV2";

    public static final String METHOD_POST = "POST";

    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_TITLE = "title";
    public static final String PARAMETER_AUTHOR = "author";
    public static final String PARAMETER_DESC = "description";
    public static final String PARAMETER_LINK = "youtube_link";
    public static final String PARAMETER_LINK_ID = "youtube_link_id";
    public static final String PARAMETER_VIEWS = "views";
    public static final String PARAMETER_UPLOADER = "uploader";
    public static final String PARAMETER_USER = "username";
    public static final String PARAMETER_PASS = "password";
    public static final String PARAMETER_REPEAT_PASS = "confirmPassword";
    public static final String PARAMETER_EMAIL = "email";

    public static final String TUBE_ENTITIES = "tubes";
    public static final String USER_ENTITIES = "users";

    private static final String JSP_VIEWS_RELATIVE_PATH = "/JSPs/%s.jsp";

    public static final String URL_SPLITTER = "/";
    private static final String ALL_URL = "/*";

    private static final String LOGIN = "login";
    public static final String LOGIN_URL = URL_SPLITTER + LOGIN;
    public static final String LOGIN_FILE_PATH = String.format(JSP_VIEWS_RELATIVE_PATH, LOGIN);

    private static final String LOGOUT = "logout";
    public static final String LOGOUT_URL = URL_SPLITTER + LOGOUT;

    private static final String REGISTER = "register";
    public static final String REGISTER_URL = URL_SPLITTER + REGISTER;
    public static final String REGISTER_FILE_NAME = String.format(JSP_VIEWS_RELATIVE_PATH, REGISTER);

    private static final String PROFILE = "profile";
    public static final String PROFILE_URL = URL_SPLITTER + PROFILE;
    public static final String PROFILE_FILE_NAME = String.format(JSP_VIEWS_RELATIVE_PATH, PROFILE);

    private static final String INDEX = "index";
    public static final String INDEX_URL = URL_SPLITTER;
    public static final String INDEX_URL_TAGLIB = EMPTY_STRING;
    public static final String INDEX_FILE_NAME = String.format(JSP_VIEWS_RELATIVE_PATH, INDEX);

    private static final String HOME = "home";
    public static final String HOME_URL = URL_SPLITTER + HOME;
    public static final String HOME_FILE_NAME = String.format(JSP_VIEWS_RELATIVE_PATH, HOME);

    public static final String TUBE_ALL_URL = URL_SPLITTER + TUBE_ENTITIES + ALL_URL;

    private static final String TUBE_UPLOAD = "upload";
    public static final String TUBE_UPLOAD_URL = URL_SPLITTER + TUBE_ENTITIES + URL_SPLITTER + TUBE_UPLOAD;
    public static final String TUBE_UPLOAD_FILE_NAME = String.format(JSP_VIEWS_RELATIVE_PATH,
            TUBE_ENTITIES + "-" + TUBE_UPLOAD);

    private static final String TUBE_DETAILS = "details";
    public static final String TUBE_DETAILS_URL = URL_SPLITTER + TUBE_ENTITIES + URL_SPLITTER + TUBE_DETAILS;
    public static final String TUBE_DETAILS_FILE_NAME = String.format(JSP_VIEWS_RELATIVE_PATH,
            TUBE_ENTITIES + "-" + TUBE_DETAILS);
    public static final String TUBE_DETAILS_ALL_URL = TUBE_DETAILS_URL + ALL_URL;

    public static final String YOUTUBE_ID_PATTERN = "(?:youtube.com\\/(?:[^\\/]+\\/.+\\/|(?:v|e(?:mbed)?)\\/|" +
            ".*[?&]v=)|youtu.be\\/)([^\\\"&?\\/ ]{11})";
    public static final String YOUTUBE_EMBED_LINK = "https://www.youtube.com/embed/";
    public static final String YOUTUBE_THUMB_LINK_START = "https://img.youtube.com/vi/";
    public static final String YOUTUBE_THUMB_LINK_END = "/mqdefault.jpg";

    public static final String SINGLE_RESULT = "singleResult";
    public static final String MULTIPLE_RESULT = "multipleResult";
    public static final String ALL_RESULT = "allResult";

    public static final String CACHE_MODE = "org.hibernate.cacheMode";
    public static final String CACHE_IGNORE = "IGNORE";


    public String getHead() {
        return "templates/head.jsp";
    }

    public String getNavbar() {
        return "templates/navbar.jsp";
    }

    public String getFooter() {
        return "templates/footer.jsp";
    }
}