package org.sct.fv.loreattribute.listener;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.sct.fv.loreattribute.api.AttributeApi;
import org.sct.fv.loreattribute.dao.CacheDao;
import org.sct.fv.loreattribute.dto.Attribute;
import org.sct.fv.loreattribute.file.Message;
import org.sct.fv.loreattribute.util.BasicUtil;
import org.sct.fv.loreattribute.util.MethodsUtil;

/*
 目前实现的属性
 伤害
 防御
 暴击
 */
public class EntityDamageByEntityListner implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageByEntityEvent e) {

        if(e.getDamager() instanceof Player) {
            Player damager = (Player) e.getDamager();
            LivingEntity entity = (LivingEntity) e.getEntity();
            double damage = e.getDamage();

            Attribute DamagerAttribute = AttributeApi.calcutePlayerAttribute(damager);
            Attribute PlayerAttribute = AttributeApi.getEntityEquipMentAttribute(entity);
            //是否命中，没有命中就GG
            //  e.setCancelled(MethodsUtil.isHit(DamagerAttribute));
            //是否被闪避，闪避就取消Event
            if(MethodsUtil.isDodge(PlayerAttribute)) {
              //  e.setCancelled(true);
                damager.sendMessage(BasicUtil.getMessage(Message.getDodge()));
                entity.sendMessage(BasicUtil.getMessage(Message.getDodged()));
            }
            //总伤害= 攻击*暴击-防御，真伤,秒杀另算
            damage += (double) DamagerAttribute.getDamage() * (1.0 + (double) DamagerAttribute.getCritical()/100.0) - PlayerAttribute.getDefand();

            if(DamagerAttribute.getRealattack() >= 0 && e.getEntity() instanceof Player) {
                ((Player) e.getEntity()).setHealth(((Player) e.getEntity()).getHealth() - DamagerAttribute.getRealattack());
                damager.sendMessage(BasicUtil.getMessage(Message.getRealAttack()));
            }
            if(MethodsUtil.isSeckill(DamagerAttribute)) {
                entity.setHealth(0);
                damager.sendMessage(BasicUtil.getMessage(Message.getSecKill()));
            }
            entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, DamagerAttribute.getIcy(), 5));
            entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, DamagerAttribute.getPoison(), 5));
            entity.setFireTicks(DamagerAttribute.getFire() * 20);
            e.setDamage(damage);
        }

    }
}
