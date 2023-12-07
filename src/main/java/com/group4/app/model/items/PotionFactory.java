package com.group4.app.model.items;

public class PotionFactory {
    public static Potion createHealthPotion(){
        return new HealthPotion("Health Potion", 10);
    }

}
