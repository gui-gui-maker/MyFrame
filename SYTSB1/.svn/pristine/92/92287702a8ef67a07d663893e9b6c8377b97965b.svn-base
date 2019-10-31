package com.fwxm.supplies.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.material.bean.Goods;
import com.fwxm.material.service.GoodsManager;
import com.fwxm.outstorage.bean.Tjy2ChCk;
import com.fwxm.recipients.bean.Tjy2ChLq;
import com.fwxm.supplies.bean.GoodsReturn;
import com.fwxm.supplies.bean.Warehousing;
import com.fwxm.supplies.service.GoodsReturnManager;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

@SuppressWarnings("serial")
@Controller
@RequestMapping("com/tjy2/goodsReturn")
public class GoodsReturnAction  extends SpringSupportAction<GoodsReturn, GoodsReturnManager>{
	@Autowired
	private GoodsReturnManager goodsReturnManager;
	/**
	 * 添加退货订单
	 * 
	 * @param request
	 * @return
	 */
  	@RequestMapping(value = "saveGoodsReturn")
  	@ResponseBody
	public HashMap<String,Object> saveGoodsReturn(HttpServletRequest request){
		HashMap<String,Object> map=new HashMap<String, Object>();
		try {
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
  			String entity=request.getParameter("goodsReturn").toString();
  			map=goodsReturnManager.saveGoodsReturn(curUser,entity);
		} catch (Exception e) {
			System.out.println(e);
		}
		return map;
	}
	
	
	/**
	 * 根据入库单号查询入库信息
	 * @param request
	 * @return
	 */
  	@RequestMapping(value = "getRkGoods")
  	@ResponseBody
	public HashMap<String, Object> getRkGoods(HttpServletRequest request){
		HashMap<String,Object> map=new HashMap<String, Object>();
		try {
			String rkId=request.getParameter("rkId");
//			String oldBH=request.getParameter("oldBH");
//			 List<Goods> rkBean=goodsReturnManager.getRkGoods(rkId.split(","),oldBH.split(","));
			List<Goods> rkBean=goodsReturnManager.getRkGoods(rkId.split(","));
			map.put("success", true);
			map.put("data", rkBean);
		} catch (Exception e) {
			System.out.println(e);
		}
		return map;
	}
	
	/**
	 * 逻辑删除
	 * @param request
	 * @return
	 */
  	@RequestMapping(value = "deleteGoodsReturn")
  	@ResponseBody
	public HashMap<String,Object> deleteGoodsReturn(HttpServletRequest request){
		HashMap<String,Object> map=new HashMap<String, Object>();
		try {
			String id=request.getParameter("id");
			goodsReturnManager.deleteGoodsReturn(id.split(","));
		} catch (Exception e) {
			System.out.println(e);
		}
		return map;
	}

  	@RequestMapping(value = "deleteReturnByGoodsId")
  	@ResponseBody
	public HashMap<String, Object> deleteReturnByGoodsId(HttpServletRequest request){
		HashMap<String, Object> map=new HashMap<String, Object>();
		try {
			String goodsId=request.getParameter("goodsId");
			String orderId=request.getParameter("orderId");
			goodsReturnManager.deleteReturnByGoodsId(goodsId.split(","),orderId);
			
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return map;
	}
  	
}
