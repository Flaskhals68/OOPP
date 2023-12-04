package com.group4.app.model;

import java.util.Collections;
import java.util.List;

public class Enemy extends Creature {
    private final String name;

    public Enemy(String id, String name, Position pos, Weapon weapon, int maxAp, Attributes attr, int level) {
        super(id, pos, maxAp, weapon, attr, level);
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public void takeTurn() {
        System.out.println(getName() + " is taking a turn");
        Model m = Model.getInstance();
        List<Position> path;
        while(getAp() > 0) {
            if(m.nextToPlayer(this.getPos())){
                System.out.println(getName() + " is attacking player now");
                performAction(new AttackActionInput("attack", m.getPlayer()));
            } else {
                System.out.println(getName() + " should move now");
                // gives the entire path to the player, irrespective of ditance
                path = PathfindingHelper.getPathNextTo(this.getPos(), m.getPlayerPos());

                int stepsTaken = 0;

                // counts how many steps it will take to get to the player
                for(Position p : path) {
                    if(m.nextToPlayer(p)) {
                        break;
                    }
                    stepsTaken++;
                }
                // checks if the enemy is blocked by another enemy, if so reduce stepstaken by 1
                for (int i = stepsTaken; i >= 0; i--) {
                    if(m.freePosition(path.get(i))) {
                        break;
                    } else {
                        stepsTaken--;
                    }
                }


                if(stepsTaken >= 5) {
                    // if the enemy is more than or equal to 5 steps away from the player, it will move 5 steps towards the player
                    performAction(new PositionActionInput("move", path.get(4)));
                } else if(stepsTaken < 0) {
                    // this should THEORETICALLY happen only if there are no free spots around the player that are
                    // closer than what the enemy is currently standing on
                    // and the enemy will then stand still... hopefully
                    performAction(new PositionActionInput("move", path.get(0)));
                }
                else {
                    // move to nearest free spot :)
                    performAction(new PositionActionInput("move", path.get(stepsTaken)));
                }

            }
            m.updateObservers();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }

    @Override
    public void death() {
        // TODO : Implement the rest of enemy death
        Model.getInstance().giveExperience(1);
    }
}
