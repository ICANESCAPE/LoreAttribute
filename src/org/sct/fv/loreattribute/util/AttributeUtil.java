package org.sct.fv.loreattribute.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import org.sct.fv.loreattribute.dto.AttributeType;
import org.sct.fv.loreattribute.file.Config;

import java.util.ArrayList;
import java.util.List;

public class AttributeUtil {

    /**
     * 取item的属性枚举列表
     * @param lore 某一行Lore
     * @return {@link AttributeType}
     */
    public static AttributeType getAttributeType(String lore) {
        for(AttributeType type : AttributeType.values()) {
            if(BasicUtil.removeColor(lore).contains(type.getName())) {
                return type;
            }
        }
        return null;
    }

    /**
     * 取玩家的主手武器+四件盔甲
     * @param player 玩家
     * @return {@link ItemStack}
     */
    public static List<ItemStack> getItemList(Player player) {
        List<ItemStack> itemList = new ArrayList<>();
        itemList.add(player.getEquipment().getItemInMainHand());
        /* 尝试获取副手物品，如果没有则跳过  因为出售需要，这个功能不在免费版里出现
        try {
            if(player.getEquipment().getItemInOffHand() != null) {
                itemList.add(player.getEquipment().getItemInOffHand());
            }
        } catch (NoSuchMethodError e) { }*/
        for (int i = 0; i < player.getEquipment().getArmorContents().length; i++) {
            itemList.add(player.getEquipment().getArmorContents()[i]);
        }
        return itemList;
    }

    /**
     * 获取玩家的战斗力
     * @param player 玩家
     * @return 战力总和
     */
    public static int getItemsValue(Player player) {
        int value = 0;
        List<ItemStack> itemList = new ArrayList<>();
        for(int i = 0; i < player.getInventory().getSize(); i++) {
            if(player.getInventory().getItem(i) == null || player.getInventory().getItem(i).getType().equals(Material.AIR) || !player.getInventory().getItem(i).getItemMeta().hasLore()) {
            } else if(player.getInventory().getItem(i).getItemMeta().hasLore()) {
                itemList.add(player.getInventory().getItem(i));
            }
        }
        for(int i = 0; i < itemList.size(); i++) {
            List<String> lore = itemList.get(i).getItemMeta().getLore();
            for(int a = 0; a < lore.size(); a++) {
                if(lore.get(a).contains(Config.getValue())) {
                    player.sendMessage("§c测试config.getValue" + Config.getValue());
                    value += MethodsUtil.getInt(lore.get(a));
                } else {
                    player.sendMessage("§c测试信息，跳过检查");
                }
            }
        }
        return value;
    }

    /**
     * 获取属性值
     * @param item 物品
     * @param attribute 属性Lore
     * @return 属性值(Object)
     */
    public static Object getAttributeValue(ItemStack item, String attribute) {
        List<String> lores = item.getItemMeta().getLore();
        for(int i = 0; i < lores.size(); i++) {
            if(lores.get(i).contains(attribute)) {
                return MethodsUtil.getDouble(lores.get(i));
            }
        }
        return 0;
    }


}
