package com.fwxm.scientific.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.scientific.bean.TechExchange;
import com.fwxm.scientific.dao.TechExchangeDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.dao.UserDao;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;


@Service("TechExchangeManager")
public class TechExchangeManager extends EntityManageImpl<TechExchange,TechExchangeDao>{
    @Autowired
    TechExchangeDao techExchangeDao;
	@Autowired
	private UserDao userDao;
    @Autowired
	private MessageService messageService;
    
    public void getByDate(){//定时任务
		String sql="select t.create_id from TJY2_TECH_EXCHANGE t where t.act_date-sysdate<=30 and t.type='nldb'";
		try {
			List<Object> list= techExchangeDao.createSQLQuery(sql).list();
			if(list.size()>0){
				for (int j = 0; j < list.size(); j++) {
					Object [] vobjs = (Object[]) list.get(j);
					String user_id = vobjs[0].toString();//获取创建人id
					 User user= userDao.get(user_id);
					 System.out.println("发送微信。。。");
					 String con="您好,你部门填写的比对意向书将于一个月后开始，请注意比对时间！";
			    		messageService.sendWxMsg(null,"定时任务", Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, 
			    				con,user.getEmployee().getMobileTel());
				}
				
				
				
			}
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
