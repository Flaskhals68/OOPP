package com.group4.app.model.creatures;

import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;
import com.group4.app.model.actions.AttackActionInput;
import com.group4.app.model.actions.PositionActionInput;
import com.group4.app.model.dungeon.Tile;
import com.group4.app.model.items.Weapon;

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
        Model m = Model.getInstance();
        List<Position> path;

        Position pPos = m.getPlayerPos();
        int xDiff = Math.abs(pPos.getX() - getPos().getX());
        int yDiff = Math.abs(pPos.getY() - getPos().getY());

        int pDexBonus = m.getPlayer().getDexBonus();

        // if the player is too far away, don't do anything. Depends on players dex stat and enemy perception
        if(xDiff > (10 + getPerceptionBonus() - pDexBonus) || yDiff > (10 + getPerceptionBonus() - pDexBonus)) {
            return;
        }
        System.out.println(getName() + " is taking a turn");
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

                if(path.size()>0) {
                    performAction(new PositionActionInput("move", path.get(path.size()-1)));
                } else {
                    performAction(new PositionActionInput("move", getPos()));
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
