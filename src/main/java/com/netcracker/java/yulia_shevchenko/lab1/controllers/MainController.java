package com.netcracker.java.yulia_shevchenko.lab1.controllers;

import com.netcracker.java.yulia_shevchenko.lab1.model.ArrayTaskList;
import com.netcracker.java.yulia_shevchenko.lab1.model.Error;
import com.netcracker.java.yulia_shevchenko.lab1.model.CollectionsTasks;
import com.netcracker.java.yulia_shevchenko.lab1.model.Constants;
import com.netcracker.java.yulia_shevchenko.lab1.model.CreateInterval;
import com.netcracker.java.yulia_shevchenko.lab1.model.OperationForTime;
import com.netcracker.java.yulia_shevchenko.lab1.model.Task;
import com.netcracker.java.yulia_shevchenko.lab1.model.TaskIO;
import com.netcracker.java.yulia_shevchenko.lab1.model.Tasks;
import com.netcracker.java.yulia_shevchenko.lab1.model.ThreadTask;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Class MainController.
 * Controller of the main window.
 */
public final class MainController {

    /**
     * It is used to register error.
     */
    private static final Logger LOGGER
            = Logger.getLogger(MainController.class);

    /**
     * Wrapper of the task list.
     */
    private CollectionsTasks obs;

    /**
     * Table with tasks.
     */
    @FXML
    private TableView<Task> taskTable;

    /**
     * Column of the title of the task.
     */
    @FXML
    private TableColumn<Task, String> taskName;

    /**
     * Column of the activity of the task.
     */
    @FXML
    private TableColumn<Task, Boolean> taskActive;

    /**
     * Label with count tasks.
     */
    @FXML
    private Label labelSize;

    /**
     * Title of the current task.
     */
    @FXML
    private Label currentTaskName;

    /**
     * Start date of the current task.
     */
    @FXML
    private Label currentTaskStart;

    /**
     * End date on the current task.
     */
    @FXML
    private Label currentTaskEnd;

    /**
     * Repeated interval of the current task.
     */
    @FXML
    private Label currentTaskInterval;

    /**
     * Activity of the current task.
     */
    @FXML
    private Label currentTaskActive;

    /**
     * label error message when data changes.
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
     * Button Change.
     */
    @FXML
    private Button change;

    /**
     * Button Apply.
     */
    @FXML
    private Button apply;

    /**
     * Table to show data.
     */
    @FXML
    private GridPane gridView;

    /**
     * Table to change data.
     */
    @FXML
    private GridPane gridChange;

    /**
     * TextField change title of the task.
     */
    @FXML
    private TextField taskNameField;

    /**
     * Start time of the changing task.
     */
    @FXML
    private TextField timeStart;

    /**
     * End time of the changing task.
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
     * Years in interval repeating.
     */
    @FXML
    private TextField second;

    /**
     * Start time of the calendar.
     */
    @FXML
    private TextField calendarTimeStart;

    /**
     * End time of the calendar.
     */
    @FXML
    private TextField calendarTimeEnd;

    /**
     * Start date of the changing task.
     */
    @FXML
    private DatePicker dateStart;

    /**
     * dateEnd.
     * End date of the changing task.
     */

    @FXML
    private DatePicker dateEnd;

    /**
     * End date of the calendar.
     */
    @FXML
    private DatePicker calendarDateEnd;

    /**
     * Start date of the calendar.
     */
    @FXML
    private DatePicker calendarDateStart;

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
     * CheckBox: repeated task.
     */
    @FXML
    private CheckBox checkboxrepeated;

    /**
     * Index selected task.
     */
    private int countChanges = -1;

    /**
     * Minimal height window.
     */
    private final int minHeight = 450;

    /**
     * Minimal width window.
     */
    private final int minWidth = 350;

    /**
     * File for reading ot writing.
     */
    private final File temp = new File("temp.txt");

    /**
     * Thread for alert.
     */
    private ThreadTask thread;

