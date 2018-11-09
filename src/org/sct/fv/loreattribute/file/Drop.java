package org.sct.fv.loreattribute.file;

import org.bukkit.configuration.ConfigurationSection;
import org.inventivetalent.itembuilder.util.FileUtil;
import org.sct.fv.loreattribute.LoreAttribute;

import java.util.*;

public class Drop extends FileUtil {

    private static Drop drop;
    private static Map<String, Integer> diamond_data = new HashMap<>();
    private static Map<String, Integer> emerald_data = new HashMap<>();

    public Drop() { super(LoreAttribute.getInstance(), "itemdrop.yml"); }
    public static void reload() { drop = new Drop(); }

    @Override
    public void check() {
        ConfigurationSection dcs = this.getConfigurationSection("Diamond");
        ConfigurationSection ecs = this.getConfigurationSection("Emerald");

        for(String diamond_key : dcs.getKeys(false)) {
            diamond_data.put(dcs.getString(diamond_key + ".name"), dcs.getInt(diamond_key + ".chance"));
        }

        for(String emerald_key : ecs.getKeys(false)) {
            emerald_data.put(ecs.getString(emerald_key + ".name"), ecs.getInt(emerald_key + ".chance"));
        }

    }

    public static Map<String, Integer> getDiamondMap() { return diamond_data; }

    public static Map<String, Integer> getEmeraldMap() {
        return emerald_data;
    }

    public static int getChance() { return drop.getInt("DropChance"); }

}
