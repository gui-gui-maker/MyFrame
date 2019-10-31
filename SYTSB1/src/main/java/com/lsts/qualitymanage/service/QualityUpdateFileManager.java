package com.lsts.qualitymanage.service;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.archives.bean.Uploads;
import com.lsts.qualitymanage.bean.QualityAttachment;
import com.lsts.qualitymanage.bean.QualityFiles;
import com.lsts.qualitymanage.bean.QualityUpdateFile;
import com.lsts.qualitymanage.dao.QualityAttachmentDao;
import com.lsts.qualitymanage.dao.QualityUpdateFileDao;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service("qualityUpdateFileManager")
public class QualityUpdateFileManager extends EntityManageImpl<QualityUpdateFile,QualityUpdateFileDao>{
    @Autowired
    QualityUpdateFileDao qualityUpdateFileDao;
    @Autowired
    QualityAttachmentDao qualityAttachmentDao;
    @Autowired
    FlowExtManager flowExtManager;
    @Autowired
   	private AttachmentManager attachmentManager;
    @Autowired
    private QualityFilesManager qualityFilesManager;
    /**状态常量*/
    public final static String ZL_XGSQ_REGISTER = "0";// 未提交
    public final static String ZL_XGSQ_SUBMIT = "1"; // 已提交
	public final static String ZL_XGSQ_AUDIT = "2";// 申请单审核中
	public final static String ZL_XGSQ_SAN = "3";//提交新体系文件
	public final static String ZL_XGSQ_SI = "4";//传递单审核中
	public final static String ZL_XGSQ_PASS = "5"; // 审核通过
	public final static String ZL_XGSQ_NO_PASS = "6"; // 审核不通过
	public final static String ZL_XGSQ_QI = "7";//传递单未提交
	public final static String ZL_XGSQ_FC = "8";//已废除
	public final static String ZL_XGSQ_XH = "9";//已销毁
	public final static String ZL_XGSQ_JS = "js";//确认接受意见
	public final static String ZL_XGSQ_FSZ = "fsz";//复审中
	public final static String ZL_XGSQ_YTH = "yth";//已退回


	public final static String ZL_PRINT  = "PRINT"; // 已打印

	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
  	 * 
	 * @throws Exception 
  	 * */
	@SuppressWarnings("rawtypes")
  	public HashMap<String, Object> getfs(String id,String ids) throws Exception{
  		HashMap<String, Object> map = new HashMap<String, Object>();
//		List list=qualityUpdateFileDao.getF(ids);
////  		if (list != null && list.size() > 0) {
//  			for (int u = 0; u < list.size(); u++) {
//  				Object qualityAttachment[] = null;
//  				QualityAttachment qualityAttachment1=new QualityAttachment();
//  				qualityAttachment= (Object[]) list.get(u);
//  				qualityAttachment1.setFileName(qualityAttachment[1].toString());
//  				qualityAttachment1.setFileType(qualityAttachment[2].toString());
//  				qualityAttachment1.setFileSize(Long.valueOf(qualityAttachment[3].toString()));
//  				qualityAttachment1.setFilePath(qualityAttachment[4].toString());
//  				qualityAttachment1.setFileBody((byte[])qualityAttachment[5]);
//  				qualityAttachment1.setBusinessId(ids);
//  				qualityAttachment1.setUploadTime(sdf.parse(qualityAttachment[7].toString()));
//  				qualityAttachment1.setUploader(qualityAttachment[8].toString());
//  				qualityAttachment1.setUploaderName(qualityAttachment[9].toString());
//  				if(qualityAttachment[10] != null){
//  					qualityAttachment1.setWorkItem(qualityAttachment[10].toString());
//  				}
//  				if(qualityAttachment[11] != null){
//  					qualityAttachment1.setSaveType(qualityAttachment[11].toString());
//  				}
//  				if(qualityAttachment[12] != null){
//  					qualityAttachment1.setBusUniqueName(qualityAttachment[12].toString());
//  				}
//  				if(qualityAttachment[13] != null){
//  					qualityAttachment1.setJy_pic_category(qualityAttachment[13].toString());
//  				}
//  				qualityAttachment1.setStatus("0");
//  				QualityAttachmentList.add(qualityAttachment1);
//  				qualityAttachmentDao.save(qualityAttachment1);
//  				map.put("QualityAttachmentList", QualityAttachmentList);
//  			}
// 		}
		List<QualityAttachment> QualityAttachmentList=new ArrayList<QualityAttachment>();
  		List list1=qualityUpdateFileDao.getFf(id);
  			for (int u = 0; u < list1.size(); u++) {
  				Object qualityAttachment[] = null;
  				QualityAttachment qualityAttachment1=new QualityAttachment();
  				qualityAttachment= (Object[]) list1.get(u);
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
  				qualityAttachment1.setBusinessId(ids);
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
  				qualityAttachment1.setStatus("1");
  				QualityAttachmentList.add(qualityAttachment1);
  				qualityUpdateFileDao.setstatus(ids);//设置不启用
  				qualityAttachmentDao.save(qualityAttachment1);
  				map.put("QualityAttachmentList1", QualityAttachmentList);
//  				qualityUpdateFileDao.setFff(ids,qualityAttachment[1].toString(),qualityAttachment[2].toString(),
//  						qualityAttachment[4].toString(),qualityAttachment[3].toString());
  				QualityFiles qualityFiles=qualityFilesManager.get(ids);
  				String a=qualityAttachment[1].toString();
  				String arr[] = a.split("（");
  				System.out.println(arr[0]);
  				qualityFiles.setFileId(arr[0]);//编号
  				
  				String arr1[] = a.split("）");
  				System.out.println(arr1[0]);
  				String arr2[] = arr1[0].split("（");
  		    	String strs = arr2[1].substring(arr2[1].length()-8,arr2[1].length());
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
  		    	qualityFilesManager.save(qualityFiles);
  			}
  			QualityUpdateFile qualityUpdateFile=this.get(id);
  			qualityUpdateFile.setStatus("TH");//替换状态
  			this.save(qualityUpdateFile);
  		return map;
  	}
//	public static void main(String[] args) throws Exception{
//	  String a = "CTJC-001-B14（20150824）手工出具检验报告证书审批表.pdf"; 
//	  String arr1[] = a.split("-");
//		System.out.println(arr1[0]);
//		String arr2[] = arr1[0].split("（");
//    	String strs = arr2[1].substring(arr2[1].length()-8,arr2[1].length());
//		System.out.println(strs);

//	  str = str.substring(str.length()-8,str.length());
//	  String arr[] = str.split("（");
//		System.out.println(arr[0]);//编号
//		String arr1[] = str.split("）");
//		System.out.println(arr1[0]);
//		String arr2[] = arr1[0].split("（");
//    	String strs = arr2[1].substring(arr2[1].length()-8,arr2[1].length());
//    	System.out.println(strs);

//	}
    /**
  	 * 提交
  	 * */
  	
