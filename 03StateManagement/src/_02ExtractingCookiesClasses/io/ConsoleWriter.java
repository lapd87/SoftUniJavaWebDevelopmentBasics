package _02ExtractingCookiesClasses.io;


import _02ExtractingCookiesClasses.interfaces.OutputWriter;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 2.8.2018 г.
 * Time: 14:13 ч.
 */
public class ConsoleWriter implements OutputWriter {

    @Override
    public void print(String line) {
        System.out.print(line);
    }

    @Override
    public void println(String line) {
        System.out.println(line);
    }
}