package com.scts.payment.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import util.Base64Utils;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.report.bean.ReportDraw;
import com.scts.payment.bean.InspectionPayInfo;
import com.scts.payment.bean.InspectionPaySign;
import com.scts.payment.dao.InspectionPaySignDao;

@Service
public class InspectionPaySignService extends EntityManageImpl<InspectionPaySign, InspectionPaySignDao>{

	@Autowired
	private InspectionPaySignDao inspectionPaySignDao;
	@Autowired
	private InspectionPayInfoService inspectionPayInfoService;

	public void save(HttpServletRequest request,InspectionPaySign entity) throws Exception {
		String base64text = entity.getBase64_sign_file();
		String base64ImagePath = "";
		if(!StringUtil.isEmpty(base64text)){
			 // 上传图片  
			String path = request.getServletContext().getRealPath("/upload/financial/sign/");
			String id = UUID.randomUUID().toString();
	        String fileName =id + ".txt"; 
	        File filepath = new File(path,fileName);
	        //判断路径是否存在，如果不存在就创建一个
	        if (!filepath.getParentFile().exists()) { 
	            filepath.getParentFile().mkdirs();
	        }
	        FileWriter fw;
	        try {
	        	fw = new FileWriter(filepath);
	        	BufferedWriter bw = new BufferedWriter (fw);
	    		bw.write(base64text);
	        	bw.flush();
	        	bw.close();
	        } catch (IOException e) {
	        	e.printStackTrace();
	    	} 
	        base64ImagePath = filepath.getAbsolutePath();
	        entity.setBase64_sign_file(base64ImagePath);
	        entity.setSign_date(new Date());
		}
		inspectionPaySignDao.save(entity);
	}

