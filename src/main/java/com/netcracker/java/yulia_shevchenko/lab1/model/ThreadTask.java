package com.netcracker.java.yulia_shevchenko.lab1.model;

import java.util.*;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Class ThreadTask.
 * Realisation of alert the user of an approaching task.
 */

public class ThreadTask {

    /**
     *map with calls.
     * true, if the notification was at this time.
     */
    private Map<Date, Boolean> timeStamps;

    /**
     * Thread for notification.
     */
    private Thread thread;

    /**
     * close thread.
     */
    private boolean work = true;

    /**
     * Method ThreadTask(Task tas).
     * Constructor for creating thread.
     * @param tasks current tasks for alert.
     */
    public ThreadTask(final ArrayTaskList tasks) {
        ArrayTaskList thisTasks = tasks;
        this.timeStamps = new TreeMap<>();
        new Thread(() -> {
            while (work) {
                Date nowPlusHalfMinute = OperationForTime.localDateTimeToDate(
                        OperationForTime.dateToLocalDateTime(
                                OperationForTime.nowTime()).plusMinutes(30));
                if (thisTasks.size() != 0) {
                    Map<Date, Set<Task>> map = Tasks.calendar(thisTasks,
                            OperationForTime.nowTime(), nowPlusHalfMinute);
                    Set<Date> dates = map.keySet();
                    for (Date date : dates) {
                        if (!timeStamps.containsKey(date)) {
                            timeStamps.put(date, false);
                        }
                        while (work && !this.timeStamps.get(date)) {
                            long countMSecond = date.getTime()
                                    - OperationForTime.nowTime().getTime();
                            Set<Task> task = map.get(date);
                            if (countMSecond <= Constants.HALF_HOUR) {
                                String str = "";
                                for (Task thisTask : task) {
                                    str += thisTask.getTitle() + Constants.ENTER;
                                }
                                String finalStr = str;
                                Platform.runLater(() -> {
                                    Alert inf = new Alert(Alert.
                                            AlertType.INFORMATION);
                                    inf.setTitle("Notification");
                                    inf.setHeaderText("At " + date
                                            + " it is necessary to perform:"
                                            + Constants.ENTER + finalStr);
                                    inf.show();
                                });
                                this.timeStamps.put(date, true);
                            }
                        }
                    }
                }
            }
        });
        this.thread.start();
    }

    /**
     * Add task in TaskList.
     * @param task current task for add.
     */
    public void add(Task task) {
        Date nowPlusHalfMinute = OperationForTime.localDateTimeToDate(
                OperationForTime.dateToLocalDateTime(
                        OperationForTime.nowTime()).plusMinutes(30));
        List<Task> list = new ArrayList<>();
        list.add(task);
        Map<Date, Set<Task>> map = Tasks.calendar(list,
                OperationForTime.nowTime(), nowPlusHalfMinute);
        for (Date date : map.keySet()) {
            Set<Task> set = map.get(date);
            for (Task thisTask : set) {
                Platform.runLater(() -> {
                    Alert inf = new Alert(Alert.
                            AlertType.INFORMATION);
                    inf.setTitle("Notification");
                    inf.setHeaderText("At " + date
                            + " it is necessary to perform:"
                            + Constants.ENTER + thisTask.getTitle());
                    inf.show();
                });
            }
            timeStamps.put(date, true);
        }
    }

    /**
     * Close current thread.
     */
    public void close() {
        this.work = false;
    }
}
