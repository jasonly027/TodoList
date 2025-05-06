package net.jasonly027.todolist.models;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import net.jasonly027.todolist.lib.Database;

import java.util.*;
import java.util.function.Predicate;

// A model containing Tasks.
public class TasksModel {
    private final Database db;
    private final ObservableList<Task> tasks;
    private final List<Predicate<Task>> filters;
    private final FilteredList<Task> filteredTasks;
    private final SortedList<Task> sortedTasks;

    public TasksModel(Database database) {
        db = database;
        tasks = FXCollections.observableArrayList(db.getTasks());

        filters = new ArrayList<>();
        filteredTasks = new FilteredList<>(tasks, t -> true);
        sortedTasks = new SortedList<>(filteredTasks);
    }

    public ObservableList<Task> getTasks() {
        return sortedTasks;
    }

    public void add(Task task) {
        tasks.add(task);
        db.add(task);
    }

    public void updateTask(Task task) {
        filterTasks();
        db.update(task);
    }

    public void remove(UUID id) {
        tasks.removeIf(task -> task.getId().equals(id));
        db.remove(id);
    }

    public void remove(UUID... ids) {
        tasks.removeIf(task -> Arrays.stream(ids).anyMatch(id -> id.equals(task.getId())));
        db.remove(ids);
    }

    public void addFilter(Predicate<Task> predicate) {
        filters.add(predicate);
        filterTasks();
    }

    public void removeFilter(Predicate<Task> predicate) {
        filters.remove(predicate);
        filterTasks();
    }

    // Filter tasks by the searchFilter and filter list
    private void filterTasks() {
        Predicate<Task> combined = filters.stream()
                .reduce(task -> true, Predicate::and);
        filteredTasks.setPredicate(combined);
    }

    public void setSortComparator(ReadOnlyProperty<Comparator<Task>> comparator) {
        sortedTasks.comparatorProperty().bind(comparator);
    }
}
