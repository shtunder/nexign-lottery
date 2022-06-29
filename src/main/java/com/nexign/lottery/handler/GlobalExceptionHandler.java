package com.nexign.lottery.handler;

import com.nexign.lottery.dto.ResponseBody;
import com.nexign.lottery.exception.NotEnoughParticipantsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

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


    private ResponseEntity<ResponseBody<Void>> generateErrorResponse(
            final Exception exc,
            final HttpStatus code,
            final HttpServletRequest request,
            final ResponseBody.Color errorColor
    ) {
        log.debug(exc.getMessage(), exc);
        final ResponseBody<Void> errorResponse = ResponseBody
                .<Void>builder()
                .status(code.value())
                .message(exc.getMessage())
                .path(request.getServletPath())
                .color(errorColor.getValue())
                .build();

        return new ResponseEntity<>(errorResponse, code);
    }

}
