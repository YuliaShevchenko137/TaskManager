package com.netcracker.java.YuliaShevchenko.lab1.model;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Class TaskList.
 * Realisation of main method for list of task.
 */

public abstract class TaskList implements Iterable<Task>, Serializable {

    /**
     * Version control for serialization.
     */
    static final long serialVersionUID = 42L;

    /**
     * Method add(Task task).
     * Add current task in the list.
     * @param task current task.
     */
    public abstract void add(Task task);

    /**
     * Method size().
     * @return count task in list.
     */
    public abstract int size();

    /**
     * Method iterator().
     * Realize iterator.
     * Specifies the basic functions to be iterated in the list.
     * @return iterator.
     */
    public abstract Iterator<Task> iterator();

    /**
     * Method remove(Task task).
     * Delete task from the list.
     * @param t current task.
     * @return true, if task removed, or false.
     */
    public abstract boolean remove(Task t);

}
