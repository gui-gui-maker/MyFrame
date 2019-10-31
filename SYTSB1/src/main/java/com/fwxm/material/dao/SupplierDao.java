package com.fwxm.material.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.fwxm.material.bean.Goods;
import com.fwxm.material.bean.Supplier;
import com.fwxm.supplies.bean.Warehousing;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

@Repository("supplierDao")
public class SupplierDao extends EntityDaoImpl<Supplier>{
	
	public List<Goods> getGoodsByGysIdAndTime(String gysId,String startTime,String endTime,String typeId,String orgId){
		String sql="SELECT o.bz bz,o.fph warehousing_num,g.* FROM TJY2_CH_GOODS g "
				+ " INNER JOIN TJY2_CH_GOODSANDORDER o ON o.FK_GOODS_ID=g.ID AND o.FK_ORDER_ID=g.FK_WAREHOUSING_ID"
				+ " WHERE g.STATE='2' AND o.STATUS='2' AND g.FK_SUPPLIER_ID=? AND g.FK_GOODSTYPE_ID=? "
				+ "and g.RK_TIME>= to_date('"+startTime+"','yyyy-mm') and g.RK_TIME< to_date('"+endTime+"','yyyy-mm')"
						+ " and g.create_org_id=?";
		SQLQuery query=((SQLQuery)this.createSQLQuery(sql,gysId,typeId,orgId)).addEntity(Goods.class);
		
		return query.list();
	}
	
	
	/**
	 * 查询办公耗材验收表
	 * @param gysId
	 * @param startTime
	 * @param endTime
	 * @param orgId
	 * @return
	 */
	public List<Map<String, Object>> getBghcBean(String gysId,String startTime,String endTime,String orgId){
		String sql="SELECT w.SHDW,w.DZ,w.LXRMC,w.LXRBM,w.dh, g.* FROM  TJY2_CH_GOODS g "
				+ " INNER JOIN TJY2_CH_GOODSANDORDER cg ON g.ID=cg.FK_GOODS_ID "
				+ " INNER JOIN TJY2_CH_WAREHOUSING w ON cg.FK_ORDER_ID=w.id"
				+ " WHERE g.FK_SUPPLIER_ID=? AND g.STATE='2' AND g.WPLX='办公耗材' "
				+ " AND cg.TYPE='RK' AND cg.CRK_TYPE='CG'  AND g.RK_time>=to_date('"+startTime+"','yyyy-mm') AND g.rk_time<to_date('"+endTime+"','yyyy-mm') AND w.CREATE_ORG_ID=?";
		List<Map<String, Object>> list=this.createSQLQuery(sql, gysId,orgId).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	//------验收表查询其它耗材-----开始----------
	public List<Warehousing> getWarehousing(String gysId,String startTime,String endTime,String orgId,String typeId){
		String sql="SELECT w.* FROM TJY2_CH_WAREHOUSING w INNER JOIN TJY2_CH_GOODS g ON g.FK_WAREHOUSING_ID=w.id"
				+ " where g.RK_TIME>= to_date('"+startTime+"','yyyy-mm') and g.RK_TIME< to_date('"+endTime+"','yyyy-mm')"
				+ " AND  g.STATE='2' and w.create_org_id=? and g.FK_SUPPLIER_ID=? AND g.WPLX <> '办公耗材' AND g.FK_GOODSTYPE_ID=?";
		SQLQuery query=((SQLQuery)this.createSQLQuery(sql, orgId,gysId,typeId)).addEntity(Warehousing.class);
		return query.list();
	}
	
	public List<Map<String, Object>> getWpList(String gysId,String startTime,String endTime,String orgId,String typeId){
		String sql="SELECT * FROM ("
				+ " SELECT g.WPMC,g.GYSMC,g.GGJXH,g.DW,o.SJLQSL SL,g.JE,g.SE,out.lqbm FROM TJY2_CH_GOODS g"
				+ " INNER JOIN TJY2_CH_GOODSANDORDER o ON g.id=o.FK_GOODS_ID"
				+ " INNER JOIN TJY2_CH_OUT out ON out.id=o.FK_ORDER_ID"
				+ " WHERE g.STATE='2' AND g.RK_TIME>=to_date('"+startTime+"','yyyy-mm') AND g.RK_TIME<=TO_DATE('"+endTime+"','yyyy-mm')"
				+ " AND o.type='CK' AND o.CRK_TYPE='LQ' AND g.FK_SUPPLIER_ID=? AND o.SJLQSL <> 0 AND o.SJLQSL IS NOT NULL  AND g.WPLX <> '办公耗材' AND out.CREATE_ORG_ID=? and g.FK_GOODSTYPE_ID=?"
				+ " union ALL"
				+ " SELECT g.WPMC,g.GYSMC,g.GGJXH,g.DW,g.SL,g.JE,g.SE,g.CREATE_ORG_NAME lqbm FROM TJY2_CH_GOODS g"
				+ " WHERE g.STATE='2' AND g.RK_TIME>=to_date('"+startTime+"','yyyy-mm') AND g.RK_TIME<=to_date('"+endTime+"','yyyy-mm') AND g.SL <> 0"
				+ " AND g.FK_SUPPLIER_ID=? AND g.WPLX <> '办公耗材'  AND g.CREATE_ORG_ID=? AND g.SL <> 0 and g.FK_GOODSTYPE_ID=?"
				+ " ) a ORDER BY a.lqbm ,a.WPMC";
		List<Map<String, Object>> list=this.createSQLQuery(sql, gysId,orgId,typeId,gysId,orgId,typeId).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	//-------验收表查询其它耗材-----结束----------
	
}
