package _02ExtractingCookiesClasses.impl;

import _02ExtractingCookiesClasses.interfaces.HttpCookie;
import _02ExtractingCookiesClasses.interfaces.HttpRequest;
import _02ExtractingCookiesClasses.interfaces.InputReader;
import _02ExtractingCookiesClasses.io.ConsoleReader;

import java.util.*;

import static _02ExtractingCookiesClasses.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 23.1.2019 г.
 * Time: 20:58 ч.
 */
public class HttpRequestImpl implements HttpRequest {

    private InputReader reader;

    private String method;
    private String requestUrl;
    private String httpVersion;
    private Map<String, String> headers;
    private Map<String, String> bodyParams;
    private List<HttpCookie> cookies;

    public HttpRequestImpl(String httpRequest) {
        this.reader = new ConsoleReader();

        this.headers = new LinkedHashMap<>();
        this.bodyParams = new LinkedHashMap<>();
        this.cookies = new ArrayList<>();

        processRequest(httpRequest);
    }

    private void processRequest(String httpRequest) {
        List<String> inputLines = new LinkedList<>(
                Arrays.asList(httpRequest
                        .split(NEW_LINE)));

        String remove = inputLines.remove(0);
        String[] requestLineData = remove
                .split(SPACE_SPLITTER);

        this.method = requestLineData[0];
        this.requestUrl = requestLineData[1];
        this.httpVersion = requestLineData[2];

        parseHeadersParams(inputLines);

        parseBodyParams(inputLines);
    }

    private void parseBodyParams(List<String> splitLines) {

        if (splitLines.isEmpty()) {
            return;
        }

        String[] bodyLine = splitLines.remove(0)
                .split(BODY_SPLITTER);

        for (int i = 0; i < bodyLine.length - 1; i += 2) {
            this.addBodyParameter(bodyLine[i], bodyLine[i + 1]);
        }
    }


    private void parseHeadersParams(List<String> splitLines) {
        String input;

        while (!splitLines.isEmpty()
                && !EMPTY_STRING.equals(input = splitLines.remove(0))) {

            String[] headerLine = input.split(HEADER_SPLITTER);

            switch (headerLine[0]) {
                case HEADER_DATE:
                    this.addHeader(HEADER_DATE, headerLine[1]);
                    break;
                case HEADER_HOST:
                    this.addHeader(HEADER_HOST, headerLine[1]);
                    break;
                case HEADER_CONTENT_TYPE:
                    this.addHeader(HEADER_CONTENT_TYPE, headerLine[1]);
                    break;
                case HEADER_AUTH:
                    this.addHeader(HEADER_AUTH, decodeAuth(headerLine[1]));
                    break;
                case HEADER_COOKIE:
                    this.parseCookieParams(headerLine[1]);
                    break;
            }
        }
    }

    private void parseCookieParams(String cookiesParams) {

        String[] cookiesArgs = cookiesParams
                .split(COOKIES_SPLITTER);

        for (String cookieArg : cookiesArgs) {

            String[] cookieKeyValue = cookieArg
                    .split(COOKIE_ARG_SPLITTER);

            HttpCookie httpCookie = new HttpCookieImpl(cookieKeyValue[0],
                    cookieKeyValue[1]);

            this.cookies.add(httpCookie);
        }
    }

    private String decodeAuth(String encoded) {

        return new String(Base64.getDecoder()
                .decode(encoded
                        .replaceFirst(AUTH_HEADER_REPLACEMENT,
                                EMPTY_STRING)),
                CHARSET);
    }


    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public String getHttpVersion() {
        return this.httpVersion;
    }

    @Override
    public void setHttpVersion(String version) {
        this.httpVersion = version;
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return Collections.unmodifiableMap(this.bodyParams);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParams.put(parameter, value);
    }

    @Override
    public List<HttpCookie> getCookies() {
        return Collections.unmodifiableList(this.cookies);
    }

    @Override
    public void addCookie(HttpCookie cookie) {
        this.cookies.add(cookie);
    }

    @Override
    public boolean isResource() {
        return this.requestUrl.contains(DOT);
    }
}