package com.nexign.lottery.exception;

/**
 * @author Andrey Shtunder
 *
 * Checks the number of participants in the database, if there are less than
 * two participants, the service throws an exception.
 */
public class NotEnoughParticipantsException extends RuntimeException{

    /**
     * Exception text.
     */
    public static final String NOT_ENOUGH_PARTICIPANTS_MESSAGE = "Unfortunately there are less than two " +
            "participants in the lottery.";

    /**
     * Constructor.
     *
     * @param message exception's text.
     */
    public NotEnoughParticipantsException(String message) {
        super(message);
    }
}
