package _02ExtractingCookiesClasses.constants;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 23.1.2019 г.
 * Time: 21:40 ч.
 */
public class Constants {

    public static final String HEADER_DATE = "Date";
    public static final String HEADER_HOST = "Host";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_AUTH = "Authorization";
    public static final String HEADER_COOKIE = "Cookie";
    public static final String HEADER_SPLITTER = ":\\s+";
    public static final String COOKIES_SPLITTER = ";\\s+";
    public static final String COOKIE_ARG_SPLITTER = "=";
    public static final String SPACE_SPLITTER = "\\s+";
    public static final String BODY_SPLITTER = "[=&]";
    public static final String NEW_LINE = System.lineSeparator();
    public static final String DOT = ".";
    public static final String SPACE = " ";
    public static final String EMPTY_STRING = "";
    public static final String AUTH_HEADER_REPLACEMENT = "Basic ";
    public static final Charset CHARSET = StandardCharsets.UTF_8;
    public static final String AUTH_HEADER_RESPONSE = "Greetings %s!";
    public static final String HEADER_RESPONSE = "%s: %s";
    public static final String BODY_RESPONSE = "You have successfully created %s with ";
    public static final String BODY_RESPONSE_JOINER = "%s - %s, ";
    public static final String BODY_NAME = "name";
    public static final String NOT_FOUND = "Not Found";
    public static final int NOT_FOUND_CODE = 404;
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final int UNAUTHORIZED_CODE = 401;
    public static final String BAD_REQUEST = "Bad Request";
    public static final int BAD_REQUEST_CODE = 400;
    public static final String OK = "OK";
    public static final int OK_CODE = 200;
    public static final String OK_RESPONSE = "";
    public static final String BAD_REQUEST_RESPONSE = "There was an error with the " +
            "requested functionality due to malformed request.";
    public static final String UNAUTHORIZED_RESPONSE = "You are not authorized to access the requested functionality.";
    public static final String NOT_FOUND_RESPONSE = "The requested functionality was not found.";
    public static final String HTTP_POST = "POST";
    public static final String HTTP_GET = "GET";


}