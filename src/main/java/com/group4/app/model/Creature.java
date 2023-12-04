package com.group4.app.model;
import java.util.*;

import com.group4.app.model.actions.Action;
import com.group4.app.model.actions.IAction;
import com.group4.app.model.actions.PlayerAttackAction;
import com.group4.app.model.actions.PlayerMoveAction;

public abstract class Creature extends Entity implements IAttackable, ICanAttack, ITurnTaker, IUser {

    private ResourceBar hp;
    private ResourceBar ap;
    private int level;
    private Weapon weapon;
    private Armour armour;
    private Inventory inv;
    private Attributes attributes;
    private Map<String, IAction<Position>> moveActions;
    private Map<String, IAction<IAttackable>> attackActions;

    public Creature(String id, Position pos, int ap, Weapon weapon, Attributes attr, int level) {
        super(id, pos);
        this.attributes = attr;
        this.hp = new ResourceBar(attributes.getStat(AttributeType.CONSTITUTION)/5);
        this.ap = new ResourceBar(ap);
        this.weapon = weapon;
        this.armour = ArmourFactory.createArmour(ArmourType.NONE, level);
        this.inv = new Inventory();
        this.level = level;
        this.moveActions = new HashMap<String, IAction<Position>>();
        this.attackActions = new HashMap<String, IAction<IAttackable>>();
        this.addAttackAction("attack", new PlayerAttackAction(1, "attack", this));
        this.addMoveAction("move", new PlayerMoveAction(1, "move", this));
    }

    public void performAction(String action, Position target) {
        if (moveActions.containsKey(action)) {
            moveActions.get(action).perform(target);
        } else {
            throw new IllegalArgumentException("Action not available");
        }
    }

    public void performAction(String action, IAttackable target) {
        if (attackActions.containsKey(action)) {
            attackActions.get(action).perform(target);
        } else {
            throw new IllegalArgumentException("Action not available");
        }
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int lvl) {
        this.level = lvl;
    }

    public void addMoveAction(String actionId, Action<IPositionable, Position> action) {
        action.setActionTaker(this);
        moveActions.put(actionId, action);
    }

    public void addAttackAction(String actionId, Action<ICanAttack, IAttackable> action) {
        action.setActionTaker(this);
        attackActions.put(actionId, action);
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

    @Override
    public int getMaxHitPoints() {
        return hp.getMax();
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

    public Map<AttributeType, Integer> getAttributesMap() {
        return attributes.getAttributeMap();
    }
}
