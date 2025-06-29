package org.example;

import java.util.Set;

public class App {
    public static void main(String[] args) {
        // Add some tasks
        Task.addTask("Buy milk", Set.of("shopping"));
        Task.addTask("Buy eggs", Set.of("shopping"));
        Task.addTask("Prepare lesson", Set.of("work"));
        Task.addTask("Sow beet seeds", Set.of("garden"));

        // Should be rejected silently (blank description)
        Task.addTask("", null);

        // Duplicate incomplete task should be rejected
        Task.addTask("Buy eggs", Set.of("shopping"));

        // Complete a task
        Task.completeTask("Buy eggs");

        // Print all tasks
        System.out.println("All tasks:");
        for (Task t : Task.getAllTasks()) {
            System.out.printf("- %s [tags: %s] %s\n",
                t.getDescription(),
                t.getTags(),
                t.isComplete() ? "(completed)" : "(incomplete)");
        }
    }
}
