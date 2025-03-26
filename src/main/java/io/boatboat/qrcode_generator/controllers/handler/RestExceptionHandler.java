package io.boatboat.qrcode_generator.controllers.handler;

import com.google.zxing.WriterException;
import io.boatboat.qrcode_generator.exceptions.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<ExceptionResponse> handleBaseException(BaseException e) {
        log.error("Base Exception", e);
        return ResponseEntity.status(e.getStatusCode())
                .body(new ExceptionResponse(
                        e.getMessage(),
                        e.getStatusCode()
                ));
    }


    /**
     * Handle File Processing Exception
     */
    @ExceptionHandler({IOException.class, WriterException.class})
    public ResponseEntity<ExceptionResponse> handleFileProcessException(Exception e) {
        log.error("IO Exception", e);
        return ResponseEntity.internalServerError()
                .body(new ExceptionResponse(
                        "File Processing Error",
                        500,
                        List.of(e.getMessage())
                ));
    }


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("Missing request parameter: {}", ex.getMessage());
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(
                        "Missing request parameter",
                        400,
                        List.of(ex.getMessage())
                ));
    }


    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
            org.springframework.web.HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("Media type not acceptable: {}", ex.getMessage());
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(
                        "Media type not acceptable",
                        400,
                        List.of(ex.getMessage())
                ));
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("Internal Exception: {}", ex.getMessage());
        log.info("Exception Class : {}", ex.getClass());
        return ResponseEntity.status(status)
                .body(new ExceptionResponse(
                        "Internal Exception",
                        status.value(),
                        List.of(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("Validation failed: {}", ex.getMessage());
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(
                        "Validation failed",
                        400,
                        ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList()
                ));
    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException
            (HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("Validation Request Parameter failed: {}", ex.getMessage());
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(
                        "Validation Request Parameter failed",
                        400,
                        ex.getAllErrors().stream().map(MessageSourceResolvable::getDefaultMessage).toList()
                ));

    }
}
