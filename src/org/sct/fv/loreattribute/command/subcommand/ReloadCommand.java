package org.sct.fv.loreattribute.command.subcommand;

import org.bukkit.command.CommandSender;
import org.sct.fv.loreattribute.util.FileUtil;
import org.sct.fv.loreattribute.util.SubCommandUtil;

public class ReloadCommand implements SubCommandUtil {

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if(args.length != 1) {
            sender.sendMessage("§c参数不正确");
            return true;
        }

        if(!sender.isOp()) {
            sender.sendMessage("§c权限不足");
            return true;
        }

        FileUtil.loadFile();
        sender.sendMessage("§a配置文件已重载");
        return false;
    }
}
