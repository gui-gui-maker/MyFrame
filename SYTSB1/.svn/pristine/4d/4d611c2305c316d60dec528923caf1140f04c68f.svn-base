package com.lsts.equipment2.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.DocimasyFk;
import com.lsts.equipment2.dao.DocimasyFkDao;
import com.lsts.office.bean.Ywhbsgz;
import com.lsts.office.service.WeightyTaskManager;


@Service("docimasyFkManager")
public class DocimasyFkManager extends EntityManageImpl<DocimasyFk, DocimasyFkDao> {
	@Autowired
	DocimasyFkDao docimasyFkDao;
	@Autowired
	private AttachmentManager attachmentManager;
	  public final static String JD_WXF="wxf";//未下发
	  public final static String JD_WKS="wks"; //未开始
	  public final static String JD_YWC="ywc"; //已完成、、
	  public final static String JD_JXZ="jxz"; //进行中、、
	  public final static String JD_WWC="wwc"; //未完成、、
	
	/**
	 * 级联删除写法
	 * @param ids
	 */
	public void delete(String ids) {
		Session session = docimasyFkDao.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		for(String id:ids.split(",")){
			DocimasyFk docimasyFk = docimasyFkDao.get(id);
			session.delete(docimasyFk);
		}
	}
 
	public void addxf(DocimasyFk docimasyFk) throws Exception{
		docimasyFk.setStatus(JD_WKS);//下发
		docimasyFkDao.save(docimasyFk);
	}
	
	public void addjs(DocimasyFk docimasyFk) throws Exception{
		docimasyFk.setStatus(JD_JXZ);//接受
		docimasyFkDao.save(docimasyFk);
	}

	public Date setStatus(String uploadFiles,DocimasyFk docimasyFk, String status, Date practicalTime) {
		// TODO Auto-generated method stub
	    //获取实际检定日期加上检定周期获得下次检定日期
		Calendar calendar = Calendar.getInstance();
	    docimasyFk.setPracticalTime(practicalTime);//获得检定时间
	    calendar.setTime(practicalTime);//
	    calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1); //减去一天
	    java.math.BigDecimal number1 = docimasyFk.getPeriod();//获得周期（月份）
        if(number1==null){
        	docimasyFk.setNextTime(null);//生成下次检定日期
        }else if(practicalTime==null){
        	docimasyFk.setNextTime(null);//生成下次检定日期
    	}else{
    		//当不为空才执行
	        String number = number1.toString();//转成 String 格式
	        java.math.BigDecimal    bg2 = new BigDecimal(number);//把String的周期方进来
	        int  i1 = bg2.intValue();//再转成 Int 直接转成Int会出错 
	        calendar.add(Calendar.MONTH, i1); //月     //再过几个月
	        //得到的结果日期
	        System.out.println("增加月份后的日期："+calendar.getTime());
	        docimasyFk.setNextTime(calendar.getTime());//生成下次检定日期
	        }
		 docimasyFk.setStatus(status);//检定状态
		 docimasyFkDao.save(docimasyFk);
		 if(StringUtil.isNotEmpty(uploadFiles)){
				String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
				for(String file : files){
					if (StringUtil.isNotEmpty(file)) {
						attachmentManager.setAttachmentBusinessId(file, docimasyFk.getId());
					}
				}
			}
		 return calendar.getTime();
		    
	}
	 
}
