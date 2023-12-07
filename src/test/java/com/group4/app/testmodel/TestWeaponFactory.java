package com.group4.app.testmodel;

import org.junit.jupiter.api.Test;

import com.group4.app.model.items.Weapon;
import com.group4.app.model.items.WeaponFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestWeaponFactory {
    @Test
    public void testSwordConstructor() {
        Weapon sword = WeaponFactory.createSword();
        assertEquals(sword.getName(), "Basic Sword");
        assertEquals(sword.getAttack(), 8);
    }

    @Test
    public void testClawsConstructor() {
        Weapon claw = WeaponFactory.createClaws();
        assertEquals(claw.getName(), "Basic Claws");
        assertEquals(claw.getAttack(), 4);
    }

    @Test
    public void testWeaponAdditionalMethods() {
        Weapon sword = WeaponFactory.createSword();
        assertTrue(sword.getLootable());
        assertEquals(sword.getType(), "Sword");
    }
}
