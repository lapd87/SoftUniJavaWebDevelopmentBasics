package _02ExtractingCookiesClasses.impl;

import _02ExtractingCookiesClasses.interfaces.HttpCookie;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 28.1.2019 г.
 * Time: 11:02 ч.
 */
public class HttpCookieImpl implements HttpCookie {

    private String key;
    private String value;

    public HttpCookieImpl(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        return String.format("%s <-> %s",
                this.key, this.value);
    }
}