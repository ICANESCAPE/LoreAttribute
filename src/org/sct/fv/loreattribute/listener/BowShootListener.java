package org.sct.fv.loreattribute.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import org.sct.fv.loreattribute.dto.ProjectileAttribute;


public class BowShootListener implements Listener {

    @EventHandler
    public void onShoot(EntityShootBowEvent e) {
        if(e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            ItemStack bow = e.getBow();
            if(bow != null && !bow.getItemMeta().equals(Material.AIR)) {
                if(bow.getItemMeta().hasLore()) {
             //       ProjectileAttribute.recordData(player,  );
                }
            }
        }
    }
}
