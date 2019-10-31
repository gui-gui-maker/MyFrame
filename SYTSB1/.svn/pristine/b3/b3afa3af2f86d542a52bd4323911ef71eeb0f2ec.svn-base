package com.lsts.archives.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.lsts.archives.bean.ArchivesDestroy;
import com.lsts.archives.dao.ArchivesDestroyDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("archivesDestroyManager")
public class ArchivesDestroyManager extends EntityManageImpl<ArchivesDestroy,ArchivesDestroyDao>{
    @Autowired
    ArchivesDestroyDao archivesDestroyDao;
    
    
    
    
	/**
  	 * 生成修改单编号
  	 * */
  	public void saveTask(ArchivesDestroy archivesDestroy,CurrentSessionUser user){
		//新增保存时，生成新编号，修改功能不需要重新生成编号
		if(null==archivesDestroy.getId() || "".equals(archivesDestroy.getId())){
			String docNum = "";
			Calendar a=Calendar.getInstance();
    		int nowYear = a.get(Calendar.YEAR);
    		List<ArchivesDestroy> archivesDestroylist = archivesDestroyDao.getTaskAllot();
    		if(archivesDestroylist==null || archivesDestroylist.size()==0) {
    			docNum = nowYear+"-"+"001";
    		} else {
    			int[] docNumArray = new int[archivesDestroylist.size()];
    			for (int i=0;i<archivesDestroylist.size();i++) {
    				//将编号去掉“-”，转换成int型存入数组
    				docNumArray[i] = Integer.parseInt(archivesDestroylist.get(i).getIdentifier().replaceAll("-", ""));
    			}
    			int max = docNumArray[0];
    			//获取数组中最大的值
    			for (int i : docNumArray) {
    				max = max>i?max:i;
    			}
    			String docNum1 = String.valueOf(max+1);
    			if(nowYear>Integer.parseInt(docNum1.substring(0, 4))) {
     	 			docNum = nowYear+"-"+"001";
     	 		}else{
	    			//编号加1后重新组
	    			docNum = docNum1.substring(0, 4)+"-"+docNum1.substring(4, 7);
     	 		}
    		}
    		archivesDestroy.setIdentifier(docNum);
		}
		archivesDestroy.setRegistrant(user.getName());
		archivesDestroy.setRegistrantTime(new Date());
		archivesDestroy.setRegistrantId(user.getId());
   		//改变状态
		archivesDestroy.setStatus(ArchivesBorrowManager.DA_JYSQ_WTJ);
   		archivesDestroyDao.save(archivesDestroy);
	}
}
