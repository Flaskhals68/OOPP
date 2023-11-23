package com.group4.app.model;

import java.util.Random;

public class Player extends Creature {

    private ResourceBar xp;
    private Attributes attributes;

    public Player(String id, int ap, Weapon weapon, String floorId, Position position) {
        super(id, floorId, position, ap, weapon, new Attributes(50, 50, 50, 50, 50, 50), 1);
        this.xp = new ResourceBar(10);
    }

    public void giveXP(int amount) {
        xp.increaseCurrent(amount);
        if (xp.getCurrent() == xp.getMax()) {
            levelUp();
            xp.setCurrent(0);
        }
    }


    /**
     * Current implementation before we have a proper level up system
     * TODO: We want the player to be able to decide themselves what to increase!
     */
    private void levelUp() {
        this.setLevel(this.getLevel() + 1);
        Attributes attr = this.getAttributes();

        switch (new Random().nextInt(6)) {
            case 0:
                attr.levelUpStat("strength");
                break;
            case 1:
                attr.levelUpStat("dexterity");
                break;
            case 2:
                attr.levelUpStat("constitution");
                break;
            case 3:
                attr.levelUpStat("perception");
                break;
            case 4:
                attr.levelUpStat("meleeWeaponSkill");
                break;
            case 5:
                attr.levelUpStat("rangedWeaponSkill");
                break;
            default:
                break;
        }
    }


    @Override
    public void startTurn() {
        Model.getInstance().startPlayerTurn();
        // TODO : Implement player turn
    }

    @Override
    public void endTurn() {
        Model.getInstance().endPlayerTurn();
    }


    @Override
    public void death() {
        // TODO : Implement player death
        System.out.println("Player died");
    }

    public static void main(String[] args) {
        Model model = Model.getInstance();
        World world = new World(10);
        model.addWorld(world);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                world.addTile(new Tile("stone", world.getId(), new Position(i, j)));
            }
        }
        Player p = new Player("player", 5, null, world.getId(), new Position(0, 0));

        p.move(new Position(9, 9));
    }
}
