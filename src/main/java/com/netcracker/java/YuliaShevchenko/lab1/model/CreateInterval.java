package com.netcracker.java.YuliaShevchenko.lab1.model;

/**
 * Class CreateInterval.
 * Helper class for creating repeated interval.
 */
public class CreateInterval {

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
}
