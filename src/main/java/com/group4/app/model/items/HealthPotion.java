package com.group4.app.model.items;

public class HealthPotion extends Potion{

    private int hp;
    public HealthPotion(String name, int hp) {
        super(name);
        this.hp = hp;
    }

    @Override
    public void use(IUser user) {
        user.heal(hp);
    }
}
