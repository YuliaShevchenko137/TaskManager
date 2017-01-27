package com.netcracker.java.YuliaShevchenko.lab1.controllers;

import com.netcracker.java.YuliaShevchenko.lab1.model.Constants;
import com.netcracker.java.YuliaShevchenko.lab1.model.Task;
import com.netcracker.java.YuliaShevchenko.lab1.model.TaskIO;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Class CalendarController.
 * Realization of the calendar.
 */
public class CalendarController {

     /**
     * List dates.
     */
    @FXML
    private ListView<Date> listView;

    /**
     * Table of the tasks for the current date.
     */
    @FXML
    private TableView<Task> taskTable;

    /**
     * Column of the title of the task.
     */
    @FXML
    private TableColumn<Task, String> taskName;

    /**
     * Column of the start date of the task.
     */
    @FXML
    private TableColumn<Task, Date> taskStart;

    /**
     * Column of the end date of the task.
     */
    @FXML
    private TableColumn<Task, Date> taskEnd;

    /**
     * Column of the repetition interval of the task.
     */
    @FXML
    private TableColumn<Task, String> taskInterval;

    /**
     * Column of the activity of the task.
     */
    @FXML
    private TableColumn<Task, Boolean> taskActive;

    /**
     * Map with date and tasks set.
     */
    private Map<Date, Set<Task>> maps;

    /**
     * Method initialize().
     * Executed when a controller is loaded.
     */
    @FXML
    public final void initialize() {
        this.taskName.setText(Constants.TITLE);
        this.taskName.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        this.taskStart.setText(Constants.START);
        this.taskStart.setCellValueFactory(
                new PropertyValueFactory<>("start"));
        this.taskEnd.setText(Constants.END);
        this.taskEnd.setCellValueFactory(
                new PropertyValueFactory<>("end"));
        this.taskInterval.setText(Constants.INTERVAL);
        this.taskInterval.setCellValueFactory(
                new PropertyValueFactory<>("interval"));
        this.taskActive.setText(Constants.ACTIVE);
        this.taskActive.setCellValueFactory(
                new PropertyValueFactory<>("active"));
        this.taskName.setText("");
        this.taskStart.setText("");
        this.taskEnd.setText("");
        this.taskInterval.setText("");
        this.taskActive.setText("");
        this.listView.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) ->
                        this.showTaskDetails(newValue));
    }

    /**
     * Method showTaskDetails(Date date).
     * Executed when you select a date. Filling a table.
     * @param date selected date.
     */
    private void showTaskDetails(final Date date) {
        Set<Task> task = this.maps.get(date);
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        for (Task t : task) {
            tasks.add(t);
        }
        this.taskTable.setItems(tasks);
    }

    /**
     * Method safe(ActionEvent actionEvent).
     * Saving map at file.
     * @param actionEvent button press.
     */
    public final void safe(final ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.doc"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        File f = fileChooser.showOpenDialog(stage);
        if (f != null) {
            TaskIO.writeMap(this.maps, f);
        }
    }

    /**
     * Method fillingList(final Map map).
     * Filling by key map list.
     * @param map map with key.
     */
    final void fillingList(final Map<Date, Set<Task>> map) {
        Set<Date> dateSet = map.keySet();
        ObservableList<Date> dateObs = FXCollections.observableArrayList();
        for (Date t : dateSet) {
            dateObs.add(t);
        }
        this.listView.setItems(dateObs);
    }

    /**
     * Method setMaps(Map maps).
     * Setter for map.
     * @param map new map.
     */
    final void setMaps(final Map<Date, Set<Task>> map) {
        this.maps = map;
    }
}
