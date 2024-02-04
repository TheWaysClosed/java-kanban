package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    public void shouldBePositiveWhenBothEpicWithSameIdIsEqual() {
        Epic epic1 = new Epic("Epic 1", "Description 1", new ArrayList<>());
        epic1.setId(1);
        Epic epic2 = new Epic("Epic 2", "Description 2", new ArrayList<>());
        epic2.setId(1);

        assertEquals(epic1, epic2);
    }

}