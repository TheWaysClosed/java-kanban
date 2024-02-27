package manager;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class InMemoryHistoryManagerTest {

    private final InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    @Test
    void shouldBePositiveWhenListCanTakeAndGetTheTasks() {
        Task task = new Task("task name", "task desc");
        task.setId(1);
        Epic epic = new Epic("epic name", "epic desc", new ArrayList<>());
        epic.setId(2);

        historyManager.add(task);
        historyManager.add(epic);

        List<Task> list1 = historyManager.getAllTasks();

        assertEquals(2, list1.size());
        assertTrue(list1.contains(task));
        assertTrue(list1.contains(epic));
    }

    @Test
    void shouldBePositiveWhenListCanRemoveHisTasks() {
        Task task = new Task("task name", "task desc");
        task.setId(1);

        historyManager.add(task);
        List<Task> history = historyManager.getAllTasks();
        assertEquals(1, history.size());

        historyManager.remove(task.getId());
        history = historyManager.getAllTasks();
        assertTrue(history.isEmpty());
    }
}