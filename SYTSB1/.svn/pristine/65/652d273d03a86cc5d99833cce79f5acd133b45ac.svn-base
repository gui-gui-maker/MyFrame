package com.fwxm.material.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fwxm.material.bean.GoodsAndOrder;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

@Repository("goodsAndOrderDao")
public class GoodsAndOrderDao extends EntityDaoImpl<GoodsAndOrder>{
	
	public GoodsAndOrder getGoodsAndOrderByOrderIdAndGoodsId(String orderId,String goodsId){
		String hql=" from GoodsAndOrder where fk_order_id=? and fk_goods_id=?  and status!=?";
		GoodsAndOrder bean=(GoodsAndOrder)this.createQuery(hql, orderId,goodsId,"99").uniqueResult();
		return bean;
	}
	public List<GoodsAndOrder> getBeanByOrderId(String orderId){
		String hql=" from GoodsAndOrder where fk_order_id=? and status!='99'";
		List<GoodsAndOrder> list=this.createQuery(hql, orderId).list();
		return list;
	}
}
