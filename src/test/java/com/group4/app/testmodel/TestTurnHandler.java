package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.group4.app.model.Position;
import org.junit.jupiter.api.Test;

import com.group4.app.model.ITurnTaker;
import com.group4.app.model.TurnHandler;
import com.group4.app.model.creatures.EnemyFactory;

public class TestTurnHandler {

    public class MockTurnTaker implements ITurnTaker {
            private int ap;
            private int maxAp;
            private boolean startedTurn;
            private boolean endedTurn;

            public MockTurnTaker(int ap, int maxAp){
                this.ap = ap;
                this.maxAp = maxAp;
                this.startedTurn = false;
                this.endedTurn = false;
            }

            public void takeTurn(){
                this.startedTurn = true;
                this.endTurn();
            }

            public void startTurn(){
                this.startedTurn = true;
                this.endTurn();
            }

            public void endTurn(){
                this.endedTurn = true;
            }

            public void refillAp(){
                this.ap = this.maxAp;
            }

            public void useAp(int amount){
                this.ap -= amount;
            }

            public int getAp(){
                return this.ap;
            }

            public int getMaxAp(){
                return this.maxAp;
            }

            public boolean hasStartedTurn(){
                return this.startedTurn;
            }

            public boolean hasEndedTurn(){
                return this.endedTurn;
            }
    }

    @Test
    public void testStartTurn(){
        TurnHandler turnHandler = new TurnHandler();
        MockTurnTaker o1 = new MockTurnTaker(3, 3);
        MockTurnTaker o2 = new MockTurnTaker(3, 3);
        turnHandler.add(o1);
        turnHandler.add(o2);
        turnHandler.nextTurn();
        assertTrue(o1.hasStartedTurn());
        assertFalse(o2.hasStartedTurn());
    }

    @Test
    public void testEndTurn(){
        TurnHandler turnHandler = new TurnHandler();
        MockTurnTaker o1 = new MockTurnTaker(3, 3);
        MockTurnTaker o2 = new MockTurnTaker(3, 3);
        turnHandler.add(o1);
        turnHandler.add(o2);
        turnHandler.nextTurn();
        assertEquals(o1.hasEndedTurn(), true);
        assertEquals(o2.hasEndedTurn(), false);
    }
}