package com.group4.app.model;

public enum AttributeType {
    STRENGTH,
    DEXTERITY,
    CONSTITUTION,
    PERCEPTION,
    MELEE_WEAPON_SKILL,
    RANGED_WEAPON_SKILL;

    @Override
    public String toString() {
        switch (this) {
            case STRENGTH:
                return "Str";
            case DEXTERITY:
                return "Dex";
            case CONSTITUTION:
                return "Con";
            case PERCEPTION:
                return "Per";
            case MELEE_WEAPON_SKILL:
                return "Melee";
            case RANGED_WEAPON_SKILL:
                return "Ranged";
            default:
                return "Unknown";
        }
    }
}
