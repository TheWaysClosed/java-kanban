package taskManager;

import model.*;

import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final LinkedList<Task> history = new LinkedList<>();

    @Override
    public void add(Task task) {
        if (history.size() > 9) {
            history.removeFirst();
        }
        history.addLast(task);
    }

    @Override
    public List<Task> getAll() {
        return new LinkedList<>(history);
    }
}
