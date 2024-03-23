package manager;
import model.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileBackedTaskManagerTest {

    @Test
    public void shouldBePositiveWhenSaveAndLoadEmptyFile() throws IOException {
        File tempFile = File.createTempFile("tempFile", ".csv");

        FileBackedTaskManager loadedManager = FileBackedTaskManager.loadFromFile(tempFile);

        assertEquals(0, loadedManager.getAllTasks().size());
        assertEquals(0, loadedManager.getAllEpics().size());
        assertEquals(0, loadedManager.getAllSubTasks().size());
    }

    @Test
    public void shouldBePositiveWhenSaveAndLoadTasks() throws IOException {
        File tempFile = File.createTempFile("tempFile", ".csv");
        HistoryManager historyManager = new InMemoryHistoryManager();
        FileBackedTaskManager taskManager = new FileBackedTaskManager(historyManager, tempFile);

        Task task1 = new Task("Task 1", "Description 1");
        Task task2 = new Task("Task 2", "Description 2");

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        taskManager.save();

        FileBackedTaskManager loadedManager = FileBackedTaskManager.loadFromFile(tempFile);

        List<Task> loadedTasks = loadedManager.getAllTasks();
        assertEquals(2, loadedTasks.size());
        assertEquals("Task 1", loadedTasks.get(0).getName());
        assertEquals("Description 2", loadedTasks.get(1).getDescription());
    }
}