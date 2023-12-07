package com.group4.app.testmodel;

import com.group4.app.model.Model;
import com.group4.app.model.Player;
import com.group4.app.model.Potion;
import com.group4.app.model.PotionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPotion {
    @Test
    public void testUseHealthPotion() {
        Model.getInstance().addBasicMap(10, 0);
        Player player = Model.getInstance().getPlayer();
        int maxHP = player.getHitPoints();

        player.takeHit(8);
        player.useItemFromInventory("Health Potion");
        assertEquals(maxHP, player.getHitPoints());
    }
}
