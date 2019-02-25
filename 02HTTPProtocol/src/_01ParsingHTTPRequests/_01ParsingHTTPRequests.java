package _01ParsingHTTPRequests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 23.1.2019 г.
 * Time: 16:41 ч.
 */
public class _01ParsingHTTPRequests {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] urls = bufferedReader.readLine()
                .split("\\s+");

        String[] requestLineData = bufferedReader.readLine()
                .split("\\s+");

        Map<String, String> headersParams = parseHeadersParams(bufferedReader);

        String bodyParams = parseBodyParams(bufferedReader);

        String response = constructResponse(urls, requestLineData, headersParams, bodyParams);

        System.out.println(response);
    }

    private static String constructResponse(String[] urls,
                                            String[] requestLineData,
                                            Map<String, String> headersParams,
                                            String bodyParams) {
        StringBuilder response = new StringBuilder();

        String requestMethod = requestLineData[0];
        String url = requestLineData[1];
        String httpVersion = requestLineData[2];

        String authResponse = getAuthResponse(headersParams);

        if (!Arrays.asList(urls).contains(url)) {

            appendResponseLine(response, httpVersion,
                    "404 Not Found");
            appendResponseHeaders(headersParams, response);
            response.append("The requested functionality was not found.");

        } else if (authResponse.isEmpty()) {

            appendResponseLine(response, httpVersion,
                    "401 Unauthorized");
            appendResponseHeaders(headersParams, response);
            response.append("You are not authorized to access the requested functionality.");

        } else if ("POST".equals(requestMethod)) {

            if (bodyParams.isEmpty()) {

                appendResponseLine(response, httpVersion,
                        "400 Bad Request");
                appendResponseHeaders(headersParams, response);
                response.append("There was an error with the requested functionality due to malformed request.");

            } else {

                appendResponseLine(response, httpVersion,
                        "200 OK");
                appendResponseHeaders(headersParams, response);
                response.append(authResponse)
                        .append(" ")
                        .append(bodyParams);
            }

        } else if ("GET".equals(requestMethod)) {

            appendResponseLine(response, httpVersion,
                    "200 OK");
            appendResponseHeaders(headersParams, response);
            response.append(authResponse);
        }

        return response.toString();
    }

    private static void appendResponseLine(StringBuilder response, String httpVersion, String message) {
        response.append(httpVersion)
                .append(" ")
                .append(message);
    }

    private static String getAuthResponse(Map<String, String> headersParams) {

        String authResponse = "";

        String authHeader = headersParams.get("Authorization");

        if (authHeader != null) {
            String user = new String(Base64.getDecoder()
                    .decode(authHeader
                            .substring(6)),
                    StandardCharsets.UTF_8);

            authResponse = String.format("Greetings %s!",
                    user);
        }

        return authResponse;
    }

    private static void appendResponseHeaders(Map<String, String> headersParams,
                                              StringBuilder response) {
        response.append(System.lineSeparator())
                .append(headersParams.entrySet()
                        .stream()
                        .filter(k -> !k.getKey().equals("Authorization"))
                        .map(kv -> String.format("%s: %s",
                                kv.getKey(), kv.getValue()))
                        .collect(Collectors.joining(System.lineSeparator())))
                .append(System.lineSeparator())
                .append(System.lineSeparator());
    }

    private static String parseBodyParams(BufferedReader bufferedReader) throws IOException {

        StringBuilder bodyParams = new StringBuilder();

        String input = bufferedReader.readLine();

        if ("".equals(input) || input == null) {
            return bodyParams.toString();
        }

        String[] bodyLine = input.split("[=&]");

        bodyParams.append(String
                .format("You have successfully created %s with ",
                        bodyLine[1]));

        for (int i = 2; i < bodyLine.length - 1; i += 2) {
            bodyParams.append(String.format("%s - %s, ",
                    bodyLine[i], bodyLine[i + 1]));
        }

        bodyParams.replace(bodyParams.length() - 2,
                bodyParams.length(), ".");

        return bodyParams.toString();
    }

    private static Map<String, String> parseHeadersParams(BufferedReader bufferedReader) throws IOException {
        Map<String, String> headersParams = new LinkedHashMap<>();

        String input;
        while (true) {
            if ("".equals(input = bufferedReader.readLine())
                    || input == null) {
                break;
            }

            String[] headerLine = input.split(":\\s+");

            switch (headerLine[0]) {
                case "Date":
                    headersParams.put("Date", headerLine[1]);
                    break;
                case "Host":
                    headersParams.put("Host", headerLine[1]);
                    break;
                case "Content-Type":
                    headersParams.put("Content-Type", headerLine[1]);
                    break;
                case "Authorization":
                    headersParams.put("Authorization", headerLine[1]);
                    break;
            }
        }

        return headersParams;
    }
}