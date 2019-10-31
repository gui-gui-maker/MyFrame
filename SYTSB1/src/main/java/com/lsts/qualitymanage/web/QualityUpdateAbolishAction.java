package com.lsts.qualitymanage.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.finance.bean.CwBorrowMoney;
import com.lsts.finance.service.CwBorrowMoneyManager;
import com.lsts.qualitymanage.bean.QualityUpdateAbolish;
import com.lsts.qualitymanage.service.QualityUpdateAbolishManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;






import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("update/abolish")
public class QualityUpdateAbolishAction extends SpringSupportAction<QualityUpdateAbolish, QualityUpdateAbolishManager> {

    @Autowired
    private QualityUpdateAbolishManager  qualityUpdateAbolishManager;
    /**
   	 * 添加
   	 * @param response
        * @throws Exception 
   	 */
    @Override
   	public HashMap<String, Object> save(HttpServletRequest request,QualityUpdateAbolish qualityUpdateAbolish) throws Exception {
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		//获取当前登录人姓名
   		qualityUpdateAbolish.setRegistrant(user.getName());
   		qualityUpdateAbolish.setRegistrantId(user.getId());
   		qualityUpdateAbolish.setRegistrantTime(new Date());
   		//改变状态
   		qualityUpdateAbolish.setStatus(qualityUpdateAbolishManager.ZL_XGSQ_FC);
   		return super.save(request, qualityUpdateAbolish);

   	}
}