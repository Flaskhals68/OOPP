package com.group4.app.model;


import org.w3c.dom.Attr;

import java.util.Random;

public class Player extends Creature {

    private ResourceBar xp;
    private Attributes attributes;

    public Player(String id, int ap, Weapon weapon, String floorId, int xPos, int yPos) {
        super(id, floorId, xPos, yPos, ap, weapon, new Attributes(50, 50, 50, 50, 50, 50), 1);
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
                attr.levelUpStat(AttributeType.STRENGTH);
                break;
            case 1:
                attr.levelUpStat(AttributeType.DEXTERITY);
                break;
            case 2:
                attr.levelUpStat(AttributeType.CONSTITUTION);
                break;
            case 3:
                attr.levelUpStat(AttributeType.PERCEPTION);
                break;
            case 4:
                attr.levelUpStat(AttributeType.MELEE_WEAPON_SKILL);
                break;
            case 5:
                attr.levelUpStat(AttributeType.RANGED_WEAPON_SKILL);
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
}
