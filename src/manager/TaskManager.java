package manager;

import model.Epic;
import model.SubTask;
import model.Task;
import java.util.List;

public interface TaskManager {

    Task create(Task task);

    Epic create(Epic epic);

    SubTask create(SubTask subTask, Epic epic);

    void printAllTasks();

    void printAllEpics();

    void printAllSubTasks();

    void printAllSubTasksInEpic(Epic epic);

    void printTask(int id);

    void printEpic(int id);

    void printSubTasks(int id);

    void removeTask(int id);

    void removeEpic(int id);

    void removeSubTasks(int id);

    void removeAllTasks();

    void removeAllEpic();

    void removeAllSubTasks();

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubTask(SubTask subTask);

    Task getTask(int id);

    Epic getEpic(int id);

    SubTask getSubTask(int id);

    List<Task> getHistory();
}
