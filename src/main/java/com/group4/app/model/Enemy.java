package com.group4.app.model;

public class Enemy extends Entity implements IAttackable, ICanAttack{
    private final String name;
    private final Weapon weapon;
    private int hp;
    public Enemy(String id, String name, Weapon weapon, int hp){
        super(id);
        this.name = name;
        this.weapon = weapon;
        this.hp = hp;
    }

    @Override
    public void takeHit(int damage) {
        hp = hp - damage;
        if(hp <= 0) {
            // TODO Add some logic for removing enemy if dead
            System.out.println("Dead");
        }
    }

    @Override
    public int getHitPoints() {
        return hp;
    }

    @Override
    public void attack(IAttackable entity) {
        entity.takeHit(weapon.getAttack());
    }

    @Override
    public int getDamage() {
        return weapon.getAttack();
    }
}
