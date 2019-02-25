package _02ExtractingCookiesClasses.interfaces;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 23.1.2019 г.
 * Time: 20:56 ч.
 */
public interface HttpRequest {

    String getMethod();

    String getRequestUrl();

    String getHttpVersion();

    Map<String, String> getHeaders();

    Map<String, String> getBodyParameters();

    List<HttpCookie> getCookies();

    void setMethod(String method);

    void setRequestUrl(String requestUrl);

    void setHttpVersion(String version);

    void addHeader(String header, String value);

    void addBodyParameter(String parameter, String value);

    void addCookie(HttpCookie cookie);

    boolean isResource();
}
