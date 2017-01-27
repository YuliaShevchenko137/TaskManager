package com.netcracker.java.YuliaShevchenko.lab1.model;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Class Task.
 * Realization of of some task that you can add, change or delete a user.
 */
public class Task implements Cloneable, Serializable {

    /**
     * It is used to register error.
     */
    private static final Logger LOGGER = Logger.getLogger(Task.class);

    /**
     * Version control for serialization.
     */
    private static final long serialVersionUID = 42L;

    /**
     * Title ot the current task.
     */
    private String title;

    /**
     * Activity of the current task.
     */
    private boolean active;

    /**
     * Repeated of the current task.
     */
    private boolean repeated;

    /**
     * Start date of the current task.
     */
    private Date start;

    /**
     * End date of the current task.
     */
    private Date end;

    /**
     * Object type CreateInterval.
     * It creates string view repetition interval of the current task.
     */
    private CreateInterval interval;
    
    /**
     * Method Task(String title, Date time).
     * Constructor creation non recurring task and thread for alert.
     * @param   titles title task.
     * @param   times time performance.
     */
    public Task(final String titles, final Date times) {
        this.setTitle(titles);
        this.setTime(times);
        this.interval = new CreateInterval();
    }

    /**
     *  Method Task(String title, Date start, Date end,
     *  CreateInterval interval).
     * Constructor creation repeated task and thread for alert.
     * @param titles title task.
     * @param starts start date performance.
     * @param ends end date performance.
     * @param intervals repetition interval.
     */
    public Task(final String titles, final Date starts,
                final Date ends, final CreateInterval intervals) {
        this.setTitle(titles);
        this.interval = intervals;
        this.setTime(starts, ends);
    }

    public final String getTitle() {
        return this.title;
    }

    public final CreateInterval getCreateInterval() {
        return this.interval;
    }

    public final void setTitle(final String titles) {
        this.title = titles;
    }

    public final boolean isActive() {
        return this.active;
    }

    public final void setActive(final boolean actives) {
        this.active = actives;
    }

    public final Date getStart() {
        return this.start;
    }

    public final Date getEnd() {
        return this.end;
    }

    public final boolean isRepeated() {
        return this.repeated;
    }

    /**
     * Method clone().
     * Create clone of the current task: {@link Object#clone()}.
     * @return  copy current object.
     */
    @Override
    public final Task clone() {
        try {
            return (Task) super.clone();
        } catch (CloneNotSupportedException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * Method setTime(Date time).
     * Set a date value for non recurring task {@link #setTime(Date, Date)}.
     * @param time time of the notification.
     */
    public final void setTime(final Date time) {
        this.setTime(time, time);
    }

    /**
     * Метод setTime(Date start, Date end).
     * Set a date value for repeated task.
     * @param starts stand date notification.
     * @param ends end date notification.
     */
    private void setTime(final Date starts, final Date ends) {
        this.setStart((Date) starts.clone());
        this.setEnd((Date) ends.clone());
    }

    /**
     * Method nextTimeAfter(Date current).
     * Search date when task will be performed after current.
     * @param current start date for search.
     * @return time to next performed.
     */
    public final  Date nextTimeAfter(final Date current) {
        Date a = null;
        boolean b = false;
        if (!this.active) {
            a = null;
            b = true;
        } else if (current.before(this.start)) {
            a = this.start;
            b = true;
        } else if (current.after(this.end)) {
            a = null;
            b = true;
        } else if (!b) {
            LocalDateTime start1 =
                    OperationForTime.dateToLocalDateTime(this.start);
            LocalDateTime end1 =
                    OperationForTime.dateToLocalDateTime(this.end);
            LocalDateTime current1 =
                    OperationForTime.dateToLocalDateTime(current);
            do {
                start1 = OperationForTime.plusTime(start1, this);
            } while (start1.isBefore(current1) || start1.equals(current1));
            if (start1.isBefore(end1) || start1.equals(end1)) {
                a =  OperationForTime.localDateTimeToDate(current1);
            }
        }
        return a;
    }

    /**
     * Method equals(Object obj).
     * equals two tasks: {@link Object#equals(Object)}.
     * @param obj the object of comparison .
     * @return true, if objects is identical, or false.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            Task task = (Task) obj;
            boolean a5 = this.title.equals(task.getTitle());
            boolean a2 = this.start.equals(task.getStart());
            boolean a3 = this.isActive() == task.isActive();
            boolean a4 = this.interval.getInterval().
                    equals(task.getInterval());
            boolean a = this.end.equals(task.getEnd());
            return a & a2 & a3 & a4 & a5;
        }

    }

    /**
     * Method hashCode().
     * use for hashing date: {@link Object#hashCode()}.
     * @return hashcode of the current task.
     */
    @Override
    public final int hashCode() {
        return this.title.hashCode()
                + this.interval.hashCode()
                + this.start.hashCode()
                + this.end.hashCode();
    }

    /**
     * Method getInterval().
     * Getter for text representation of the repetition interval.
     * @return repetition interval.
     */
    public final String getInterval() {
        return this.interval.getInterval();
    }

    public final void setStart(final Date starts) {
        this.start = starts;
    }

    public final void setEnd(final Date ends) {
        this.end = ends;
    }

    public final void setRepeated(final boolean repeateds) {
        this.repeated = repeateds;
    }
}
