package com.lsts.equipment2.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.lsts.equipment2.bean.PpeLoan;
import com.lsts.equipment2.bean.PpeLoanSub;
import com.lsts.equipment2.service.PpeLoanManager;
import com.lsts.equipment2.service.PpeLoanSubManager;
import com.lsts.qualitymanage.bean.QualityZssq;
import com.lsts.qualitymanage.service.QualitySgcjjybgManager;


@Controller
@RequestMapping("PpeLoanAction")
public class PpeLoanAction extends SpringSupportAction<PpeLoan, PpeLoanManager> {

    @Autowired
    private PpeLoanManager  ppeLoanManager;
    @Autowired
    private PpeLoanSubManager  ppeLoanSubManager;
    /**
     * 保存借用信息及相关联资产信息
     * @param request
     * @param ppeLoan
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "saveLoan")
	@ResponseBody
   	public HashMap<String, Object> saveLoan(HttpServletRequest request,@RequestBody PpeLoan ppeLoan) throws Exception {
   		CurrentSessionUser curUser = this.getCurrentUser(); //获取当前用户登录信息
   		String pageStatus = request.getParameter("pageStatus");//获取状态
   		String status = request.getParameter("status");//获取登记表初始状态
   		ppeLoan.setStatus(status);
   		List<PpeLoanSub> ppeLoanSubs = ppeLoan.getPpeLoanSubs();
   		try {
   			if (pageStatus.equals("add")) {
   				ppeLoan.setCreateDate(new Date());
   				ppeLoan.setCreateId(curUser.getId());
   				ppeLoan.setCreateBy(curUser.getName());
   			}else if(pageStatus.equals("modify")) {
   				ppeLoan.setLastModifyDate(new Date());
   				ppeLoan.setLastModifyId(curUser.getId());
   				ppeLoan.setLastModifyBy(curUser.getName());
   			}
   			ppeLoan.setIdentifier(ppeLoanManager.initNum(ppeLoan));//赋值编号
   			ppeLoanManager.saveAll(ppeLoan,ppeLoanSubs);
   		} catch (Exception e) {
   			e.printStackTrace();
   			return JsonWrapper.failureWrapperMsg("保存登记表失败，请重试！");
   		}
   		return JsonWrapper.successWrapper(ppeLoan);
   	}
    /**
     * 查询借用信息及相关联资产信息
     * @param request
     * @param ppeLoan
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "detailLoan")
	@ResponseBody
   	public HashMap<String, Object> detailLoan(HttpServletRequest request,String id) throws Exception {
    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
    	List<PpeLoanSub> ppeLoanSubs=new ArrayList<PpeLoanSub>();
    	PpeLoan ppeLoan=ppeLoanManager.get(id);
    	ppeLoanSubs = ppeLoanSubManager.getPpeLoanSubList(id);
    	ppeLoan.setPpeLoanSubs(ppeLoanSubs);
    	wrapper.put("success", true);
    	wrapper.put("ppeLoan", ppeLoan);
    	wrapper.put("ppeLoanSubs", ppeLoanSubs);
   		return wrapper;
   	}
    @Override
   	public HashMap<String, Object> delete(String ids) throws Exception {
   		HashMap<String, Object> map = new HashMap<String, Object>();
   		String numbers=null;
   		for(String id:ids.split(",")){
   			PpeLoan ppeLoan=ppeLoanManager.get(id);
   			if(!ppeLoan.getStatus().equals("WJC")){
   				numbers=numbers+ppeLoan.getIdentifier()+",";
			}else{
				ppeLoanManager.delete(id);
			}
   		}
   		if(numbers!=null){
   			map.put("msg", "\""+numbers.substring(4, numbers.length()-1)+"\"删除不成功！");
			map.put("success", false);
   		}else{
   			map.put("msg", "数据删除成功！");
			map.put("success", true);
   		}
   		return map;
   		//return JsonWrapper.successWrapper();
   	}
    @RequestMapping(value = "saveBack")
	@ResponseBody
   	public HashMap<String, Object> saveBack(HttpServletRequest request,@RequestBody PpeLoan ppeLoan) throws Exception {
    	try {
    		Date backDate=null;
    		ppeLoanSubManager.saveBack(ppeLoan);
    		if(ppeLoanSubManager.checkBackAll(ppeLoan.getId())){
    			ppeLoan.setStatus("QBGH");
    		}else{
    			ppeLoan.setStatus("BFGH");
    		}
    		List<PpeLoanSub> ppeLoanSubs = ppeLoanSubManager.getPpeLoanSubList(ppeLoan.getId());
    		for(PpeLoanSub ppeLoanSub:ppeLoanSubs){
    			Date tempDate=ppeLoanSub.getBackDate();
    			backDate=tempDate!=null?(backDate!=null?(tempDate.getTime()>backDate.getTime()?tempDate:backDate) :tempDate) :backDate;
    		}
    		ppeLoan.setBackDate(backDate);
    		ppeLoanManager.save(ppeLoan);
   		} catch (Exception e) {
   			e.printStackTrace();
   			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
   		}
   		return JsonWrapper.successWrapper(ppeLoan);
   	}
}