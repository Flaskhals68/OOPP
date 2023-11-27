package com.group4.app.testmodel;

import com.group4.app.model.Armour;
import com.group4.app.model.ArmourType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestArmour {

    @Test
    public void test_constructor(){
        Armour armour = new Armour("Test Armour", 10, true, ArmourType.HEAVY);
        assertEquals("Test Armour", armour.getName());
        assertTrue(armour.getLootable());
    }

    @Test
    public void testDamageReductionHeavy() {
        Armour armour = new Armour("Test Armour", 10, true, ArmourType.HEAVY);
        assertEquals(10, armour.getDamageReduction(0));
        assertEquals(10, armour.getDamageReduction(100));
    }

    @Test
    public void testDamageReductionMedium() {
        Armour armour = new Armour("Test Armour", 10, true, ArmourType.MEDIUM);
        assertEquals(10, armour.getDamageReduction(0));
        assertEquals(12, armour.getDamageReduction(90));
    }

}
