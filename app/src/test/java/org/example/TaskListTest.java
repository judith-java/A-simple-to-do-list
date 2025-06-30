package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @BeforeEach
    public void setup() {
        Task.clearTasks();
    }

    @Test
    public void testAddValidTask() {
        assertTrue(Task.addTask("Learn Java", Set.of("education")));
    }

    @Test
    public void testRejectBlankTask() {
        assertFalse(Task.addTask("", Set.of("misc")));
    }

    @Test
    public void testRejectDuplicateIncompleteTask() {
        Task.addTask("Duplicate Task", Set.of("work"));
        assertFalse(Task.addTask("Duplicate Task", Set.of("work")));
    }

    @Test
    public void testAllowDuplicateIfPreviousComplete() {
        Task.addTask("Done Task", Set.of("work"));
        Task.completeTask("Done Task");
        assertTrue(Task.addTask("Done Task", Set.of("work")));
    }

    @Test
    public void testCompleteTask() {
        Task.addTask("Finish test", null);
        assertTrue(Task.completeTask("Finish test"));
    }

    @Test
    public void testCompleteNonExistentTask() {
        assertFalse(Task.completeTask("Ghost Task"));
    }

    @Test
    public void testGetCompletedTasks() {
        Task.addTask("Done", null);
        Task.completeTask("Done");
        assertEquals(1, Task.getCompletedTasks().size());
    }

    @Test
    public void testGetIncompleteTasks() {
        Task.addTask("Pending", null);
        assertEquals(1, Task.getIncompleteTasks().size());
    }

    @Test
    public void testGetTasksByTag() {
        Task.addTask("Clean room", Set.of("home"));
        Task.addTask("Write blog", Set.of("writing"));
        assertEquals(1, Task.getTasksByTag("home").size());
        assertEquals(0, Task.getTasksByTag("fitness").size());
    }

    @Test
    public void testClearTasks() {
        Task.addTask("Task1", null);
        Task.clearTasks();
        assertTrue(Task.getAllTasks().isEmpty());
    }
}
