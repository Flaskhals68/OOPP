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
                Tile startTile = m.getTile(getPos());
                Tile playerTile = m.getTile(m.getPlayerPos());
                path = PathfindingHelper.pathToClosest(startTile, playerTile);

                path = path.subList(0, Math.min(path.size(), 5));

                performAction(new PositionActionInput("move", path.get(path.size()-1)));

            }

        }



    }

    @Override
    public void death() {
        // TODO : Implement the rest of enemy death
        Model.getInstance().giveExperience(1);
    }
}
