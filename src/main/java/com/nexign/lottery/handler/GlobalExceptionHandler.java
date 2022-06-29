package com.nexign.lottery.handler;

import com.nexign.lottery.dto.ResponseBody;
import com.nexign.lottery.exception.NotEnoughParticipantsException;
import com.nexign.lottery.exception.ServerSideErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.nexign.lottery.dto.ResponseBody.Color.DANGER;
import static com.nexign.lottery.dto.ResponseBody.Color.WARNING;

/**
 * @author Andrey Shtunder
 *
 * General exception handler for rest calls.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotEnoughParticipantsException.class)
    public ResponseEntity<ResponseBody<Void>> handleNotEnoughParticipantsExceptions(
            final NotEnoughParticipantsException exception,
            final HttpServletRequest request
    ) {
        return generateErrorResponse(exception, HttpStatus.BAD_REQUEST, request, WARNING);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseBody<Void>> handleIllegalStateExceptions(
            final IllegalArgumentException exception,
            final HttpServletRequest request
    ) {
        return generateErrorResponse(exception, HttpStatus.BAD_REQUEST, request, WARNING);
    }

    @ExceptionHandler(ServerSideErrorException.class)
    public ResponseEntity<ResponseBody<Void>> handleServerSideErrorException(
            final ServerSideErrorException exception,
            final HttpServletRequest request
    ) {
        return generateErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR, request, DANGER);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBody<Void>> handleValidationExceptions(
            final MethodArgumentNotValidException exception,
            final HttpServletRequest request
    ) {
        final String errorMessage = exception.getBindingResult().getAllErrors().stream()
                .map(error ->
                        String.format("%s: %s", ((FieldError) error).getField(), error.getDefaultMessage()))
                .reduce((a, b) -> a + " " + b)
                .orElse("Undefined error message.");
        return generateErrorResponse(exception, HttpStatus.BAD_REQUEST, request, errorMessage, WARNING);
    }

    private ResponseEntity<ResponseBody<Void>> generateErrorResponse(
            final Exception exc,
            final HttpStatus code,
            final HttpServletRequest request,
            final ResponseBody.Color errorColor
    ) {
        return this.generateErrorResponse(exc, code, request, null, errorColor);
    }

    private ResponseEntity<ResponseBody<Void>> generateErrorResponse(
            final Exception exc,
            final HttpStatus code,
            final HttpServletRequest request,
            final String customMessage,
            final ResponseBody.Color errorColor
    ) {
        log.debug(exc.getMessage(), exc);
        final ResponseBody<Void> errorResponse = ResponseBody
                .<Void>builder()
                .status(code.value())
                .message(Optional.ofNullable(customMessage).orElse(exc.getMessage()))
                .path(request.getServletPath())
                .color(errorColor.getValue())
                .build();

        return new ResponseEntity<>(errorResponse, code);
    }

}
