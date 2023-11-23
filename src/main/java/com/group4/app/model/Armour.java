package com.group4.app.model;

public class Armour implements IInventoriable{
    private final int defence;
    private final String name;
    private final boolean isLootable;
    private final ArmourType type;

    public Armour(String name, int defence, boolean isLootable, ArmourType type){
        this.name = name;
        this.defence = defence;
        this.isLootable = isLootable;
        this.type = type;
    }


    public int getDefence() {
        return defence;
    }
    public String getName() {
        return name;
    }

    public boolean getLootable() {
        return isLootable;
    }

    public ArmourType getType() {
        return type;
    }

    /**
     * Since this is a weapon, use simply equips the weapon
     * @param user the user that would like to equip this weapon
     */
    public void use(IUser user) {
        user.setArmour(this);
    }
}
