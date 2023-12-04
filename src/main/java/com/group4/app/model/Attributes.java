package com.group4.app.model;

import org.w3c.dom.Attr;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Attributes {
    private HashMap<AttributeType, Integer> stats;

    public Attributes(int meleeWeaponSkill, int rangedWeaponSkill, int str, int dex, int con, int per) {
        this.stats = new HashMap<>();
        this.stats.put(AttributeType.STRENGTH, str);
        this.stats.put(AttributeType.DEXTERITY, dex);
        this.stats.put(AttributeType.CONSTITUTION, con);
        this.stats.put(AttributeType.PERCEPTION, per);
        this.stats.put(AttributeType.MELEE_WEAPON_SKILL, meleeWeaponSkill);
        this.stats.put(AttributeType.RANGED_WEAPON_SKILL, rangedWeaponSkill);
    }

    public int getStat(AttributeType stat) {
        return stats.get(stat);
    }

    public void levelUpStat(AttributeType stat) {
        this.stats.put(stat, this.stats.get(stat) + 10);
    }

    public Map<AttributeType, Integer> getAttributeMap() {
        return new HashMap<>(stats);
    }
    /**
     * Should be used to level up a random stat
     */
    public void levelUpRandom() {
        AttributeType[] types = AttributeType.values();
        int randomIndex = new Random().nextInt(types.length);
        AttributeType randomType = types[randomIndex];
        levelUpStat(randomType);
        System.out.println("Leveled up " + randomType.toString() + " to " + getStat(randomType));
    }


}
