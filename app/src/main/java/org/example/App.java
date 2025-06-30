package org.example;

import java.util.Set;

public class App {
    public static void main(String[] args) {
        Task.addTask("Buy milk", Set.of("shopping"));
        Task.addTask("Buy eggs", Set.of("shopping"));
        Task.addTask("Prepare lesson", Set.of("work"));
        Task.addTask("Sow beet seeds", Set.of("garden"));

        // Blank task rejected
        Task.addTask("", null);

        // Duplicate incomplete task rejected
        Task.addTask("Buy eggs", Set.of("shopping"));

        // Complete a task
        Task.completeTask("Buy eggs");

        System.out.println("\n📋 All Tasks:");
        for (Task t : Task.getAllTasks()) {
            System.out.printf("- %s [tags: %s] %s\n",
                    t.getDescription(),
                    t.getTags(),
                    t.isComplete() ? "(completed)" : "(incomplete)");
        }

        System.out.println("\n✅ Completed Tasks:");
        for (Task t : Task.getCompletedTasks()) {
            System.out.println("- " + t.getDescription());
        }

        System.out.println("\n🕓 Incomplete Tasks:");
        for (Task t : Task.getIncompleteTasks()) {
            System.out.println("- " + t.getDescription());
        }

        System.out.println("\n🏷️ Tasks tagged with 'shopping':");
        for (Task t : Task.getTasksByTag("shopping")) {
            System.out.println("- " + t.getDescription());
        }

        Task.clearTasks();
        System.out.println("\n🗑️ Tasks after clear:");
        System.out.println(Task.getAllTasks().isEmpty() ? "No tasks remaining." : "Tasks still exist.");
    }
}
