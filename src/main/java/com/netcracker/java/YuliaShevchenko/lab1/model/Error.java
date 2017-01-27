package com.netcracker.java.YuliaShevchenko.lab1.model;

/**
 * The enumeration of possible errors.
 */
public enum Error {

    ERROR_INTERVAL("interval = 0 for repeated task"),
    ERROR_COUNT_MONTHS("incorrect number of months"),
    ERROR_COUNT_DAYS("incorrect number of days"),
    ERROR_COUNT_HOURS("incorrect number of hours"),
    ERROR_COUNT_MINUTES("incorrect number of minutes"),
    ERROR_COUNT_SECONDS("incorrect number of seconds"),
    ERROR_TIME("Incorrect time"),
    ERROR_START_TIME("Incorrect time of start"),
    ERROR_END_TIME("Incorrect time of end"),
    ERROR_EMPTY_TITLE("Title is empty"),
    ERROR_EARLIER_TIME("End of execution earlier than the beginning"
            + " or they are identical");

    /**
     * Text or error message.
     */
    private final String message;

    /**
     * Constructor.
     * @param str text or error message.
     */
    Error(String str) {
        this.message = str;
    }

    public String message() {
        return this.message;
    }
}
