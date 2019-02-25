package _02CreateHTTPParsingClasses.interfaces;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 23.1.2019 г.
 * Time: 20:56 ч.
 */
public interface HttpRequest {

    Map<String, String> getHeaders();

    Map<String, String> getBodyParameters();

    String getMethod();

    void setMethod(String method);

    String getRequestUrl();

    void setRequestUrl(String requestUrl);

    void addHeader(String header, String value);

    void addBodyParameter(String parameter, String value);

    boolean isResource();

    String getHttpVersion();

    void setHttpVersion(String version);
}
