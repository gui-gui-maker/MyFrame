package com.lsts.office.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.khnt.security.util.SecurityUtil;
import com.lsts.office.bean.Ywhbsgz;
import com.lsts.office.bean.YwhbsgzFk;
import com.lsts.office.service.YwhbsgzManager;
import com.lsts.office.service.Ywhbsgz_fkManager;

@Controller
@RequestMapping("office/ywhbsgz_fkAction")
public class Ywhbsgz_fkAction extends SpringSupportAction<YwhbsgzFk, Ywhbsgz_fkManager> {

    @Autowired
    private Ywhbsgz_fkManager  ywhbsgz_fkManager;
    @Autowired
    private YwhbsgzManager  ywhbsgzManager;
    
    @RequestMapping(value="fdetail1")
    @ResponseBody
    public HashMap<String, Object> getDetail(String id) throws Exception{
    	List<YwhbsgzFk> list = ywhbsgz_fkManager.getList(id);
    	return JsonWrapper.successWrapper("Rows",list);
    }
  
    /**
     * 保存反馈信息
     * @param request
     * @param weightyDep
     * @return
     */
    @RequestMapping(value = "saveWei")
   	@ResponseBody
       public HashMap<String,Object> saveWei(HttpServletRequest request, YwhbsgzFk ywhbsgzFk){
       	HashMap<String, Object> map = new HashMap<String, Object>();
       	try {
       		ywhbsgz_fkManager.saveWei(ywhbsgzFk);
       	} catch (Exception e) {
   			e.printStackTrace();
   		}
       	map.put("success", true);
   		return map;
       }
      
    /**
     * 微信保存反馈信息
     * @param request
     * @param weightyDep
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "saveWXFK")
   	@ResponseBody
       public HashMap<String,Object> saveWXFK(HttpServletRequest request) throws UnsupportedEncodingException{
       	HashMap<String, Object> map = new HashMap<String, Object>();
       	String id = request.getParameter("id");
       	String status1 = request.getParameter("status1");
       	String notCompleteReason1 = request.getParameter("notCompleteReason");
       	String notCompleteReason = URLDecoder.decode(notCompleteReason1,"utf-8");
       	String feedbackRemark1 = request.getParameter("feedbackRemark");
       	String feedbackRemark = URLDecoder.decode(feedbackRemark1,"utf-8");
       	//get the object of Ywhbsgz
       	Ywhbsgz ywhbsgz = ywhbsgzManager.get(id);
       	ywhbsgz.setStatus(status1);
       	if(status1.equals("WWC")){
       		ywhbsgz.setUnfinishedReason(notCompleteReason);
       	}else{
       		ywhbsgz.setPerformance(feedbackRemark);
       	}
       	try {
       		ywhbsgz_fkManager.saveWXFK(ywhbsgz);
       	} catch (Exception e) {
   			e.printStackTrace();
   		}
       	map.put("success", true);
   		return map;
       }
      
  
    
    
}