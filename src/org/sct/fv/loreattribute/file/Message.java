package org.sct.fv.loreattribute.file;

import org.bukkit.configuration.ConfigurationSection;
import org.inventivetalent.itembuilder.util.FileUtil;
import org.sct.fv.loreattribute.LoreAttribute;

public class Message extends FileUtil {

    private static Message message;

    public Message() { super(LoreAttribute.getInstance(), "message.yml"); }
    public static void reload() { message = new Message(); }

    @Override
    public void check() {
        ConfigurationSection cs = this.getConfigurationSection("");
    }

    public static String getValueMsg() { return message.getString("Message.ValueInfo"); }
    public static String getNotRune() { return message.getString("Message.NoRune"); }
    public static String getNoPunchedLore() { return message.getString("Message.NoPunchedLore"); }
    public static String getRuneSuccess() { return message.getString("Message.RuneSuccess"); }
    public static String getPunchTitle() { return message.getString("Message.PunchTitle"); }
    public static String getPunchSuccess() { return message.getString("Message.PunchSuccess"); }
    public static String getDropItem() { return message.getString("Message.DropItem"); }
    public static String getNotCorrectMaterial() { return message.getString("Message.NotCorrectMaterial"); }
    public static String getForgeSuccess() { return message.getString("Message.ForgeSuccess"); }
    public static String getForgeExpNotEnough() { return message.getString("Message.ForgeExpNotEnough"); }
    public static String getForgeExpMax() { return message.getString("Message.ForgeExpMax"); }
    public static String getForgeExpAdd() { return message.getString("Message.ForgeExpAdd"); }
    public static String getDurWarn() { return message.getString("Message.DurWarn"); }
    public static String getDurZero() { return message.getString("Message.DurZero"); }
    public static String getSecKill() { return message.getString("Message.SecKill"); }
    public static String getDodge() { return message.getString("Message.Dodge"); }
    public static String getDodged() { return message.getString("Message.Dodged"); }
    public static String getCrit() { return message.getString("Message.Crit"); }
    public static String getRealAttack() { return message.getString("Message.RealAttack"); }
    public static String getAddPoint() { return message.getString("Message.AddPoint"); }
    public static String getMonitForgeExp() { return message.getString("Message.MonitForgeExp"); }
    public static String getCheckAttr() { return message.getString("Message.CheckAttr"); }
}
