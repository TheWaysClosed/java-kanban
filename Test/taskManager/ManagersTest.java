package taskManager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ManagersTest {

    @Test
    public void ShouldBeNullIfUtilClassNotInitializedInstances() {
        TaskManager taskManager = new InMemoryTaskManager(new InMemoryHistoryManager());
        HistoryManager historyManager = new InMemoryHistoryManager();

        assertNotNull(taskManager);
        assertNotNull(historyManager);
    }
}
