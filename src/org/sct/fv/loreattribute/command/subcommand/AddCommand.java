package org.sct.fv.loreattribute.command.subcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sct.fv.loreattribute.api.ForgeApi;
import org.sct.fv.loreattribute.file.Message;
import org.sct.fv.loreattribute.util.BasicUtil;
import org.sct.fv.loreattribute.util.SubCommandUtil;

public class AddCommand implements SubCommandUtil {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(args.length != 3) {
            sender.sendMessage("§c参数不正确");
            return true;
        }
        if(!sender.isOp()) {
            sender.sendMessage("§c权限不足");
            return true;
        }
        /* /la add [player] [amount ]*/
        if(ForgeApi.hasData(args[2])) {
            ForgeApi.addExp(args[2], Integer.parseInt(args[3]));
            sender.sendMessage(BasicUtil.getMessage(Message.getAddPoint()));
        }
        return false;
    }
}
