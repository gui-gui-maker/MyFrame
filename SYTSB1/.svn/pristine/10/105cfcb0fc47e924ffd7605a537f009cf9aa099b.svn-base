package com.fwxm.certificate.web;

import com.fwxm.certificate.bean.Tjy2CertificateTrain;
import com.fwxm.certificate.service.Tjy2CertificateTrainManager;
import com.fwxm.certificate.service.Tjy2CertificateTrainSubManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.employee.bean.EmployeeCert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;









import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("tjy2CertificateTrainAction")
public class Tjy2CertificateTrainAction extends SpringSupportAction<Tjy2CertificateTrain, Tjy2CertificateTrainManager> {

    @Autowired
    private Tjy2CertificateTrainManager  tjy2CertificateTrainManager;
    @Autowired
    private Tjy2CertificateTrainSubManager  tjy2CertificateTrainSubManager;
    
    /**
	 * 保存
	 * 
	 * @param request
	 * @param employeeCert
	 * @throws Exception
	 */
	@RequestMapping(value = "saveTrain")
	@ResponseBody
	public HashMap<String, Object> saveTrain(HttpServletRequest request, Tjy2CertificateTrain train) throws Exception {
		String uploadFiles = request.getParameter("uploadFiles");
		CurrentSessionUser user=SecurityUtil.getSecurityUser();
		try {
			if(StringUtil.isEmpty(train.getId())){
				train.setCreateDate(new Date());
				train.setCreateMan(user.getName());
			}else{
				train.setLastDate(new Date());
				train.setLastMan(user.getName());
			}
			tjy2CertificateTrainManager.save(train);
			tjy2CertificateTrainSubManager.saveTrainSub(request,train);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存情况失败，请重试！");
		}
		return JsonWrapper.successWrapper(train);
	}
	/**
	 * 根据ids删除数据
	 * @param request
	 * @param businessId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteTrain")
	@ResponseBody
	public HashMap<String, Object> deleteTrain(HttpServletRequest request, String ids) throws Exception {
		try {
			if(StringUtil.isNotEmpty(ids)){
				tjy2CertificateTrainManager.deleteTrain(ids);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("操作失败，请重试！");
		}
		return JsonWrapper.successWrapper("操作成功！");
	}
	
}