package org.sct.fv.loreattribute.dto;

import org.sct.fv.loreattribute.file.Config;

public enum AttributeType {

    /**
     * 攻击属性的枚举类型
     * true[百分比计算]/false[整型计算]
     * DAMAGE 伤害
     * DEFAND 防御
     * CRITICAL 暴击
     * REALDAMAGE 真伤
     * FLAME 火焰伤害
     * EXP 经验增加
     * SECKILL 秒杀
     * POSION 中毒
     * HIT 命中
     * LUCK 幸运
     * DURABILITY 耐久
     * UNBREAK 无限耐久
     * ICY 冰冻
     * FIRE 燃烧
     */

    DAMAGE(Config.getDamage(), false),
    DEFAND(Config.getDefand() ,false),
    REALDAMAGE(Config.getRealAttack(), false),
    FLAME(Config.getFlame(), false),
    EXP(Config.getExp(), false),
    VALUE(Config.getValue(), false),
   // LUCK(Config.getLuck(), false),
    DURABILITY(Config.getDurability(), false),
//    UNBREAK(Config.getUnbreak(), false),
    UNBREAK("无法破坏", false),
    DODGE(Config.getDodge(), true),
    CRITICAL(Config.getCritical(), true),
    POSION(Config.getPosion(), true),
    SECKILL(Config.getSeckill(), true),
    HIT(Config.getHit(), true),
    ICY(Config.getIcy(), true),
    FIRE(Config.getFire(),true);



    String name;
    boolean percent;

    /**
     * 构造函数
     * @param name 属性名字
     * @param percent 是否是百分比伤害
     */
    AttributeType(String name, boolean percent) {
        this.name = name;
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public boolean isPercent() {
        return percent;
    }

}
