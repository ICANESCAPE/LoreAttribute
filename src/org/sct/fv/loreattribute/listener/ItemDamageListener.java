package org.sct.fv.loreattribute.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.sct.core.util.EntityUtil;
import org.sct.core.util.ItemStackUtil;
import org.sct.core.util.NBTUtil;
import org.sct.core.util.NMSUtil;
import org.sct.fv.loreattribute.api.AttributeApi;
import org.sct.fv.loreattribute.dto.Attribute;
import org.sct.fv.loreattribute.file.Config;
import org.sct.fv.loreattribute.file.Message;
import org.sct.fv.loreattribute.util.BasicUtil;

public class ItemDamageListener implements Listener {

    @EventHandler
    public void onDamage(PlayerItemDamageEvent e) {
      /*  Player player = e.getPlayer();
        ItemStack item = e.getItem();
        Attribute attribute = AttributeApi.getAttributeFromItem(item);
        /* 非耐Lore耐久物品就不计算
        if(attribute.getDurability() == 0 && item != null && !item.getItemMeta().equals(Material.AIR)) {
           // ItemStackUtil.TakePlayerItem(player, item);
        } else {
            e.setDamage(0);
            String lore = Config.getDurability() + attribute.getDurability();
            player.sendMessage(""+player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(lore));
            player.sendMessage(lore);
            String new_lore = Config.getDurability() + (attribute.getDurability() - Config.getDurDrop());
            player.sendMessage(new_lore);
            player.getInventory().setItemInMainHand(ItemStackUtil.replaceLore(item, lore, new_lore));
            if(attribute.getDurability() <= Config.getDurWarn()) {
                player.sendMessage(BasicUtil.getMessage(Message.getDurWarn()).replace("%left%", "" + attribute.getDurability()));
            }
        }

        /* 有无限耐久表示就不计算
        if(attribute.isUnbreak()) {
            e.setDamage(0);
        }*/
    }
}
