package org.sct.fv.loreattribute.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.sct.core.Core;
import org.sct.core.util.EntityUtil;
import org.sct.core.util.ItemStackUtil;
import org.sct.core.util.NBTUtil;
import org.sct.core.util.NMSUtil;
import org.sct.fv.loreattribute.api.AttributeApi;
import org.sct.fv.loreattribute.dto.Attribute;
import org.sct.fv.loreattribute.file.Config;
import org.sct.fv.loreattribute.file.Message;
import org.sct.fv.loreattribute.util.BasicUtil;

import java.util.List;

public class ItemDamageListener implements Listener {

    @EventHandler
    public void onDamage(PlayerItemDamageEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
        Attribute attribute = AttributeApi.getAttributeFromItem(item);
        List<String> lores = item.getItemMeta().getLore();

        if (item == null || item == null || item.getItemMeta().equals(Material.AIR) || attribute == null) {
            Core.info("&c为Null");
            return;
        }
        /* 如果带有无限耐久表示则不会损耗 */
        if (attribute.isUnbreak()) {
            e.setDamage(0);
        }

        /* 如果没有这行Lore(attribute属性为0) 则不管 */
        if (attribute.getDurability() == 0) {
            return;
        } else if (attribute.getDurability() > 0) {
            e.setDamage(0);
            int line = ItemStackUtil.getLoreIndex(lores, BasicUtil.removeColor(Config.getDurability()));
            int dur = attribute.getDurability() - Config.getDurDrop();
            item = ItemStackUtil.setLore(item, line, (Config.getDurability() + dur));
            /* 每一次损耗重新进入事件，所以attribute数值不需要额外计算 */
            player.getInventory().setItemInMainHand(item);
            if (attribute.getDurability() <= Config.getDurWarn()) {
                player.sendMessage(BasicUtil.getMessage(Message.getDurWarn().replace("%left%", String.valueOf(dur))));
            }
            /* 如果dur为0，耐久损耗完了，清空物品 */
            if (dur == 0) {
                ItemStackUtil.TakePlayerItem(player, item);
                player.sendMessage(BasicUtil.getMessage(Message.getDurZero()));
            }
        }
    }
}
