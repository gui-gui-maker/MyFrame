package com.lsts.inspection.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.InspectionHallPara;



/**
 * 报检大厅流转管理   dao
 * 
 * @author 肖慈边 2014-1-27
 */

@Repository("inspectionHallParaDao")
public class InspectionHallParaDao extends EntityDaoImpl<InspectionHallPara> {

	/**
	 * 根据大厅报检ID查询大厅报检设备参数
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-21 上午10:22:00
	 */
	@SuppressWarnings("unchecked")
    public List<InspectionHallPara> queryInspectionHallParaByHallId(String id) {
    	List<InspectionHallPara> list = new ArrayList<InspectionHallPara>();
    	try {
    		if (StringUtil.isNotEmpty(id)) {
    			String hql = "from InspectionHallPara r where r.inspectionHall.id=?";
    			list = this.createQuery(hql, id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }

}
