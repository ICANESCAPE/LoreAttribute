package org.sct.fv.loreattribute.file;

import org.bukkit.configuration.ConfigurationSection;
import org.inventivetalent.itembuilder.util.FileUtil;
import org.sct.fv.loreattribute.LoreAttribute;

public class Config extends FileUtil {

   private static Config config;

    public Config() { super(LoreAttribute.getInstance(), "config.yml"); }
    public static void reload() { config = new Config(); }

    @Override
    public void check() {
        ConfigurationSection cs = this.getConfigurationSection("");
    }

    public static String getDamage() { return config.getString("Attribute.damage"); }
    public static String getDefand() { return config.getString("Attribute.defand"); }
    public static String getLuck() { return config.getString("Attribute.luck"); }
    public static String getHit() { return config.getString("Attribute.hit"); }
    public static String getDodge() { return config.getString("Attribute.dodge"); }
    public static String getCritical() { return config.getString("Attribute.critical"); }
    public static String getRealAttack() { return config.getString("Attribute.realattack"); }
    public static String getExp() { return config.getString("Attribute.exp"); }
    public static String getFlame() { return config.getString("Attribute.flame"); }
    public static String getSeckill() { return config.getString("Attribute.seckill"); }
    public static String getPosion() { return config.getString("Attribute.posion"); }
    public static String getValue() { return config.getString("Attribute.value"); }
    public static String getDurability() { return config.getString("Attribute.durability"); }
    public static String getUnbreak() { return config.getString("Attribute.unbreak"); }
    public static String getIcy(){ return config.getString("Attribute.icy"); }
    public static String getFire(){ return config.getString("Attribute.fire");}
    public static int getValueNotice() { return config.getInt("Setting.ValueNotice"); }
    public static int getHitpercent() { return config.getInt("Setting.Hitpercent"); }
    public static int getLuckrange() { return config.getInt("Setting.Luckrange"); }
    public static int getDurDrop() { return config.getInt("Setting.DurDrop"); }
    public static int getDurWarn() { return config.getInt("Setting.DurWarn"); }

}
