package org.sct.fv.loreattribute.util;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.sct.core.file.FileTool;
import org.sct.fv.loreattribute.LoreAttribute;
import org.sct.fv.loreattribute.dto.AttributeType;
import org.sct.fv.loreattribute.file.Config;

import java.util.ArrayList;
import java.util.List;

public class BasicUtil {

    /**
     * 替换字符从&到§
     * @param msg
     * @return 替换后的文本
     */
    public static String getMessage(String msg) {
        return msg.replace("&", "§");
    }


    /**
     * 判断物品是否含有某条Lore
     * @param item 物品
     * @param lore Lore
     * @return [true]/[false]
     */
    public static boolean isContainLore(ItemStack item, String lore) {
        List<String> lores = item.getItemMeta().getLore();
        for(String key : lores) {
            if(key.contains(lore)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 生成 1-max 之间的一个随机数
     * @param max 最大的数值
     * @return 生成的随机数
     */
    public static int getRandom(int max) {
        return 1 + (int) (Math.random() * max);
    }

    /**
     * 移除字符串的颜色
     * @param msg 字符串
     * @return 移除颜色后的
     */
    public static String removeColor(String msg) { return ChatColor.stripColor(msg); }

    /**
     * string转换成ItemStack
     * @param item 物品名字
     * @return ItemStack
     */
    public static ItemStack toItem(String item) {
        return FileTool.getItem(item);
    }

    /**
     * 发送奖励列表给玩家
     * @param reward 奖励列表
     * @param player 受到奖励的玩家
     */
    public static void sentReward(List<String> reward, Player player) {
        for(int i = 0; i < reward.size(); i++) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), reward.get(i).replace("%player%", player.getName()));
        }
    }
}
