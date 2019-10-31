package com.fwxm.scientific.web;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fwxm.scientific.bean.TechExchange;
import com.fwxm.scientific.service.TechExchangeManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.lsts.equipment2.bean.EquipPpe;


@Controller
@RequestMapping("TechExchangeAction")
public class TechExchangeAction extends SpringSupportAction<TechExchange, TechExchangeManager> {

    @Autowired
    private TechExchangeManager  techExchangeManager;
    /**
	 * 保存
	 * 
	 * @param request
	 * @param equipPpe
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> save(HttpServletRequest request, TechExchange techExchange) throws Exception {
		CurrentSessionUser curUser = this.getCurrentUser(); //获取当前用户登录信息
		String status = request.getParameter("status");//获取状态
		try {
			if (status.equals("add")) {
				techExchange.setCreateDate(new Date());
				techExchange.setCreateId(curUser.getId());
				techExchange.setCreateBy(curUser.getName());
			}else if(status.equals("modify")) {
				techExchange.setLastModifyDate(new Date());
				techExchange.setLastModifyId(curUser.getId());
				techExchange.setLastModifyBy(curUser.getName());
			}
			techExchangeManager.save(techExchange);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper(techExchange);
	}
}