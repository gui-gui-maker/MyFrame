package com.lsts.qualitymanage.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
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
import com.lsts.log.service.SysLogService;
import com.lsts.qualitymanage.bean.QualityZssq;
import com.lsts.qualitymanage.bean.QualityZssqSub;
import com.lsts.qualitymanage.service.QualityZssqManager;
import com.lsts.qualitymanage.service.QualityZssqSubManager;


@Controller
@RequestMapping("QualityZssqSubAction")
public class QualityZssqSubAction extends SpringSupportAction<QualityZssqSub, QualityZssqSubManager> {

    @Autowired
    private QualityZssqSubManager  qualityZssqSubManager;
    @Autowired
    private QualityZssqManager  qualityZssqManager;
    @Autowired
	private SysLogService logService;
    /**
     * 获取列表
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getQualityZssqSubs")
	@ResponseBody
	public List<QualityZssqSub> getQualityZssqSubs(HttpServletRequest request)throws Exception {
    	String id = request.getParameter("id");
    	return qualityZssqSubManager.getQualityZssqSubs(id);
	}
    /**
     * 部分归还
     * @param request
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "gh")
  	@ResponseBody
  	public Map<String, Object> gh(ServletRequest request,String ids,String quality_zssq_fk) throws Exception {
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
    	Map<String, Object> map = new HashMap<String, Object>();
  		String[] id=ids.split(",");
  		for(int i=0;i<id.length;i++ ){
  			QualityZssqSub qualityZssqSub=qualityZssqSubManager.get(id[i]);
  			if(StringUtil.isEmpty(qualityZssqSub.getIsBack()) || qualityZssqSub.getIsBack().equals("0")){
  				qualityZssqSub.setIsBack("1");//修改子表报告状态为已归还
  	  			qualityZssqSubManager.save(qualityZssqSub);
  	  			//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  		  		logService.setLogs(StringUtil.isNotEmpty(qualityZssqSub.getReportFk()) ? qualityZssqSub.getReportFk() : "此为手动填写的报告，无ID外键", 
  		  				"无原始资料打印申请报告归还", 
  		  				"无原始资料打印申请报告归还，操作结果：已归还", 
  		  				user != null ? user.getId() : "未获取到操作用户编号",
  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
  						request != null ? request.getRemoteAddr() : "");
  			}
  		}
  		if(qualityZssqSubManager.checkAllBack(quality_zssq_fk)){
  			QualityZssq qualityZssq=qualityZssqManager.get(quality_zssq_fk);
  			qualityZssq.setNextPeople(user.getName());
  			qualityZssq.setNextStatus(QualityZssqManager.ZL_SGCJ_YES_NEXT);//修改主表归还状态为已归还
  			qualityZssq.setNextTime(new Date());
  			qualityZssq.setReturnTime(new Date());
  	  		qualityZssqManager.save(qualityZssq);
  	  		//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		logService.setLogs(quality_zssq_fk, 
	  			"无原始资料打印申请报告部分归还", 
  				"无原始资料打印申请报告部分归还，操作结果：已归还",  
	  				user != null ? user.getId() : "未获取到操作用户编号",
					user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
					request != null ? request.getRemoteAddr() : "");
  		}else{
  			QualityZssq qualityZssq=qualityZssqManager.get(quality_zssq_fk);
  			qualityZssq.setNextPeople(user.getName());
  			qualityZssq.setNextStatus(QualityZssqManager.ZL_SGCJ_BF_NEXT);//修改主表归还状态为部分归还
  			qualityZssq.setNextTime(new Date());
  			qualityZssq.setReturnTime(new Date());
  	  		qualityZssqManager.save(qualityZssq);
  	  		//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		logService.setLogs(quality_zssq_fk, 
	  			"无原始资料打印申请报告部分归还", 
  				"无原始资料打印申请报告部分归还，操作结果：部分归还",  
	  				user != null ? user.getId() : "未获取到操作用户编号",
					user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
					request != null ? request.getRemoteAddr() : "");
  		}
  		map.put("success", true);
    return map;
    }
}