  	public void doStartPress(Map<String, Object> map)throws Exception{
  		flowExtManager.startFlowProcess(map);
      }
  	

    /**
  	 * 审核
  	 * */
  	
  	public void doProcess(Map<String, Object> map)throws Exception{
  		flowExtManager.submitActivity(map);
      }
  	
  	/**
  	 * 退回
  	 * */
  	
  	public void doreturn(Map<String, Object> map)throws Exception{
  		flowExtManager.returnedActivity(map);
      }
  	
  	/**
  	 * 流程结束
  	 * */
  	public void stop(Map<String, Object> map)throws Exception{
  		flowExtManager.finishProcess(map);
      }
  	
  	/**
  	 * 生成修改单编号
  	 * */
  	public void saveTask(QualityUpdateFile qualityUpdateFile,CurrentSessionUser user){
		//新增保存时，生成新编号，修改功能不需要重新生成编号
		if(null==qualityUpdateFile.getId() || "".equals(qualityUpdateFile.getId())){
			String docNum = "";
			Calendar a=Calendar.getInstance();
    		int nowYear = a.get(Calendar.YEAR);
    		List<QualityUpdateFile> qualityUpdateFilelist = qualityUpdateFileDao.getTaskAllot();
    		if(qualityUpdateFilelist==null || qualityUpdateFilelist.size()==0) {
    			docNum =  "CTJC-001-B03-"+nowYear+"-"+"0001";
    		} else {
    			int[] docNumArray = new int[qualityUpdateFilelist.size()];
    			for (int i=0;i<qualityUpdateFilelist.size();i++) {
    				String str =qualityUpdateFilelist.get(i).getIdentifier();
    		  		StringBuffer sb=new StringBuffer(str);
    				//将编号去掉“-”，转换成int型存入数组
    				docNumArray[i] = Integer.parseInt(sb.substring(13,22).replaceAll("-", ""));
    			}
    			int max = docNumArray[0];
    			//获取数组中最大的值
    			for (int i : docNumArray) {
    				max = max>i?max:i;
    			}
    			String docNum1 = String.valueOf(max+1);
    			if(nowYear>Integer.parseInt(docNum1.substring(0, 4))) {
     	 			docNum =  "CTJC-001-B03-"+nowYear+"-"+"0001";
     	 		}else{
     	 			//编号加1后重新组
     	 			docNum = "CTJC-001-B03-"+docNum1.substring(0, 4)+"-"+docNum1.substring(4, 8);
     	 		}
    		}
    		qualityUpdateFile.setIdentifier(docNum);
		}
		qualityUpdateFile.setRegistrant(user.getName());
   		qualityUpdateFile.setRegistrantTime(new Date());
		qualityUpdateFile.setRegistrantId(user.getId());
   		//改变状态
		qualityUpdateFile.setProjectsName(qualityUpdateFile.getFileName());//设置传递单的文件编号
		qualityUpdateFile.setFileIdCdd(qualityUpdateFile.getFileId());
   		qualityUpdateFile.setStatus(QualityUpdateFileManager.ZL_XGSQ_REGISTER);
		qualityUpdateFileDao.save(qualityUpdateFile);

		
	}
  	

  	/**
  	 * 生成传递单编号
  	 * */
  	public void savetwo(HttpServletRequest request,QualityUpdateFile qualityUpdateFile
  			,CurrentSessionUser user, String uploadFiles){
  		String str =qualityUpdateFile.getIdentifier();
  		StringBuffer sb=new StringBuffer(str);
		String cNum = "CTJC-001-B09-"+sb.substring(13,22);
    	qualityUpdateFile.setIdentifierC(cNum);
		qualityUpdateFile.setRegistrant(user.getName());
   		qualityUpdateFile.setRegistrantTime(new Date());
   		//改变状态
   		//qualityUpdateFile.setStatus(QualityUpdateFileManager.ZL_XGSQ_QI);
   		
   		qualityUpdateFileDao.save(qualityUpdateFile);
   		//保存附件
   		if(StringUtil.isNotEmpty(uploadFiles)){
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for(String file : files){
				if (StringUtil.isNotEmpty(file)) {
					attachmentManager.setAttachmentBusinessId(file, qualityUpdateFile.getId());
				}
			}
		}	
		
	}
  	/**
	 * 保存质量文件
	 * @param inputStream
	 * @param order
	 */
	public String saveO(InputStream inputStream, QualityAttachment qualityAttachment) {
		String orderId = qualityUpdateFileDao.saveO(inputStream,qualityAttachment);
		return orderId;
	}
}
