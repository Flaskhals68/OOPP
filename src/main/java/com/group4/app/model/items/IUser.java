package com.group4.app.model.items;

public interface IUser {
    void setWeapon(Weapon weapon);
    void setArmour(Armour armour);

    void heal(int amount);
}
