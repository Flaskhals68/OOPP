package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import com.group4.app.model.Entity;

public class TestEntity {
    private String randomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < 10; i++) {
            sb.append((char)random.nextInt(('z' - 'a') + 'a'));
        }
        return sb.toString();
    }

    @Test
    public void testHashCode() {
        
        String id = randomString(20);
        Entity e1 = new Entity(id);
        Entity e2 = new Entity(id);
        assertEquals(e1.hashCode(), e2.hashCode());

        String id2;
        do {
            id2 = randomString(20);
        } while (id == id2);
        Entity e3 = new Entity(id2);

        assertTrue(e1.hashCode() != e3.hashCode());
    }
}
