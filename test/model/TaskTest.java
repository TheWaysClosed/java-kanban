package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void shouldBePositiveWhenBothTaskWithSameIdIsEqual() {
        Task task1 = new Task("Task 1", "Description 1");
        task1.setId(1);
        Task task2 = new Task("Task 2", "Description 2");
        task2.setId(1);

        assertEquals(task1, task2);
    }
}
