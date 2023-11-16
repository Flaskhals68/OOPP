package com.group4.app.model;

import jdk.jshell.spi.ExecutionControl;

import java.awt.geom.Point2D;
import java.util.List;

public class Enemy extends Entity implements IAttackable, ICanAttack, IMovable{
    private final String name;
    private final Weapon weapon;
    private HealthBar hp;
    public Enemy(String id, String name, Weapon weapon, int maxHp){
        super(id);
        this.name = name;
        this.weapon = weapon;
        this.hp = new HealthBar(maxHp);
    }

    @Override
    public void takeHit(int damage) {
        hp.reduceCurrent(damage);
        if(hp.getCurrent() == 0) {
            // TODO Add some logic for removing enemy if dead
            System.out.println("Dead");
        }
    }

    @Override
    public int getHitPoints() {
        return hp.getCurrent();
    }

    @Override
    public void attack(IAttackable entity) {
        entity.takeHit(weapon.getAttack());
    }

    @Override
    public int getDamage() {
        return weapon.getAttack();
    }

    public String getName(){
        return name;
    }

    @Override
    public void move(int xPos, int yPos) {
        // TODO not implemented yet
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Point2D> getLegalMoves() {
        return null;
    }
}
