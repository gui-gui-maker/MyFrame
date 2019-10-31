package com.fwxm.outstorage.inf;

import com.fwxm.material.bean.GoodsAndOrder;
import net.sf.json.JSONObject;

import java.util.List;

public interface OutStorageInf<T> {
    /**
     * 单号ID
     *
     * @param id
     */
    void startOutStorage(String id);

    /**
     * 单号ID和出库数量
     *
     * @param id
     * @param orders
     * @param param  key 是 OutStorageParam的值
     */
    void finishOutStorage(String id, List<GoodsAndOrder> orders, JSONObject param);

    /**
     * 单号ID
     *
     * @param id
     * @return
     */
    List<GoodsAndOrder> getGoodsAndOrder(String id);

    String getOrderNumber(String id);

    /**
     * 删除出库单的相应操作
     *
     * @param id
     */
    void deleteOutStorage(String id) throws Exception;

    boolean canOutStorage(String id);
}
