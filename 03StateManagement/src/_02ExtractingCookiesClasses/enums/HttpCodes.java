package _02ExtractingCookiesClasses.enums;

import _02ExtractingCookiesClasses.constants.Constants;

import static _02ExtractingCookiesClasses.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 23.1.2019 г.
 * Time: 22:50 ч.
 */
public enum HttpCodes {
    OK(OK_CODE, Constants.OK, OK_RESPONSE),
    BAD_REQUEST(BAD_REQUEST_CODE, Constants.BAD_REQUEST, BAD_REQUEST_RESPONSE),
    UNAUTHORIZED(UNAUTHORIZED_CODE, Constants.UNAUTHORIZED, UNAUTHORIZED_RESPONSE),
    NOT_FOUND(NOT_FOUND_CODE, Constants.NOT_FOUND, NOT_FOUND_RESPONSE);

    private final int code;
    private final String name;
    private final String message;

    HttpCodes(int code, String name, String message) {
        this.code = code;
        this.name = name;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }}