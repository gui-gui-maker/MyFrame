package com.lsts.advicenote.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fwxm.scientific.bean.TjDTO;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.bean.MessageHistory;


/**
 * 短信
 * @ClassName messageHistoryDao	
 * @JDK 1.7

 */
@Repository("messageHistoryDao")
public class MessageHistoryDao extends EntityDaoImpl<MessageHistory> {
	
	   //获取消息日志统计
    public  List<TjDTO>  getMessageLog(String name,String org_name,String sdate,String edate){
    	//List<String> lists=getFybxlb();
    	List<TjDTO> list = new ArrayList<TjDTO>();
    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
    	try {
    		String sql1="select e.name, s.org_name, t.msg_type, count(e.name) from MESSAGE_INFO t, employee e, sys_org s "
              +" where t.mobile = e.mobile_tel and e.org_id = s.id  ";
    		if(StringUtil.isNotEmpty(name)){
    			sql1=sql1+" and e.name like '%"+name+"%'";
    		}
    		if(StringUtil.isNotEmpty(org_name)){
    			sql1=sql1+" and e.org_id='"+org_name+"'";
    		}
    		if (StringUtil.isNotEmpty(sdate) && StringUtil.isNotEmpty(edate)) {
    			sql1=sql1+ " and t.SEND_TIME<=to_date('"+edate+"','YYYY-MM-DD HH24:MI:SS') "+
          			     " and t.SEND_TIME>=to_date('"+sdate+"','YYYY-MM-DD HH24:MI:SS') ";
			}    
    		sql1=sql1+"group by e.name, s.org_name, t.msg_type";
    		List<Object> list1=this.createSQLQuery(sql1).list();
	    	HashMap<String, Object> wrapper1 = new HashMap<String, Object>();
	    	int number=0;
			for (int i = 0; i < list1.size(); i++) {
				TjDTO tj=new TjDTO();
				Object [] vobjs = (Object[]) list1.get(i);
				String depName = vobjs[1].toString();//部门
				String user_name = vobjs[0].toString();//人员
				String type = vobjs[2].toString();//类型
				if(type.equals("0")){
					type="短信";
				}
				if(type.equals("1")){
					type="微信";
				}
				String num = vobjs[3].toString();//数量
				number=number+Integer.parseInt(num);
				tj.setDepName(depName);
				tj.setZsxm(num);
				tj.setZsxz(type);
				tj.setUserName(user_name);
				list.add(tj);
			}
			//计算总数
			TjDTO tj=new TjDTO();
			tj.setDepName("总数");
			tj.setZsxm(Integer.toString(number));
			tj.setZsxz("");
			tj.setUserName("");
			list.add(tj);
			wrapper.put("list", list);
			
    	
    	
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return list;
    }
}