	public InspectionPaySign getSignByPayInfo(String id) throws Exception {
		InspectionPaySign inspectionPaySign = null;
		List<InspectionPaySign> list = inspectionPaySignDao.createQuery("from InspectionPaySign where fk_pay_info_id = ?", id).list();
		if(list.size()>0){
			inspectionPaySign = list.get(0);
			String imgPath = inspectionPaySign.getBase64_sign_file();
			//获取图片
			String rs = "";
			
			if(StringUtil.isEmpty(imgPath)){
				throw new Exception("未获取到签名图片！");
			}
			StringBuffer sb = new StringBuffer();
		    // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
			BufferedReader bufReader = null;
			try {
				File file = new File(imgPath);
			    // FileReader:用来读取字符文件的便捷类。
				bufReader = new BufferedReader(new FileReader(file));
				// buf = new BufferedReader(new InputStreamReader(new
				// FileInputStream(file)));
				String temp = null;
				while ((temp = bufReader.readLine()) != null) {
					sb.append(temp);
			    }
				rs = sb.toString();
				inspectionPaySign.setBase64_text(rs);
			} catch (Exception e) {
			    e.getStackTrace();
			} finally {
			    if (bufReader != null ) {
			        try {
			            bufReader.close();
			        } catch (IOException e) {
			            e.getStackTrace();
			        }
			    }
			}
		}
		return inspectionPaySign;
	}
	
	
	/**
	 * 保存收费签名
	 * @param request
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public void mobileReportDrawSaveSgnFile(HttpServletRequest request,Map<String, Object> data) throws Exception {
		//List<InspectionPaySign> list = new ArrayList<InspectionPaySign>();
		String imgString = data.get("sign_file").toString();
		if(StringUtil.isEmpty(imgString)) {
			throw new Exception("签名图片上传失败！");
		}
		//日期格式化
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date date=new Date();
		String dates=df.format(date);
		String base64ImagePath = "";
		if(!StringUtil.isEmpty(imgString)){
			 // 上传图片  
			String id = UUID.randomUUID().toString();
	        String fileName1 =id + ".ico"; 
	        String paths = request.getServletContext().getRealPath("/");
	        String pp=paths+"upload\\financial\\sign\\"+dates+"\\"+fileName1;
			File filepath = new File(paths+"upload\\financial\\sign\\"+dates+"\\"+fileName1);
			//判断路径是否存在，如果不存在就创建一个
	        if (!filepath.getParentFile().exists()) { 
	            filepath.getParentFile().mkdirs();
	        }
	        String ps=pp.replaceAll("\\\\", "\\/");
	        String str = imgString.toString().substring(22,imgString.toString().length()-1);
	        System.out.println(ps);
			Base64Utils.Base64ToImage(str,ps);
			base64ImagePath="upload\\financial\\sign\\"+dates+"\\"+fileName1;
	        
		}
		
		/*
		 * Set<Entry<String, Object>> entrySet = data.entrySet();
		 * 
		 * Iterator<Entry<String, Object>> it = entrySet.iterator();
		 * 
		 * List<Map<String, Object>> list1=new ArrayList<Map<String,Object>>();
		 * Map<String, Object> map=new HashMap<String, Object>(); while(it.hasNext()){
		 * Entry<String, Object> me = it.next(); String key = me.getKey(); String value
		 * = (String) me.getValue(); map.put(key, value); }
		 */
		//list1.add(map);
		String invoiceNo=  data.get("invoiceNo").toString();
		if(StringUtil.isEmpty(invoiceNo)) {
			throw new Exception("发票号未获取到！");
		}
		List<InspectionPayInfo> listInvoiceNo=inspectionPayInfoService.queryInspectionPayInfo2(invoiceNo);
		if(listInvoiceNo.size()==0) {
			throw new Exception("支付信息保存失败！");
		}
		String tel=data.get("tel").toString();
		//将保存信息存到application中
		ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		@SuppressWarnings("unchecked")
		ConcurrentHashMap<String,Object> paymentmap = (ConcurrentHashMap<String,Object>)context.getAttribute("payment");
		if(null == paymentmap) {
			paymentmap = new ConcurrentHashMap<String,Object>();
			context.setAttribute("payment", paymentmap);
		}
		for (InspectionPayInfo inspectionPayInfo : listInvoiceNo) {
			inspectionPayInfo.setTel(tel);
			List<InspectionPaySign> list = inspectionPaySignDao.createQuery("from InspectionPaySign where fk_pay_info_id = ? ", inspectionPayInfo.getId()).list();
			if(list.size() == 0) {
				InspectionPaySign inspectionPaySign = new InspectionPaySign();
				inspectionPaySign.setFk_pay_info_id(inspectionPayInfo.getId());
				inspectionPaySign.setBase64_sign_file(base64ImagePath); 
				inspectionPaySign.setSign_date(new Date());
				inspectionPaySignDao.save(inspectionPaySign);
			}else {
				for (InspectionPaySign inspectionPaySign : list) {
					inspectionPaySign.setBase64_sign_file(base64ImagePath);
					inspectionPaySign.setSign_date(new Date());
				}
			}
			paymentmap.put(inspectionPayInfo.getFk_inspection_info_id(), inspectionPayInfo);
		}
		/*
		 * for (int i = 0; i < list1.size(); i++) { //根据收费信息表ID查询报检信息收费详细信息表信息
		 * List<Map<String, Object>>
		 * queryList=inspectionPaySignDao.queryInsperctionPayInfo(fkInspectionInfoId);
		 * if(queryList.size()>0){ //查询是否签名 List<Map<String, Object>>
		 * listPaySign=inspectionPayInfoService.queryInspectionPayInfoSign(queryList.get
		 * (0).get("ID").toString()); if(listPaySign.size()==0){ InspectionPaySign
		 * entity=new InspectionPaySign();
		 * entity.setFk_pay_info_id(queryList.get(0).get("ID").toString());
		 * entity.setBase64_sign_file(base64ImagePath); entity.setSign_date(new Date());
		 * inspectionPaySignDao.save(entity); list.add(entity); } }
		 * 
		 * }
		 * 
		 * return list;
		 */
	}
	
	
	
	public String addCid(String clientid,String name){
		String successMsg="";
		for(int i=0;i<10;i++){
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			List<Map<String, Object>> queryTableList=this.queryTableValuesId(uuid);
			if(queryTableList.size()>0){
				successMsg="0";
			}else{
				List<Map<String, Object>> listSort=this.querySort();
				int maxNum=Integer.parseInt(listSort.get(0).get("MAX(SORT)").toString());
				int sort=maxNum+1;
				List<Map<String, Object>> list= this.queryTableValues(clientid);
				if(list.size()>0){
					successMsg="0";
				}else{
					String sql="insert INTO PUB_CODE_TABLE_VALUES  (ID,CODE_TABLE_ID,NAME,VALUE,SORT) VALUES(?,?,?,?,?)";
					inspectionPaySignDao.createSQLQuery(sql, uuid,"4028800664ba5bca0164bb3b116d0006",name,clientid,sort).executeUpdate();
					successMsg="1";
					break;
				}
			}
		}
		return successMsg;
	}
	
	/**
	 * 查询码表最大值
	 * @return
	 */
	public List<Map<String, Object>> querySort(){
		String sql="select max(sort) from PUB_CODE_TABLE_VALUES where CODE_TABLE_ID='4028800664ba5bca0164bb3b116d0006' ";
		Query query=inspectionPaySignDao.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
	
	/**
	 * 查询码表最大值
	 * @return
	 */
	public List<Map<String, Object>> queryTableValues(String value){
		String sql="select * from PUB_CODE_TABLE_VALUES where CODE_TABLE_ID='4028800664ba5bca0164bb3b116d0006' and value=? ";
		Query query=inspectionPaySignDao.createSQLQuery(sql,value).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
	
	/**
	 * 根据uuid查询码表是否存在
	 * @return
	 */
	public List<Map<String, Object>> queryTableValuesId(String codeTableValuesId){
		String sql="select b.* from PUB_CODE_TABLE a,PUB_CODE_TABLE_VALUES b \n"
				+ "where a.id=b.CODE_TABLE_ID and a.code='SIGN_DEVICE' and b.id=? ";
		Query query=inspectionPaySignDao.createSQLQuery(sql,codeTableValuesId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
	
}
