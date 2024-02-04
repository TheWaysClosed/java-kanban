package taskManager;

import model.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class InMemoryHistoryTest {
    //убедитесь, что задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных.

    @Test
    public void testTaskHistory() {
        HistoryManager historyManager = new InMemoryHistoryManager();
        TaskManager taskManager = new InMemoryTaskManager(historyManager);

        Task task1 = taskManager.create(new Task("task1", "Description 1"));
        Task task2 = taskManager.create(new Task("task2", "Description 2"));

        taskManager.getTask(task1.getId());

        List<Task> history = historyManager.getAll();

        assertEquals(1, history.size());

        taskManager.getTask(task2.getId());

        assertEquals(2, history.size());
    }
}
