package com.extendedclip.papi.expansion.forge;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.sct.fv.loreattribute.api.ForgeApi;

public class ForgeVariable extends PlaceholderExpansion {

    public boolean canRegister() {
        return Bukkit.getPluginManager().getPlugin(getPlugin()) != null;
    }

    public String getAuthor() {
        return "alchemy";
    }

    public String getIdentifier() {
        return "Forge";
    }

    public String getPlugin() {
        return "LoreAttribute";
    }

    public String getVersion() {
        return "1.0";
    }

    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }
        if (identifier.equals("exp")) {
            return String.valueOf(ForgeApi.getExp(player.getName()));
        }
        return null;
    }
}
