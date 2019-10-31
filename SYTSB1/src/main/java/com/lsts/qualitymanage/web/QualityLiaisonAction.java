package com.lsts.qualitymanage.web;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.lsts.advicenote.bean.AdviceNote;
import com.lsts.equipment2.bean.EquipPpe;
import com.lsts.qualitymanage.bean.QualityLiaison;
import com.lsts.qualitymanage.bean.QualityNote;
import com.lsts.qualitymanage.bean.QualityZssq;
import com.lsts.qualitymanage.bean.Tyfabh;
import com.lsts.qualitymanage.service.QualityLiaisonManager;
import com.lsts.qualitymanage.service.QualityNoteManager;
import com.lsts.qualitymanage.service.TyfabhManager;


@Controller
@RequestMapping("QualityLiaisonAction")
public class QualityLiaisonAction extends SpringSupportAction<QualityLiaison, QualityLiaisonManager> {

    @Autowired
    private QualityLiaisonManager  qualityLiaisonManager;
    
    @Override
	public HashMap<String, Object> save(HttpServletRequest request, QualityLiaison qualityLiaison) throws Exception {
		CurrentSessionUser curUser = this.getCurrentUser(); //获取当前用户登录信息
		String pageStatus = request.getParameter("pageStatus");//获取状态
		try {
			if (pageStatus.equals("add")) {
				qualityLiaison.setIdentifier(qualityLiaisonManager.initIdentifier());//生成编号
				qualityLiaison.setCheckStatus(qualityLiaisonManager.NOTE_WTJ);
				qualityLiaison.setPrintStatus(qualityLiaisonManager.NOTE_NO_PRINT);
				qualityLiaison.setCreateDate(new Date());
				qualityLiaison.setCreateId(curUser.getId());
				qualityLiaison.setCreateBy(curUser.getName());
			}else if(pageStatus.equals("modify")) {
				qualityLiaison.setLastModifyDate(new Date());
				qualityLiaison.setLastModifyId(curUser.getId());
				qualityLiaison.setLastModifyBy(curUser.getName());
			}
			qualityLiaisonManager.save(qualityLiaison);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存联络单失败，请重试！");
		}
		return JsonWrapper.successWrapper(qualityLiaison);
	}
    
    /**
     * 提交
     * @param ids
     * @return
     * @throws Exception
     */
  	@RequestMapping(value = "submit")
   	@ResponseBody
   	public HashMap<String, Object> submit(String ids) throws Exception{
  		HashMap<String, Object> map = new HashMap<String, Object>();
  		String[] id=ids.split(",");
  		for(int i=0;i<id.length;i++){
  			QualityLiaison qualityLiaison=qualityLiaisonManager.get(id[i]);
  			qualityLiaison.setCheckStatus(qualityLiaisonManager.NOTE_DSH);
  			qualityLiaisonManager.save(qualityLiaison);
  		}
		map.put("success", true);
  		return map;
   	}
  	/**
	 * 审核
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "check")
	@ResponseBody
	public HashMap<String, Object> check(HttpServletRequest request) throws Exception {
		try {
			CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息		
			String id = request.getParameter("id");
			String checkStatus = request.getParameter("checkStatus");
			String checkContent = request.getParameter("checkContent");
			QualityLiaison qualityLiaison = qualityLiaisonManager.get(id);
			if (qualityLiaison!=null) {
				qualityLiaison.setCheckStatus(checkStatus);	// 审核状态（结果）
				qualityLiaison.setCheckContent(checkContent);	// 审核意见
				qualityLiaison.setCheckUserId(curUser.getId());	// 审核人ID
				qualityLiaison.setCheckUserName(curUser.getName());	// 审核人姓名
				qualityLiaison.setCheckDate(new Date());	// 审核时间
				qualityLiaisonManager.save(qualityLiaison);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("通知书审核失败，请重试！");
		}
		return JsonWrapper.successWrapper();
	}
	/**
	 * 打印
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "doPrint")
	@ResponseBody
	public HashMap<String, Object> doPrint(HttpServletRequest request) throws Exception {
		try {
			String id = request.getParameter("id");
			QualityLiaison qualityLiaison = qualityLiaisonManager.get(id);
			if (qualityLiaison!=null) {
				qualityLiaison.setPrintStatus("Y");	// 已打印
				qualityLiaisonManager.save(qualityLiaison);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonWrapper.successWrapper();
	}
}