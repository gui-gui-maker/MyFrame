package com.scts.payment.order.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.scts.payment.order.bean.LockUserCid;
import com.scts.payment.order.service.LockUserCidService;

@Controller
@RequestMapping("payment/order/lockUserCid")
public class LockUserCidAction extends
	SpringSupportAction<LockUserCid,LockUserCidService>{
	@Autowired
	private LockUserCidService lockUserCidService;
	
	/**
	 * 初始化，并查询用户授权信息
	 * @param request
	 * @param type
	 * @param checkbox
	 * @param fk_per_user_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({"getSelectdData"})
    public String getSelectdData(HttpServletRequest request, String type, String checkbox,String cid) throws Exception {
		List<LockUserCid> hasPer = lockUserCidService.authQuery(cid);
        request.setAttribute("type", type);
        request.setAttribute("checkbox", checkbox);
        request.setAttribute("hasPer", hasPer);
        return "app/flow/userauth_select_index";
    }
	
	/**
	 * 用户绑定设备
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({"saveMiddle"})
	@ResponseBody
	public HashMap saveMiddle(HttpServletRequest request,String userId,String cid) throws Exception{
		CurrentSessionUser user=SecurityUtil.getSecurityUser();
		lockUserCidService.deleteLockUser(cid);
		if(StringUtil.isNotEmpty(userId)){
			for(String permid : userId.split(",")){
				//根据用户id查询用户姓名
				List<Map<String, Object>> list=lockUserCidService.queryUser(permid);
				String name=list.get(0).get("NAME").toString();
				//查询是否绑定设备
				List<Map<String, Object>> list1=lockUserCidService.queryLockUserCid(permid);
				if(list1.size()>0){
					String id=list1.get(0).get("ID").toString();
					LockUserCid entity=lockUserCidService.get(id);
					entity.setFkUserId(permid);
					entity.setCid(cid);
					entity.setName(name);
					lockUserCidService.save(entity);
				}else{
					LockUserCid entity = new LockUserCid();
					entity.setFkUserId(permid);
					entity.setCid(cid);
					entity.setName(name);
					lockUserCidService.save(entity);
				}
			}
		}
		return JsonWrapper.successWrapper();
	}
	
	@RequestMapping("queryLockUser")
	@ResponseBody
	public HashMap<String, Object> queryLockUser(String userId){
		//查询绑定用户的设备id
		List<Map<String, Object>> list=lockUserCidService.queryLockCid(userId);
		Map<String, Object> map=new HashMap<String, Object>();
		if(list.size()>0){
			String cid=list.get(0).get("CID").toString();
			map.put("cid", cid);
		}else{
			map.put("cid", "");
		}
		return JsonWrapper.successWrapper(map);
	}
	
	@RequestMapping("deleteTableValues")
	@ResponseBody
	public HashMap<String, Object> deleteTableValues(String ids){
		lockUserCidService.deleteTableValue(ids);
		return JsonWrapper.successWrapper(ids);
	}
}
