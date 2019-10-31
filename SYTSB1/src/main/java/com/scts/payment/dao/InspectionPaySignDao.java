package com.scts.payment.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.payment.bean.InspectionPaySign;
@Repository
public class InspectionPaySignDao extends EntityDaoImpl<InspectionPaySign>{
	
	/**
	 * 根据收费信息表ID查询报检信息收费详细信息表信息
	 * @param fkInspectionInfoId
	 * @return
	 */
	public List<Map<String, Object>> queryInsperctionPayInfo(String fkInspectionInfoId){
		String sql="select * from TZSB_INSPECTION_PAY_INFO where fk_inspection_info_id like '%"+fkInspectionInfoId+"%' and payment_status='2' ";
		Query query=this.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
	
	/**
	 * 查询收费签名信息表信息
	 * @param fkPayInfoId
	 * @return
	 */
	public List<Map<String, Object>> queryPaySign(String fkPayInfoId){
		String sql="select * from TZSB_INSPECTION_PAY_SIGN where FK_PAY_INFO_ID=? ";
		Query query=this.createSQLQuery(sql,fkPayInfoId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
	
	/**
	 * 根据收费信息表ID查询报检信息收费详细信息表信息
	 * @param fkInspectionInfoId
	 * @return
	 */
	public List<Map<String, Object>> queryInsperctionPayInfo1(String fkInspectionInfoId){
		String sql="select * from TZSB_INSPECTION_PAY_INFO where fk_inspection_info_id=? and payment_status='2'";
		Query query=this.createSQLQuery(sql,fkInspectionInfoId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
}
