package com.group4.app.testmodel;

import com.group4.app.model.Inventory;
import com.group4.app.model.Weapon;
import com.group4.app.model.WeaponFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestInventory {
    Inventory inv;
    Weapon weapon1, weapon2;
    @BeforeEach
    void init() {
        inv = new Inventory();
        weapon1 = WeaponFactory.createSword();
        weapon2 = WeaponFactory.createSword();
    }
    @Test
    public void testAddItemIfItDoesNotExistYet() {
        inv.addItem(weapon1);
        assertEquals(weapon1.getName(), inv.getItem(weapon1.getName()).getName());
    }

    @Test
    public void testAddItemIfItDoesExist() {

        inv.addItem(weapon1);
        inv.addItem(weapon2);

        assertEquals(weapon1.getName(), inv.getItem(weapon1.getName()).getName());
        assertEquals(weapon2.getName(), inv.getItem(weapon2.getName()).getName());
    }

    @Test
    public void testGetItemIfDoesNotExist() {
        inv.addItem(weapon1);
        inv.getItem(weapon1.getName());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            inv.getItem(weapon1.getName());
        }, "IllegalArgumentException was expected");

    }
}
