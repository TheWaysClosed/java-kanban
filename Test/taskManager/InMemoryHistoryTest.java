package taskManager;

import model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class InMemoryHistoryTest {
    @Test
    public void shouldBePositiveWhenHistorySaveOldValues() {
        HistoryManager historyManager = new InMemoryHistoryManager();
        TaskManager taskManager = new InMemoryTaskManager(historyManager);

        Task task1 = taskManager.create(new Task("task1", "Description 1"));
        Epic epic1 = taskManager.create(new Epic("epic1", "Description 1", new ArrayList<>()));
        List<Task> history = historyManager.getAll();

        taskManager.getEpic(epic1.getId());

        assertEquals(1, history.size());

        taskManager.getTask(task1.getId());
        assertEquals(2, history.size(), "Список должен увеличиться");
        taskManager.getTask(task1.getId());

        assertEquals(history.get(1), history.get(2), "В истории могут быть одинаковые объекты");

        taskManager.getTask(task1.getId());
        taskManager.getTask(task1.getId());
        taskManager.getTask(task1.getId());
        taskManager.getTask(task1.getId());
        taskManager.getTask(task1.getId());
        taskManager.getTask(task1.getId());
        taskManager.getTask(task1.getId());
        taskManager.getTask(task1.getId());
        taskManager.getTask(task1.getId()); // 11
        assertEquals(10, history.size(), "История хранит 10 пунктов");
        assertEquals(task1, history.get(0), "Переполненый список должен удалить первый элемент");
    }
}
