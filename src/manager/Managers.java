package manager;

public class Managers {
    public static TaskManager getDefault() {
        return new FileBackedTaskManager(getDefaultHistory());
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
