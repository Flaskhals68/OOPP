package com.group4.app.model.items;

public class ArmourFactory {
    public static Armour createArmour(ArmourType type, int level) {
        return switch (type) {
            case LIGHT -> new Armour("Light Armour", level, true, ArmourType.LIGHT);
            case MEDIUM -> new Armour("Medium Armour", 2 + level, true, ArmourType.MEDIUM);
            case HEAVY -> new Armour("Heavy Armour", 4 + level, true, ArmourType.HEAVY);
            default -> new Armour("No Armour", 0, false, ArmourType.NONE);
        };
    }
}
