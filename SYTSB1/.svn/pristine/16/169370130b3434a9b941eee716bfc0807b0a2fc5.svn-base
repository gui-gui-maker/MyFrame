package com.lsts.sinspection.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.IdFormat;
import com.lsts.sinspection.bean.SupervisionInspection;

/**
 * 产品监检数据处理对象
 * @ClassName SupervisionInspectionDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-26 上午11:50:00
 */
@Repository("supervisionInspectionDao")
public class SupervisionInspectionDao extends EntityDaoImpl<SupervisionInspection> {
	
	/**
	 * 根据产品监检ID查询附件ID
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-26 上午11:52:00
	 */
	@SuppressWarnings("unchecked")
    public String queryAttachmentId(String id) {
    	String attachmentId = "";
    	try {
    		if (StringUtil.isNotEmpty(id)) {
    			String sql = "select a.content_doc_id from TZSB_SUPERVISION_INSPECTION a where a.id=?";
    			List list = this.createSQLQuery(sql, id).list();
    			if (!list.isEmpty()) {
    				attachmentId = String.valueOf(list.get(0));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return attachmentId;
    }
	
	//删除
	public void delSupervisionInspection(String ids) {
		ids=IdFormat.formatIdStr(ids);
	    // 删除产品监检信息
	    String hql="delete from SupervisionInspection where id in ("+ids+")";
		this.createQuery(hql).executeUpdate();
	}
}
