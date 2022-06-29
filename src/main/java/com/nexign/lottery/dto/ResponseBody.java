package com.nexign.lottery.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Andrey Shtunder
 */
@Builder
@Getter
public class ResponseBody<T> {

    /**
     * Request timestamp.
     */
    private final LocalDateTime timestamp = LocalDateTime.now();

    /**
     * Response's status.
     */
    private final int status;

    /**
     * Exception's text.
     */
    private final String message;

    /**
     * Path.
     */
    private final String path;

    /**
     * Warning color.
     */
    private final String color;

    /**
     * Payload.
     */
    private final T data;

    @Getter
    @RequiredArgsConstructor
    public enum Color {
        SUCCESS("success", "green"), //201
        DANGER("danger", "red"), //500+
        WARNING("warning", "yellow"), //400+
        INFO("info", "blue"); //200

        private final String value;

        private final String color;
    }
}