package model;

abstract class Weapon {

    private final int attack;
    private final String name;
    private final boolean isLootable;
    Weapon(String name, int attack, boolean isLootable){
        this.name = name;
        this.attack = attack;
        this.isLootable = isLootable;
    }

    public int getAttack() {
        return attack;
    }
}
