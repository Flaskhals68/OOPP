package com.group4.app.model.items;

public class WeaponFactory {
    public static Weapon createSword(){
        return new Weapon("Basic Sword", 8, true, "Sword", false);
    }
    public static Weapon createClaws() {
        return new Weapon("Basic Claws", 4, false, "Claws", false);
    }
}
