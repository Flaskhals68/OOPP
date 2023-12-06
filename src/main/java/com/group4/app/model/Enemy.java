package com.group4.app.model;

import com.group4.app.model.actions.PlayerMoveAction;

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
                // gives the entire path to the player, irrespective of distance
                path = PathfindingHelper.getPathNextTo(this.getPos(), m.getPlayerPos());

                if(path.size() > 5) {
                    // if the path is longer than 5, it will only take the first 5 steps
                    path = path.subList(0, 5);
                }
                Collections.reverse(path);

                Position target = getPos();
                // counts how many steps it will take to get to the player
                for(Position p : path) {
                    if(m.freePosition(p)) {
                        target = p;
                        break;
                    }
                }
                performAction(new PositionActionInput("move", target));
                m.updateObservers();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }



    }

    @Override
    public void death() {
        // TODO : Implement the rest of enemy death
        Model.getInstance().giveExperience(1);
    }
}
