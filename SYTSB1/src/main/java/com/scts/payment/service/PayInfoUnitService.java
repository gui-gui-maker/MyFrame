package com.scts.payment.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.scts.payment.bean.InspectionPayInfo;
import com.scts.payment.bean.PayInfoUnit;
import com.scts.payment.dao.InspectionPayInfoDao;
import com.scts.payment.dao.PayInfoUnitDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("payInfoUnitService")
public class PayInfoUnitService extends EntityManageImpl<PayInfoUnit, PayInfoUnitDao> {

	@Autowired
	private PayInfoUnitDao payInfoUnitDao;
	@Autowired
	private InspectionPayInfoDao payInfoDao;

	public void savePayUnit(String data) {

		JSONObject json = JSONObject.fromString(data);
		
		Object payInfoUnits = json.get("payInfoUnits");
		if(payInfoUnits==null){
			throw new KhntException("保存失败！");
		}
		JSONArray attr = JSONArray.fromString(payInfoUnits.toString());
		
		if(data!=null){
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			payInfoUnitDao.deleteByPayId(json.getString("id"));
			for (int i = 0; i < attr.length(); i++) {
				JSONObject jsona = (JSONObject) attr.get(i);
				if(jsona.has("__status")){
					jsona.remove("__status");
				}
				if(jsona.has("op_time")){
					jsona.remove("op_time");
				}
				if(jsona.has("id")){
					jsona.remove("id");
				}
				PayInfoUnit payInfoUnit = (PayInfoUnit) JSONObject.toBean(jsona,PayInfoUnit.class);
				payInfoUnit.setFk_pay_info_id(json.getString("id"));
				payInfoUnit.setOp_id(user.getId());
				payInfoUnit.setOp_name(user.getName());
				payInfoUnit.setOp_time(new Date());
				
				payInfoUnitDao.save(payInfoUnit);
			}
					
		}else{
			throw new KhntException("保存失败！");
		}
		
		
		
	}

	public InspectionPayInfo getDetailByPayId(String payId) {
		InspectionPayInfo inspectionPayInfo  = payInfoDao.get(payId);
		List<PayInfoUnit> list = payInfoUnitDao.getListByPayId(payId);
		inspectionPayInfo.setPayInfoUnits(list);
		
		return inspectionPayInfo;
	}
	
}
