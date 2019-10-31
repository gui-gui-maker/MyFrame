package com.fwxm.material.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.fwxm.material.bean.Goods;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;

@Repository("goodsDao")
public class GoodsDao extends EntityDaoImpl<Goods>{
	//根据入库单号查询物资
//	public List<Goods> getGoodsByWarehousing_id(String id){
//		String hql=" from Goods where fk_warehousing_id=?";
//		List<Goods> list=this.createQuery(hql, id).list();
//		return list;
//	}
	/**
	 * 根据入库单Id查询-入库单物品类型
	 * @param rkId
	 * @return
	 */
	public List<Map<String, String>> getGoodsTypeByOrderId(String rkId,String orgId){
		String sql="SELECT t.ID,t.LX_NAME FROM TJY2_CH_GOODS g INNER JOIN TJY2_CH_GOODS_TYPE t ON t.ID= g.FK_GOODSTYPE_ID "
				+ "  WHERE g.FK_WAREHOUSING_ID in ('"+rkId.trim().replaceAll(",", "','")+"') AND g.STATE='2' and g.CREATE_ORG_ID=? GROUP BY t.ID,t.LX_NAME ";
		List<Map<String, String>> list=this.createSQLQuery(sql,orgId).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	/**
	 * 根据供应商Id查询-入库单物品类型
	 * @param rkId
	 * @return
	 */
	public List<Map<String, String>> getGoodsTypeBygysId(String gysId,String startTime,String endTime,String orgId){
		String sql="SELECT t.ID,t.LX_NAME FROM TJY2_CH_GOODS g INNER JOIN TJY2_CH_GOODS_TYPE t ON t.ID= g.FK_GOODSTYPE_ID "
				+ "  WHERE g.FK_SUPPLIER_ID=? AND g.STATE='2' and g.RK_TIME>= to_date('"+startTime+"','yyyy-mm') and g.RK_TIME< to_date('"+endTime+"','yyyy-mm')"
				+ " and g.create_org_id=?"
				+ " GROUP BY t.ID,t.LX_NAME";
		List<Map<String, String>> list=this.createSQLQuery(sql, gysId,orgId).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	/**
	 * 根据供应商Id查询-入库单物品类型(排除办公耗材)
	 * @param rkId
	 * @return
	 */
	public List<Map<String, String>> getGoodsTypeBygysIdNotBg(String gysId,String startTime,String endTime,String orgId){
		String sql="SELECT t.ID,t.LX_NAME FROM TJY2_CH_GOODS g INNER JOIN TJY2_CH_GOODS_TYPE t ON t.ID= g.FK_GOODSTYPE_ID "
				+ "  WHERE g.FK_SUPPLIER_ID=? AND g.STATE='2' and g.RK_TIME>= to_date('"+startTime+"','yyyy-mm') and g.RK_TIME< to_date('"+endTime+"','yyyy-mm')"
				+ " and g.create_org_id=? and t.LX_NAME <> '办公耗材'"
				+ " GROUP BY t.ID,t.LX_NAME";
		List<Map<String, String>> list=this.createSQLQuery(sql, gysId,orgId).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	
	
	public List<Goods> getgoodsByRkIdAndTypeId(String rkId,String typeId){
		String hql=" from Goods where fk_warehousing_id=? and goodstype.id=? and state='2'";
		List<Goods> list=this.createQuery(hql, rkId,typeId).list();
		return list;
	}
	
	/**
	 * 根据goodsId查询出库记录
	 * @param goodsId
	 * @return
	 */
	public List<Map<String, Object>> getOutGoodsById(String goodsId){
		String sql="SELECT o.lqsj,g.sjlqsl,c.je,o.lqr FROM  TJY2_CH_OUT o "
				+ "INNER JOIN TJY2_CH_GOODSANDORDER g ON o.id=g.FK_ORDER_ID"
				+ " INNER JOIN TJY2_CH_GOODS c ON c.id=g.fk_goods_id"
				+ " WHERE  g.TYPE='CK' and c.id=?";
		List<Map<String, Object>> list=this.createSQLQuery(sql, goodsId).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	
	public List<Goods> getgoodsByRkId(String rkId,String startTime,String endTime){
		String hql=" from Goods where state='2'";
		if(StringUtil.isNotEmpty(rkId)){
			hql += " and fk_warehousing_id in ('"+rkId.trim().replaceAll(",", "','")+"') ";
		}
		if(StringUtil.isNotEmpty(startTime)){
			hql += " and rk_time >=to_date('"+startTime+"','yyyy-mm-dd')";
		}
		if(StringUtil.isNotEmpty(endTime)){
			hql+=" and rk_time <=to_date('"+endTime+"','yyyy-mm-dd')";
		}
		hql+=" ORDER BY rk_time DESC";
		List<Goods> list=this.createQuery(hql).list();
		return list;
	} 
}
