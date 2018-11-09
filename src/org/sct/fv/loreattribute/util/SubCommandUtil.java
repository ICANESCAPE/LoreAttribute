package org.sct.fv.loreattribute.util;

import org.bukkit.command.CommandSender;

public interface SubCommandUtil {
    public abstract boolean execute(CommandSender sender, String[] args);
}
