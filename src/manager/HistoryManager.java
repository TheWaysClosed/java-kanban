    package manager;

    import model.*;

    import java.util.List;

    public interface HistoryManager {

        void add(Task task);

        List<Task> getAllTasks();

        public void remove(int id);
    }
