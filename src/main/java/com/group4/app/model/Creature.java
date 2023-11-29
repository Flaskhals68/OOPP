package com.group4.app.model;

import java.util.*;

public abstract class Creature extends Entity implements IAttackable, ICanAttack, IMovable, ITurnTaker, IUser {

    private ResourceBar hp;
    private ResourceBar ap;
    private int level;
    private Weapon weapon;
    private Armour armour;
    private Inventory inv;
    private Attributes attributes;
    public Creature(String id, Position pos, int ap, Weapon weapon, Attributes attr, int level) {
        super(id, pos);
        this.attributes = attr;
        this.hp = new ResourceBar(attributes.getStat(AttributeType.CONSTITUTION)/5);
        this.ap = new ResourceBar(ap);
        this.weapon = weapon;
        this.armour = ArmourFactory.createArmour(ArmourType.NONE, level);
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
        Tile target = Model.getInstance().getTile(pos);
        Set<Position> legalMoves = getLegalMoves();
        Position targetPos = target.getPos();
        if (!legalMoves.contains(new Position(targetPos.getX(), targetPos.getY(), target.getFloor()))) {
            throw new IllegalArgumentException("Illegal move");
        }

        Model.getInstance().removeEntity(this);
        this.setPosition(pos);
        Model.getInstance().addEntity(this, pos);
    }

    @Override
    public Set<Position> getLegalMoves() {
        // TODO: Change to use players actionpoints instead of static value
        return Model.getInstance().getSurrounding(getPos(), 5);
    }

    public void setWeapon(Weapon weapon) {
        // Puts current weapon in inventory if player already has one
        if (this.weapon != null) {
            inv.addItem(this.weapon);
        }
        this.weapon = weapon;
    }

    public void setArmour(Armour armour) {
        // Puts current armour in inventory if player already has one
        if (!armour.getType().equals(ArmourType.NONE)) {
            inv.addItem(this.armour);
        }
        this.armour = armour;
    }

    public void heal(int amount) {
        hp.increaseCurrent(amount);
    }

    /**
     * Should be called when the entity attacks another entity, determines if attack hits or not
     * @param other the entity that is being attacked
     */
    @Override
    public void attack(IAttackable other) {
        // Roll 100 sided dice, if roll is less than or equal to hit chance, hit
        int roll = new Random().nextInt(100) + 1;
        if (weapon.getIsRanged()) {
            if(roll <= attributes.getStat(AttributeType.RANGED_WEAPON_SKILL)) {
                other.takeHit(this.getDamage());
            } else {
                // TODO: Probably add some notification to the player that the attack missed other than console message
                System.out.println("Missed");
            }
        } else {
            if(roll <= attributes.getStat(AttributeType.MELEE_WEAPON_SKILL)) {
                other.takeHit(this.getDamage());
            } else {
                System.out.println("Missed");
            }
        }
    }

    /**
     * Should be called when attack will land, determines how much damage the attack will do
     * @return the amount of damage the attack will do
     */
    @Override
    public int getDamage() {
        return weapon.getAttack() + calculateBonusDamage(weapon.getIsRanged());
    }

    private int calculateBonusDamage(Boolean isRanged) {
        if(isRanged){
            return (attributes.getStat(AttributeType.DEXTERITY) - 50) / 10;
        } else {
            return (attributes.getStat(AttributeType.STRENGTH) - 50) / 10;
        }
    }

    /**
     * Should be called when the entity takes damage, and should handle death if hp reaches 0
     * this is done through the death() method, I believe this is called a Template Method Pattern
     * @param damage the damage an entity will take
     */
    @Override
    public void takeHit(int damage) {
        hp.reduceCurrent(damage - armour.getDamageReduction(attributes.getStat(AttributeType.DEXTERITY)));
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

    public Attributes getAttributes() {
        return attributes;
    }
}
