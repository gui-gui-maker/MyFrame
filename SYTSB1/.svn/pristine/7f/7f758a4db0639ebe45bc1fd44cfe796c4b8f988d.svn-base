package com.lsts.advicenote.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.IdFormat;
import com.lsts.advicenote.bean.AdviceNote;


/**
 * 通知书数据处理对象
 * @ClassName AdviceNoteDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-24 下午03:29:00
 */
@Repository("adviceNoteDao")
public class AdviceNoteDao extends EntityDaoImpl<AdviceNote> {
	
	/**
	 * 根据通知书ID查询附件ID
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-02-19 上午10:49:00
	 */
	@SuppressWarnings("unchecked")
    public String queryAttachmentId(String id) {
    	String attachmentId = "";
    	try {
    		if (StringUtil.isNotEmpty(id)) {
    			String sql = "select a.advicenote_content from TZSB_ADVICENOTE a where a.id=?";
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
	public void delAdviceNote(String ids) {
		ids=IdFormat.formatIdStr(ids);
	    // 删除通知书信息
	    String hql="delete from AdviceNote where id in ("+ids+") and check_result='0'";
		this.createQuery(hql).executeUpdate();
	}
}
