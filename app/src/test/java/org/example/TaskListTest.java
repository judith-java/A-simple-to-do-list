package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @BeforeEach
    public void setup() {
        // Clear tasks before each test
        Task.clearTasks();
    }

    @Test
    public void testAddTask() {
        assertTrue(Task.addTask(new Task("Test task", Set.of("unit"))));
    }

    @Test
    public void testRejectBlankTask() {
        assertFalse(Task.addTask(new Task("", null)));
    }

    @Test
    public void testRejectDuplicateTask() {
        Task.addTask(new Task("Test task", Set.of("unit")));
        assertFalse(Task.addTask(new Task("Test task", Set.of("unit"))));
    }

    @Test
    public void testCompleteTask() {
        Task t = new Task("Complete me", null);
        Task.addTask(t);
        t.setComplete(true);
        assertTrue(t.isComplete());
    }

    @Test
    public void testGetCompletedTasks() {
        Task t1 = new Task("Done", null);
        t1.setComplete(true);
        Task t2 = new Task("Not done", null);
        Task.addTask(t1);
        Task.addTask(t2);
        assertEquals(1, Task.getCompletedTasks().size());
    }

    @Test
    public void testGetIncompleteTasks() {
        Task t1 = new Task("Done", null);
        t1.setComplete(true);
        Task t2 = new Task("Not done", null);
        Task.addTask(t1);
        Task.addTask(t2);
        assertEquals(1, Task.getIncompleteTasks().size());
    }

    @Test
    public void testGetTasksByTag() {
        Task t1 = new Task("Task1", Set.of("work"));
        Task t2 = new Task("Task2", Set.of("home"));
        Task.addTask(t1);
        Task.addTask(t2);
        assertEquals(1, Task.getTasksByTag("work").size());
        assertEquals(1, Task.getTasksByTag("home").size());
        assertEquals(0, Task.getTasksByTag("nonexistent").size());
    }
}
