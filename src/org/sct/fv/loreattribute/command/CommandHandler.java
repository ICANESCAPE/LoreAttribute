package org.sct.fv.loreattribute.command;

import com.google.common.collect.Maps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.sct.fv.loreattribute.LoreAttribute;
import org.sct.fv.loreattribute.command.subcommand.AddCommand;
import org.sct.fv.loreattribute.command.subcommand.CheckCommand;
import org.sct.fv.loreattribute.command.subcommand.OpenGuiCommand;
import org.sct.fv.loreattribute.command.subcommand.ReloadCommand;
import org.sct.fv.loreattribute.util.SubCommandUtil;

import java.util.Map;

public class CommandHandler implements CommandExecutor  {

    private Map<String, SubCommandUtil> subCommandMap = Maps.newHashMap();

    public CommandHandler() {
        registerSubCommand("open", new OpenGuiCommand());
        registerSubCommand("reload", new ReloadCommand());
        registerSubCommand("check", new CheckCommand());
        registerSubCommand("add", new AddCommand());
    }

    public void registerSubCommand(String commandName, SubCommandUtil command) {
        if (subCommandMap.containsKey(commandName)) {
            LoreAttribute.getInstance().getLogger().warning("发现重复子命令注册!");
        }
        subCommandMap.put(commandName, command);
    }

    public void unregisterSubCommand(String commandName) {
        subCommandMap.remove(commandName);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("la")) {
            if(args.length == 0) {
                if(!(sender instanceof Player)) {
                    subCommandMap.get("admin").execute(sender, args);
                    return true;
                }
                return true;
            }

            SubCommandUtil subCommand = subCommandMap.get(args[0]);
            if (subCommand == null) {
                sender.sendMessage("§c未知指令!");
                return true;
            }
            subCommand.execute(sender, args);
        }
        return false;
    }

}
