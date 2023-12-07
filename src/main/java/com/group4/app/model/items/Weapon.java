package com.group4.app.model.items;

public class Weapon implements IInventoriable {

    private final int attack;
    private final String name;
    private final boolean isLootable;
    private final boolean isRanged;

    private final String type;
    public Weapon(String name, int attack, boolean isLootable, String type, Boolean isRanged){
        this.name = name;
        this.attack = attack;
        this.isLootable = isLootable;
        this.type = type;
        this.isRanged = isRanged;
    }

    public int getAttack() {
        return attack;
    }
    public String getName() {
        return name;
    }

    public boolean getLootable() {
        return isLootable;
    }

    public boolean getIsRanged() {
        return isRanged;
    }

    public String getType() {
        return type;
    }

    /**
     * Since this is a weapon, use simply equips the weapon
     * @param user the user that would like to equip this weapon
     */
    @Override
    public void use(IUser user) {
        user.setWeapon(this);
    }
}
