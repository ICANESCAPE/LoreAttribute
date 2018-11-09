package org.sct.fv.loreattribute.service;

import org.sct.fv.loreattribute.dao.ForgeDao;

public class ForgeServiceImpl implements ForgeService {

    @Override
    public void initData(String name) {
        if (name == null || name.equalsIgnoreCase("")) {
            return;
        }
        if (!hasData(name)) {
            ForgeDao.initData(name);
        }
    }

    @Override
    public boolean hasData(String name) {
        if (name == null || name.equalsIgnoreCase("")) {
            return false;
        }
        if (ForgeDao.hasData(name)) {
            return true;
        }
        return false;
    }

    @Override
    public void addExp(String name, int amount) {
        if (name == null || name.equalsIgnoreCase("")) {
            return;
        }
        if (!hasData(name)) {
            ForgeDao.initData(name);
            return;
        }
        ForgeDao.addExp(name, amount);
    }

    @Override
    public int getExp(String name) {
        if (name == null || name.equalsIgnoreCase("")) {
            return 0;
        }
        if (!hasData(name)) {
            ForgeDao.initData(name);
            return 0;
        }
        return ForgeDao.getExp(name);
    }
}
