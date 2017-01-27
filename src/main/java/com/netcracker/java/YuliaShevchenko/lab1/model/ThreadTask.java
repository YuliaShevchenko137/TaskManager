package com.netcracker.java.YuliaShevchenko.lab1.model;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Class ThreadTask.
 * Realisation of alert the user of an approaching task.
 */

public class ThreadTask {

    /**
     * Map with tasks.
     */
    private ArrayTaskList thistasks;

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
        this.thistasks = tasks;
        this.timeStamps = new TreeMap<>();
        this.thread = new Thread(() -> {
            while (work) {
                Date nowPlusDay = OperationForTime.localDateTimeToDate(
                        OperationForTime.dateToLocalDateTime(
                                OperationForTime.nowTime()).plusHours(1));
                if (thistasks.size() != 0) {
                    Map<Date, Set<Task>> map = Tasks.calendar(thistasks,
                            OperationForTime.nowTime(), nowPlusDay);
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
     * Close current thread.
     */
    public void close() {
        this.work = false;
    }
}
