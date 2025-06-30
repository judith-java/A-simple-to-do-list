package org.example;

import java.util.*;

public class Task {
    private String description;
    private Set<String> tags;
    private boolean isComplete;

    private static final List<Task> tasks = new ArrayList<>();

    public Task(String description, Set<String> tags) {
        this.description = description != null ? description.trim() : "";
        this.tags = tags != null ? new HashSet<>(tags) : new HashSet<>();
        this.isComplete = false;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getTags() {
        return new HashSet<>(tags);
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

    public static boolean addTask(Task task) {
        if (task.description.isEmpty()) {
            System.out.println("❌ Cannot add a blank task.");
            return false;
        }

        for (Task t : tasks) {
            if (t.description.equalsIgnoreCase(task.description) && !t.isComplete) {
                System.out.println("❌ Duplicate incomplete task rejected: " + task.description);
                return false;
            }
        }

        tasks.add(task);
        return true;
    }

    public static boolean addTask(String description, Set<String> tags) {
        return addTask(new Task(description, tags));
    }

    public static boolean completeTask(String description) {
        for (Task t : tasks) {
            if (t.description.equalsIgnoreCase(description)) {
                t.setComplete(true);
                return true;
            }
        }
        System.out.println("⚠️ Task not found: " + description);
        return false;
    }

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
        List<Task> tagged = new ArrayList<>();
        for (Task t : tasks) {
            if (t.hasTag(tag)) {
                tagged.add(t);
            }
        }
        return tagged;
    }

    public static void clearTasks() {
        tasks.clear();
        System.out.println("✅ All tasks cleared.");
    }
}
