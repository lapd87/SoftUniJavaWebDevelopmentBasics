package _01ExtractingCookies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 23.1.2019 г.
 * Time: 16:41 ч.
 */
public class _01ExtractingCookies {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, String> headersParams = parseHeadersParams(bufferedReader);
        Map<String, String> cookies = parseCookies(headersParams);

        String response = constructResponse(cookies);

        System.out.println(response);
    }


    private static String constructResponse(Map<String, String> cookies) {
        StringBuilder response = new StringBuilder();

        cookies.entrySet()
                .stream()
                .map(kv -> String.format("%s <-> %s",
                        kv.getKey(), kv.getValue()))
                .forEach(c -> response.append(c)
                        .append(System.lineSeparator()));

        return response.toString();
    }

    private static Map<String, String> parseCookies(Map<String, String> headersParams) {
        Map<String, String> cookies = new LinkedHashMap<>();

        Arrays.stream(headersParams.get("Cookie")
                .split(";\\s+"))
                .forEach(kvp -> {
                    String[] cookieArg = kvp.split("=");
                    cookies.put(cookieArg[0], cookieArg[1]);
                });

        return cookies;
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
                case "Cookie":
                    headersParams.put("Cookie", headerLine[1]);
                    break;
            }
        }

        return headersParams;
    }
}