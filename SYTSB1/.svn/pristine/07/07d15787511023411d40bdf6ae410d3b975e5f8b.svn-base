package com.lsts.inspection.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import sun.misc.BASE64Decoder;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.inspection.bean.ReportPicValue;
import com.lsts.inspection.dao.ReportItemValueDao;
import com.lsts.inspection.dao.ReportPicValueDao;
/**
 * 报告检验项目业务逻辑对象
 * @ClassName ReportItemValueService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-26 上午09:09:00
 */
@Service("reportPicValueService")
public class ReportPicValueService extends EntityManageImpl<ReportPicValue, ReportPicValueDao> {
	@Autowired
	private ReportPicValueDao reportpicValueDao;
	
	@Autowired
	private ReportItemValueDao reportItemValueDao ;

	public String fileUp(CommonsMultipartFile file,String item_name, String ins_info_id, String type) throws Exception {
		ReportPicValue picvalue = new ReportPicValue();
		picvalue.setBusiness_id(ins_info_id);
		picvalue.setItem_name(item_name.toUpperCase());
		InputStream input = file.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
	    byte[] buffer = new byte[2048];
	    int n = 0;
	    while (-1 != (n = input.read(buffer))) {
	        output.write(buffer, 0, n);
	    }
	    
		picvalue.setPic_blob(output.toByteArray());
		Date date = new Date();
		picvalue.setUpload_date(date);
		reportpicValueDao.save(picvalue);
		
		return picvalue.getId() ;
	}
	
	
	public String saveDraw(String ins_info_id, String item_name, String vgxml , String base64 ) throws Exception {
		ReportPicValue picvalue = new ReportPicValue();
		picvalue.setBusiness_id(ins_info_id);
		picvalue.setPic_clob(vgxml);
		picvalue.setItem_name(item_name.toUpperCase());
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes = decoder.decodeBuffer(base64);
		for (int i = 0; i < bytes.length; ++i) {
			if (bytes[i] < 0) {// 调整异常数据
				bytes[i] += 256;
			}
		}
		picvalue.setPic_blob(bytes);
		Date date = new Date();
		picvalue.setUpload_date(date);
		reportpicValueDao.save(picvalue);
		
		return picvalue.getId() ;
	}
	
	public String getPicXml(String ins_info_id, String item_name) throws Exception {
		
		StringBuffer picSql = new StringBuffer();
		picSql.append("from ReportPicValue p,ReportItemValue t ");
		//picSql.append("where p.id = t.item_value and p.business_id = t.fk_inspection_info_id and p.business_id=? and lower(p.item_name) = ?");
		picSql.append("where p.id = t.item_value and t.fk_inspection_info_id=? and lower(p.item_name) = ?");
		List list = reportpicValueDao.createQuery(picSql.toString(), new String[]{ins_info_id,item_name}).list();
		String ret = "" ;
		for(int i = 0 ; i < list.size() ; i++) {
			Object o[] = (Object[])list.get(i);
			ReportPicValue  picv = (ReportPicValue)o[0];
			ret = picv.getPic_clob() ;
		}
		return ret;
	}
	
}



