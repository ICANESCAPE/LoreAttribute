package org.sct.fv.loreattribute.service;

public interface ForgeService {
    void initData(String name);
    boolean hasData(String name);
    void addExp(String name, int amount);
    int getExp(String name);
}
