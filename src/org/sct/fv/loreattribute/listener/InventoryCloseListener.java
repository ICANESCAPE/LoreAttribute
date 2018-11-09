package org.sct.fv.loreattribute.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onCloseInv(InventoryCloseEvent e) {

        Player player = (Player) e.getPlayer();
        ItemStack weapon, tool;

        if(e.getPlayer() instanceof Player) {
            return;
        }

        if (e.getInventory().getName().equals("§d§l武器打孔")) {
            weapon = e.getInventory().getItem(11);
            if(weapon != null) {
                player.getInventory().addItem(new ItemStack[] { weapon } );
            }
        }

        if (e.getInventory().getName().equals("§d§l符文镶嵌")) {
            weapon = e.getInventory().getItem(10);
            tool = e.getInventory().getItem(13);
            if(weapon != null) {
                player.getInventory().addItem(new ItemStack[] { weapon } );
            }
            if(tool != null) {
                player.getInventory().addItem(new ItemStack[] { tool } );
            }
        }

        if(e.getInventory().getName().equalsIgnoreCase("§3§l锻造台 - 放入锻造材料")) {
            ItemStack[] items = new ItemStack[e.getInventory().getSize()];
            for(int i = 0; i < e.getInventory().getSize(); i++) {
                if(i != 35 && e.getInventory().getItem(i) != null && !e.getInventory().getItem(i).getItemMeta().equals(Material.AIR)) {
                    ItemStack item = e.getInventory().getItem(i);
                    items[i] = item;
                }
            }
            player.sendMessage("测试，你的东西没拿走");
            player.getInventory().addItem(items);
        }

    }
}
