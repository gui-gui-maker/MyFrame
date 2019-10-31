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
import com.lsts.qualitymanage.bean.QualityNote;
import com.lsts.qualitymanage.bean.QualityZssq;
import com.lsts.qualitymanage.bean.Tyfabh;
import com.lsts.qualitymanage.service.QualityNoteManager;
import com.lsts.qualitymanage.service.TyfabhManager;


@Controller
@RequestMapping("QualityNoteAction")
public class QualityNoteAction extends SpringSupportAction<QualityNote, QualityNoteManager> {

    @Autowired
    private QualityNoteManager  qualityNoteManager;
    
    @Override
	public HashMap<String, Object> save(HttpServletRequest request, QualityNote qualityNote) throws Exception {
		CurrentSessionUser curUser = this.getCurrentUser(); //获取当前用户登录信息
		String pageStatus = request.getParameter("pageStatus");//获取状态
		try {
			if (pageStatus.equals("add")) {
				qualityNote.setIdentifier(qualityNoteManager.initIdentifier());//生成编号
				qualityNote.setCheckStatus(qualityNoteManager.NOTE_WTJ);
				qualityNote.setPrintStatus(qualityNoteManager.NOTE_NO_PRINT);
				qualityNote.setCreateDate(new Date());
				qualityNote.setCreateId(curUser.getId());
				qualityNote.setCreateBy(curUser.getName());
			}else if(pageStatus.equals("modify")) {
				qualityNote.setLastModifyDate(new Date());
				qualityNote.setLastModifyId(curUser.getId());
				qualityNote.setLastModifyBy(curUser.getName());
			}
			qualityNoteManager.save(qualityNote);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存联络单失败，请重试！");
		}
		return JsonWrapper.successWrapper(qualityNote);
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
  			QualityNote qualityNote=qualityNoteManager.get(id[i]);
  			qualityNote.setCheckStatus(qualityNoteManager.NOTE_DSH);
  			qualityNoteManager.save(qualityNote);
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
			QualityNote qualityNote = qualityNoteManager.get(id);
			if (qualityNote!=null) {
				qualityNote.setCheckStatus(checkStatus);	// 审核状态（结果）
				qualityNote.setCheckContent(checkContent);	// 审核意见
				qualityNote.setCheckUserId(curUser.getId());	// 审核人ID
				qualityNote.setCheckUserName(curUser.getName());	// 审核人姓名
				qualityNote.setCheckDate(new Date());	// 审核时间
				qualityNoteManager.save(qualityNote);
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
			QualityNote qualityNote = qualityNoteManager.get(id);
			if (qualityNote!=null) {
				qualityNote.setPrintStatus("Y");// 已打印
				qualityNoteManager.save(qualityNote);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonWrapper.successWrapper();
	}
	/**
	 * 保存盖章信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveSealInfo")
	@ResponseBody
	public HashMap<String, Object> saveSealInfo(HttpServletRequest request) throws Exception {
		try {
			CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息	
			String id = request.getParameter("id");
			QualityNote qualityNote = qualityNoteManager.get(id);
			if (qualityNote!=null) {
				qualityNote.setSealDate(new Date());
				qualityNote.setSealPeopleId(curUser.getId());
				qualityNote.setSealPeopleName(curUser.getName());
				qualityNote.setSealStatus("Y");// 已盖章
				qualityNoteManager.save(qualityNote);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonWrapper.successWrapper();
	}
}