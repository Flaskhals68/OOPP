package com.group4.app.model;

import java.util.Set;

public class Enemy extends Entity implements IAttackable, ICanAttack, IMovable, ITurnTaker, IUser{
    private final String name;
    private Weapon weapon;
    private Inventory inv;
    private ResourceBar hp;
    private ResourceBar ap;
    private Attributes attributes;
    private int level;
    public Enemy(String id, String name, Weapon weapon, int maxAp, Attributes attr, int level){
        super(id);
        this.name = name;
        this.weapon = weapon;
        this.attributes = attr;
        this.hp = new ResourceBar(attr.getConstitution()/5);
        this.level = level;
        this.ap = new ResourceBar(maxAp);
        this.inv = new Inventory();
    }

    @Override
    public void takeHit(int damage) {
        hp.reduceCurrent(damage);
        if(hp.getCurrent() <= 0) {
            // TODO Add some logic for removing enemy if dead
            // TODO : XP currently flat number. Fix this to scale.
            Model.getInstance().getPlayer().giveXP(1);
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
        if(weapon.getIsRanged()){
            return weapon.getAttack() + attributes.getDexterity()/10;
        } else {
            return weapon.getAttack() + attributes.getStrength()/10;
        }
    }

    public String getName(){
        return name;
    }

    @Override
    public void move(Position pos) {
        // TODO not implemented yet
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Set<Position> getLegalMoves() {
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

    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