    /**
     * Method initialize().
     * Executed when a controller is loaded.
     * @throws ParseException appears when converting dates.
     * @throws IOException appears when opening a file.
     */
    @FXML
    public void initialize() throws ParseException, IOException {
        this.obs = new CollectionsTasks();
        ArrayTaskList tasks = new ArrayTaskList();
        TaskIO.readText(tasks, this.temp);
        this.thread = new ThreadTask(tasks);
        this.obs.setTasks(tasks);
        this.taskTable.setItems(this.obs.getObs());
        this.taskName.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        this.taskActive.setCellValueFactory(
                new PropertyValueFactory<>("active"));
        this.taskTable.setItems(this.getObs().getObs());
        this.taskTable.getSelectionModel().selectedItemProperty().addListener(
            (observableValue, oldValue,
             newValue) -> this.showTaskDetails(newValue));
        this.gridChange.setVisible(false);
        this.gridView.setVisible(true);
        this.showNoting();
        this.apply.setVisible(false);
        this.apply.setText(Constants.APPLY);
        this.change.setText(Constants.CHANGE);
        this.labelSize.setText(Constants.COUNT_TASK
                + this.obs.getObs().size());
    }

    /**
     * Method showNoting().
     * Called when no task is selected.
     */
    private void showNoting() {
        this.currentTaskName.setText("");
        this.currentTaskStart.setText("");
        this.currentTaskEnd.setText("");
        this.currentTaskInterval.setText("");
        this.currentTaskActive.setText("");
    }

    /**
     * Method showTaskDetails(Task task).
     * Show details of the selected task.
     * @param task selected task.
     */
    private void showTaskDetails(final Task task) {
        if (task != null) {
            this.currentTaskName.setText(task.getTitle());
            this.currentTaskStart.setText(String.valueOf(task.getStart()));
            this.currentTaskEnd.setText(String.valueOf(task.getEnd()));
            this.currentTaskInterval.setText(task.getInterval());
            this.currentTaskActive.setText(String.valueOf(task.isActive()));
        } else {
            this.showNoting();
        }
    }

    /**
     * Метод showDetailsForChange(Task task).
     * Show data fow changing task.
     * @param task task for changing.
     */
    private void showDetailsForChange(final Task task) {
        this.taskNameField.setText(task.getTitle());
        String str = String.valueOf(task.getStart());
        String[] words = str.split(Constants.REG_EXP);
        this.dateStart.setValue(OperationForTime.dateToLocalDate(
                task.getStart()));
        this.timeStart.setText(words[3]);
        this.getDateEnd().setValue(OperationForTime.dateToLocalDate(
                task.getEnd()));
        str = String.valueOf(task.getStart());
        words = str.split(Constants.REG_EXP);
        this.getTimeEnd().setText(words[3]);
        this.activeTrue.setSelected(task.isActive());
        this.activeFalse.setSelected(!task.isActive());
        this.getCheckboxrepeated().setSelected(task.isRepeated());
        this.getYear().setText(String.valueOf(
                task.getCreateInterval().getIntervalYear()));
        this.getMonth().setText(String.valueOf(
                task.getCreateInterval().getIntervalMonth()));
        this.getDay().setText(String.valueOf(
                task.getCreateInterval().getIntervalDay()));
        this.getMinute().setText(String.valueOf(
                task.getCreateInterval().getIntervalMinute()));
        this.getHour().setText(String.valueOf(
                task.getCreateInterval().getIntervalHour()));
        this.getSecond().setText(String.valueOf(
                task.getCreateInterval().getIntervalSecond()));
        task.getInterval();
        this.getCheckboxrepeated().setSelected(task.isRepeated());
        this.repeatedTask();
    }

