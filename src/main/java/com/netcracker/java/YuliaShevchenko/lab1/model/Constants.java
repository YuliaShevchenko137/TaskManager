package com.netcracker.java.YuliaShevchenko.lab1.model;

import java.text.SimpleDateFormat;

/**
 * Class Constants.
 * Helper class with the constants
 */
public final class Constants {

    /**
     * the initial length of the array.
     */
    public static final int ARRAY_START_SIZE = 10;

    public static final int ZERO = 0;

    /**
     * Time.
     */

    public static final int HALF_HOUR = 30 * 60 * 1000;

    /**
     * Format.
     */
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final String REG_EXP = "[ \\t\\n]+";

    public static SimpleDateFormat DATE_TIME_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * TEXT.
     */
    public static final String SPACE = " ";

    public static final String ENTER = "\n";

    public static final String SEMICOLON = ";";

    /**
     * lABEL.
     */
    public static final String TITLE = "Title";

    public static final String TIME = "Time";

    public static final String START = "Start";

    public static final String END = "End";

    public static final String INTERVAL = "Interval";

    public static final String ACTIVE = "Active";

    public static final String COUNT_TASK = "Count tasks: ";

    public static final String APPLY = "Apply";

    public static final String CHANGE = "Change";

    public static final String AT = "at";

    public static final String FROM = "from";

    public static final String INACTIVE = "inactive";

    /**
     * Max count part of the interval.
     */
    public static final int MAX_MONTHS = 12;

    public static final int MAX_DAYS = 31;

    public static final int MAX_HOURS = 23;

    public static final int MAX_MINUTES = 59;

    public static final int MAX_SECONDS = 59;

}
