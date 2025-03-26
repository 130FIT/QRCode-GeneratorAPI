package io.boatboat.qrcode_generator.controllers.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private final String message;
    private final int status;
    private final List<String> details;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public ExceptionResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.details = List.of();
    }

}
