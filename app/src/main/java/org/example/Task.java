package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task {
    private String description;
    private Set<String> tags;
    private boolean isComplete;

    private static List<Task> tasks = new ArrayList<>();

    public Task(String description, Set<String> tags) {
        this.description = description != null ? description.trim() : "";
        this.tags = tags != null ? new HashSet<>(tags) : new HashSet<>();
        this.isComplete = false;
    }

    // Add a Task object to the task list if valid and not duplicate
    public static boolean addTask(Task task) {
        if (task.description.isEmpty()) {
            return false;  // Reject blank task
        }
        for (Task t : tasks) {
            if (t.description.equals(task.description) && !t.isComplete) {
                return false; // Reject duplicate incomplete task
            }
        }
        tasks.add(task);
        return true;
    }

    // Overloaded helper method to add a task by description and tags (for easier calls)
    public static boolean addTask(String description, Set<String> tags) {
        return addTask(new Task(description, tags));
    }

    // Find a task by description and mark complete
    public static boolean completeTask(String description) {
        for (Task t : tasks) {
            if (t.description.equals(description)) {
                t.isComplete = true;
                return true;
            }
        }
        return false;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        this.isComplete = complete;
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getTags() {
        return new HashSet<>(tags);
    }

    // Getters for filtered tasks
    public static List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public static List<Task> getCompletedTasks() {
        List<Task> completed = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isComplete) {
                completed.add(t);
            }
        }
        return completed;
    }

    public static List<Task> getIncompleteTasks() {
        List<Task> incomplete = new ArrayList<>();
        for (Task t : tasks) {
            if (!t.isComplete) {
                incomplete.add(t);
            }
        }
        return incomplete;
    }

    public static List<Task> getTasksByTag(String tag) {
        List<Task> taggedTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.hasTag(tag)) {
                taggedTasks.add(t);
            }
        }
        return taggedTasks;
    }

    // Clear all tasks (useful for testing)
    public static void clearTasks() {
        tasks.clear();
    }
}
