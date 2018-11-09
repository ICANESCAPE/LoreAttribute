package org.sct.fv.loreattribute.command.subcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sct.fv.loreattribute.util.ForgeUtil;
import org.sct.fv.loreattribute.util.RuneUtil;
import org.sct.fv.loreattribute.util.SubCommandUtil;

public class OpenGuiCommand implements SubCommandUtil {

    public boolean execute(CommandSender sender, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;
            if(!(sender instanceof Player)) {
                sender.sendMessage("§c必须是玩家才能使用这个命令");
            }
            if(args[2].equalsIgnoreCase("punch")) {
                RuneUtil.openPunchInventory(player);
            }
            if(args[2].equalsIgnoreCase("inlay")) {
                RuneUtil.openInlayInventory(player);
            }
            if(args[1].equalsIgnoreCase("forge")) {
                ForgeUtil.openSelectInv(player);
            } else {
                player.sendMessage("§f/la open punch §a-> §6打开打孔GUI");
                player.sendMessage("§f/la open inlay §a-> §6打开镶嵌GUI");
                player.sendMessage("§f/la open forge §a-> §6打开锻造GUI");
            }
        }
        return false;
    }

}
