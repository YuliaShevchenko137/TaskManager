package com.netcracker.java.yulia_shevchenko.lab1.model;

import org.apache.log4j.Logger;

/**
 * Class CreateInterval.
 * Helper class for creating repeated interval.
 */
public class CreateInterval {

    /**
     * It is used to register error.
     */
    private static final Logger LOGGER
            = Logger.getLogger(CreateInterval.class);

    /**
     * component interval in years.
     */
    private int intervalYear;

    /**
     * component interval in months.
     */
    private int intervalMonth;

    /**
     * component interval in days.
     */
    private int intervalDay;

    /**
     * component interval in hours.
     */
    private int intervalHour;

    /**
     * intervalMinute.
     * component interval in minutes.
     */

    private int intervalMinute;

    /**
     * component interval in seconds.
     */
    private int intervalSecond;

    /**
     * Method CreateInterval().
     * Constructor for non recurring task.
     */
    public CreateInterval() {
        this.setIntervalYear(Constants.ZERO);
        this.setIntervalMonth(Constants.ZERO);
        this.setIntervalDay(Constants.ZERO);
        this.setIntervalHour(Constants.ZERO);
        this.setIntervalMinute(Constants.ZERO);
        this.setIntervalSecond(Constants.ZERO);
    }

    /**
     * Method CreateInterval(final int year, final int month,
     * final int day, final int hour,
     * final int minute, final int second).
     * Constructor for repeated task.
     * @param year count years.
     * @param month count months.
     * @param day count days.
     * @param hour count hours.
     * @param minute count minutes.
     * @param second count seconds.
     */
    public CreateInterval(final int year, final int month,
                          final int day, final int hour,
                          final int minute, final int second) {
        this.setIntervalYear(year);
        this.setIntervalMonth(month);
        this.setIntervalDay(day);
        this.setIntervalHour(hour);
        this.setIntervalMinute(minute);
        this.setIntervalSecond(second);
    }

    public final void setIntervalYear(final int intervalYears) {
        this.intervalYear = intervalYears;
    }

    public final void setIntervalMonth(final int intervalMonths) {
        this.intervalMonth = intervalMonths;
    }

    public final void setIntervalDay(final int intervalDays) {
        this.intervalDay = intervalDays;
    }

    public final void setIntervalHour(final int intervalHours) {
        this.intervalHour = intervalHours;
    }

    public final void setIntervalMinute(final int intervalMinutes) {
        this.intervalMinute = intervalMinutes;
    }

    public final void setIntervalSecond(final int intervalSeconds) {
        this.intervalSecond = intervalSeconds;
    }

    /**
     * Method getInterval.
     * Returns a textual representation of the interval.
     * @return string of the interval.
     */
    public final String getInterval() {
        return this.getIntervalYear() + " year "
                + this.getIntervalMonth() + " month "
                + this.getIntervalDay() + " day "
                + this.getIntervalHour() + " hour "
                + this.getIntervalMinute() + " minute "
                + this.getIntervalSecond() + " second";
    }

    public final int getIntervalYear() {
        return this.intervalYear;
    }

    public final int getIntervalMonth() {
        return this.intervalMonth;
    }

    public final int getIntervalDay() {
        return this.intervalDay;
    }

    public final int getIntervalHour() {
        return this.intervalHour;
    }

    public final int getIntervalMinute() {
        return this.intervalMinute;
    }

    public final int getIntervalSecond() {
        return this.intervalSecond;
    }

    /**
     * Create error message about filling interval.
     * @return error message.
     */
    public String errorInterval() {
        String str1 = "";
        if (this.intervalMonth < Constants.ZERO
                || this.intervalMonth > Constants.MAX_MONTHS) {
            LOGGER.warn(Error.ERROR_COUNT_MONTHS.message());
            str1 += Error.ERROR_COUNT_MONTHS.message()
                    + Constants.ENTER;
        }
        if (this.intervalDay < Constants.ZERO
                || this.intervalDay > Constants.MAX_DAYS) {
            LOGGER.warn(Error.ERROR_COUNT_DAYS.message());
            str1 += Error.ERROR_COUNT_DAYS.message()
                    + Constants.ENTER;
        }
        if (this.intervalHour < Constants.ZERO
                || this.intervalHour > Constants.MAX_HOURS) {
            LOGGER.warn(Error.ERROR_COUNT_HOURS.message());
            str1 += Error.ERROR_COUNT_HOURS.message()
                    + Constants.ENTER;
        }
        if (this.intervalMinute < Constants.ZERO
                || this.intervalMinute > Constants.MAX_MINUTES) {
            LOGGER.warn(Error.ERROR_COUNT_MINUTES.message());
            str1 += Error.ERROR_COUNT_MINUTES.message()
                    + Constants.ENTER;
        }
        if (this.intervalSecond < Constants.ZERO
                || this.intervalSecond > Constants.MAX_SECONDS) {
            LOGGER.warn(Error.ERROR_COUNT_SECONDS.message());
            str1 += Error.ERROR_COUNT_SECONDS.message()
                    + Constants.ENTER;
        }
        return str1;
    }

    /**
     * Create error massage about empty repeater interval.
     * @return error message.
     */
    public String errorEmptyInterval() {
        boolean part1 = this.intervalYear == Constants.ZERO
                && this.intervalMonth == Constants.ZERO
                && this.intervalDay == Constants.ZERO;
        boolean part2 = this.intervalHour == Constants.ZERO
                && this.intervalMinute == Constants.ZERO
                && this.intervalSecond == Constants.ZERO;
        if (part1 && part2) {
            LOGGER.warn(Error.ERROR_INTERVAL.message());
            return Error.ERROR_INTERVAL.message();
        } else {
            return "";
        }
    }
}
