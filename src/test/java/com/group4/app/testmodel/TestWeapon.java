package com.group4.app.testmodel;

import com.group4.app.model.Claws;
import com.group4.app.model.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestWeapon {
    @Test
    public void testSwordConstructor() {
        Sword sword = new Sword("Excalibur", 10);
        assertEquals(sword.getName(), "Excalibur");
        assertEquals(sword.getAttack(), 10);
    }

    @Test
    public void testClawsConstructor() {
        Claws claw = new Claws("Basic claws", 3);
        assertEquals(claw.getName(), "Basic claws");
        assertEquals(claw.getAttack(), 3);
    }

    @Test
    public void testWeaponAdditionalMethods() {
        Sword sword = new Sword("Excalibur", 10);
        assertTrue(sword.getLootable());
        assertEquals(sword.getType(), "Sword");
    }
}
