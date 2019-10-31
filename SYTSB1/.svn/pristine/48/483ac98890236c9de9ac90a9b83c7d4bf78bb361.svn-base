package com.lsts.qualitymanage.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.qualitymanage.bean.QualitySgcjjybg;
import com.lsts.qualitymanage.bean.SgcjjybgNum;
import com.lsts.qualitymanage.bean.Tyfabh;
import com.lsts.qualitymanage.dao.SgcjjybgNumDao;
import com.lsts.qualitymanage.service.QualitySgcjjybgManager;
import com.lsts.qualitymanage.service.SgcjjybgNumManager;
import com.lsts.qualitymanage.service.TyfabhManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;








import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("sgcjjybgNum/a")
public class SgcjjybgNumAction extends SpringSupportAction<SgcjjybgNum, SgcjjybgNumManager> {

    @Autowired
    private SgcjjybgNumManager  sgcjjybgNumManager;
    @Autowired
    private SgcjjybgNumDao  sgcjjybgNumDao;
    @Autowired
    private QualitySgcjjybgManager  qualitySgcjjybgManager;

    
//  public static void main(String[] args){
//	  String str = "weicc-2010-0001"; 
//	  str = str.substring(str.length()-9,str.length());
//	  System.out.println(str);//输出
//	}
    
    /**
  	 * 提交
  	 * */
  	@RequestMapping(value = "savefa")
   	@ResponseBody
   	public HashMap<String, Object> savefa(HttpServletRequest request,@RequestBody SgcjjybgNum sgcjjybgNum) throws Exception{
  		HashMap<String, Object> map = new HashMap<String, Object>();
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();

		sgcjjybgNum.setRegistrant(user.getName());
   		sgcjjybgNum.setRegistrantId(user.getId());
   		sgcjjybgNum.setRegistrantDate(new Date());
	   		
	   	sgcjjybgNumManager.save(sgcjjybgNum);
		map.put("success", true);
  		return map;
   	}
    /**
   	 * 添加
   	 * @param response
        * @throws Exception 
   	 */
       @Override
   	public HashMap<String, Object> save(HttpServletRequest request,@RequestBody SgcjjybgNum sgcjjybgNum) throws Exception {
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		String fid=request.getParameter("fid");
   		String xzbh=request.getParameter("xzbh");//1到7分别为7个按钮
   		int njts = 0;
   		QualitySgcjjybg qualitySgcjjybg=qualitySgcjjybgManager.get(fid);
   		if(xzbh.equals("1")){
   			njts=Integer.parseInt(qualitySgcjjybg.getNjts());
   		}else if(xzbh.equals("2")){
   			njts=Integer.parseInt(qualitySgcjjybg.getNjts2());
   		}else if(xzbh.equals("3")){
   			njts=Integer.parseInt(qualitySgcjjybg.getNjts3());
   		}else if(xzbh.equals("4")){
   			njts=Integer.parseInt(qualitySgcjjybg.getNjts4());
   		}else if(xzbh.equals("5")){
   			njts=Integer.parseInt(qualitySgcjjybg.getNjts5());
   		}else if(xzbh.equals("6")){
   			njts=Integer.parseInt(qualitySgcjjybg.getNjts6());
   		}else if(xzbh.equals("7")){
   			njts=Integer.parseInt(qualitySgcjjybg.getNjts7());
   		}
   		HashMap<String, Object> map = new HashMap<String, Object>();
   		//sgcjjybgNum.getFwbjhbgNum();
   		
   		for (int ii = 0; ii < njts; ii++) {
   			//if(null==sgcjjybgNum.getId() || "".equals(sgcjjybgNum.getId())){
   				String docNum = "";
   				Calendar a=Calendar.getInstance();
   	 		int nowYear = a.get(Calendar.YEAR);
   	 		List<SgcjjybgNum> sgcjjybgNumlist = sgcjjybgNumDao.getTaskAllot();
   	 		if(sgcjjybgNumlist==null || sgcjjybgNumlist.size()==0) {
   	 			docNum = sgcjjybgNum.getFwbjhbgNum()+"-"+nowYear+"-"+"0001";
   	 		} else {
   	 			int[] docNumArray = new int[sgcjjybgNumlist.size()];
   	 			for (int i=0;i<sgcjjybgNumlist.size();i++) {
   	 				//将编号去掉“-”，转换成int型存入数组
   	 				if(sgcjjybgNumlist.get(i).getIdentifierType()!=null && !"".equals(sgcjjybgNumlist.get(i).getIdentifierType())){
   	 					String str =sgcjjybgNumlist.get(i).getFwbjhbgNum();
   	    				//将编号去掉“-”，转换成int型存入数组
   	    				docNumArray[i] = Integer.parseInt(str.substring(str.length()-9,str.length()).replaceAll("-", ""));
   	 				}
   	 			}
   	 			int max = docNumArray[0];
   	 			//获取数组中最大的值
   	 			for (int i : docNumArray) {
   	 				max = max>i?max:i;
   	 			}
   	 			String docNum1 = String.valueOf(max+1);
   	 			if(nowYear>Integer.parseInt(docNum1.substring(0, 4))) {
   	 	 			docNum = sgcjjybgNum.getFwbjhbgNum()+"-"+nowYear+"-"+"0001";
   	 	 		}else{
   		 			//编号加1后重新组
   		 			docNum = sgcjjybgNum.getFwbjhbgNum()+"-"+docNum1.substring(0, 4)+"-"+docNum1.substring(4, 8);
   	 	 		}
   	 		}
   	 		sgcjjybgNum.setFwbjhbgNum(docNum);
   			//}
   			sgcjjybgNum.setRegistrant(user.getName());
   	   		sgcjjybgNum.setRegistrantId(user.getId());
   	   		sgcjjybgNum.setRegistrantDate(new Date());
   	   		
   	   	sgcjjybgNumManager.save(sgcjjybgNum);
   			//sgcjjybgNumManager.savesgcjjybgNum(sgcjjybgNum,user);
			
		}
   		map.put("success", true);
   		return map;
   		
    }
}