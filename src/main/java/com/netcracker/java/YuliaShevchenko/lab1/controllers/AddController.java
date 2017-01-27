package com.netcracker.java.YuliaShevchenko.lab1.controllers;

import org.apache.log4j.Logger;

import com.netcracker.java.YuliaShevchenko.lab1.model.Constants;
import com.netcracker.java.YuliaShevchenko.lab1.model.CreateInterval;
import com.netcracker.java.YuliaShevchenko.lab1.model.Error;
import com.netcracker.java.YuliaShevchenko.lab1.model.OperationForTime;
import com.netcracker.java.YuliaShevchenko.lab1.model.Task;

import java.time.LocalDate;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Class AddController.
 * Adding windows controller.
 */
public class AddController {

    /**
     * It is used to register error.
     */
    private static final Logger LOGGER
            = Logger.getLogger(AddController.class);

    /**
     * Title of the task.
     */
    @FXML
    private TextField taskName;

    /**
     * Start time of the task.
     */
    @FXML
    private TextField timeStart;

    /**
     * End time of the task.
     */
    @FXML
    private TextField timeEnd;

    /**
     * Years in interval repeating.
     */
    @FXML
    private TextField year;

    /**
     * Months in interval repeating.
     */
    @FXML
    private TextField month;

    /**
     * Days in interval repeating.
     */
    @FXML
    private TextField day;

    /**
     * hour.
     * Hours in interval repeating.
     */

    @FXML
    private TextField hour;

    /**
     * Minutes in interval repeating.
     */
    @FXML
    private TextField minute;

    /**
     * Seconds in interval repeating.
     */

    @FXML
    private TextField second;

    /**
     * Start date of the task.
     */
    @FXML
    private DatePicker dateStart;

    /**
     * End date of the task.
     */
    @FXML
    private DatePicker dateEnd;

    /**
     * RadioButton: active task.
     */
    @FXML
    private RadioButton activeTrue;

    /**
     * RadioButton: inactive task.
     */
    @FXML
    private RadioButton activeFalse;

    /**
     * label error message when data incorrect.
     */
    @FXML
    private Label error;

    /**
     * Label start.
     */
    @FXML
    private Label labelStart;

    /**
     * Label End.
     */
    @FXML
    private Label labelEnd;

    /**
     * Label Interval.
     */
    @FXML
    private Label labelInterval;

    /**
     * Label years.
     */
    @FXML
    private Label labelYear;

    /**
     * Label months.
     */
    @FXML
    private Label labelMonth;

    /**
     * Label days.
     */
    @FXML
    private Label labelDay;

    /**
     * Label hours.
     */
    @FXML
    private Label labelHour;

    /**
     * Label minutes.
     */
    @FXML
    private Label labelMinute;

    /**
     * Label seconds.
     */
    @FXML
    private Label labelSecond;

    /**
     *  CheckBox: repeated task.
     */
    @FXML
    private CheckBox checkboxrepeated;

    /**
     * On the basis of this string displays an error message.
     */
    private String str1 = "";

    /**
     * New task.
     */
    private Task newTask;

    /**
     * True, if data correct and new task is created.
     */
    private boolean bool;

    /**
     * Method initialize().
     * Executed when a controller is loaded.
     */
    @FXML
    public final void initialize() {
        this.timeStart.setPromptText(Constants.TIME_FORMAT);
        this.getTimeEnd().setPromptText(Constants.TIME_FORMAT);
        this.dateStart.setValue(LocalDate.now());
        this.getDateEnd().setValue(LocalDate.now());
        this.startValueInterval();

    }

    /**
     * Method startValueInterval().
     * It sets the initial value of the interval component.
     */
    private void startValueInterval() {
        this.getYear().setText(String.valueOf(Constants.ZERO));
        this.getMonth().setText(String.valueOf(Constants.ZERO));
        this.getDay().setText(String.valueOf(Constants.ZERO));
        this.getHour().setText(String.valueOf(Constants.ZERO));
        this.getMinute().setText(String.valueOf(Constants.ZERO));
        this.getSecond().setText(String.valueOf(Constants.ZERO));
    }

