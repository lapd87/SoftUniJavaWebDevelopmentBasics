package panda.constants;

import java.time.format.DateTimeFormatter;
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

    public static final String PERSISTENCE_NAME = "panda";
    public static final String ID_GENERATOR = "uuid-string";
    public static final String ID_GENERATOR_STRATEGY = "org.hibernate.id.UUIDGenerator";


    public static final String ALL_URL = "/*";
    public static final String INDEX_SHORT_URL = "/";
    public static final String INDEX_NO_URL = "";

    public static final String INDEX_URL = "/index";
    public static final String LOGIN_URL = "/login";
    public static final String HOME_URL = "/home";
    public static final String REGISTER_URL = "/register";
    public static final String PENDING_URL = "/pending";
    public static final String SHIPPED_URL = "/shipped";
    private static final String DELIVERED_URL = "/delivered";
    private static final String CREATE_PACKAGE_URL = "/create-package";
    private static final String DETAILS_PACKAGE_URL = "/details-package";
    private static final String RECEIPTS_URL = "/receipts";
    private static final String DETAILS_RECEIPTS_URL = "/details-receipt";
    private static final String FAVICON_URL = "/favicon.ico";

    public static final String RESOURCE_PATH_URL = "javax.faces.resource";

    public static final List<String> VALID_URLS = Arrays.asList(INDEX_URL, LOGIN_URL, HOME_URL,
            REGISTER_URL, PENDING_URL, CREATE_PACKAGE_URL, SHIPPED_URL, DELIVERED_URL, DETAILS_PACKAGE_URL,
            RECEIPTS_URL, DETAILS_RECEIPTS_URL, FAVICON_URL);

    public static final List<String> VALID_NO_USER_URLS = Arrays.asList(INDEX_URL,
            LOGIN_URL, REGISTER_URL);

    public static final String USER_ENTITIES = "users";

    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_USERNAME = "username";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_ROLE = "role";
    public static final String PARAMETER_RECIPIENT = "recipient";

    public static final String PACKAGE_ENTITIES = "packages";

    public static final String PARAMETER_DESC = "description";
    public static final String PARAMETER_WEIGHT = "weight";
    public static final String PARAMETER_ADDRESS = "shipping_address";
    public static final String PARAMETER_STATUS = "status";
    public static final String PARAMETER_EDT = "EDT";
    public static final String PARAMETER_RECIPIENT_ID = "recipient_id";

    public static final String INITIAL_EDT = "N/A";
    private static final String EDT_FORMAT = "dd/MM/yyyy";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(EDT_FORMAT);
    public static final String FINAL_EDT = "Delivered";
    public static final int MIN_EDT_TIME = 20;
    public static final int MAX_EDT_TIME = 40;

    public static final String RECEIPT_ENTITIES = "receipts";

    public static final String PARAMETER_FEE = "fee";
    public static final String PARAMETER_ISSUED_ON = "issued_on";
    public static final String PARAMETER_PACKAGE_ID = "package_id";
    public static final Double RECEIPT_FEE_MULTIPLIER = 2.67;


    public static final String SINGLE_RESULT = "singleResult";
    public static final String MULTIPLE_RESULT = "multipleResult";
    public static final String ALL_RESULT = "allResult";

    public static final String CACHE_MODE = "org.hibernate.cacheMode";
    public static final String CACHE_IGNORE = "IGNORE";
}