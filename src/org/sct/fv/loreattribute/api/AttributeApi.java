package org.sct.fv.loreattribute.api;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.sct.fv.loreattribute.dto.Attribute;
import org.sct.fv.loreattribute.dto.AttributeType;
import org.sct.fv.loreattribute.util.AttributeUtil;
import org.sct.fv.loreattribute.util.MethodsUtil;

import java.util.ArrayList;
import java.util.List;

public class AttributeApi {

    /**
     * 通过解析Lore来获取属性列表
     *
     * @param item 单个物品
     * @return Attribute类
     */
    public static Attribute getAttributeFromItem(ItemStack item) {
        Attribute attribute = new Attribute(null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 0, 0, 0);
        if (item != null && item.hasItemMeta() && item.getItemMeta().hasLore()) {
            //获取物品的属性枚举列表
            List<String> lores = item.getItemMeta().getLore();
            for (int i = 0; i < lores.size(); i++) {
                AttributeType type = AttributeUtil.getAttributeType(lores.get(i));
                if (type == null) {
                    return attribute;
                }
                switch (type) {
                    case DAMAGE:
                        attribute.setDamage(attribute.getDamage() + MethodsUtil.getInt(lores.get(i)));
                        break;
                    case EXP:
                        attribute.setExp(attribute.getExp() + MethodsUtil.getInt(lores.get(i)));
                        break;
                    case DODGE:
                        attribute.setDodge(attribute.getDodge() + MethodsUtil.getInt(lores.get(i)));
                        break;
                    case FLAME:
                        attribute.setFlame(attribute.getFlame() + MethodsUtil.getInt(lores.get(i)));
                        break;
                    case DEFAND:
                        attribute.setDefand(attribute.getDefand() + MethodsUtil.getInt(lores.get(i)));
                        break;
                    case POSION:
                        attribute.setPoison(attribute.getPoison() + MethodsUtil.getInt(lores.get(i)));
                        break;
                    case SECKILL:
                        attribute.setSeckill(attribute.getSeckill() + MethodsUtil.getInt(lores.get(i)));
                        break;
                    case CRITICAL:
                        attribute.setCritical(MethodsUtil.getInt(lores.get(i)));
                        break;
                    case REALDAMAGE:
                        attribute.setRealattack(attribute.getRealattack() + MethodsUtil.getInt(lores.get(i)));
                        break;
                    case UNBREAK:
                        attribute.setUnbreak(true);
                        break;
                    case DURABILITY:
                        attribute.setDurability(attribute.getDurability() + MethodsUtil.getInt(lores.get(i)));
                        break;
                    case ICY:
                        attribute.setIcy(attribute.getIcy() + MethodsUtil.getInt(lores.get(i)));
                        break;
                    case VALUE:
                        attribute.setValue(attribute.getValue() + MethodsUtil.getInt(lores.get(i)));
                        break;
                }
            }
        }
        return attribute;
    }

    /**
     * 取玩家装备的Attribute
     *
     * @param entity 被攻击的实体
     * @return Attribute
     */
    public static Attribute getEntityEquipMentAttribute(LivingEntity entity) {
        Attribute attribute = new Attribute(null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 0, 0, 0);
        List<ItemStack> itemList = new ArrayList<>();
        if (entity == null) {
            return attribute;
        }
        for (int i = 0; i < entity.getEquipment().getArmorContents().length; i++) {
            itemList.add(entity.getEquipment().getArmorContents()[i]);
        }
        for (int i = 0; i < itemList.size(); i++) {
            attribute = calculate(attribute, getAttributeFromItem(itemList.get(i)));
        }
        return attribute;
    }

    /**
     * 计算装备属性
     *
     * @param attributes
     * @return 计算后的属性对象
     */
    public static Attribute calculate(Attribute... attributes) {
        Attribute attribute = new Attribute(null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 0, 0, 0);
        for (Attribute att : attributes) {
            attribute.setRealattack(attribute.getRealattack() + att.getRealattack());
            attribute.setCritical(attribute.getCritical() + att.getCritical());
            attribute.setLuck(attribute.getLuck() + att.getLuck());
            attribute.setDodge(attribute.getDodge() + att.getDodge());
            attribute.setDamage(attribute.getDamage() + att.getDamage());
            attribute.setDefand(attribute.getDefand() + att.getDefand());
            attribute.setHit(attribute.getHit() + att.getHit());
        }
        return attribute;
    }

    /**
     * 计算玩家一身的装备属性
     *
     * @param player
     * @return 计算后的属性
     */
    public static Attribute calcutePlayerAttribute(Player player) {
        Attribute attribute = new Attribute(null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 0, 0, 0);
        List<ItemStack> itemList = new ArrayList<>();
        for (int i = 0; i < player.getEquipment().getArmorContents().length; i++) {
            itemList.add(player.getEquipment().getArmorContents()[i]);
        }
        for (int i = 0; i < itemList.size(); i++) {
            attribute = calculate(attribute, getAttributeFromItem(itemList.get(i)));
        }
        if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().hasItemMeta() && player.getInventory().getItemInHand().getItemMeta().hasLore()) {
            attribute = calculate(attribute, getAttributeFromItem(player.getInventory().getItemInHand()));
        }
        return attribute;
    }


}
