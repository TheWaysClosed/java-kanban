package taskManager;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class InMemoryTaskManagerTest {

    InMemoryTaskManager memoryTaskManager = new InMemoryTaskManager(new InMemoryHistoryManager());

    @Test
    void ShouldBeEqualsByTaskAndTaskId() {
        Task task = memoryTaskManager.create(new Task("task", "description"));
        Epic epic = memoryTaskManager.create(new Epic("epic", "description", new ArrayList<>()));
        SubTask subTask = memoryTaskManager.create(new SubTask("sub", "description"), epic);

        assertEquals(task, memoryTaskManager.getTask(task.getId()));
        assertEquals(epic, memoryTaskManager.getEpic(epic.getId()));
        assertEquals(subTask, memoryTaskManager.getSubTask(subTask.getId()));
    }

    @Test
    void ShouldBePositiveWhenTaskDoNotConflictWithinManager() {
        Task task1 = new Task("Task 1", "Description 1");
        task1.setId(1);
        memoryTaskManager.create(task1);
        Task task2 = memoryTaskManager.create(new Task("Task 2", "Description 2"));

        Task foundTask1 = memoryTaskManager.getTask(1);
        Task foundTask2 = memoryTaskManager.getTask(task2.getId());

        assertEquals(task1, foundTask1);
        assertEquals(task2, foundTask2);
    }
}
