package com.lsts.humanresources.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.humanresources.bean.PositionTitleRemind;
import com.lsts.humanresources.dao.PositionTitleRemindDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;


@Service("positionTitleRemind")
public class PositionTitleRemindManager extends EntityManageImpl<PositionTitleRemind,PositionTitleRemindDao>{
	private Logger logger = LoggerFactory.getLogger(PositionTitleRemindManager.class);
    @Autowired
    PositionTitleRemindDao positionTitleRemindDao;
    @Autowired
	private MessageService messageService;
    
 	/**
 	 * 获取消息提醒设置
 	 * @return
 	 */
    public PositionTitleRemind getSetting(){
    	return positionTitleRemindDao.getSetting();
    }
    
    /**
     * 合同到期提醒定时消息发送
     * @return
	 * @throws Exception 
     */
    public void sendPositionTitleRemind(){
    	System.out.println("------------系统定时发送合同到期提醒------------");
    	HttpServletRequest request = null;
    	try {
    		SimpleDateFormat formatData = new SimpleDateFormat("yyyy年MM月dd日");
	    	List<?> list = positionTitleRemindDao.getRemindInfo();//获取到期合同信息
	    	List<?> phoneList = positionTitleRemindDao.getPhoneBySetting();//获取消息接收人（除当事人之外）
	    	String signed_man_cache = null;
	    	if(list!=null && list.size()>0) {
				for (Object o : list) {
					Object[] objs = (Object[]) o;
					String business_id = (String) objs[0];//业务id
					String employee_id = (String) objs[1];//当事人id
					String signed_man = (String) objs[2];//当事人姓名
					String emp_phone = (String) objs[4];//当事人手机号
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("signed_man", signed_man);
					map.put("positionTitle_stop_date", formatData.format(objs[3]));
					messageService.sendMassageByConfig(request, business_id, emp_phone, "", "positionTitleRemind_part"
							, employee_id, map, null,
							null, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD);//发送消息给当事人
					this.logger.debug("{}的职称聘用还有60天到期，已向其手机{}发送短信进行提醒。", signed_man, emp_phone);
					if (signed_man_cache == null) {
						signed_man_cache = signed_man;
					} else {
						signed_man_cache += "," + signed_man;
					}
				}
			} else {
				this.logger.info("没有距今日60天到期的职称聘用信息");
			}
	    	if(phoneList!=null && phoneList.size()>0 && signed_man_cache !=null) {
				for (Object o : phoneList) {
					Object[] objs_cache = (Object[]) o;
					String receive_id = (String) objs_cache[0];//接收人id
					String receive_phone = (String) objs_cache[1];//接收人手机号
					HashMap<String, Object> map1 = new HashMap<String, Object>();
					map1.put("signed_man_cache", signed_man_cache);
					if(StringUtil.isEmpty(receive_id) || StringUtil.isEmpty(receive_phone)) {
						this.logger.error("接收人ID或手机号码不能为空");
					}
					messageService.sendMassageByConfig(request, null, receive_phone, "", "positionTitleRemind_receive"
							, receive_id, map1, null,
							null, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD);//发送消息给接收人（除当事人）
					this.logger.debug("{}的合同还有60天到期，已向接收人手机{}发送短信进行提醒。", signed_man_cache, receive_phone);
				}
			}
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
