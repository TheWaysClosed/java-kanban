package manager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ManagersTest {

    @Test
    public void ShouldBeNullIfUtilClassNotInitializedInstances() {
        TaskManager taskManager = Managers.getDefaultTaskManager();
        HistoryManager historyManager = Managers.getDefaultHistory();

        assertNotNull(taskManager);
        assertNotNull(historyManager);
    }
}
