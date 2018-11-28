package org.sct.fv.loreattribute.dto;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.sct.fv.loreattribute.api.AttributeApi;

import java.util.HashMap;
import java.util.Map;

public class ProjectileAttribute {

    static Attribute attribute = new Attribute(null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 0, 0, 0);
    static Map<Player, Attribute> playerMap = new HashMap<>();

    public static void recordData(Player player, ItemStack item) {
        attribute = AttributeApi.getAttributeFromItem(item);
        playerMap.put(player, attribute);
    }

    public static Map<Player, Attribute> getMap() {
        return playerMap;
    }


}
