package com.group4.app.model;

abstract class Weapon {

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

    int getAttack() {
        return attack;
    }
    String getName() {
        return name;
    }

    boolean getLootable() {
        return isLootable;
    }

    String getType() {
        return type;
    }
}
