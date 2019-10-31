package com.lsts.hall.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.hall.bean.ReportHallPara;



/**
/**
 * @author Mr.Dawn
 * @date 2014-12-22
 * @summary 
 */

@Repository("reportHallParaDao")
public class ReportHallParaDao extends EntityDaoImpl<ReportHallPara> {

	/**
	 * 根据大厅报检ID查询大厅报检设备参数
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-21 上午10:22:00
	 */
	@SuppressWarnings("unchecked")
    public List<ReportHallPara> queryInspectionHallParaByHallId(String id) {
    	List<ReportHallPara> list = new ArrayList<ReportHallPara>();
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
