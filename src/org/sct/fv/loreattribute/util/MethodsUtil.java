package org.sct.fv.loreattribute.util;

import org.sct.fv.loreattribute.dto.Attribute;

import java.util.Random;

public class MethodsUtil {

    /**
     * 取数值
     * @param lore
     * @return
     */
    public static double getDouble(String lore) {
        return Double.parseDouble(lore.replaceAll("[^0-9.+-]", ""));
    }

    /**
     * 取数值
     * @param lore 传入的物品Lore
     * @return 获取的数值
     */
    public static int getInt(String lore) {
        return Integer.parseInt(lore.replaceAll("[^0-9.+-]", ""));
    }

    /**
     * 是否命中
     * @param attribute 属性对象
     * @return 命中[false]/不命中[true]
     */
    public static boolean isHit(Attribute attribute) {
        Random random = new Random();
        int percent = random.nextInt(100);
        return (double)percent < attribute.getHit() ? false : true;
    }

    /**
     * 是否被闪避
     * @param attribute 属性对象
     * @return 闪避[true]/没闪避[false]
     */
    public static boolean isDodge(Attribute attribute) {
        Random random = new Random();
        int percent = random.nextInt(100);
        return (double)percent < attribute.getDodge() ? true : false;
    }

    /**
     * 是否秒杀
     * @param attribute 属性对象
     * @return 秒杀[true]/没有中奖[false]
     */
    public static boolean isSeckill(Attribute attribute) {
        Random random = new Random();
        int percent = random.nextInt(100);
        return (double)percent < attribute.getSeckill() ? true : false;
    }

    public static void main(String args) {
        System.out.println(getInt("暴击+10%"));
    }
}
