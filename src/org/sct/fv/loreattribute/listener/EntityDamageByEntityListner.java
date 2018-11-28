package org.sct.fv.loreattribute.listener;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.sct.fv.loreattribute.api.AttributeApi;
import org.sct.fv.loreattribute.dto.Attribute;
import org.sct.fv.loreattribute.dto.ProjectileAttribute;
import org.sct.fv.loreattribute.file.Message;
import org.sct.fv.loreattribute.util.BasicUtil;
import org.sct.fv.loreattribute.util.MethodsUtil;

public class EntityDamageByEntityListner implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageByEntityEvent e) {

        Player damager = null;
        LivingEntity entity = entity = (e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof ArmorStand)) ? (LivingEntity) e.getEntity() : null;;
        Attribute damagerAttribute = null;
        double damage= e.getDamage();

        if (e.getDamager() instanceof Player) {
            damager = (Player) e.getDamager();
            damagerAttribute = AttributeApi.calcutePlayerAttribute(damager);
        } else if (e.getDamager() instanceof Projectile && ((Projectile) e.getDamager()).getShooter() instanceof Player) {
            damager = (Player) ((Projectile) e.getDamager()).getShooter();
            damagerAttribute = AttributeApi.calcutePlayerAttribute(damager);
        }

        Attribute DamagerAttribute = AttributeApi.calculate(AttributeApi.calcutePlayerAttribute(damager), ProjectileAttribute.getMap().get(damager));
        Attribute PlayerAttribute = AttributeApi.getEntityEquipMentAttribute(entity);

        //总伤害= 攻击*暴击-防御，真伤,秒杀另算
        damage += (double) DamagerAttribute.getDamage() * (1.0 + (double) DamagerAttribute.getCritical() / 100.0) - PlayerAttribute.getDefand();

        if (MethodsUtil.isDodge(PlayerAttribute)) {
            //  e.setCancelled(true);
            damager.sendMessage(BasicUtil.getMessage(Message.getDodge()));
            entity.sendMessage(BasicUtil.getMessage(Message.getDodged()));
        }

        if (DamagerAttribute.getRealattack() >= 0 && e.getEntity() instanceof Player) {
            ((Player) e.getEntity()).setHealth(((Player) e.getEntity()).getHealth() - DamagerAttribute.getRealattack());
            damager.sendMessage(BasicUtil.getMessage(Message.getRealAttack()));
        }
        if (MethodsUtil.isSeckill(DamagerAttribute)) {
            entity.setHealth(0);
            damager.sendMessage(BasicUtil.getMessage(Message.getSecKill()));
        }
        if (entity != null) {
            entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, DamagerAttribute.getIcy(), 5));
            entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, DamagerAttribute.getPoison(), 5));
            entity.setFireTicks(DamagerAttribute.getFire() * 20);
        }
        e.setDamage(damage);
    }
}
