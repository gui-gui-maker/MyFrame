package com.lsts.archives.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.lsts.archives.bean.ArchivesBorrow;
import com.lsts.archives.bean.ArchivesPrint;
import com.lsts.archives.dao.ArchivesPrintDao;
import com.lsts.log.bean.SysLog;
import com.lsts.log.dao.SysLogDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("archivesPrint")
public class ArchivesPrintManager extends EntityManageImpl<ArchivesPrint,ArchivesPrintDao>{
    @Autowired
    ArchivesPrintDao archivesPrintDao;
    @Autowired
    private SysLogDao sysLogDao;
    /**
  	 * 生成修改单编号
  	 * */
  	public void saveTask(ArchivesPrint archivesPrint,CurrentSessionUser user){
		//新增保存时，生成新编号，修改功能不需要重新生成编号
		if(null==archivesPrint.getId() || "".equals(archivesPrint.getId())){
			String docNum = "";
			Calendar a=Calendar.getInstance();
    		int nowYear = a.get(Calendar.YEAR);
    		List<ArchivesPrint> archivesPrintlist = archivesPrintDao.getTaskAllot();
    		if(archivesPrintlist==null || archivesPrintlist.size()==0) {
    			docNum = "CTJC-028-B03-"+nowYear+"-"+"0001";
    		} else {
    			int[] docNumArray = new int[archivesPrintlist.size()];
    			for (int i=0;i<archivesPrintlist.size();i++) {
    				//将编号去掉“-”，转换成int型存入数组
     				if(archivesPrintlist.get(i).getIdentifier()!=null && !"".equals(archivesPrintlist.get(i).getIdentifier())){
						String str =archivesPrintlist.get(i).getIdentifier();
				  		StringBuffer sb=new StringBuffer(str);
						//将编号去掉“-”，转换成int型存入数组
						docNumArray[i] = Integer.parseInt(sb.substring(13,22).replaceAll("-", ""));
     				}
    			}
    			int max = docNumArray[0];
    			//获取数组中最大的值
    			for (int i : docNumArray) {
    				max = max>i?max:i;
    			}
    			String docNum1 = String.valueOf(max+1);
     			if(nowYear>Integer.parseInt(docNum1.substring(0, 4))) {
     	 			docNum = "CTJC-028-B03-"+nowYear+"-"+"0001";
     	 		}else{
    	 			//编号加1后重新组
    	 			docNum = "CTJC-028-B03-"+docNum1.substring(0, 4)+"-"+docNum1.substring(4, 8);
     	 		}
    		}
    		archivesPrint.setIdentifier(docNum);
		}
		//设置当前登记人
   		archivesPrint.setRegistrant(user.getName());
   		archivesPrint.setRegistrantId(user.getId());
   		archivesPrint.setRegistrantTime(new Date());
   		archivesPrint.setStatus(ArchivesBorrowManager.DA_JYSQ_WTJ);
		
   		archivesPrintDao.save(archivesPrint);
	}
  	public HashMap<String, Object>  getFlowStep(String id) throws Exception{	
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<SysLog> list = sysLogDao.getBJLogs(id);
		map.put("flowStep", list);
		map.put("size", list.size());
		map.put("identifier", archivesPrintDao.get(id).getIdentifier());
		map.put("success", true);
		
		return map;
    }
}
