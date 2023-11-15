package com.group4.app.model;

import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public class Enemy extends Entity implements IAttackable, ICanAttack, IMovable, ITurnTaker{
    private final String name;
    private final Weapon weapon;
    private ResourceBar hp;
    private ResourceBar ap;
    public Enemy(String id, String name, Weapon weapon, int maxHp, int maxAp){
        super(id);
        this.name = name;
        this.weapon = weapon;
        this.hp = new ResourceBar(maxHp);
        this.ap = new ResourceBar(maxAp);
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
    public List<Tile> getLegalMoves() {
        return null;
    }

    public void startTurn(){
        this.useAp(this.ap.getMax());
    }

    public int getAp(){
        return this.ap.getCurrent();
    }

    private void endTurn(){
        Model.getInstance().endTurn();
    }

    public void refillAp(){
    this.ap.setCurrent(this.ap.getMax());
    }
    
    public void useAp(int amount){
        if (this.ap.getCurrent() < amount) {
            throw new IllegalArgumentException();
        }
        this.ap.reduceCurrent(amount);
        if (this.ap.getCurrent() <= 0){
            this.endTurn();
        }
    }
}
