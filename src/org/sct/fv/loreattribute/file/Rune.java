package org.sct.fv.loreattribute.file;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.inventivetalent.itembuilder.util.FileUtil;
import org.sct.core.util.ItemStackUtil;
import org.sct.fv.loreattribute.LoreAttribute;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rune extends FileUtil {

    private static Rune config;
    private static Map<String, String> data = new HashMap<>();
    private static List<String> runeList = new ArrayList<>();


    public Rune() { super(LoreAttribute.getInstance(), "rune.yml"); }
    public static void reload() { config = new Rune(); }

    @Override
    public void check() {
        ConfigurationSection cs = this.getConfigurationSection("RuneSetting");

        for(String key : cs.getKeys(false)) {
            data.put(cs.getString(key + ".name"), cs.getString(key + ".lore"));
            runeList.add(key + ".name");
        }

    }

    public static List<String> getRuneLore() { return runeList; }
    public static Map<String, String> getDataMap() { return data; }

    public static List<String> getLoreList() { return config.getStringList("LoreList"); }

    public static String getPunchTool() { return config.getString("Punch"); }

    public static String getPunchedLore() { return config.getString("PunchedLore"); }

}
