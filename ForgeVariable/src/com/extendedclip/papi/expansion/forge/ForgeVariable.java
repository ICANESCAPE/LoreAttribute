package com.extendedclip.papi.expansion.forge;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.sct.fv.loreattribute.api.ForgeApi;

public class ForgeVariable extends PlaceholderExpansion {

    @Override
    public boolean canRegister() {
        return Bukkit.getPluginManager().getPlugin(getPlugin()) != null;
    }

    @Override
    public String getAuthor() {
        return "alchemy";
    }

    @Override
    public String getIdentifier() {
        return "Forge";
    }

    @Override
    public String getPlugin() {
        return "LoreAttribute";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }
        if ("exp".equals(identifier)) {
            return String.valueOf(ForgeApi.getExp(player.getName()));
        }
        return null;
    }
}
