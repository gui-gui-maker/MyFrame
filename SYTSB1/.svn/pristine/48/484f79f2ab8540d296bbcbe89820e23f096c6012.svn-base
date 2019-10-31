package com.lsts.humanresources.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository; 
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.humanresources.bean.MultipleDeclare;
import com.lsts.inspection.bean.FlowInfoDTO;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName MultipleDeclareDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("multipleDeclareDao")
public class MultipleDeclareDao extends EntityDaoImpl<MultipleDeclare> {
	/**
  	 * 通过业务ID查询worktaskId和title
  	 * */
    @SuppressWarnings("unchecked")
    public List<FlowInfoDTO> getWorktaskId(HttpServletRequest request,String typeCode, String service_id) {
    	try {
    		if (StringUtil.isNotEmpty(service_id)) {
    			String sql = "select t.ID,t.TITLE from v_pub_worktask t where t.WORK_TYPE='"+typeCode+"' and t.SERVICE_ID = '"+service_id+"' and t.STATUS='0'";
    			System.out.println(sql.toString());
    			List<Object> list = this.createSQLQuery(sql).list();
    			for (int i = 0; i < list.size(); i++) {
					Object[] objs = (Object[]) list.get(0);
					String id = objs[0]==null?null:objs[0].toString();
					String title = objs[1]==null?null:objs[1].toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
    }
}