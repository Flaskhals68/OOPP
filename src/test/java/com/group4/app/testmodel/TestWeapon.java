package com.group4.app.testmodel;

import com.group4.app.model.Sword;
import org.junit.jupiter.api.Test;

public class TestWeapon {
    @Test
    public void testSwordConstructor() {
        Sword sword = new Sword("Excalibur", 10);
    }
}
