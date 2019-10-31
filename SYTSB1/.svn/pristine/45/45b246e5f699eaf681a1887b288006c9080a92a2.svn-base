package com.lsts.humanresources.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.lsts.humanresources.bean.ContractRemind;
import com.lsts.humanresources.bean.RemindMessage;
import com.lsts.humanresources.service.ContractRemindManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("contractRemindAction")
public class ContractRemindAction extends SpringSupportAction<ContractRemind, ContractRemindManager> {

    @Autowired
    private ContractRemindManager  contractRemindManager;
    
    /**
     * 保存消息提醒设置
     * @param request
     * @param contractRemind
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "saveSetting")
	@ResponseBody
    public HashMap<String, Object> saveSetting(HttpServletRequest request, ContractRemind contractRemind) throws Exception{
    	CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
    	String business_id=contractRemind.getId();
		if(business_id==null){
			contractRemind.setCreateDate(new Date());
			contractRemind.setCreateId(curUser.getId());
			contractRemind.setCreateBy(curUser.getName());
			contractRemindManager.save(contractRemind);
		}else{
			ContractRemind contractRemind_exist = contractRemindManager.get(business_id);
			contractRemind_exist.setRemindId(contractRemind.getRemindId());
			contractRemind_exist.setRemindName(contractRemind.getRemindName());
			contractRemind_exist.setLastModifyDate(new Date());
			contractRemind_exist.setLastModifyId(curUser.getId());
			contractRemind_exist.setLastModifyBy(curUser.getName());
			contractRemindManager.save(contractRemind_exist);
		}
		return JsonWrapper.successWrapper(contractRemind);
    	
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
    	ContractRemind contractRemind=contractRemindManager.getSetting();
		wrapper.put("success", true);
		wrapper.put("data", contractRemind);
		return wrapper;
    }
	
    /**
	 * 合同到期提醒定时发送
	 * 预警天数为60天
	 * 
	 * @param request
	 * @throws Exception
	 */
    @RequestMapping(value = "sendContractRemind")
	@ResponseBody
	public HashMap<String, Object> sendContractRemind(HttpServletRequest request) throws Exception{
    	try {
			contractRemindManager.sendContractRemind();
			return JsonWrapper.successWrapper("消息发送成功！");
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("消息发送失败！");
		}
    }
}