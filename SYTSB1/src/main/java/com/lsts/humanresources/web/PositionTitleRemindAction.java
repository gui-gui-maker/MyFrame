package com.lsts.humanresources.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.lsts.humanresources.bean.PositionTitleRemind;
import com.lsts.humanresources.service.PositionTitleRemindManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;


@Controller
@RequestMapping("positionTitleRemindAction")
public class PositionTitleRemindAction extends SpringSupportAction<PositionTitleRemind, PositionTitleRemindManager> {

    @Autowired
    private PositionTitleRemindManager  positionTitleRemindManager;
    
    /**
     * 保存消息提醒设置
     * @param request
     * @param positionTitleRemind
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "saveSetting")
	@ResponseBody
    public HashMap<String, Object> saveSetting(HttpServletRequest request, PositionTitleRemind positionTitleRemind) throws Exception{
    	CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
    	String business_id=positionTitleRemind.getId();
		if(business_id==null){
			positionTitleRemind.setCreateDate(new Date());
			positionTitleRemind.setCreateId(curUser.getId());
			positionTitleRemind.setCreateBy(curUser.getName());
			positionTitleRemindManager.save(positionTitleRemind);
		}else{
			PositionTitleRemind positionTitleRemind_exist = positionTitleRemindManager.get(business_id);
			positionTitleRemind_exist.setRemindId(positionTitleRemind.getRemindId());
			positionTitleRemind_exist.setRemindName(positionTitleRemind.getRemindName());
			positionTitleRemind_exist.setLastModifyDate(new Date());
			positionTitleRemind_exist.setLastModifyId(curUser.getId());
			positionTitleRemind_exist.setLastModifyBy(curUser.getName());
			positionTitleRemindManager.save(positionTitleRemind_exist);
		}
		return JsonWrapper.successWrapper(positionTitleRemind);
    	
    }
    
    /**
	 * 获取消息提醒设置
	 * 
	 * @param request
	 * @throws Exception
	 */
    @RequestMapping(value = "getSetting")
	@ResponseBody
	public HashMap<String, Object> getSetting(HttpServletRequest request) throws Exception{
    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
    	String fkRlEmplpyeeId=request.getParameter("fkRlEmplpyeeId");
    	PositionTitleRemind positionTitleRemind=positionTitleRemindManager.getSetting();
		wrapper.put("success", true);
		wrapper.put("data", positionTitleRemind);
		return wrapper;
    }
	
    /**
	 * 合同到期提醒定时发送
	 * 预警天数为60天
	 * 
	 * @param request
	 * @throws Exception
	 */
    @RequestMapping(value = "sendPositionTitleRemind")
	@ResponseBody
	public HashMap<String, Object> sendPositionTitleRemind(HttpServletRequest request) throws Exception{
    	try {
			positionTitleRemindManager.sendPositionTitleRemind();
			return JsonWrapper.successWrapper("消息发送成功！");
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("消息发送失败！");
		}
    }
}