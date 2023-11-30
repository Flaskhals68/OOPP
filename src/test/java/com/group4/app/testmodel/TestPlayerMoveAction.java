package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import com.group4.app.model.Enemy;
import com.group4.app.model.EnemyFactory;
import com.group4.app.model.IAttackable;
import com.group4.app.model.ICanAttack;
import com.group4.app.model.IPositionable;
import com.group4.app.model.Model;
import com.group4.app.model.Player;
import com.group4.app.model.Position;
import com.group4.app.model.Tile;
import com.group4.app.model.WeaponFactory;
import com.group4.app.model.World;
import com.group4.app.model.actions.Action;
import com.group4.app.model.actions.PlayerAttackAction;
import com.group4.app.model.actions.PlayerMoveAction;

public class TestPlayerMoveAction {
    @Test
    public void testGetTargetable(){
        

        World world = new World(10);
        Model.getInstance().addWorld(world);
        Model.getInstance().setCurrentWorld(world.getId());
        Model.getInstance().add(new Tile("stone", new Position(0, 0, world.getId())));
        Model.getInstance().add(new Tile("stone", new Position(0, 1, world.getId())));
        String worldId = Model.getInstance().getCurrentWorldId();
        Player p = new Player("player", 3, null, new Position(0, 0, worldId));
        
        Action<IPositionable, Position> action = new PlayerMoveAction(1, "action", p);
        HashSet<Position> pos = new HashSet<Position>();
        pos.add(new Position(0, 0, world.getId()));
        pos.add(new Position(0, 1, world.getId()));
        assertEquals(pos, action.getTargetable());
    }

    @Test
    public void testPerform(){
        World world = new World(10);
        Model.getInstance().addWorld(world);
        Model.getInstance().setCurrentWorld(world.getId());
        Model.getInstance().add(new Tile("stone", new Position(0, 0, world.getId())));
        Model.getInstance().add(new Tile("stone", new Position(0, 1, world.getId())));
        String worldId = Model.getInstance().getCurrentWorldId();
        Player p = new Player("player", 3, null, new Position(0, 0, worldId));
        Position pos = new Position(0, 1, worldId);
        Action<IPositionable, Position> action = new PlayerMoveAction(1, "action", p);
        action.perform(pos);
    }
}