    /**
     * Method add(ActionEvent actionEvent).
     * Create new window Add task and add new task to the task list.
     * @param actionEvent button press.
     * @throws IOException appears when opening a file.
     */
    public void add(final ActionEvent actionEvent) throws IOException {
        Stage addStage = new Stage();
        FXMLLoader addfxmlLoader = new FXMLLoader();
        addfxmlLoader.setLocation(getClass().getResource("/com/netcracker/java/yulia_shevchenko/lab1/view/add.fxml"));
        Parent root = addfxmlLoader.load();
        addStage.setTitle("Add task");
        addStage.setMinHeight(this.minHeight);
        addStage.setMinWidth(this.minWidth);
        addStage.setResizable(false);
        addStage.setScene(new Scene(root));
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(((Node)
                actionEvent.getSource()).getScene().getWindow());
        addStage.showAndWait();
        AddController addController = addfxmlLoader.getController();
        if (addController.isBool()) {
            Task task = addController.getNewTask();
            this.obs.add(task);
            this.thread.add(task);
            this.taskTable.setItems(this.obs.getObs());
            this.labelSize.setText(Constants.COUNT_TASK
                    + this.obs.getObs().size());
            TaskIO.writeText(this.obs.getTasks(), this.temp);
        }
    }

    /**
     * Method remove().
     * Call notification about the removal of the problem and
     * remove it in the event of confirmation.
     * @throws IOException appears when writing a file.
     */
    public void remove() throws IOException {
        Task t = this.taskTable.getSelectionModel().getSelectedItem();
        if (t == null) {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Warning");
            info.setHeaderText("Nothing to selected.");
            info.showAndWait();
            LOGGER.warn("remove: nothing selected");
            return;
        }
        Alert delete = new Alert(Alert.AlertType.CONFIRMATION);
        delete.setTitle("Remove");
        delete.setHeaderText("Are you sure?");
        Optional<ButtonType> result = delete.showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            this.obs.remove(t);
            TaskIO.writeText(this.obs.getTasks(), this.temp);
            this.labelSize.setText(Constants.COUNT_TASK
                    + this.obs.getObs().size());
        }
    }

    /**
     * Method calendar(ActionEvent actionEvent).
     * Create new window Calendar for a selected interval of the time.
     * @param actionEvent button press.
     * @throws IOException appears when opening a file.
     * @throws ParseException appears when converting dates.
     */
    public void calendar(final ActionEvent actionEvent)
            throws IOException, ParseException {
        LocalDate dateStarts = this.calendarDateStart.getValue();
        LocalDate dateEnds = this.calendarDateEnd.getValue();
        String timestart = this.calendarTimeStart.getText();
        String timeend = this.calendarTimeEnd.getText();
        if (dateStarts == null
                || dateEnds == null
                || "".equals(timestart)
                || "".equals(timeend)) {
            LOGGER.warn("calendar: Interval is not selected");
            return;
        }
        Stage calendarStage = new Stage();
        FXMLLoader calendarfxmlLoader = new FXMLLoader();
        calendarfxmlLoader.setLocation(
                getClass().getResource("/com/netcracker/java/yulia_shevchenko/lab1/view/calendar.fxml"));
        Parent root = calendarfxmlLoader.load();
        calendarStage.setTitle("Calendar");
        calendarStage.setMinHeight(this.minHeight);
        calendarStage.setMinWidth(2*this.minWidth);
        calendarStage.setScene(new Scene(root));
        calendarStage.initModality(Modality.WINDOW_MODAL);
        calendarStage.initOwner(((Node)
                actionEvent.getSource()).getScene().getWindow());
        Date dateSt = OperationForTime.parseDate(dateStarts
                + Constants.SPACE + timestart);
        Date dateEn = OperationForTime.parseDate(dateEnds
                + Constants.SPACE + timeend);
        Map<Date, java.util.Set<Task>> maps
                = Tasks.calendar(this.obs.getObs(), dateSt, dateEn);
        CalendarController calendarController
                = calendarfxmlLoader.getController();
        calendarController.setMaps(maps);
        calendarController.fillingList(maps);
        calendarStage.showAndWait();
    }

    public CollectionsTasks getObs() {
        return this.obs;
    }

    /**
     * Method applyChanges().
     * If the data is correct, it changes the current task.
     * @throws CloneNotSupportedException if object nonclonability.
     * @throws ParseException appears when converting dates.
     * @throws IOException appears when opening a file.
     */
    public void applyChanges() throws CloneNotSupportedException,
            ParseException, IOException {
        if (this.countChanges == -1) {
            this.countChanges
                    = this.taskTable.getSelectionModel().getSelectedIndex();
        }
        Task task = this.obs.getObs().get(this.countChanges);
        Task task1 = task.clone();
        String str1;
        this.obs.remove(task1);
        if (this.getCheckboxrepeated().isSelected()) {
            str1 = this.changeRepeatedTask(task, task1);
        } else {
            str1 = this.changeNoRepeatedTask(task, task1);
        }
        if ("".equals(str1)) {
            this.gridChange.setVisible(false);
            this.gridView.setVisible(true);
            task.getInterval();
            this.obs.add(task);
            this.taskTable.refresh();
            this.thread.add(task);
            this.countChanges = -1;
            this.change.setVisible(true);
            this.apply.setVisible(false);
            this.error.setText("");
        } else {
            this.error.setVisible(true);
            this.obs.add(task1);
            this.error.setText(str1);
        }
    }

    /**
     * Method changeRepeatedTask(Task task, Task task1).
     * Change repeated task.
     * @param task current task.
     * @param task1 in the case of incorrect data.
     * @return error message.
     */
    private String changeRepeatedTask(final Task task, final Task task1) {
        String str1 = "";
        task.setRepeated(true);
        String s = this.taskNameField.getText();
        if (!"".equals(s)) {
            task.setTitle(s);
        } else {
            LOGGER.warn(Error.ERROR_EMPTY_TITLE.message());
            str1 += Error.ERROR_EMPTY_TITLE.message() + Constants.ENTER;
            task.setTitle(task1.getTitle());
        }
        task.setActive(this.activeTrue.isSelected());
        Date date = OperationForTime.parseDate(this.dateStart.getValue()
                + Constants.SPACE + this.timeStart.getText());
        if (date == null) {
            LOGGER.warn(Error.ERROR_START_TIME.message());
            task.setStart(task1.getStart());
            str1 += Error.ERROR_START_TIME.message() + Constants.ENTER;
        } else {
            task.setStart(date);
        }
        date = OperationForTime.parseDate(this.getDateEnd().getValue()
                + Constants.SPACE + this.getTimeEnd().getText());
        if (date == null) {
            LOGGER.warn(Error.ERROR_END_TIME.message());
            task.setEnd(task1.getEnd());
            str1 += Error.ERROR_END_TIME.message() + Constants.ENTER;
        } else {
            task.setEnd(date);
        }
        if (task.getStart().after(task.getEnd())
                || task.getStart().equals(task.getEnd())) {
            LOGGER.warn(Error.ERROR_EARLIER_TIME.message());
            str1 += Error.ERROR_EARLIER_TIME.message() + Constants.ENTER;
        }
        CreateInterval interval = task.getCreateInterval();
        str1 += this.changeInterval(task);
        if (interval.getIntervalYear() == Constants.ZERO
                && interval.getIntervalMonth() == Constants.ZERO
                && interval.getIntervalDay() == Constants.ZERO
                && interval.getIntervalHour() == Constants.ZERO
                && interval.getIntervalMinute() == Constants.ZERO
                && interval.getIntervalSecond() == Constants.ZERO) {
            LOGGER.warn(Error.ERROR_INTERVAL.message());
            str1 += Error.ERROR_INTERVAL.message() + Constants.ENTER;
        }
        return str1;
    }

    /**
     * Method changeInterval(Task task).
     * Change repeated interval.
     * @param task for changes.
     * @return error message.
     */
    private String changeInterval(final Task task) {
        String str1 = "";
        task.getCreateInterval().setIntervalYear(
                Integer.parseInt(this.getYear().getText()));
        task.getCreateInterval().setIntervalMonth(
                Integer.parseInt(this.getMonth().getText()));
        task.getCreateInterval().setIntervalDay(
                Integer.parseInt(this.getDay().getText()));
        task.getCreateInterval().setIntervalHour(
                Integer.parseInt(this.getHour().getText()));
        task.getCreateInterval().setIntervalMinute(
                Integer.parseInt(this.getMinute().getText()));
        task.getCreateInterval().setIntervalSecond(
                Integer.parseInt(this.getSecond().getText()));
        return task.getCreateInterval().errorInterval()
                + task.getCreateInterval().errorEmptyInterval();
    }

    /**
     * Метод changeNoRepeatedTask(Task task, Task task1).
     * Change no recurring task.
     * @param task current task.
     * @param task1 in the case of incorrect data.
     * @return error message.
     */
    private String changeNoRepeatedTask(final Task task, final Task task1) {
        String str = "";
        task.setRepeated(false);
        String s = this.taskNameField.getText();
        if (!"".equals(s)) {
            task.setTitle(s);
        } else {
            LOGGER.warn(Error.ERROR_EMPTY_TITLE.message());
            str += Error.ERROR_EMPTY_TITLE.message() + Constants.ENTER;
            task.setTitle(task1.getTitle());
        }
        task.setActive(this.activeTrue.isSelected());
        Date date = OperationForTime.parseDate(this.dateStart.getValue()
                + Constants.SPACE + this.timeStart.getText());
        if (date == null) {
            LOGGER.warn(Error.ERROR_TIME.message());
            str += Error.ERROR_TIME.message() + Constants.ENTER;
            task.setTime(task1.getStart());
        } else {
            task.setTime(date);
        }
        task.setRepeated(false);
        task.getCreateInterval().setIntervalYear(Constants.ZERO);
        task.getCreateInterval().setIntervalMonth(Constants.ZERO);
        task.getCreateInterval().setIntervalDay(Constants.ZERO);
        task.getCreateInterval().setIntervalHour(Constants.ZERO);
        task.getCreateInterval().setIntervalMinute(Constants.ZERO);
        task.getCreateInterval().setIntervalSecond(Constants.ZERO);
        return str;
    }

    /**
     * Method changeTask().
     * Change task.
     * @throws CloneNotSupportedException if object non clonability.
     */
    public void changeTask() throws CloneNotSupportedException {
        if (this.getObs().getObs().size() != Constants.ZERO) {
            this.showDetailsForChange(
                    this.taskTable.getSelectionModel().getSelectedItem());
            this.gridChange.setVisible(true);
            this.gridView.setVisible(false);
            this.change.setVisible(false);
            this.apply.setVisible(true);
        }
    }

    /**
     * Method repeatedTask().
     * Change showing window when user press CheckBox Repeated.
     */
    public void repeatedTask() {
        new InfoClass(this);
    }

    public Label getLabelStart() {
        return this.labelStart;
    }

    public Label getLabelEnd() {
        return this.labelEnd;
    }

    public Label getLabelInterval() {
        return this.labelInterval;
    }

    public Label getLabelYear() {
        return this.labelYear;
    }

    public Label getLabelMonth() {
        return this.labelMonth;
    }

    public Label getLabelDay() {
        return this.labelDay;
    }

    public Label getLabelHour() {
        return this.labelHour;
    }

    public Label getLabelMinute() {
        return this.labelMinute;
    }

    public Label getLabelSecond() {
        return this.labelSecond;
    }

    public TextField getTimeEnd() {
        return this.timeEnd;
    }

    public TextField getYear() {
        return this.year;
    }

    public TextField getMonth() {
        return this.month;
    }

    public TextField getDay() {
        return this.day;
    }

    public TextField getHour() {
        return this.hour;
    }

    public TextField getMinute() {
        return this.minute;
    }

    public TextField getSecond() {
        return this.second;
    }

    public DatePicker getDateEnd() {
        return this.dateEnd;
    }

    public CheckBox getCheckboxrepeated() {
        return this.checkboxrepeated;
    }

    public ThreadTask getThread() {
        return thread;
    }
}
