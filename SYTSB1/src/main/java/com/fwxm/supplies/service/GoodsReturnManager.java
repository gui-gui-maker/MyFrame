package com.fwxm.supplies.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fwxm.material.bean.Goods;
import com.fwxm.material.bean.GoodsAndOrder;
import com.fwxm.material.dao.GoodsAndOrderDao;
import com.fwxm.material.dao.GoodsDao;
import com.fwxm.outstorage.inf.OutStorageExtends;
import com.fwxm.supplies.bean.GoodsReturn;
import com.fwxm.supplies.bean.GoodsReturnState;
import com.fwxm.supplies.dao.GoodsReturnDao;
import com.fwxm.supplies.dao.WarehousingDao;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;

@Service("goodsReturnManager")
public class GoodsReturnManager extends OutStorageExtends<GoodsReturn, GoodsReturnDao>  {
	@Autowired
	GoodsReturnDao goodsReturnDao; 
	@Autowired
	GoodsAndOrderDao goodsAndOrderDao;
	@Autowired
	GoodsDao goodsDao;
	@Autowired
	WarehousingDao warehousingDao; 
	
	public HashMap<String, Object> saveGoodsReturn(CurrentSessionUser curUser,String goodsReturn){	
		HashMap<String,Object> map=new HashMap<String,Object>();

		JSONObject json= JSON.parseObject(goodsReturn);
		GoodsReturn entity=JSON.parseObject(goodsReturn, GoodsReturn.class);
		boolean save=StringUtil.isEmpty(entity.getId());//是否新增
		if(save){
			entity.setCreate_org_id(curUser.getDepartment().getId());
			entity.setCreate_org_name(curUser.getDepartment().getOrgName());
			entity.setCreate_time(new Date());
			entity.setCreate_user_Id(curUser.getId());
			entity.setCreate_user_name(curUser.getName());
			//设置退货单编号  TH20181012001
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		     Date date = new Date();
		     String bh= "TH"+sdf.format(date);
			 Map<String,String> mapBean=goodsReturnDao.getBeanByYear(bh);
			 if(mapBean!=null){
			     int no=Integer.parseInt(mapBean.get("THBH"))+1;
			     entity.setThbh(bh+String.format("%03d", no));
		     }else{
		    	 entity.setThbh(bh+"001");
		     }
		}

		 entity.setState("1");

		//保存存货表
		 goodsReturnDao.save(entity);
		 JSONArray goodsArray=json.getJSONArray("goods");
		
		for (int i = 0; i < goodsArray.size(); i++) {
			JSONObject goodsObject=goodsArray.getJSONObject(i);
			//保存中间表
			GoodsAndOrder bean=goodsAndOrderDao.getGoodsAndOrderByOrderIdAndGoodsId(entity.getId(),goodsObject.getString("id"));
			if(bean==null){
				bean=new GoodsAndOrder();
			}
			bean.setFk_order_id(entity.getId());
			bean.setFk_goods_id(goodsObject.getString("id"));
			bean.setSl(goodsObject.getFloat("sl"));
			bean.setType("TH");
			bean.setCreate_time(new Date());
			bean.setStatus(GoodsReturnState.WCK);
			bean.setBz(goodsObject.getString("bzs"));
			
			goodsAndOrderDao.save(bean);
			
		}

		
		map.put("success", "true");
		
  		return map;
	}
	
	public  List<Goods> getRkGoods(String[] rkId){
		return goodsReturnDao.getRkGoods(rkId);
	}
	
	public void deleteReturnByGoodsId(String[] goodsId,String orderId){
		List<GoodsAndOrder> list=goodsReturnDao.getDelete(goodsId,orderId);
		for (GoodsAndOrder goodsAndOrder : list) {
			goodsAndOrderDao.remove(goodsAndOrder);
		}
	}
	public void deleteGoodsReturn(String[] ids){
		for (int i = 0; i < ids.length; i++) {
			GoodsReturn r=this.get(ids[i]);
			r.setState("99");
			
			List<GoodsAndOrder> list=r.getGoodsAndOrder();
  			for (GoodsAndOrder goodsAndOrder : list) {
				goodsAndOrder.setStatus("99");
			}
  			goodsReturnDao.save(r);
		}
	}
	
	@Override
	public void startOutStorage(String id) {
		System.out.println(id);
	}
	
	
	/**
	 * id：退货单id
	 */
	@Override
	public void finishOutStorage(String id, List<GoodsAndOrder> orders, net.sf.json.JSONObject param) {
		//开始----修改退货单数据
		GoodsReturn gr=this.get(id);
		
		List<String> rhbhs=new ArrayList<String>();
		for (GoodsAndOrder goodsAndOrder : orders) {
			 if(!rhbhs.contains(goodsAndOrder.getGoods().getWarehousing_num())){//判断是否有重复数据，如果没有就将数据装进临时集合
				 rhbhs.add(goodsAndOrder.getGoods().getWarehousing_num());
	            }
		}
		
		List<GoodsAndOrder> oldGAO=gr.getGoodsAndOrder();
		for (GoodsAndOrder goodsAndOrder : oldGAO) {
			goodsAndOrderDao.remove(goodsAndOrder);
		}


		Date now = new Date();
		String bh="";
		if(rhbhs!=null && StringUtil.isNotEmpty(rhbhs.toString())){
			String bh1=rhbhs.toString();
			bh=bh1.substring(1, bh1.length()-1);
		}
		gr.setRkdbh(bh.trim());//修改入库退货单号
		List<GoodsAndOrder> sourceGoods = gr.getGoodsAndOrder();
        sourceGoods.removeAll(sourceGoods);
        for (GoodsAndOrder gad : orders) {
            gad.setType("TH");
            gad.setCreate_time(now);
            gad.setFk_order_id(gr.getId());
            gad.setStatus(GoodsReturnState.CK);
            goodsAndOrderDao.save(gad);
        }
        sourceGoods.addAll(orders);
        gr.setGoodsAndOrder(sourceGoods);//更新物资关联
        gr.setState(GoodsReturnState.CK);//标记已经出库
        //结束---修改退货单
        
		//开始----修改入库单数据
        for (GoodsAndOrder goodsAndOrder : sourceGoods) {
        	//修改Goods
			Goods g=goodsDao.get(goodsAndOrder.getGoods().getId());
	        //修改入库单GoodsAndOrder
			GoodsAndOrder go= goodsAndOrderDao.getGoodsAndOrderByOrderIdAndGoodsId(g.getFk_warehousing_id(),g.getId());
			go.setSl(g.getCssl());
		}
        
        //结束---修改入库单
        goodsReturnDao.save(gr);
	}
	//返回退货物资
	@Override
	public List<GoodsAndOrder> getGoodsAndOrder(String id) {
		System.out.println(id);
		return null;
	}
	//退货单号
	@Override
	public String getOrderNumber(String id) {
		GoodsReturn gr=new GoodsReturn();
		if(StringUtil.isNotEmpty(id)){
			gr=this.get(id);	
		}
		return gr.getThbh();
	}
	//
	@Override
	public void deleteOutStorage(String id) throws Exception {
		if(StringUtil.isNotEmpty(id)){
			deleteGoodsReturn(id.split(","));
		}
	}
	//是否能出库
	@Override
	public boolean canOutStorage(String id) {
		System.out.println(id);
		return true;
	}
}
