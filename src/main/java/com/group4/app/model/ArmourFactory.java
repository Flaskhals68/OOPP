package com.group4.app.model;

public class ArmourFactory {
    public static Armour createArmour(ArmourType type, int level) {
        switch (type) {
            case LIGHT:
                return new Armour("Light Armour", level, true, ArmourType.LIGHT);
            case MEDIUM:
                return new Armour("Medium Armour", 2*level, true, ArmourType.MEDIUM);
            case HEAVY:
                return new Armour("Heavy Armour", 3*level, true, ArmourType.HEAVY);
            default:
                return new Armour("No Armour", 0, false, ArmourType.NONE);
        }
    }
}
