package org.sct.fv.loreattribute.dto;

import org.bukkit.inventory.ItemStack;
import org.sct.fv.loreattribute.util.BasicUtil;

import java.util.List;

public class Attribute {

    /**
     * 属性列表
     * name 玩家名字
     * damage 伤害
     * defand 防御
     * luck 幸运值
     * hit 命中
     * dodge 闪避
     * critical 暴击
     * realattack 真实伤害
     * exp 经验加成
     * poison 中毒
     * flame 燃烧
     * seckill 秒杀
     * durability 耐久
     * unbreak 无限耐久
     * icy 冰冻攻击
     */
    String name;
    int damage;
    int defand;
    int luck;
    int hit;
    int dodge;
    int critical;
    int realattack;
    int exp;
    int flame;
    int poison;
    int seckill;
    int durability;
    boolean unbreak;
    int icy;
    int value;
    int fire;

    /**
     * 构造方法
     * @param name       玩家名字
     * @param damage     伤害
     * @param defand     防御
     * @param luck       幸运值
     * @param hit        命中
     * @param dodge      闪避
     * @param critical   暴击
     * @param realattack 真实伤害
     * @param durability 耐久
     * @param unbreak 无限耐久
     * @parm icy 冰冻
     * @parm value 战斗力
     *
     */
    public Attribute(String name, int damage, int defand, int luck, int hit, int dodge, int critical, int realattack, int exp, int flame, int poison, int seckill, int durability, boolean unbreak, int icy, int value, int fire) {
        this.name = name;
        this.damage = damage;
        this.defand = defand;
        this.luck = luck;
        this.hit = hit;
        this.dodge = dodge;
        this.critical = critical;
        this.realattack = realattack;
        this.exp = exp;
        this.flame = flame;
        this.poison = poison;
        this.seckill = seckill;
        this.durability = durability;
        this.unbreak = unbreak;
        this.icy = icy;
        this.value = value;
        this.fire = fire;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefand() {
        return defand;
    }

    public void setDefand(int defand) {
        this.defand = defand;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getDodge() {
        return dodge;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getRealattack() {
        return realattack;
    }

    public void setRealattack(int realattack) {
        this.realattack = realattack;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getFlame() {
        return flame;
    }

    public void setFlame(int flame) {
        this.flame = flame;
    }

    public int getPoison() {
        return poison;
    }

    public void setPoison(int poison) {
        this.poison = poison;
    }

    public int getSeckill() {
        return seckill;
    }

    public void setSeckill(int seckill) {
        this.seckill = seckill;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public boolean isUnbreak() {
        return unbreak;
    }

    public void setUnbreak(boolean unbreak) {
        this.unbreak = unbreak;
    }

    public int getIcy() {
        return icy;
    }

    public void setIcy(int icy) {
        this.icy = icy;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getFire() {
        return fire;
    }

    public void setFire(int fire) {
        this.fire = fire;
    }
}
