package com.lsts.qualitymanage.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.qualitymanage.bean.QualityFfhs;
import com.lsts.qualitymanage.dao.QualityFfhsDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("QualityFfhsManager")
public class QualityFfhsManager extends EntityManageImpl<QualityFfhs,QualityFfhsDao>{
    @Autowired
    QualityFfhsDao qualityFfhsDao;
    @Autowired
   	private AttachmentManager attachmentManager;
    
    
    public final static String ZL_FFHS_WTJ = "WTJ";// 未提交
    public final static String ZL_FFHS_YTJ = "YTJ"; // 已提交
	public final static String ZL_FFHS_YJS = "YJS";// 已接受
    /**
   	 * 生成修改单编号
   	 * @throws ParseException 
   	 * */
   	public synchronized void saveQualityFfhs(QualityFfhs qualityFfhs,CurrentSessionUser user,String uploadFiles) throws ParseException{
   		//新增保存时，生成新编号，修改功能不需要重新生成编号
   		if(null==qualityFfhs.getId() || "".equals(qualityFfhs.getId())){
   			String docNum = "";
   			Calendar a=Calendar.getInstance();
    		int nowYear = a.get(Calendar.YEAR);
    		List<QualityFfhs> qualityFfhslist = qualityFfhsDao.getTaskAllot();
    		if(qualityFfhslist==null || qualityFfhslist.size()==0) {
    			docNum = "CTJC-001-B04-"+nowYear+"-"+"0001";
    		} else {
    			int[] docNumArray = new int[qualityFfhslist.size()];
    			for (int i=0;i<qualityFfhslist.size();i++) {
    				//将编号去掉“-”，转换成int型存入数组
    				if(qualityFfhslist.get(i).getIdentifier()!=null && !"".equals(qualityFfhslist.get(i).getIdentifier())){
    					String str =qualityFfhslist.get(i).getIdentifier();
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
    	 			docNum = "CTJC-001-B04-"+nowYear+"-"+"0001";
    	 		}else{
   	 			//编号加1后重新组
   	 			docNum = "CTJC-001-B04-"+docNum1.substring(0, 4)+"-"+docNum1.substring(4, 8);
    	 		}
    		}
    		qualityFfhs.setIdentifier(docNum);
    		qualityFfhs.setStatus("WTJ");
    		//获取当前登录人姓名
    		qualityFfhs.setRegistrant(user.getName());
    		qualityFfhs.setRegistrantId(user.getId());
    		qualityFfhs.setRegistrantDate(new Date());
   		}
   		
   		qualityFfhsDao.save(qualityFfhs);
   		//保存附件
   		if(StringUtil.isNotEmpty(uploadFiles)){
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for(String file : files){
				if (StringUtil.isNotEmpty(file)) {
					attachmentManager.setAttachmentBusinessId(file, qualityFfhs.getId());
				}
			}
		}	
   	}
}
