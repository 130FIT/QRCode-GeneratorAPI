package io.boatboat.qrcode_generator.exceptions;

import org.springframework.http.HttpStatus;


/**
 * Exception for validation errors case
 * example: invalid input, invalid data, etc.
 */
public class ValidationException extends BaseException {
    public ValidationException(String message) {
        super(message, "Validation Exception", 400, HttpStatus.BAD_REQUEST.value());
    }
}
