package org.sct.fv.loreattribute.dao;

import org.bukkit.entity.Player;
import org.sct.fv.loreattribute.dto.Attribute;

import java.util.HashMap;
import java.util.Map;

public class CacheDao {

    private static Map<String, Attribute> cache = new HashMap<String, Attribute>();

    public static void putPlayerAttribute(String name, Attribute attribute) {
        cache.put(name, attribute);
    }

    public static Attribute getPlayerAttribute(String name) {
        if (cache.containsKey(name)) {
            return cache.get(name);
        }else {
            return null;
        }
    }

    public static void deletePlayerData(Player player) { cache.remove(player.getName()); }

}
