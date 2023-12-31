package com.group4.app.model.items;

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

    /**
     * Gets the damage reduction of the armour, factoring in the dex bonus
     * Medium maxed at 70 dex, light gets defence + (dex above 50) / 10, heavy no dex bonus
     * @param dexBonus the dexterity bonus of the user
     * @return the amount of damage reduction
     */
    public int getDamageReduction(int dexBonus) {

        if(type.equals(ArmourType.MEDIUM)) {
            return defence + Math.min(dexBonus, 2);
        } else if(type.equals(ArmourType.HEAVY)) {
            return defence;
        }
        return defence + dexBonus;
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
     * Since this is armour, use simply equips the armour
     * @param user the user that would like to equip this armour
     */
    public void use(IUser user) {
        user.setArmour(this);
    }
}
