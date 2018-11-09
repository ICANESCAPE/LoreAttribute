package org.sct.fv.loreattribute.util;

import org.sct.fv.loreattribute.file.Drop;

import java.util.Map;

public class DropUtil {

    /**
     * 进行一次抽奖活动
     *
     * @param map 奖池
     * @return 具体抽到的装备
     */
    public static String luckyDraw(Map<String, Integer> map) {
        if(Math.random() >= Drop.getChance()) { return null; }

        int sum = 0;
        for (String key : map.keySet()) {
            sum += map.get(key);
        }

        int random = BasicUtil.getRandom(sum);

        sum = 0;
        for (String key : map.keySet()) {
            sum += map.get(key);
            if (sum >= random) return key;
            if(sum < random) return null;
        }
        return null;
    }

}
