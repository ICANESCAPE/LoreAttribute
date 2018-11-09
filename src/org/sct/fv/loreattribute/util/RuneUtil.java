package org.sct.fv.loreattribute.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.sct.fv.loreattribute.file.Rune;

import java.util.ArrayList;
import java.util.List;

public class RuneUtil {
    /**
     * 符文类的方法封装在这里
     */
    private static Inventory inventory;

    public static void openPunchInventory(Player player) {
        inventory = Bukkit.createInventory(null, 54, "§d§l武器打孔");
        addPunchIcon();
        player.openInventory(inventory);
    }

    public static void openInlayInventory(Player player) {
        inventory = Bukkit.createInventory(null, 36, "§d§l符文镶嵌");
        addInlayIcon();
        player.openInventory(inventory);
    }

    private static void addPunchIcon() {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
        ItemMeta id = item.getItemMeta();
        id.setDisplayName("§c§l请放入武器");
        item.setItemMeta(id);
        inventory.setItem(0, item);
        inventory.setItem(1, item);
        inventory.setItem(2, item);
        inventory.setItem(9, item);
        inventory.setItem(11, item);
        inventory.setItem(18, item);
        inventory.setItem(19, item);
        inventory.setItem(20, item);

        item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1);
        id = item.getItemMeta();
        id.setDisplayName("§6§l请放入打孔道具");
        item.setItemMeta(id);
        inventory.setItem(3, item);
        inventory.setItem(4, item);
        inventory.setItem(5, item);
        inventory.setItem(12, item);
        inventory.setItem(14, item);
        inventory.setItem(21, item);
        inventory.setItem(22, item);
        inventory.setItem(23, item);

        item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
        id = item.getItemMeta();
        id.setDisplayName("§4§l此处提取物品");
        item.setItemMeta(id);
        inventory.setItem(6, item);
        inventory.setItem(7, item);
        inventory.setItem(8, item);
        inventory.setItem(15, item);
        inventory.setItem(17, item);
        inventory.setItem(24, item);
        inventory.setItem(25, item);
        inventory.setItem(26, item);

        item = new ItemStack(Material.DIAMOND_AXE);
        id = item.getItemMeta();
        id.setDisplayName("§e§l点击进行升级");
        List<String> lore = new ArrayList<>();
        lore.add("§7需要将物品放齐");
        lore.add("§7点击后可以将满足条件物品升级");
        id.setLore(lore);
        item.setItemMeta(id);
        inventory.setItem(40, item);

        item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        id = item.getItemMeta();
        id.setDisplayName("§7§l*");
        item.setItemMeta(id);
        for (int x = 1; x <= 27; x++) {
            if (x != 14) {
                inventory.setItem(26 + x, item);
            }
        }
    }

    private static void addInlayIcon() {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
        ItemMeta id = item.getItemMeta();
        id.setDisplayName("");
        item.setItemMeta(id);
        for(int i = 0; i < 36; i++) {
            if(i == 11 || i == 15) {

            } else {
                inventory.setItem(i, item);
            }
        }

        item = new ItemStack(Material.DIAMOND_AXE);
        id = item.getItemMeta();
        id.setDisplayName("§e§l点击进行升级");
        List<String> lore = new ArrayList<>();
        lore.add("§7需要将物品放齐");
        lore.add("§7点击后可以将满足条件物品升级");
        id.setLore(lore);
        item.setItemMeta(id);
        inventory.setItem(22, item);
    }

    public static String getRuneLore(ItemStack rune) {
        List<String> lores = rune.getItemMeta().getLore();
        for(String lore : lores) {
            for(String target : Rune.getRuneLore()) {
                if(target.equals(lore)) {
                    return lore;
                }
            }
        }
        return null;
    }

}
