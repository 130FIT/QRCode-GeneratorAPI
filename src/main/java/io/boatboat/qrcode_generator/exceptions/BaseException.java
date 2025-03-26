package io.boatboat.qrcode_generator.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Base Exception class
 * All custom exceptions should extend this class
 * Handles the title, status code and code of the exception
 */
@Slf4j
@Getter
public class BaseException extends RuntimeException {

    /**
     * The title of the exception
     */
    private final String title;

    /**
     * The HTTP status code of the exception
     */
    private final int statusCode;

    /**
     * The custom code of the exception
     */
    private final int code;

    /**
     * Constructs a new BaseException with the specified details
     *
     * @param message    the detail message
     * @param title      the title of the exception
     * @param statusCode the HTTP status code of the exception
     * @param code       the custom code of the exception
     */
    public BaseException(String message, String title, int statusCode, int code) {
        super(message);
        this.title = title;
        this.statusCode = statusCode;
        this.code = code;
    }
}
