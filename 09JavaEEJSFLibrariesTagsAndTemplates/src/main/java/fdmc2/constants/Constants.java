package fdmc2.constants;

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

    public static final String PERSISTENCE_NAME = "fdmc2";

    public static final String ALL_URL = "/*";
    public static final String INDEX_URL = "/faces/view/index.xhtml";
    public static final String ALL_CAT_URL = "/faces/view/all-cats.xhtml";
    private static final String CREATE_CAT_URL = "/faces/view/create-cat.xhtml";

    public static final String RESOURCE_PATH_URL = "javax.faces.resource";
    public static final List<String> VALID_URLS = Arrays.asList(INDEX_URL, CREATE_CAT_URL, ALL_CAT_URL);

    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_BREED = "breed";
    public static final String PARAMETER_COLOR = "color";
    public static final String PARAMETER_AGE = "age";
    public static final String PARAMETER_GENDER = "gender";
    public static final String PARAMETER_PRICE = "price";
    public static final String PARAMETER_ADDED = "addedOn";
    public static final String PARAMETER_PASSPORT = "hasPassport";

    public static final String CAT_ENTITIES = "cats";

    public static final String SINGLE_RESULT = "singleResult";
    public static final String MULTIPLE_RESULT = "multipleResult";
    public static final String ALL_RESULT = "allResult";

    public static final String CACHE_MODE = "org.hibernate.cacheMode";
    public static final String CACHE_IGNORE = "IGNORE";
}