    /**
     * Method createRepeatedTask().
     * Create repeated task.
     * @return new task.
     */
    private Task createRepeatedTask() {
        String title = this.taskName.getText();
        if (title.length() == 0) {
            LOGGER.warn(Error.ERROR_EMPTY_TITLE.message());
            this.str1 += Error.ERROR_EMPTY_TITLE.message() + Constants.ENTER;
        }
        String str = this.dateStart.getValue().toString()
                + Constants.SPACE + this.timeStart.getText();
        Date start = OperationForTime.parseDate(str);
        if (start == null) {
            LOGGER.warn(Error.ERROR_START_TIME.message());
            this.str1 += Error.ERROR_START_TIME.message() + Constants.ENTER;
            start = new Date(Constants.ZERO);
        }
        Date end = OperationForTime.parseDate(
                this.getDateEnd().getValue().toString()
                + Constants.SPACE + this.getTimeEnd().getText());
        if (end == null) {
            LOGGER.warn(Error.ERROR_END_TIME.message());
            this.str1 += Error.ERROR_END_TIME.message() + Constants.ENTER;
            end = new Date(Constants.ZERO);
        }
        if (end.before(start) || end.equals(start)) {
            LOGGER.warn(Error.ERROR_EARLIER_TIME.message());
            this.str1 += Error.ERROR_EARLIER_TIME.message() + Constants.ENTER;
        }
        CreateInterval interval = this.createNewInterval();
        if ("".equals(this.str1)) {
            Task task = new Task(title, start, end, interval);
            task.setRepeated(true);
            task.getInterval();
            if (this.activeTrue.isSelected()) {
                task.setActive(true);
            }
            return task;
        } else {
            return null;
        }
    }

    /**
     * Method createNewInterval().
     * Create new repeated interval.
     * @return object type CreateInterval.
     */
    private CreateInterval createNewInterval() {
        final int intervalYear = Integer.parseInt(this.getYear().getText());
        final int intervalMonth = Integer.parseInt(this.getMonth().getText());
        final int intervalDay = Integer.parseInt(this.getDay().getText());
        final int intervalHour = Integer.parseInt(this.getHour().getText());
        final int intervalMinute = Integer.parseInt(this.getMinute().getText());
        final int intervalSecond = Integer.parseInt(this.getSecond().getText());
        if (intervalMonth < Constants.ZERO 
                || intervalMonth > Constants.MAX_MONTHS) {
            LOGGER.warn(Error.ERROR_COUNT_MONTHS.message());
            this.str1 += Error.ERROR_COUNT_MONTHS.message()
                    + Constants.ENTER;
        }
        if (intervalDay < Constants.ZERO 
                || intervalDay > Constants.MAX_DAYS) {
            LOGGER.warn(Error.ERROR_COUNT_DAYS.message());
            this.str1 += Error.ERROR_COUNT_DAYS.message()
                    + Constants.ENTER;
        }
        if (intervalHour < Constants.ZERO 
                || intervalHour > Constants.MAX_HOURS) {
            LOGGER.warn(Error.ERROR_COUNT_HOURS.message());
            this.str1 += Error.ERROR_COUNT_HOURS.message()
                    + Constants.ENTER;
        }
        if (intervalMinute < Constants.ZERO 
                || intervalMinute > Constants.MAX_MINUTES) {
            LOGGER.warn(Error.ERROR_COUNT_MINUTES.message());
            this.str1 += Error.ERROR_COUNT_MINUTES.message()
                    + Constants.ENTER;
        }
        if (intervalSecond < Constants.ZERO 
                || intervalSecond > Constants.MAX_SECONDS) {
            LOGGER.warn(Error.ERROR_COUNT_SECONDS.message());
            this.str1 += Error.ERROR_COUNT_SECONDS.message()
                    + Constants.ENTER;
        }
        boolean part1 = intervalYear == Constants.ZERO
                && intervalMonth == Constants.ZERO
                && intervalDay == Constants.ZERO;
        boolean part2 = intervalHour == Constants.ZERO
                && intervalMinute == Constants.ZERO
                && intervalSecond == Constants.ZERO;
        if (part1 && part2) {
            LOGGER.warn(Error.ERROR_INTERVAL.message());
            this.str1 += Error.ERROR_INTERVAL.message();
        }
        return new CreateInterval(intervalYear, intervalMonth, intervalDay,
                intervalHour, intervalMinute, intervalSecond);
    }

