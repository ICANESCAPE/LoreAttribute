package org.sct.fv.loreattribute.dao;

import org.sct.core.Core;
import org.sct.core.mysql.Util;
import org.sct.fv.loreattribute.LoreAttribute;
import org.sct.fv.loreattribute.file.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ForgeDao {

    public static boolean hasData(String name) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT Name FROM drawing WHERE name = '" + name + "'";

        try {
            connection = BasicDao.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return false;
    }

    public static void initData(String name) {
        Connection connection = null;
        PreparedStatement ps = null;
        int id = 0;
        //id,name,exp
        String sql = "INSERT INTO drawing VALUES(0, '" + name + "', 0)";

        try {
            connection = BasicDao.getConnection();
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
    }

    public static void addExp(String name, int amount) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE drawing SET exp = exp + " + amount + " WHERE name = '" + name + "'";
        try {
            conn = BasicDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            Core.info(Message.getMonitForgeExp()
                    .replace("%player%", name)
                    .replace("%amount%", String.valueOf(amount)));
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
    }

    public static int getExp(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        String sql = "SELECT exp FROM drawing WHERE name = '" + name + "'";
        try {
            conn = BasicDao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if(rs.next()){
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return result;
    }


}
