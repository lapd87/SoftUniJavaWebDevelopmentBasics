package _02CreateHTTPParsingClasses.impl;

import _02CreateHTTPParsingClasses.interfaces.HttpRequest;
import _02CreateHTTPParsingClasses.interfaces.InputReader;
import _02CreateHTTPParsingClasses.io.ConsoleReader;

import java.util.*;

import static _02CreateHTTPParsingClasses.constants.Constants.*;

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

    public HttpRequestImpl(String httpRequest) {
        this.reader = new ConsoleReader();

        this.headers = new LinkedHashMap<>();
        this.bodyParams = new LinkedHashMap<>();

        processRequest(httpRequest);
    }

    private void processRequest(String httpRequest) {
        List<String> inputLines = new LinkedList<>(
                Arrays.asList(httpRequest
                        .split(NEW_LINE)));

        String remove = inputLines.remove(0);
        String[] requestLineData = remove
                .split(SPACE_SPLITTER);

        String requestMethod = requestLineData[0];
        String url = requestLineData[1];
        String version = requestLineData[2];

        this.method = requestMethod;
        this.requestUrl = url;
        this.httpVersion = version;

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
            }
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
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return this.bodyParams;
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
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParams.put(parameter, value);
    }

    @Override
    public boolean isResource() {
        return this.requestUrl.contains(DOT);
    }

    @Override
    public String getHttpVersion() {
        return this.httpVersion;
    }

    @Override
    public void setHttpVersion(String version) {
        this.httpVersion = version;
    }
}