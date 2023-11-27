package com.group4.app.testmodel;

import com.group4.app.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCreature {

    private class ConcreteCreature extends Creature {

        public ConcreteCreature(String id, String floorId, int xPos, int yPos, int ap, Weapon weapon, Attributes attr, int level) {
            super(id, floorId, xPos, yPos, ap, weapon, attr, level);
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
    @BeforeEach
    public void setup() {

    }

    @Test
    public void testTakeHitNoArmour() {

    }
}
