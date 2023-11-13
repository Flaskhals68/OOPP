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
}
