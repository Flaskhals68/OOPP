package com.group4.app.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public abstract class FightingEntity extends Entity implements IAttackable, ICanAttack, IMovable, ITurnTaker, IUser {

    private ResourceBar hp;
    private ResourceBar ap;
    private int level;
    private Weapon weapon;
    private Inventory inv;
    private Attributes attributes;
    public FightingEntity(String id, String floorId, int xPos, int yPos, int ap, Weapon weapon, Attributes attr, int level) {
        super(id, floorId, xPos, yPos);
        this.attributes = attr;
        this.hp = new ResourceBar(attributes.getConstitution()/5);
        this.ap = new ResourceBar(ap);
        this.weapon = weapon;
        this.inv = new Inventory();
        this.level = level;
    }

    public FightingEntity(String id, int ap, Weapon weapon, Attributes attr, int level) {
        super(id);
        this.attributes = attr;
        this.hp = new ResourceBar(attributes.getConstitution()/5);
        this.ap = new ResourceBar(ap);
        this.weapon = weapon;
        this.inv = new Inventory();
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int lvl) {
        this.level = lvl;
    }

    @Override
    public void move(Position pos) {
        Tile target = Model.getInstance().getTile(getFloor(), pos.getX(), pos.getY());
        Set<Position> legalMoves = getLegalMoves();
        if (!legalMoves.contains(new Position(target.getXPos(), target.getYPos()))) {
            throw new IllegalArgumentException("Illegal move");
        }

        Model.getInstance().removeEntity(this);
        this.setPosition(getFloor(), pos.getX(), pos.getY());
        Model.getInstance().addEntity(this, getFloor(), pos.getX(), pos.getY());
    }

    @Override
    public Set<Position> getLegalMoves() {
        // TODO: Change to use players actionpoints instead of static value
        return PathfindingHelper.getSurrounding(Model.getInstance().getTile(getFloor(), getYPos(), getYPos()), 5);
    }

    public void setWeapon(Weapon weapon) {
        // Puts current weapon in inventory if player already has one
        if (this.weapon != null) {
            inv.addItem(this.weapon);
        }
        this.weapon = weapon;
    }

    @Override
    public void attack(IAttackable other) {
        other.takeHit(this.getDamage());
    }

    @Override
    public int getDamage() {
        if(weapon.getIsRanged()){
            return weapon.getAttack() + attributes.getDexterity()/10;
        } else {
            return weapon.getAttack() + attributes.getStrength()/10;
        }
    }

    /**
     * Should be called when the entity takes damage, and should handle death if hp reaches 0
     * this is done through the death() method, I believe this is called a Template Method Pattern
     * @param damage the damage an entity will take
     */
    @Override
    public void takeHit(int damage) {
        hp.reduceCurrent(damage);
        if (hp.getCurrent() <= 0) {
            this.death();
        }
    }

    /**
     * Implement in subclasses to handle death of entity
     */
    public abstract void death();

    @Override
    public int getHitPoints() {
        return hp.getCurrent();
    }

    public int getAp() {
        return this.ap.getCurrent();
    }

    public void refillAp() {
        this.ap.setCurrent(this.ap.getMax());
    }

    public void useAp(int amount) {
        if (this.ap.getCurrent() < amount) {
            throw new IllegalArgumentException();
        }
        this.ap.reduceCurrent(amount);
        if (this.ap.getCurrent() <= 0){
            this.endTurn();
        }
    }

    /**
     * Use this in model later to fetch (and remove) a specific item to from an entities inventory
     *
     * @param name name of the item you would like to fetch from the inventory
     * @return the item object
     */
    public IInventoriable fetchItemFromInventory(String name) {
        return inv.getItem(name);
    }

    public void addItemToInventory(IInventoriable item) {
        inv.addItem(item);
    }

    /**
     * Should make it easy to draw inventory in the view later
     *
     * @return HashMap with item names as keys, and amount of that item as values
     */
    public Map<String, Integer> getInventoryItems() {
        Map<String, Stack<IInventoriable>> ogMap = inv.getItems();
        Map<String, Integer> returnMap = new HashMap<>();

        // Should count how many of each item there exist. To make drawing them simple
        ogMap.forEach((key, value) -> {
            returnMap.put(key, value.size());
        });
        return returnMap;
    }
}
