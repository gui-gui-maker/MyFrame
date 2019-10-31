package com.fwxm.scientific.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fwxm.scientific.bean.Tjy2ScientificFiles;
import com.fwxm.scientific.dao.Tjy2ScientificFilesDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.utils.StringUtil;
import com.lsts.archives.bean.ArchivesFile;
import com.lsts.qualitymanage.bean.QualityAttachment;
import com.lsts.qualitymanage.bean.QualityFiles;
import com.lsts.qualitymanage.dao.QualityAttachmentDao;
import com.lsts.qualitymanage.dao.QualityFilesDao;
import com.lsts.qualitymanage.dao.QualityUpdateFileDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service("tjy2ScientificFilesManager")
public class Tjy2ScientificFilesManager extends EntityManageImpl<Tjy2ScientificFiles,Tjy2ScientificFilesDao>{
    @Autowired
    Tjy2ScientificFilesDao tjy2ScientificFilesDao;
    @Autowired
    QualityAttachmentDao qualityAttachmentDao;
    @Autowired
    QualityUpdateFileDao qualityUpdateFileDao;
    @Autowired
   	private AttachmentManager attachmentManager;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    /**
	 * 
	 * */
	public void settxwj(String id) throws Exception{
		String yy=tjy2ScientificFilesDao.getbusiness_id(id).toString();//业务id
		String ids = Pattern.compile("\\b([\\w\\W])\\b").matcher(yy.toString()
		    	.substring(1,yy.toString().length()-1)).replaceAll("'$1'");
		qualityUpdateFileDao.setstatus(ids);//设置不启用
		qualityUpdateFileDao.setstatus2(id);//启用这个

		String uu=tjy2ScientificFilesDao.getfileName(id).toString();//文件名称
		String fileName = Pattern.compile("\\b([\\w\\W])\\b").matcher(uu.toString()
		    	.substring(1,uu.toString().length()-1)).replaceAll("'$1'");
		Tjy2ScientificFiles qualityFiles=tjy2ScientificFilesDao.get(ids);
		StringBuffer m=new StringBuffer(); 
		String tt=fileName.replaceAll("\'", " ");  
		System.out.println(m);	
			String a=tt;
			String arr[] = a.split("（");
			System.out.println(arr[0]);
			qualityFiles.setFileId(arr[0]);//编号
			
			String arr1[] = a.split("）");
			System.out.println(arr1[0]);
			String arr2[] = arr1[0].split("（");
	    	String strs = arr2[0].substring(arr2[0].length()-8,arr2[0].length());
	    	Pattern pattern = Pattern.compile("[0-9]*");
	    	Matcher isNum = pattern.matcher(strs);
	    	if(isNum.matches()) {
	    		StringBuffer sb=new StringBuffer(strs);
	    		String a1=strs.substring(strs.length()-2,strs.length());
	    		String b=sb.substring(4,6);
	    		String c=sb.substring(0,4);
	    		String e=c+"-"+b+"-"+a1;
	    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
	    		java.util.Date date=sdf.parse(e);//时间
	    		qualityFiles.setImplementDate(date);
	    	}else{
	    		qualityFiles.setImplementDate(null);//时间
	    	}
	    	
	    	String str31=a.substring(a.indexOf("."));
	    	StringBuffer sb=new StringBuffer(str31);
	    	System.out.println(str31);
	    	String ss=sb.substring(1,2);
	    	if(ss.equals("d")){
	    		String str3[] = a.split("d");
	    		qualityFiles.setFileName(str3[0].substring(0,str3[0].length()-1));
	    	}else if(ss.equals("p")){
	    		String str3[] = a.split("p");
	    		qualityFiles.setFileName(str3[0].substring(0,str3[0].length()-1));
	    	}
	    	this.save(qualityFiles);
	}
       
    /**
     * 保存信息（含附件）
     * */
    	public void saveEquipments(Tjy2ScientificFiles entity, String uploadFiles,String wjid) throws Exception{
    		tjy2ScientificFilesDao.save(entity);
    		if(StringUtil.isNotEmpty(uploadFiles)){
    			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
    			for(String file : files){
    				if (StringUtil.isNotEmpty(file)) {
    					attachmentManager.setAttachmentBusinessId(file, entity.getId());
    				}
    			}
    		}
    		
			@SuppressWarnings("rawtypes")
			List list=tjy2ScientificFilesDao.getwj(uploadFiles);
    		List<QualityAttachment> QualityAttachmentList=new ArrayList<QualityAttachment>();
  			for (int u = 0; u < list.size(); u++) {
  				Object qualityAttachment[] = null;
  				QualityAttachment qualityAttachment1=new QualityAttachment();
  				qualityAttachment= (Object[]) list.get(u);
  				if(qualityAttachment[1] != null){
  					qualityAttachment1.setFileName(qualityAttachment[1].toString());
  				}
  				if(qualityAttachment[2] != null){
  					qualityAttachment1.setFileType(qualityAttachment[2].toString());
  				}
  				if(qualityAttachment[3] != null){
  					qualityAttachment1.setFileSize(Long.valueOf(qualityAttachment[3].toString()));
  				}
  				if(qualityAttachment[4] != null){
  					qualityAttachment1.setFilePath(qualityAttachment[4].toString());
  				}
  				if(qualityAttachment[5] != null){
  					qualityAttachment1.setFileBody((byte[])qualityAttachment[5]);
  				}
  				if(wjid.isEmpty()){
  	  				qualityAttachment1.setBusinessId(entity.getId());
  	  				qualityAttachment1.setStatus("1");
  				}else{
  					qualityAttachment1.setBusinessId(wjid);
  					qualityAttachment1.setStatus("0");
  				}
  				if(qualityAttachment[7] != null){
  					qualityAttachment1.setUploadTime(sdf.parse(qualityAttachment[7].toString()));
  				}
  				if(qualityAttachment[8] != null){
  					qualityAttachment1.setUploader(qualityAttachment[8].toString());
  				}
  				if(qualityAttachment[9] != null){
  					qualityAttachment1.setUploaderName(qualityAttachment[9].toString());
  				}
  				if(qualityAttachment[10] != null){
  					qualityAttachment1.setWorkItem(qualityAttachment[10].toString());
  				}
  				if(qualityAttachment[11] != null){
  					qualityAttachment1.setSaveType(qualityAttachment[11].toString());
  				}
  				if(qualityAttachment[12] != null){
  					qualityAttachment1.setBusUniqueName(qualityAttachment[12].toString());
  				}
  				if(qualityAttachment[13] != null){
  					qualityAttachment1.setJy_pic_category(qualityAttachment[13].toString());
  				}
  				QualityAttachmentList.add(qualityAttachment1);
  				qualityAttachmentDao.save(qualityAttachment1);
  			}
  			if(wjid.isEmpty()){
  				
  			}else{
  				this.deleteBusiness(entity.getId());
  			}
    	}
	
	
}
