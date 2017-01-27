package com.netcracker.java.YuliaShevchenko.lab1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.time.Instant;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.time.ZoneId;

/**
 * Class Tasks.
 * Task List Processing. Create a calendar.
 * All methods are static.
 */
public final class Tasks {

    /**
     * Method incoming(Iterable tasks, Date start, Date end).
     * Create list of the tasks that enter within the range of.
     * @param tasks task list.
     * @param start start date of the interval.
     * @param end end date of the interval.
     * @return list of the tasks.
     */
    private static Iterable<Task> incoming(
            final Iterable<Task> tasks, final Date start,
            final Date end) {
        List<Task> list = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isActive()) {
                Date s = t.nextTimeAfter(start);
                if (s != null && s.equals(end)) {
                    list.add(t);
                } else if (s != null && s.before(end)) {
                    list.add(t);
                }
            }
        }
        return list;
    }

    /**
     * Method calendar(Iterable tasks, Date start, Date end).
     * Creating calendar on the current task list.
     * @param tasks task list.
     * @param start start date.
     * @param end end date.
     * @return map on the current task list.
     */
    public static SortedMap<Date, Set<Task>> calendar(
            final Iterable<Task> tasks, final Date start,
            final Date end) {
        Iterable<Task> t1 = incoming(tasks, start, end);
        List<Task> tasks1 = (List<Task>) t1;
        SortedMap<Date, Set<Task>> res = new TreeMap<>();
        Set<Task> s;
        for (Task t : tasks1) {
            if (!t.isRepeated()) {
                if (!res.containsKey(t.getStart())) {
                    s = new HashSet<>();
                    s.add(t);
                    res.put(t.getStart(), s);
                } else {
                    res.get(t.getStart()).add(t);
                }
            }
            Instant instant = Instant.ofEpochMilli(t.getStart().getTime());
            LocalDateTime start1 = LocalDateTime.
                    ofInstant(instant, ZoneId.systemDefault());
            if (t.getStart().before(start)) {
                while (start1.isBefore(LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(start.getTime()),
                        ZoneId.systemDefault()))) {
                    start1 = OperationForTime.plusTime(start1, t);
                }
            }
            instant = Instant.ofEpochMilli(t.getEnd().getTime());
            LocalDateTime end1 = LocalDateTime.ofInstant(instant,
                    ZoneId.systemDefault());
            if (t.getEnd().after(end)) {
                while (end1.isAfter(LocalDateTime.ofInstant(Instant.
                        ofEpochMilli(end.getTime()), ZoneId.systemDefault()))) {
                    end1 = OperationForTime.minusTime(end1, t);
                }
            }
            while (start1.isBefore(end1)) {
                instant = start1.atZone(ZoneId.systemDefault()).toInstant();
                Date startT = Date.from(instant);
                if (!res.containsKey(startT)) {
                    s = new HashSet<>();
                    s.add(t);
                    res.put(startT, s);
                } else {
                    res.get(startT).add(t);
                }
                start1 = OperationForTime.plusTime(start1, t);
            }
        }
        return res;
    }

}
