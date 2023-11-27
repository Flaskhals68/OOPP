package com.group4.app.testmodel;

import com.group4.app.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCreature {

    private class ConcreteCreature extends Creature {

        public ConcreteCreature(String id, Position pos, int ap, Weapon weapon, Attributes attr, int level) {
            super(id, pos, ap, weapon, attr, level);
        }

        @Override
        public void death() {

        }

        @Override
        public void startTurn() {

        }

        @Override
        public void endTurn() {

        }
    }
    ConcreteCreature c;
    @BeforeEach
    public void setup() {
        Model.getInstance().addBasicMap(10, 0);
        String worldId = Model.getInstance().getCurrentWorldId();
        c = new ConcreteCreature("Test", new Position(0, 0, worldId), 3, WeaponFactory.createSword(), new Attributes(50, 50, 50, 60, 50, 50), 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(10, c.getHitPoints());
    }
    @Test
    public void testTakeHitNoArmour() {
        c.takeHit(5);
        assertEquals(6, c.getHitPoints());
    }

    @Test
    public void testTakeHitMediumArmour() {
        c.setArmour(ArmourFactory.createArmour(ArmourType.MEDIUM, 1));
        c.takeHit(5);
        assertEquals(9, c.getHitPoints());
    }

    @Test
    public void testTakeHitHeavyArmour() {
        c.setArmour(ArmourFactory.createArmour(ArmourType.HEAVY, 1));
        c.takeHit(5);
        assertEquals(10, c.getHitPoints());
    }
}
