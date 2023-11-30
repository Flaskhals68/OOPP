package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.group4.app.model.*;
import org.junit.jupiter.api.Test;

import com.group4.app.model.Position;
import com.group4.app.model.Creature;
import com.group4.app.model.Enemy;
import com.group4.app.model.EnemyFactory;
import com.group4.app.model.IAttackable;
import com.group4.app.model.ICanAttack;
import com.group4.app.model.Model;
import com.group4.app.model.Player;
import com.group4.app.model.Tile;
import com.group4.app.model.Weapon;
import com.group4.app.model.WeaponFactory;
import com.group4.app.model.World;
import com.group4.app.model.actions.Action;
import com.group4.app.model.actions.PlayerAttackAction;

import java.util.Map;
import java.util.Set;


public class TestPlayer {
    @Test
    public void testConstructors() {
        World world = new World(2);
        Model.getInstance().addWorld(world);
        Tile t1 = new Tile("stone", new Position(0, 0, world.getId()));
        world.add(t1);
        Player player = new Player("player", 3, null, new Position(0, 0, world.getId()));
        assertEquals("player", player.getId());
        assertEquals(10, player.getHitPoints());

        Weapon weapon = WeaponFactory.createSword();
        world = new World(2);
        Model.getInstance().addWorld(world);
        t1 = new Tile("stone", new Position(0, 0, world.getId()));
        world.add(t1);
        player = new Player("player", 3, weapon, new Position(0, 0, world.getId()));
        assertEquals("player", player.getId());
        assertEquals(10, player.getHitPoints());
        assertEquals(weapon.getAttack(), player.getDamage());
    } 

    // @Test
    // public void testMove() {
    //     Model.getInstance().addBasicMap(2, 0);
    //     String worldId = Model.getInstance().getCurrentWorldId();
    //     Tile t1 = new Tile("stone", new Position(0, 0, worldId));
    //     // world.addTile(t1);
    //     Tile t2 = Model.getInstance().getTile(new Position(0, 1, worldId));
    //     Player p = new Player("player", 3, null, new Position(0, 0, worldId));
    //     p.move(new Position(0, 1, worldId));
    //     int[] pos1 = new int[] {t2.getPos().getX(), t2.getPos().getY()};
    //     int[] pos2 = new int[] {p.getPos().getX(), p.getPos().getY()};
    //     assertEquals(pos1[0], pos2[0]);
    //     assertEquals(pos1[1], pos2[1]);
    //     assertTrue(t2.getEntities().contains(p));
    // }

    // @Test
    // public void testIllegalMove() {
    //     Model.getInstance().addBasicMap(10, 0);
    //     String worldId = Model.getInstance().getCurrentWorldId();
    //     Player p = new Player("player", 5, null, new Position(0, 0, worldId));

    //     assertThrows(IllegalArgumentException.class, () -> {
    //         p.move(new Position(9, 9, worldId));
    //     });
    // }

    @Test
    public void testFetchItemFromInventory() {

        World world = new World(2);
        Model.getInstance().addWorld(world);
        Tile t1 = new Tile("stone", new Position(0, 0, world.getId()));
        world.add(t1);
        Player p = new Player("player", 3, null, new Position(0, 0, world.getId()));

        Weapon testItem = WeaponFactory.createSword();

        p.addItemToInventory(testItem);

        assertEquals(p.fetchItemFromInventory(testItem.getName()).getName(), testItem.getName());
    }

    @Test
    public void testGetInventoryItems() {
        World world = new World(2);
        Model.getInstance().addWorld(world);
        Tile t1 = new Tile("stone", new Position(0, 0, world.getId()));
        world.add(t1);
        Player p = new Player("player", 3, null, new Position(0, 0, world.getId()));

        for(int i = 0; i<4; i++) {
            p.addItemToInventory(WeaponFactory.createSword());
            if (i >= 1) {
                p.addItemToInventory(WeaponFactory.createClaws());
            }
        }

        Map<String, Integer> testMap = p.getInventoryItems();

        assertEquals(4, testMap.get("Basic Sword"));
        assertEquals(3, testMap.get("Basic Claws"));
    }

    @Test
    public void testSetWeapon() {
        World world = new World(2);
        Model.getInstance().addWorld(world);
        Tile t1 = new Tile("stone", new Position(0, 0, world.getId()));
        world.add(t1);
        Player p = new Player("player", 3, WeaponFactory.createSword(), new Position(0, 0, world.getId()));

        Weapon basic_claws = WeaponFactory.createClaws();

        p.setWeapon(basic_claws);

        assertEquals(basic_claws.getAttack(), p.getDamage());
        assertEquals(p.fetchItemFromInventory("Basic Sword").getName(), "Basic Sword");
    }

    @Test
    public void testLevelUp() {
        Model.getInstance().addBasicMap(10, 0);
        String world = Model.getInstance().getCurrentWorldId();
        Player p = new Player("player", 3, null, new Position(0, 0, world));
        for(int i = 0; i<10; i++) {
            p.giveXP(10);
        }
        assertEquals(11, p.getLevel());
    }

    @Test
    public void testLevelUpOnKill() {
        Model.getInstance().addBasicMap(10, 0);
        String world = Model.getInstance().getCurrentWorldId();
        Player p = Model.getInstance().getPlayer();
        Enemy e = EnemyFactory.createZombie(new Position(0, 1, world));
        for(int i = 0; i<5; i++) {
            p.getAttributes().levelUpStat(AttributeType.MELEE_WEAPON_SKILL);
        }
        p.giveXP(9);

        p.performAction("attack", e);
        p.performAction("attack", e);

        assertEquals(2, p.getLevel());
    }

    public class TestAction extends Action<ICanAttack, IAttackable> {
        boolean performed = false;
        public TestAction(int apCost, String name, Creature actionTaker) {
            super(apCost, name, actionTaker);
        }

        @Override
        public void perform(IAttackable target) {
            performed = true;
        }

        @Override
        public Set<IAttackable> getTargetable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Set<Position> getTargetablePositions() {
            throw new UnsupportedOperationException();
        }

        public boolean getPerformed() {
            return performed;
        }
    }

    @Test
    public void testPerformAction(){
        World world = new World(1);
        Model.getInstance().addWorld(world);
        Model.getInstance().setCurrentWorld(world.getId());
        Model.getInstance().add(new Tile("stone", new Position(0, 0, world.getId())));
        Player p = new Player("player", 3, null, new Position(0, 0, world.getId()));
        TestAction action = new TestAction(1, "testPerformAction", p);
        p.addAttackAction("testPerformAction", action);
        p.performAction("testPerformAction", p);
        assertTrue(action.getPerformed());
    }
}
