package com.group4.app.model;

public class Weapon implements IInventoriable {

    private final int attack;
    private final String name;
    private final boolean isLootable;

    private final String type;
    public Weapon(String name, int attack, boolean isLootable, String type){
        this.name = name;
        this.attack = attack;
        this.isLootable = isLootable;
        this.type = type;
    }

    public int getAttack() {
        return attack;
    }
    public String getName() {
        return name;
    }

    public boolean getLootable() {
        return isLootable;
    }

    public String getType() {
        return type;
    }

    @Override
    public void use(Player player) {

    }
}
