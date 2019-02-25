package _02ExtractingCookiesClasses;

import _02ExtractingCookiesClasses.impl.HttpRequestImpl;
import _02ExtractingCookiesClasses.interfaces.HttpCookie;
import _02ExtractingCookiesClasses.interfaces.HttpRequest;
import _02ExtractingCookiesClasses.interfaces.InputReader;
import _02ExtractingCookiesClasses.interfaces.OutputWriter;
import _02ExtractingCookiesClasses.io.ConsoleReader;
import _02ExtractingCookiesClasses.io.ConsoleWriter;

import java.util.List;

import static _02ExtractingCookiesClasses.constants.Constants.EMPTY_STRING;
import static _02ExtractingCookiesClasses.constants.Constants.NEW_LINE;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 23.1.2019 г.
 * Time: 21:20 ч.
 */
public class Main {

    private static InputReader inputReader = new ConsoleReader();
    private static OutputWriter outputWriter = new ConsoleWriter();

    public static void main(String[] args) {

        String requestString = readRequestString();

        HttpRequest httpRequest = new HttpRequestImpl(requestString);

        List<HttpCookie> cookies = httpRequest.getCookies();

        for (HttpCookie cookie : cookies) {
            outputWriter.println(cookie.toString());
        }
    }

    private static String readRequestString() {
        StringBuilder requestString = new StringBuilder();

        boolean shouldBreak = false;
        String input;

        while (!shouldBreak) {

            if (EMPTY_STRING.equals(input = inputReader.readLine())) {
                requestString.append(NEW_LINE);
                input = inputReader.readLine();
                shouldBreak = true;
            }

            requestString.append(input)
                    .append(NEW_LINE);
        }

        return requestString.toString();
    }
}