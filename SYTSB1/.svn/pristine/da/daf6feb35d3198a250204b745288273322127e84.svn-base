package com.fwxm.material.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.material.bean.GoodsAndOrder;
import com.fwxm.material.dao.GoodsAndOrderDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;

@Service("goodsAndOrderManager")
public class GoodsAndOrderManager extends EntityManageImpl<GoodsAndOrder, GoodsAndOrderDao> {
	 @Autowired
	 GoodsAndOrderDao goodsAndOrderDao;
	 
	 public void updateGoodsAndOrderByOrderIdAndGoodsIs(String orderId,String goodsId,Float sl){
		 GoodsAndOrder entity= goodsAndOrderDao.getGoodsAndOrderByOrderIdAndGoodsId(orderId, goodsId);
		 if(entity!=null){
			 entity.setSl(sl);
			 goodsAndOrderDao.save(entity);
		 }
	 }
}
