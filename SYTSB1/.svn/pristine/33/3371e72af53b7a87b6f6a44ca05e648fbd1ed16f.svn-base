package com.lsts.office.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.office.bean.OfficeMessage;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName OfficeMessageDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("officeMessageDao")
public class OfficeMessageDao extends EntityDaoImpl<OfficeMessage> {
	/**
  	 * 查询发送类型为定时，状态为未发送的对象集合
  	 */
	public List<?> getTimeMessage(){
  		List<?> list = new ArrayList<OfficeMessage>();
  		try {
			/*String hql="from OfficeMessage where sendStyle='1' and sendStatus='0'";*/
  			String sql="select t.* "+
						  "from TJY2_OFFICE_MESSAGE t "+
						 "where to_char(t.send_time, 'yyyy-MM-dd HH24:mi') = "+
						       "to_char(sysdate, 'yyyy-MM-dd HH24:mi') "+
						   "and t.send_style = '1' "+
						   "and t.send_status = '0'";
  			
  			list = this.createSQLQuery(sql).list();
  		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
    }

}