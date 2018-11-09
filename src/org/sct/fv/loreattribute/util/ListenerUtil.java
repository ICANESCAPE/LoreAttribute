package org.sct.fv.loreattribute.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.sct.fv.loreattribute.LoreAttribute;
import org.sct.fv.loreattribute.listener.*;

public class ListenerUtil {

    private ListenerUtil(){
        throw new NullPointerException("");
    }

    public static void register(){
        register(new EntityDamageByEntityListner());
        register(new PlayerJoinListener());
        register(new PlayerQuitListener());
        register(new ItemDamageListener());
        register(new BlockBreakListener());
        register(new InventoryCloseListener());
        register(new InventoryClickListener());
        register(new EntityDeathListener());
    }

    private static void register(Listener listener){
        LoreAttribute.getInstance().getServer().getPluginManager().registerEvents(listener, LoreAttribute.getInstance());
    }

}
