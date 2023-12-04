package com.group4.app.model;

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
                path = PathfindingHelper.getPathNextTo(this.getPos(), m.getPlayerPos());
                // TODO : FIX SO THEY DONT MOVE ONTOP OF EACHOTHER :(
                int stepsTaken = 0;

                for(Position p : path) {
                    if(m.nextToPlayer(p)) {
                        break;
                    }
                    stepsTaken++;
                }
                if(stepsTaken > 4) {
                    performAction(new PositionActionInput("move", path.get(4)));
                } else {
                    performAction(new PositionActionInput("move", path.get(stepsTaken)));
                }

            }
            m.updateObservers();
            try {
                Thread.sleep(200);
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
