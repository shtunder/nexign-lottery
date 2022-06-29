package com.nexign.lottery.exception;

/**
 * @author Andrey Shtunder
 */
public class ServerSideErrorException extends RuntimeException {

    public ServerSideErrorException(String message, Exception exception) {
        super(message, exception);
    }

    public ServerSideErrorException(String message) {
        super(message);
    }
}