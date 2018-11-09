package org.sct.fv.loreattribute.dto;

import java.util.List;

public class ForgeData {

    String GuiName;
    String item;
    int exp;
    int add;
    int slot;
    List<String> require;
    List<String> reward;

    public ForgeData(String guiName, String item, int exp, int add, int slot, List<String> require, List<String> reward) {
        this.GuiName = guiName;
        this.item = item;
        this.exp = exp;
        this.add = add;
        this.slot = slot;
        this.require = require;
        this.reward = reward;
    }

    public String getGuiName() { return GuiName; }

    public void setGuiName(String guiName) {
        GuiName = guiName;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getExp() {
        return exp;
    }

    public int getSlot() {
        return slot;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getAdd() { return add; }

    public void setAdd(int add) {
        this.add = add;
    }

    public List<String> getRequire() { return require; }

    public void setRequire(List<String> require) {
        this.require = require;
    }

    public List<String> getReward() {
        return reward;
    }

    public void setReward(List<String> reward) {
        this.reward = reward;
    }

}
