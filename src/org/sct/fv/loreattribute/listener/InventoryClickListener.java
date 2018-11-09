package org.sct.fv.loreattribute.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import org.sct.core.util.ItemStackUtil;

import org.sct.fv.loreattribute.api.ForgeApi;
import org.sct.fv.loreattribute.file.Forge;
import org.sct.fv.loreattribute.file.Message;
import org.sct.fv.loreattribute.file.Rune;
import org.sct.fv.loreattribute.util.BasicUtil;
import org.sct.fv.loreattribute.util.ForgeUtil;
import org.sct.fv.loreattribute.util.RuneUtil;

import java.util.*;

public class InventoryClickListener implements Listener {

    private static Map<Player, Integer> playerMap = new HashMap<>();

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack weapon;
        ItemStack tool;
        int slot = e.getSlot();

        if (e.getWhoClicked() instanceof Player) {


            if (e.getRawSlot() < 54) {
                if ((e.isShiftClick()) || (e.getHotbarButton() != -1)) {
                    e.setCancelled(true);
                    player.sendMessage("§c用鼠标操作不要用键盘操作哟。");
                    return;
                }
            }
            if (e.getInventory().getName().equals("§d§l符文镶嵌") || e.getInventory().getName().equals("§d§l武器打孔")) {
                ItemStack current = e.getCurrentItem();
                if (current.getType().equals(Material.STAINED_GLASS_PANE)) {
                    e.setCancelled(true);
                    return;
                }
            }

            if (e.getInventory().getName().equals("§d§l符文镶嵌")) {
                if (slot == 22) {
                    e.setCancelled(true);
                    //10,13,16
                    weapon = e.getInventory().getItem(10);
                    tool = e.getInventory().getItem(13);
                    if (weapon == null || tool == null) {
                        player.sendMessage("§c不能为空");
                    }

                    if (weapon.hasItemMeta() && weapon.getItemMeta().getLore().contains(Rune.getPunchedLore())) {
                        if (RuneUtil.getRuneLore(tool) != null) {
                            e.getInventory().setItem(16, ItemStackUtil.replaceLore(weapon,
                                    Rune.getPunchedLore(),
                                    Rune.getDataMap().get(RuneUtil.getRuneLore(weapon))));
                            player.sendMessage(BasicUtil.getMessage(Message.getRuneSuccess()
                                    .replace("%item%", weapon.getItemMeta().getDisplayName())
                                    .replace("%rune%", tool.getItemMeta().getDisplayName())));
                            e.getInventory().setItem(10, null);
                            tool.setAmount(tool.getAmount() - 1);
                            e.getInventory().setItem(13, tool);
                        } else {
                            player.sendMessage(BasicUtil.getMessage(Message.getNotRune()));
                        }
                    } else {
                        player.sendMessage(BasicUtil.getMessage(Message.getNoPunchedLore()));
                    }
                }
            }

            if (e.getInventory().getName().equals("§d§l武器打孔")) {
                if (slot == 22) {
                    e.setCancelled(true);
                    weapon = e.getInventory().getItem(11);
                    if (weapon == null || weapon.getType().equals(Material.AIR)) {
                        player.sendMessage("§c不能为空");
                    }

                    if (!weapon.getItemMeta().getLore().contains(BasicUtil.getMessage(Message.getPunchTitle()))) {
                        ItemStackUtil.addLore(weapon, BasicUtil.getMessage(Message.getPunchTitle()));
                        ItemStackUtil.addLore(weapon, BasicUtil.getMessage(Rune.getPunchedLore()));
                        player.sendMessage(BasicUtil.getMessage(Message.getPunchSuccess()
                                .replace("%item%", weapon.getItemMeta().getDisplayName())));
                    } else {
                        ItemStackUtil.addLore(weapon, BasicUtil.getMessage(Rune.getPunchedLore()));
                    }
                }
            }

            if (e.getInventory().getName().equals("§3§l锻造台")) {
                if (Forge.getForgeData(slot) != null) {
                    e.setCancelled(true);
                    if (ForgeApi.getExp(player.getName()) < Forge.getForgeData(slot).getExp()) {
                        player.sendMessage(BasicUtil.getMessage(Message.getForgeExpNotEnough())
                                .replace("%require%", "" + Forge.getForgeData(slot).getExp())
                                .replace("%old%", "" + ForgeApi.getExp(player.getName()))
                        );
                        player.closeInventory();
                    } else {
                        playerMap.put(player, e.getSlot());
                        player.sendMessage("测试player" + player.getName());
                        player.sendMessage("测试slot" + e.getSlot());
                        player.closeInventory();
                        ForgeUtil.openForgeInv(player);
                    }
                }
            } else if (e.getInventory().getName().contains("锻造台 - 放入锻造材料")) {
                if (slot == 35) {
                    player.sendMessage("事件触发了");
                    e.setCancelled(true);
                    player.sendMessage("测试Map  " + playerMap);
                    player.sendMessage("测试slot " + playerMap.get(player));
                    List<String> required = Forge.getForgeData(playerMap.get(player)).getRequire();
                   // List<String> required = Forge.getForgeData(playerMap.get(player)).getRequire();
                    player.sendMessage("测试require  " + required.get(0));
                    List<String> putList = new ArrayList<>();
                    for (int i = 0; i < e.getInventory().getSize() - 1; i++) {
                        if (e.getInventory().getItem(i) == null || e.getInventory().getItem(i).getType().equals(Material.AIR)) {
                            //do nothing
                        } else {
                            putList.add(e.getInventory().getItem(i).getItemMeta().getDisplayName());
                        }
                    }
                    player.sendMessage("测试putlist  " + putList);
                    Collections.sort(required);
                    Collections.sort(putList);
                    if (required.size() != putList.size()) {
                        player.sendMessage("测试size " + required.size());
                        player.sendMessage("测试putlistsize" + putList.size());
                        player.sendMessage(BasicUtil.getMessage(Message.getNotCorrectMaterial()));
                    } else {
                        boolean flag = true;
                        for (int index = 0; index < required.size(); index++) {
                            if (BasicUtil.removeColor(required.get(index)).equals(BasicUtil.removeColor(putList.get(index)))) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            e.getInventory().clear();
                            player.closeInventory();
                            BasicUtil.sentReward(Forge.getForgeData(playerMap.get(player)).getReward(), player);
                            player.sendMessage(BasicUtil.getMessage(Message.getForgeSuccess()));
                            if (ForgeApi.getExp(player.getName()) >= Forge.getMaxExp()) {
                                player.sendMessage(BasicUtil.getMessage(Message.getForgeExpMax()));
                            } else {
                                ForgeApi.addExp(player.getName(), Forge.getForgeData(playerMap.get(player)).getAdd());
                                player.sendMessage(BasicUtil.getMessage(Message.getForgeExpAdd())
                                        .replace("%amount%",
                                                String.valueOf(Forge.getForgeData(playerMap.get(player)).getAdd())));
                            }
                        } else {
                            player.sendMessage(BasicUtil.getMessage(Message.getNotCorrectMaterial()));
                        }
                    }
                }

            }
        }
    }
}
