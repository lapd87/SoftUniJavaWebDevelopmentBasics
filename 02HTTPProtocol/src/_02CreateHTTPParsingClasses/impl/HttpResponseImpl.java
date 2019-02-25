package _02CreateHTTPParsingClasses.impl;

import _02CreateHTTPParsingClasses.enums.HttpCodes;
import _02CreateHTTPParsingClasses.interfaces.HttpRequest;
import _02CreateHTTPParsingClasses.interfaces.HttpResponse;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static _02CreateHTTPParsingClasses.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 23.1.2019 г.
 * Time: 21:59 ч.
 */
public class HttpResponseImpl implements HttpResponse {

    private Map<String, String> headers;
    private int statusCode;
    private byte[] content;
    private byte[] response;

    public HttpResponseImpl(Set<String> validURL, HttpRequest httpRequest) {
        this.headers = new LinkedHashMap<>();

        processHttpRequest(validURL, httpRequest);
    }

    private void processHttpRequest(Set<String> validURL, HttpRequest httpRequest) {

        String requestMethod = httpRequest.getMethod();
        String url = httpRequest.getRequestUrl();
        String httpVersion = httpRequest.getHttpVersion();

        Map<String, String> bodyParams = httpRequest
                .getBodyParameters();
        String bodyResponse = getBodyResponse(bodyParams);

        Map<String, String> headersParams = httpRequest
                .getHeaders();
        addResponseHeaders(headersParams);

        String authResponse = getAuthResponse(headersParams);


        if (!validURL.contains(url)) {
            this.statusCode = NOT_FOUND_CODE;

        } else if (authResponse.isEmpty()) {
            this.statusCode = UNAUTHORIZED_CODE;

        } else if (HTTP_POST.equals(requestMethod)) {

            if (bodyParams.isEmpty()) {
                this.statusCode = BAD_REQUEST_CODE;
            } else {
                this.statusCode = OK_CODE;
            }

        } else if (HTTP_GET.equals(requestMethod)) {
            this.statusCode = OK_CODE;
            bodyResponse = EMPTY_STRING;
        }

        StringBuilder responseBuilder = new StringBuilder();

        appendResponse(responseBuilder, httpVersion,
                authResponse, bodyResponse);

        this.response = responseBuilder.toString().getBytes();
    }

    private String getBodyResponse(Map<String, String> bodyParams) {
        StringBuilder bodyResponse = new StringBuilder();

        if (bodyParams.isEmpty()) {
            return EMPTY_STRING;
        }

        bodyResponse.append(String
                .format(BODY_RESPONSE,
                        bodyParams.get(BODY_NAME)));

        bodyParams.remove(BODY_NAME);

        for (Map.Entry<String, String> bodyKeyValue : bodyParams.entrySet()) {
            bodyResponse.append(String
                    .format(BODY_RESPONSE_JOINER,
                            bodyKeyValue.getKey(),
                            bodyKeyValue.getValue()));
        }

        bodyResponse.replace(bodyResponse.length() - 2,
                bodyResponse.length(), DOT);

        return bodyResponse.toString();

    }

    private void appendResponse(StringBuilder response,
                                String httpVersion,
                                String authResponse,
                                String bodyResponse) {
        appendResponseLine(response, httpVersion);
        appendResponseHeaders(response);
        appendResponseMessage(response, authResponse, bodyResponse);
    }

    private void appendResponseMessage(StringBuilder response,
                                       String authResponse,
                                       String bodyResponse) {
        HttpCodes httpCode = getHttpCode();

        if (httpCode.getCode() == OK_CODE) {
            response.append(authResponse);

            if (!bodyResponse.isEmpty()) {
                response.append(SPACE)
                        .append(bodyResponse);
            }
        } else {
            response.append(httpCode.getMessage());
        }
    }


    private void appendResponseHeaders(StringBuilder response) {
        response.append(NEW_LINE)
                .append(this.headers.entrySet()
                        .stream()
                        .map(kv -> String.format(HEADER_RESPONSE,
                                kv.getKey(), kv.getValue()))
                        .collect(Collectors.joining(NEW_LINE)))
                .append(NEW_LINE)
                .append(NEW_LINE);
    }

    private void appendResponseLine(StringBuilder response, String httpVersion) {
        HttpCodes httpCodes = getHttpCode();

        if (httpCodes != null) {
            response.append(httpVersion)
                    .append(SPACE)
                    .append(httpCodes.getCode())
                    .append(SPACE)
                    .append(httpCodes.getName());
        }
    }

    private HttpCodes getHttpCode() {
        HttpCodes httpCodes = null;

        switch (this.statusCode) {
            case 200:
                httpCodes = HttpCodes.OK;
                break;
            case 400:
                httpCodes = HttpCodes.BAD_REQUEST;
                break;
            case 401:
                httpCodes = HttpCodes.UNAUTHORIZED;

                break;
            case 404:
                httpCodes = HttpCodes.NOT_FOUND;
                break;
        }

        return httpCodes;
    }

    private void addResponseHeaders(Map<String, String> headersParams) {
        for (Map.Entry<String, String> requestHeader : headersParams.entrySet()) {
            if (!requestHeader.getKey().equals(HEADER_AUTH)) {
                this.addHeader(requestHeader.getKey(), requestHeader.getValue());
            }
        }
    }

    private String getAuthResponse(Map<String, String> headersParams) {

        String authResponse = EMPTY_STRING;

        String user = headersParams.get(HEADER_AUTH);

        if (user != null) {
            authResponse = String.format(AUTH_HEADER_RESPONSE,
                    user);
        }

        return authResponse;
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public byte[] getBytes() {
        return this.response;
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }
}