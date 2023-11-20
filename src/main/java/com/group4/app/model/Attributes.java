package com.group4.app.model;

public class Attributes {
    private int strength;
    private int dexterity;
    private int constitution;
    private int perception;
    private int meleeWeaponSkill;
    private int rangedWeaponSkill;

    public Attributes(int meleeWeaponSkill, int str, int dex, int con, int per) {
        this.meleeWeaponSkill = meleeWeaponSkill;
        this.strength = str;
        this.dexterity = dex;
        this.constitution = con;
        this.perception = per;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getPerception() {
        return perception;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setPerception(int perception) {
        this.perception = perception;
    }

    public void setMeleeWeaponSkill(int meleeWeaponSkill) {
        this.meleeWeaponSkill = meleeWeaponSkill;
    }

    public void setRangedWeaponSkill(int rangedWeaponSkill) {
        this.rangedWeaponSkill = rangedWeaponSkill;
    }
}
