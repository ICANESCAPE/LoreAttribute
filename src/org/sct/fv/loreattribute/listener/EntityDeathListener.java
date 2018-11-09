package org.sct.fv.loreattribute.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.sct.fv.loreattribute.api.AttributeApi;
import org.sct.fv.loreattribute.dto.Attribute;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if(e.getEntity().getKiller() instanceof Player) {
            Player killer = e.getEntity().getKiller();
            Attribute attribute = AttributeApi.calcutePlayerAttribute(killer);
            double exp = e.getDroppedExp() * (1 + attribute.getExp()/100);
            int newexp = (int) exp;
            e.setDroppedExp(newexp);
        }
    }
}
