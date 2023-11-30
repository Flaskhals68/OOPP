package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.group4.app.model.Enemy;
import com.group4.app.model.EnemyFactory;
import com.group4.app.model.IAttackable;
import com.group4.app.model.ICanAttack;
import com.group4.app.model.Model;
import com.group4.app.model.Player;
import com.group4.app.model.Position;
import com.group4.app.model.Tile;
import com.group4.app.model.WeaponFactory;
import com.group4.app.model.World;
import com.group4.app.model.actions.Action;
import com.group4.app.model.actions.PlayerAttackAction;

public class TestPlayerAttackAction {

    @Test
    public void testGetTargetable(){
        World world = new World(10);
        Model.getInstance().addWorld(world);
        Model.getInstance().setCurrentWorld(world.getId());
        Model.getInstance().add(new Tile("stone", new Position(0, 0, world.getId())));
        Model.getInstance().add(new Tile("stone", new Position(0, 1, world.getId())));
        String worldId = Model.getInstance().getCurrentWorldId();
        Player p = new Player("player", 3, null, new Position(0, 0, worldId));
        Enemy e = EnemyFactory.createZombie(new Position(0, 1, worldId));
        Model.getInstance().add(e, e.getPos());
        Action<ICanAttack, IAttackable> action = new PlayerAttackAction(1, "action", p);
        assertEquals(e, action.getTargetable().toArray()[0]);
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
        p.setWeapon(WeaponFactory.createSword());
        Enemy e = EnemyFactory.createZombie(new Position(0, 1, worldId));
        Model.getInstance().add(e, e.getPos());
        Action<ICanAttack, IAttackable> action = new PlayerAttackAction(1, "action", p);
        int maxHp = e.getHitPoints();
        action.perform(e);
        assertTrue(e.getHitPoints() < maxHp);
    }

    @Test
    public void testPlayerAttackActionOutOfRange() {
        World world = new World(10);
        Model.getInstance().addWorld(world);
        Model.getInstance().setCurrentWorld(world.getId());
        Model.getInstance().add(new Tile("stone", new Position(0, 0, world.getId())));
        Model.getInstance().add(new Tile("stone", new Position(0, 1, world.getId())));
        Model.getInstance().add(new Tile("stone", new Position(0, 2, world.getId())));
        String worldId = Model.getInstance().getCurrentWorldId();
        Player p = new Player("player", 3, null, new Position(0, 0, worldId));
        p.setWeapon(WeaponFactory.createSword());
        Enemy e = EnemyFactory.createZombie(new Position(0, 2, worldId));
        Model.getInstance().add(e, e.getPos());
        Action<ICanAttack, IAttackable> action = new PlayerAttackAction(1, "action", p);
        

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            action.perform(e);
        }, "IllegalArgumentException was expected, since attacker and victim are out of range");

        assertTrue(ex.getMessage().contains("Attacker is out of range"));
    }

    @Test
    public void testPlayerAttackActionDifferentFloors() {
        World world = new World(10);
        Model.getInstance().addWorld(world);
        World world2 = new World(10);
        Model.getInstance().addWorld(world2);
        Model.getInstance().setCurrentWorld(world.getId());
        Model.getInstance().add(new Tile("stone", new Position(0, 0, world.getId())));
        Model.getInstance().add(new Tile("stone", new Position(0, 1, world2.getId())));
        Player p = new Player("player", 3, null, new Position(0, 0, world.getId()));
        p.setWeapon(WeaponFactory.createSword());
        Enemy e = EnemyFactory.createZombie(new Position(0, 1, world2.getId()));
        Model.getInstance().add(e, e.getPos());
        Action<ICanAttack, IAttackable> action = new PlayerAttackAction(1, "action", p);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            action.perform(e);
        }, "IllegalArgumentException was expected, since attacker and victim on different floors");

        assertTrue(ex.getMessage().contains("Attacker and victim are on different floors/worlds"));
    }
}
