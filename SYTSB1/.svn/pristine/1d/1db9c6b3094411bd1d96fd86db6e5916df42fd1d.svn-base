package com.fwxm.outstorage.bean;

import com.fwxm.recipients.bean.Tjy2ChLq;
import com.fwxm.supplies.bean.GoodsReturn;

import java.util.HashMap;
import java.util.Map;

public enum Tjy2ChCkType {
    LQ("LQ", "领取出库", Tjy2ChLq.class), TH("TH", "退货出库", GoodsReturn.class);
    private String key;
    private String description;
    private Class clazz;
    private static Map<Class, String> clazzMap = new HashMap<Class, String>();

    Tjy2ChCkType(String key, String description, Class clazz) {
        this.key = key;
        this.description = description;
        this.clazz = clazz;
    }

    public String getKey() {
        return this.key;
    }

    public String getDescription() {
        return this.description;
    }

    public Class getClazz() {
        return this.clazz;
    }

    public static String getKey(Class clazz) {
        String key = null;
        for (Tjy2ChCkType type : Tjy2ChCkType.values()) {
            if (type.getClazz().equals(clazz)) {
                key = type.getKey();
                break;
            }
        }
        return key;
    }

}
