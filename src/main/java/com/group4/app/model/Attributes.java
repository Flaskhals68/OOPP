package com.group4.app.model;

import java.util.HashMap;

public class Attributes {
    private int strength;
    private int dexterity;
    private int constitution;
    private int perception;
    private int meleeWeaponSkill;
    private int rangedWeaponSkill;
    private HashMap<String, Integer> stats;

    public Attributes(int meleeWeaponSkill, int rangedWeaponSkill, int str, int dex, int con, int per) {
        this.stats = new HashMap<>();
        this.stats.put("strength", str);
        this.stats.put("dexterity", dex);
        this.stats.put("constitution", con);
        this.stats.put("perception", per);
        this.stats.put("meleeWeaponSkill", meleeWeaponSkill);
        this.stats.put("rangedWeaponSkill", rangedWeaponSkill);
    }

    public int getStat(String stat) {
        return this.stats.getOrDefault(stat, 0);
    }

    public void levelUpStat(String stat) {
        this.stats.put(stat, this.stats.get(stat) + 10);
    }


}
