package org.sct.fv.loreattribute.file;

import org.bukkit.configuration.ConfigurationSection;
import org.inventivetalent.itembuilder.util.FileUtil;
import org.sct.core.Core;
import org.sct.fv.loreattribute.LoreAttribute;
import org.sct.fv.loreattribute.dto.ForgeData;

import java.util.ArrayList;
import java.util.List;

public class Forge extends FileUtil {

    private static Forge forge;
    private static List<ForgeData> data = new ArrayList<>();

    public Forge() { super(LoreAttribute.getInstance(), "forge.yml"); }
    public static void reload() { forge = new Forge(); }

    @Override
    public void check() {
        ConfigurationSection cs = this.getConfigurationSection("Drawing");
        for(String key : cs.getKeys(false)) {
            data.add(new ForgeData(
                    cs.getString(key + ".icon"),
                    cs.getString(key + ".item"),
                    cs.getInt(key + ".exp"),
                    cs.getInt(key + ".add"),
                    cs.getInt(key + ".slot"),
                    cs.getStringList(key + ".require"),
                    cs.getStringList(key + ".reward"))
            );
        }
    }

    public static int getMaxExp() { return forge.getInt("MaxExp"); }

    public static List<ForgeData> getList() { return data; }

    public static String getConfirm() { return forge.getString("confirm"); }

    public static ForgeData getForgeData(int slot) {
        for(ForgeData key : data) {
            if(key.getSlot() == slot) {
                return key;
            }
        }
        return null;
    }

}
