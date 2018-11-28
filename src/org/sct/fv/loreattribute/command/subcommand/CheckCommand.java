package org.sct.fv.loreattribute.command.subcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sct.fv.loreattribute.api.AttributeApi;
import org.sct.fv.loreattribute.dto.Attribute;
import org.sct.fv.loreattribute.file.Config;
import org.sct.fv.loreattribute.file.Message;
import org.sct.fv.loreattribute.util.BasicUtil;
import org.sct.fv.loreattribute.util.SubCommandUtil;

public class CheckCommand implements SubCommandUtil {

    public boolean execute(CommandSender sender, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("§c必须是玩家才能使用这个命令");
        }

        Player player = (Player) sender;
        Attribute attribute = AttributeApi.calcutePlayerAttribute(player);
        if (attribute != null) {
            player.sendMessage(BasicUtil.getMessage(Message.getCheckAttr()));
            player.sendMessage(BasicUtil.getMessage(Config.getCritical()) + attribute.getCritical());
            player.sendMessage(BasicUtil.getMessage(Config.getDamage()) + attribute.getDamage());
            player.sendMessage(BasicUtil.getMessage(Config.getDefand()) + attribute.getDefand());
            player.sendMessage(BasicUtil.getMessage(Config.getDodge()) + attribute.getDodge());
            player.sendMessage(BasicUtil.getMessage(Config.getExp()) + attribute.getExp());
            player.sendMessage(BasicUtil.getMessage(Config.getFire()) + attribute.getFire());
            player.sendMessage(BasicUtil.getMessage(Config.getHit()) + attribute.getHit());
            player.sendMessage(BasicUtil.getMessage(Config.getRealAttack()) + attribute.getRealattack());
            player.sendMessage(BasicUtil.getMessage(Config.getIcy()) + attribute.getIcy());
            player.sendMessage(BasicUtil.getMessage(Config.getSeckill()) + attribute.getSeckill());
            player.sendMessage(BasicUtil.getMessage(Config.getPosion()) + attribute.getPoison());
            player.sendMessage(BasicUtil.getMessage(Config.getValue()) + attribute.getValue());
        }
        return false;
    }
}
