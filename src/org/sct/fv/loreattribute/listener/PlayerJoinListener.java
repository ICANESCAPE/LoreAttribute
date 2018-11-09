package org.sct.fv.loreattribute.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.sct.fv.loreattribute.api.AttributeApi;
import org.sct.fv.loreattribute.api.ForgeApi;
import org.sct.fv.loreattribute.dao.CacheDao;
import org.sct.fv.loreattribute.dto.Attribute;
import org.sct.fv.loreattribute.file.Config;
import org.sct.fv.loreattribute.file.Message;
import org.sct.fv.loreattribute.util.AttributeUtil;
import org.sct.fv.loreattribute.util.BasicUtil;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if(AttributeUtil.getItemsValue(player) >= Config.getValueNotice()) {
            Bukkit.getServer().broadcastMessage(Message.getValueMsg()
                    .replace("%player%", player.getName())
                    .replace("%value%", String.valueOf(AttributeUtil.getItemsValue(player))));
        }

        /* CacheDao用于载入玩家数据缓存，因此退出服务器的时候会被清空缓存，刚进服务器属性以盔甲+主手属性计算 */
        if(CacheDao.getPlayerAttribute(player.getName()) == null) {
            Attribute attribute = AttributeApi.getEntityEquipMentAttribute(player);
            CacheDao.putPlayerAttribute(player.getName(), attribute);
        }

        /* 为玩家建立锻造数据  */
        if(!ForgeApi.hasData(e.getPlayer().getName())) {
            ForgeApi.initialData(player.getName());
            player.sendMessage("§a第一次进入服务器，正在为你创建数据...");
        }
    }
}
