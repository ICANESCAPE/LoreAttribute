package org.sct.fv.loreattribute;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import org.sct.core.Core;
import org.sct.fv.loreattribute.command.CommandHandler;
import org.sct.fv.loreattribute.dao.BasicDao;
import org.sct.fv.loreattribute.file.Config;
import org.sct.fv.loreattribute.file.Message;
import org.sct.fv.loreattribute.util.BasicUtil;
import org.sct.fv.loreattribute.util.FileUtil;
import org.sct.fv.loreattribute.util.ListenerUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LoreAttribute extends JavaPlugin {

    private static LoreAttribute Instance;
    private static Connection connection;

    @Override
    public void onEnable() {
        Instance = this;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String host = getConfig().getString("Mysql.Host");
            String port = getConfig().getString("Mysql.Port");
            String dbname = getConfig().getString("Mysql.DataBase");
            String username = getConfig().getString("Mysql.User");
            String password = getConfig().getString("Mysql.PassWord");
            String tablename = getConfig().getString("Mysql.Table");
            String ip = "jdbc:mysql://" + host + ":" + port + "/" + dbname + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
            String sql = "CREATE TABLE  IF NOT EXISTS `" + tablename +"` (\n" +
                    "  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` text,\n" +
                    "  `exp` int(11) DEFAULT '0',\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;";
            connection = DriverManager.getConnection(ip, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            BasicDao.setConnection(connection);
            Bukkit.getServer().getConsoleSender().sendMessage("§f[&cLoreAttribute&f] &6>> &a数据库连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ListenerUtil.register();
        FileUtil.loadFile();
        Bukkit.getPluginCommand("la").setExecutor(new CommandHandler());
        Core.info(BasicUtil.getMessage("&f[&cLoreAttribute&f] &6>> &a加载成功"));
        Core.info(BasicUtil.getMessage("&f来自小组: Server CT"));
    }

    @Override
    public void onDisable() {

       /* if (!BasicDao.isClose()) {
            BasicDao.close();
        }*/
        Core.info(BasicUtil.getMessage("&c插件卸载完成，感谢使用SCT的插件"));
    }

    public static LoreAttribute getInstance() { return Instance; }

}
