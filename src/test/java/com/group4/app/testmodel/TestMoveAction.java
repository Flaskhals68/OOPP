package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import com.group4.app.model.Model;
import com.group4.app.model.Position;
import com.group4.app.model.actions.Action;
import com.group4.app.model.actions.MoveAction;
import com.group4.app.model.creatures.IPositionable;
import com.group4.app.model.creatures.Player;
import com.group4.app.model.dungeon.Tile;
import com.group4.app.model.dungeon.World;

public class TestMoveAction {
    @Test
    public void testGetTargetable(){
        

        World world = new World(10);
        Model.getInstance().add(world);
        Model.getInstance().setCurrentWorld(world.getId());
        Model.getInstance().add(new Tile("stone", new Position(0, 0, world.getId())));
        Model.getInstance().add(new Tile("stone", new Position(0, 1, world.getId())));
        String worldId = Model.getInstance().getCurrentWorldId();
        Player p = new Player("player", 3, null, new Position(0, 0, worldId), Model.getInstance());
        
        Action<IPositionable, Position> action = new MoveAction(1, "action", p, 5, Model.getInstance());
        HashSet<Position> pos = new HashSet<Position>();
        pos.add(new Position(0, 0, world.getId()));
        pos.add(new Position(0, 1, world.getId()));
        assertEquals(pos, action.getTargetable());
    }

    @Test
    public void testPerform(){
        World world = new World(10);
        Model.getInstance().add(world);
        Model.getInstance().setCurrentWorld(world.getId());
        Model.getInstance().add(new Tile("stone", new Position(0, 0, world.getId())));
        Model.getInstance().add(new Tile("stone", new Position(0, 1, world.getId())));
        String worldId = Model.getInstance().getCurrentWorldId();
        Player p = new Player("player", 3, null, new Position(0, 0, worldId), Model.getInstance());
        Position pos = new Position(0, 1, worldId);
        Action<IPositionable, Position> action = new MoveAction(1, "action", p, 5, Model.getInstance());
        action.perform(pos);
    }
}
