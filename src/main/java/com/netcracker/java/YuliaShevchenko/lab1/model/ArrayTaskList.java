package com.netcracker.java.YuliaShevchenko.lab1.model;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Class ArrayTaskList.
 * Realization of the task list.
 */
public class ArrayTaskList extends TaskList implements Cloneable, Serializable {

    /**
     * Version control for serialization.
     */
    static final long serialVersionUID = 42L;

    /**
     * It is used to register error.
     */
    private static final Logger LOGGER = Logger.getLogger(ArrayTaskList.class);

    /**
     * Length of current array.
     */
    private int n;

    /**
     * Count tasks.
     */
    private int size;

    /**
     *Array of the tasks.
     */
    private Task[] array;

    /**
     * Constructor ArrayTaskList().
     * Fills the main variable.
     */
    public ArrayTaskList() {
        this.n = Constants.ZERO;
        this.size = Constants.ZERO;
        this.array = new Task[Constants.ARRAY_START_SIZE];
    }
    
    /**
     * Method add(Task task).
     * Add task in the list.
     * @param task current task for add.
     */
    public final void add(final Task task) {
        if (task == null) {
            LOGGER.warn("task is null");
            return;
        }
        if (this.n == Constants.ZERO) {
            this.array = new Task[Constants.ARRAY_START_SIZE];
            this.getArray()[this.size] = task;
            this.n = Constants.ARRAY_START_SIZE;
            this.size++;
        } else {
            if (this.size == this.n) {
                this.n += this.n / 2;
                Task[] temp = new Task[this.n];
                System.arraycopy(this.getArray(), Constants.ZERO, temp,
                        Constants.ZERO, this.n
                                - (Constants.ARRAY_START_SIZE / 2));
                this.array = temp;
            }
            this.getArray()[this.size] = task;
            this.size++;
        }
    }

    /**
     * Method size().
     * Return count tasks in Task List.
     * @return count tasks.
     */
    public final int size() {
        return this.size;
    }

    /**
     * Method remove(Task task).
     * Delete task from the list.
     * @param task current task.
     * @return true, if task removed, or false.
     */
    public final boolean remove(final Task task) {
        int i = 0;
        int res = -1;
        if (this.size == 1) {
            if (task.equals(array[0])) {
                array[0] = null;
                size --;
                return true;
            } else {
                return false;
            }
        }
        while (i < this.size) {
            if (this.getArray()[i].equals(task)) {
                res = i;
                break;
            }
            i++;
        }
        if (res == -1) {
            LOGGER.warn("task is not found");
            return false;
        }
        int k = 0;
        for (i = 0; i < this.size - 1; i++) {
            if (i == res) {
                k++;
            }
            this.getArray()[i] = this.getArray()[i + k];
        }
        this.getArray()[this.size - 1] = null;
        this.size--;
        return true;
    }

    /**
     * Method iterator().
     * Realize iterator.
     * Specifies the basic functions to be iterated in the list.
     * @return iterator.
     */
    @Override
    public final Iterator<Task> iterator() {
        return new Iterator<Task>() {

            private int current = Constants.ZERO;

            public boolean hasNext() {
                return this.current < size;
            }

            public Task next() {
                return array[this.current++];
            }

            public void remove() {
                if (this.current == Constants.ZERO) {
                    LOGGER.warn("list is empty");
                }
                int k = Constants.ZERO;
                for (int i = Constants.ZERO; i < size; i++) {
                    if (i == this.current - 1) {
                        k++;
                    }
                    array[i] = array[i + k];
                }
                array[size] = null;
                size--;
                current--;
            }
        };
    }

    /**
     * Method hashCode().
     * use for hashing date: {@link Object#hashCode()}.
     * @return hashcode of the current task.
     */
    @Override
    public final int hashCode() {
        int result = Constants.ZERO;
        for (Task t : this) {
            int res = t.hashCode();
            result = 13 * result + res;
        }
        return result;
    }

    /**
     * Method equals(Object obj).
     * equals two tasks: {@link Object#equals(Object)}.
     * @param obj the object of comparison .
     * @return true, if objects is identical, or false.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj == null)  {
            LOGGER.warn("object is null");
            return false;
        } else if (obj == this) {
            return true;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            TaskList tasks = (TaskList) obj;
            if (tasks.size() != this.size()) {
                return false;
            }
            Iterator<Task> i = this.iterator();
            for (Task t : tasks) {
                Task t1 = i.next();
                boolean a = t.equals(t1);
                if (!a) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Method clone().
     * create copy of this ArrayTaskList: {@link Object#clone()}.
     * @return  copy of this ArrayTaskList.
     */
    @Override
    protected final TaskList clone() {
        try {
            return (TaskList) super.clone();
        } catch (CloneNotSupportedException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    private final Task[] getArray() {
        return this.array;
    }
}
