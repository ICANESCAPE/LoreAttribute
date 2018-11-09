package org.sct.fv.loreattribute.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.sct.core.api.SctApi;
import org.sct.core.util.ItemStackUtil;
import org.sct.fv.loreattribute.file.Drop;
import org.sct.fv.loreattribute.file.Message;
import org.sct.fv.loreattribute.util.BasicUtil;
import org.sct.fv.loreattribute.util.DropUtil;

import java.util.Random;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBreak(org.bukkit.event.block.BlockBreakEvent e) {
        Block block = e.getBlock();
        Player player = e.getPlayer();

        if(block.getType().equals(Material.DIAMOND_ORE)) {
            if(DropUtil.luckyDraw(Drop.getDiamondMap()) != null) {
                SctApi.giveItem(player, DropUtil.luckyDraw(Drop.getDiamondMap()));
                player.sendMessage(BasicUtil.getMessage(Message.getDropItem().replace("%block%", "钻石矿")));
            } else {
                //do nothing
            }
        }

        if(block.getType().equals(Material.EMERALD_ORE)) {
           if(DropUtil.luckyDraw(Drop.getEmeraldMap()) != null) {
               SctApi.giveItem(player, DropUtil.luckyDraw(Drop.getEmeraldMap()));
               player.sendMessage(BasicUtil.getMessage(Message.getDropItem().replace("%block%", "绿宝石矿")));
           } else {
               //do nothing
           }
        }
    }
}
