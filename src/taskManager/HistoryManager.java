    package taskManager;

    import model.*;

    import java.util.List;

    public interface HistoryManager {

        void add(Task task);

        List<Task> getAll();

        public void remove(int id);
    }
