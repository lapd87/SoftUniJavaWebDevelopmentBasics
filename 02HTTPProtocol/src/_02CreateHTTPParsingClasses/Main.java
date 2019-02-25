package _02CreateHTTPParsingClasses;

import _02CreateHTTPParsingClasses.impl.HttpRequestImpl;
import _02CreateHTTPParsingClasses.impl.HttpResponseImpl;
import _02CreateHTTPParsingClasses.interfaces.HttpRequest;
import _02CreateHTTPParsingClasses.interfaces.HttpResponse;
import _02CreateHTTPParsingClasses.interfaces.InputReader;
import _02CreateHTTPParsingClasses.interfaces.OutputWriter;
import _02CreateHTTPParsingClasses.io.ConsoleReader;
import _02CreateHTTPParsingClasses.io.ConsoleWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static _02CreateHTTPParsingClasses.constants.Constants.*;

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

        Set<String> validUrls = readValidUrls();

        String requestString = readRequestString();

        HttpRequest httpRequest = new HttpRequestImpl(requestString);
        HttpResponse httpResponse = new HttpResponseImpl(validUrls, httpRequest);

        byte[] response = httpResponse.getBytes();

        outputWriter.println(new String(response, CHARSET));
    }

    private static Set<String> readValidUrls() {
        String[] urls = inputReader.readLine()
                .split("\\s+");

        return new HashSet<>(Arrays
                .asList(urls));
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