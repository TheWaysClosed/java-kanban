package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubTaskTest {

    @Test
    public void shouldBePositiveWhenBothSubTasksWithSameIdIsEqual() {
        SubTask sub1 = new SubTask("Epic 1", "Description 1");
        sub1.setId(1);
        SubTask sub2 = new SubTask("Epic 2", "Description 2");
        sub2.setId(1);

        assertEquals(sub1, sub2);
    }
}
