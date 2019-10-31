package com.lsts.qualitymanage.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.lsts.qualitymanage.bean.QualitySgcjjybg;
import com.lsts.qualitymanage.bean.SgcjjybgNum;
import com.lsts.qualitymanage.dao.SgcjjybgNumDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("SgcjjybgNum")
public class SgcjjybgNumManager extends EntityManageImpl<SgcjjybgNum,SgcjjybgNumDao>{
    @Autowired
    SgcjjybgNumDao sgcjjybgNumDao;
    
//    public static void main(String[] args){
//    	  String str = "weicc-2010-0001"; 
//    	  str = str.substring(str.length()-9,str.length());
//    	  System.out.println(1+"1");//输出
//    	}
    /**
	 * 生成手工报告编号
	 * @throws ParseException 
	 * */
	public synchronized void savesgcjjybgNum(SgcjjybgNum sgcjjybgNum,CurrentSessionUser user) throws ParseException{
		
		//新增保存时，生成新编号，修改功能不需要重新生成编号
		if(null==sgcjjybgNum.getId() || "".equals(sgcjjybgNum.getId())){
			String docNum = "";
			Calendar a=Calendar.getInstance();
 		int nowYear = a.get(Calendar.YEAR);
 		List<SgcjjybgNum> sgcjjybgNumlist = sgcjjybgNumDao.getTaskAllot();
 		if(sgcjjybgNumlist==null || sgcjjybgNumlist.size()==0) {
 			docNum = sgcjjybgNum.getFwbjhbgNum()+nowYear+"-"+"0001";
 		} else {
 			int[] docNumArray = new int[sgcjjybgNumlist.size()];
 			for (int i=0;i<sgcjjybgNumlist.size();i++) {
 				//将编号去掉“-”，转换成int型存入数组
 				if(sgcjjybgNumlist.get(i).getIdentifierType()!=null && !"".equals(sgcjjybgNumlist.get(i).getIdentifierType())){
 					String str =sgcjjybgNumlist.get(i).getIdentifierType();
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
 	 			docNum = sgcjjybgNum.getFwbjhbgNum()+nowYear+"-"+"0001";
 	 		}else{
	 			//编号加1后重新组
	 			docNum = sgcjjybgNum.getFwbjhbgNum()+docNum1.substring(0, 4)+"-"+docNum1.substring(4, 8);
 	 		}
 		}
 		sgcjjybgNum.setFwbjhbgNum(docNum);
		}
		sgcjjybgNum.setRegistrant(user.getName());
   		sgcjjybgNum.setRegistrantId(user.getId());
   		sgcjjybgNum.setRegistrantDate(new Date());
   		
		sgcjjybgNumDao.save(sgcjjybgNum);
	}
}