    /**
     * Method createNoRepeatedTask().
     * Create non recurring task.
     * @return new task.
     */
    private Task createNoRepeatedTask() {
        String title = this.taskName.getText();
        if (title.length() == 0) {
            LOGGER.warn(Error.ERROR_EMPTY_TITLE.message());
            this.str1 += Error.ERROR_EMPTY_TITLE.message() + Constants.ENTER;
        }
        String str = this.dateStart.getValue().toString()
                + Constants.SPACE + this.timeStart.getText();
        Date start = OperationForTime.parseDate(str);
        if (start == null) {
            LOGGER.warn(Error.ERROR_TIME.message());
            this.str1 += Error.ERROR_TIME.message() + Constants.ENTER;
            start = new Date(Constants.ZERO);
        }
        if ("".equals(this.str1)) {
            Task task = new Task(title, start);
            task.setRepeated(false);
            task.getInterval();
            task.setActive(this.activeTrue.isSelected());
            return task;
        } else {
            return null;
        }
    }

    /**
     * Method createTask().
     * If checkboxrepeated is selected, invokes the createRepeatedTask().
     * If checkboxrepeated is not selected, invokes the
     * createNoRepeatedTask().
     * @return new task.
     */
    private Task createTask() {
        if (this.getCheckboxrepeated().isSelected()) {
            return this.createRepeatedTask();
        } else {
            return this.createNoRepeatedTask();
        }
    }

    /**
     * Method actionOk(ActionEvent actionEvent).
     * Save task, if data is correct.
     * @param actionEvent button press.
     */
    public final void actionOk(final ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        this.newTask = this.createTask();
        if ("".equals(this.str1)) {
            this.bool = true;
            stage.close();
        } else {
            this.bool = false;
            this.error.setText(this.str1);
            this.str1 = "";
        }
    }

    /**
     * Method actionCancel(ActionEvent actionEvent).
     * Close the window.
     * @param actionEvent button press.
     */

    public final void actionCancel(final ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        this.taskName.setText("");
        this.timeStart.setText("");
        this.getTimeEnd().setText("");
        this.startValueInterval();
        this.error.setText("");
        this.dateStart.setValue(LocalDate.now());
        this.getDateEnd().setValue(LocalDate.now());
    }

    /**
     * Method repeated().
     * Change showing window when user press CheckBox Repeated.
     */
    public final void repeated() {
        new InfoClass(this);
    }

    public final Label getLabelStart() {
        return this.labelStart;
    }

    public final Label getLabelEnd() {
        return this.labelEnd;
    }

    public final Label getLabelInterval() {
        return this.labelInterval;
    }

    public final Label getLabelYear() {
        return this.labelYear;
    }

    public final Label getLabelMonth() {
        return this.labelMonth;
    }

    public final Label getLabelDay() {
        return this.labelDay;
    }

    public final Label getLabelHour() {
        return this.labelHour;
    }

    public final Label getLabelMinute() {
        return this.labelMinute;
    }

    public final Label getLabelSecond() {
        return this.labelSecond;
    }

    public final TextField getTimeEnd() {
        return this.timeEnd;
    }

    public final TextField getYear() {
        return this.year;
    }

    public final TextField getMonth() {
        return this.month;
    }

    public final TextField getDay() {
        return this.day;
    }

    public final TextField getHour() {
        return this.hour;
    }

    public final TextField getMinute() {
        return this.minute;
    }

    public final TextField getSecond() {
        return this.second;
    }

    public final DatePicker getDateEnd() {
        return this.dateEnd;
    }

    public final CheckBox getCheckboxrepeated() {
        return this.checkboxrepeated;
    }

    public final Task getNewTask() {
        return this.newTask;
    }

    public final boolean isBool() {
        return this.bool;
    }
}
