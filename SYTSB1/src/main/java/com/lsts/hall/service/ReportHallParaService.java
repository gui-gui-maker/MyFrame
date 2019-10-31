package com.lsts.hall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.lsts.hall.bean.ReportHallPara;
import com.lsts.hall.dao.ReportHallParaDao;
import com.lsts.inspection.bean.Inspection;


/**
 * @author Mr.Dawn
 * @date 2014-12-22
 * @summary 
 */
@Service("reportHallParaService")
public class ReportHallParaService extends EntityManageImpl<ReportHallPara, ReportHallParaDao> {
	@Autowired
	private ReportHallParaDao reportHallParaDao;
	public List<ReportHallPara> queryInspectionHallParaByHallId(String id) {
		return reportHallParaDao.queryInspectionHallParaByHallId(id);
	}
	
	
	public void subInspection(String ids) throws KhntException {
		String id[] =ids.split(",");
		for(int i=0;i<id.length;i++){
			reportHallParaDao.createSQLQuery("update tzsb_inspection_hall_para t1 set  t1.is_rec='3' where t1.id=?",id[i] ).executeUpdate();
			
		}
	}
	
	/**
	 * 获取已经报检的数量
	 */
	public int getInspectCount(String hall_para_id) throws KhntException {
		String hql = "from Inspection where fk_hall_para_id = ?" ;
		List list  = reportHallParaDao.createQuery(hql, hall_para_id).list();
		return list.size() ;
	}
	
	/**
	 * 检验任务生成提交后退回任务分配
	 * 
	 * @param ids
	 * @return
	 * @throws KhntException
	 */
	public Map<String, Object> returnAssign(String hall_para_id) throws KhntException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id[] = hall_para_id.split(",");
			for (int i = 0; i < id.length; i++) {
				reportHallParaDao
					.createSQLQuery(
						"update  TZSB_INSPECTION_HALL_PARA  set IS_PLAN='1' ,IS_REC='1' where id =?",
							id[i]).executeUpdate();
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("message", "退回任务分配失败！");
		}
		return map;

	}
}
