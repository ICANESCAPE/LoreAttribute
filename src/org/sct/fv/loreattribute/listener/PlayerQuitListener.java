package org.sct.fv.loreattribute.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.sct.fv.loreattribute.dao.CacheDao;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        /*如果玩家存在在列表里，把他从缓存Map里干掉*/
        if(CacheDao.getPlayerAttribute(player.getName()) != null) {
            CacheDao.deletePlayerData(player);
        }
    }
}
