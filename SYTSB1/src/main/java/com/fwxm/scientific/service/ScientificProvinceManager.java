package com.fwxm.scientific.service;


import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.fwxm.scientific.bean.ScientificProvince;
import com.fwxm.scientific.dao.ScientificProvinceDao;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName ScientificProvinceManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-01-15 13:24:16
 */
@Service("scientificProvinceManager")
public class ScientificProvinceManager extends EntityManageImpl<ScientificProvince, ScientificProvinceDao> {
	@Autowired
	ScientificProvinceDao scientificProvinceDao;
	public void saveBase(String id,String P100001,String P200001,String P300001,String P400001,String P500001,String P600001){
		String sql="update TJY2_SCIENTIFIC_PROVINCE set P100001=?,P200001=?,P300001=?,P400001=?,P500001=?,P600001=? where id=?";
		scientificProvinceDao.createSQLQuery(sql,P100001,P200001,P300001,P400001,P500001,P600001,id).executeUpdate();
	}
	public  Object[] detailBase(String id){
		Object[] o=null;
		String sql="select t.P100001,t.P200001,t.P300001,t.P400001,t.P500001,t.P600001 from TJY2_SCIENTIFIC_PROVINCE t where t.id = ?";
		List<Object> list=scientificProvinceDao.createSQLQuery(sql,id).list();
		if(list.size()>0){
			o=(Object[])list.get(0);
			
		}
		return o;
		
	}
	public String inputFile(String s,String type){
		BASE64Decoder decoder = new BASE64Decoder();
		 try {// 解密
			 byte[] b = decoder.decodeBuffer(s);
			 // 处理数据
			 for (int i = 0; i < b.length; ++i) {
				 if (b[i] < 0) {
					 b[i] += 256;
					 }
				 }
			 Date currentTime = new Date();
			   SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			   String path = formatter.format(currentTime);
			 OutputStream out = new FileOutputStream("D:/Servers/SCSEI_WEB/webapps/ROOT/upload/"+path+"."+type);
			 out.write(b);
			 out.flush();
			 out.close();
			 return "http://kh.scsei.org.cn/upload/"+path+"."+type;
			  /* OutputStream out = new FileOutputStream("D:/image/"+path+"."+type);
				 
				 out.write(b);
				 out.flush();
				 out.close();
				 return "D:/image/"+path+"."+type;*/
			 } catch (Exception e) {
				 return "";
				 }
		
	}
}
