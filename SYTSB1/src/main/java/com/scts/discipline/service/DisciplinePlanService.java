package com.scts.discipline.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.scts.discipline.bean.DisciplinePlan;
import com.scts.discipline.dao.DisciplinePlanDao;

import net.sf.json.JSONArray;

@Service("disciplinePlanService")
public class DisciplinePlanService extends EntityManageImpl<DisciplinePlan, DisciplinePlanDao> {

	@Autowired
	private DisciplinePlanDao disciplinePlanDao;

	/**
	 * 导入回访计划
	 * author pingZhou
	 * @param year
	 * @param month
	 * @param files
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	public String saveBankData(String year ,String month, String files, CurrentSessionUser user) throws ParseException {
		JSONArray array = JSONArray.fromObject(files);
		String repData="";
		String[] fileName = new String[array.length()];// 文件名
		String[] filePath = new String[array.length()];// 文件路径

		for (int i = 0; i < array.length(); i++) {
			fileName[i] = array.getJSONObject(i).getString("name");
			filePath[i] = array.getJSONObject(i).getString("path");
			repData = repData + saveDate( year,month,fileName[i], filePath[i], user);
		}
		return repData;
	}
	
	/**
	 * 根据文件路劲导入excel数据
	 * 
	 * @param file
	 * @throws ParseException 
	 * @throws IOException
	 */
	public String saveDate(String year ,String month,String fileName, String filePath, CurrentSessionUser user) throws ParseException {
		String repData="";
		
		
//		try{
		fileName = this.getSystemFilePath()+File.separator+filePath;
		File bankfile = new File(fileName); // 创建文件对象  
		Workbook bankWb=null;
		try {
			bankWb = WorkbookFactory.create(bankfile);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    Integer l = new Integer(1);
	    for (int j = 0; j < l; j++) {
	    	try {
	    		 Sheet bankSheet = bankWb.getSheetAt(j);//获得sheet
	    		 if(bankSheet!=null) {
	    			 l = l+1;
	    		 }
	    		 
		    	 for (int i=2;i<=bankSheet.getLastRowNum();i++) {
		 	    	Row row = bankSheet.getRow(i);//行
		 	    	if(row!=null && !"null".equals(row)) {
		 	    		if(row.getCell(1)!=null && !"null".equals(row.getCell(1))) {
		 	    			Integer orders = 0;
		 	    			try {
		 	    				String num = row.getCell(0).toString();
		 	    				int end = num.indexOf(".");
		 	    				System.out.println(num.substring(0,end));
		 	    				orders = row.getCell(0)==null?null:Integer.parseInt(num.substring(0,end));
		 	    			} catch (Exception e) {
		 	    				log.debug(e.getMessage());
								break;
							}
		 		    		String unit = row.getCell(1).getStringCellValue();
		 		    		String com_name = row.getCell(2).getStringCellValue();
		 		    		String maintain_name = row.getCell(3).getStringCellValue();
		 		    		String contact_man = "";
		 		    		String phone_num = "";
		 		    		try {
		 		    			contact_man = row.getCell(4)==null?null:row.getCell(4).getStringCellValue();
		 		    			phone_num = getNumFromStr(row.getCell(4)==null?null:row.getCell(4).getStringCellValue());
		 		    			
		 		    		} catch (Exception e) {
		 		    			log.debug(e.getMessage());
								contact_man = row.getCell(4)==null?null:new BigDecimal(row.getCell(4).getNumericCellValue()).toString();
								phone_num = getNumFromStr(row.getCell(4)==null?null:new BigDecimal(row.getCell(4).getNumericCellValue()).toString());
							}
		 		    		String inspect_date = row.getCell(5)==null?null:row.getCell(5).toString();
		 		    		String check_op = row.getCell(6)==null?null:row.getCell(6).toString();
		 		    		String inspector_grade = row.getCell(7)==null?null:row.getCell(7).getStringCellValue();
		 		    		String other_suggest = row.getCell(8)==null?null:row.getCell(8).getStringCellValue();
	
		 		    		DisciplinePlan plan = new DisciplinePlan();
		 		    		
		 		    		plan.setContact_man(contact_man);
		 		    		plan.setYear(year);
		 		    		plan.setMonth(month);
		 		    		plan.setFlag("0");
		 		    		plan.setData_status("0");
		 		    		plan.setEnter_op(user.getName());
		 		    		plan.setEnter_op_id(user.getId());
		 		    		plan.setEnter_time(new Date());
		 		    		
		 		    		plan.setCheck_op(check_op);
		 		    		plan.setCom_name(com_name);
		 		    		plan.setInspect_date(inspect_date);
		 		    		plan.setInspector_grade(inspector_grade);
		 		    		plan.setMaintain_name(maintain_name);
		 		    		plan.setOrders((j+1)*1000+ orders);
		 		    		plan.setOther_suggest(other_suggest);
		 		    		plan.setUnit(unit);
		 		    		plan.setPhone_num(phone_num);
		 		    		
		 		    		disciplinePlanDao.save(plan);
		 		    		
							
		 	    		}else {
		 	    			break;
		 	    		}
		 	    	}else {
		 	    		break;
		 	    	}
		 	    }
	    	} catch (Exception e) {
				// TODO: handle exception
			}
		}
	    
	
	
		return repData;
	}
	
	public String getSystemFilePath() {
		
		SysParaInf sp = Factory.getSysPara();
		String attachmentPath = sp.getProperty("attachmentPath");
		String attachmentPathType = sp.getProperty("attachmentPathType", "relative");
		
		if ("relative".equals(attachmentPathType)) {
			return Factory.getWebRoot() + File.separator + attachmentPath;
		}
		return attachmentPath;
	}

	
	/**
	 * 保存回访结果
	 * author pingZhou
	 * @param ids
	 * @param inspector_grade
	 * @param other_suggest
	 */
	public void saveResult(String ids, String inspector_grade,String self_discipline,String other_suggest) {
		String idss = ids.replace(",", "','");
		
		String sql = "update TJY2_DISCIPLINE_PLAN t set t.inspector_grade = ?,t.self_discipline=?, t.other_suggest = ? where t.id in ('"+idss+"')";
		
		disciplinePlanDao.createSQLQuery(sql, inspector_grade,self_discipline,other_suggest).executeUpdate();
	}
	
	/**
	 * 获取字符串里面的数据（包括数字，小数点和★） author pingZhou
	 * 
	 * @param str
	 * @return
	 */
	public String getNumFromStr(String str) {
		String num = "";
		boolean flag = true;
		Pattern pattern = Pattern.compile("[0-9]");
		for (int i = 0; i < str.length(); i++) {
			char stri = str.charAt(i);
			if ('{' == stri) {
				flag = false;
			}
			if ('}' == stri) {
				flag = true;
			}
			if (!flag) {
				// 标记里面的数值不取
				if (!"".equals(num) && !" ".equals(num.substring(num.length() - 1, num.length()))) {
					num = num + " ";
				}
				continue;
			}

			if (".".equals(stri + "")) {
				num = num + stri;
				continue;
			} else if ("★".equals(stri + "")) {
				num = num + stri;
				continue;
			} else if (stri >= '0' && stri <= '9') {
				num = num + stri;
			} else {
				if (!"".equals(num) && !" ".equals(num.substring(num.length() - 1, num.length()))) {
					num = num + " ";
				}
				continue;
			}

		}
		return num;

	}

	public void callFlag(String id) {
		
		
		DisciplinePlan plan = disciplinePlanDao.get(id);
		
		String sql = "update TJY2_DISCIPLINE_PLAN t set t.flag='1' where t.phone_num = ?";
		
		disciplinePlanDao.createSQLQuery(sql,plan.getPhone_num()).executeUpdate();
	}
	
	public void del(String ids) {
		String idss = ids.replace(",", "','");
		String sql = "update TJY2_DISCIPLINE_PLAN t set t.data_status='99' where t.id in ('"+idss+"')";
		
		disciplinePlanDao.createSQLQuery(sql).executeUpdate();
	}
	
	public List<Map<String, Object>> gzfwTj(String startTime,String endTime) throws HibernateException, ParseException{
//		String sql="select t.CREATE_ORG_NAME sj, "
//				+ "sum(CASE  WHEN t.JUDGE_GRADE ='S1' OR t.JUDGE_GRADE IS NULL then 1 else 0 end) fcmy, "
//				+ "  sum(CASE  WHEN t.JUDGE_GRADE ='S2' then 1 else 0 end) my, "
//				+ " sum(CASE  WHEN t.JUDGE_GRADE ='S3' then 1 else 0 end) yb,"
//				+ " sum(CASE  WHEN t.call_type ='0' then 1 else 0 end) hfsl"
//				+ " from TJY2_DISCIPLINE_CALL t"
//				+ " where t.CREATE_DATE>=? and t.CREATE_DATE<=?"
//				+ " group by  t.CREATE_ORG_NAME";
		String sql="select o.org_name sj,"
				+ "sum(CASE  WHEN t.JUDGE_GRADE ='S1' OR t.JUDGE_GRADE IS NULL then 1 else 0 end) fcmy, "
				+ "  sum(CASE  WHEN t.JUDGE_GRADE ='S2' then 1 else 0 end) my, "
				+ " sum(CASE  WHEN t.JUDGE_GRADE ='S3' then 1 else 0 end) yb,"
				+ " sum(CASE  WHEN t.call_type ='0' then 1 else 0 end) hfsl"
				+ " from TJY2_DISCIPLINE_CALL t"
				+ " LEFT JOIN TZSB_INSPECTION_INFO i ON i.id=t.BUSINESS_ID"
				+ " LEFT JOIN SYS_ORG o ON o.id=i.CHECK_UNIT_ID"
				+ " where t.CREATE_DATE>=? and t.CREATE_DATE<=?"
				+ " group by  o.org_Name";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time=sdf.parse(endTime);
        Calendar rightNow = Calendar.getInstance();  
        rightNow.setTime(time);  
        rightNow.add(Calendar.DATE, 1); 
        //比较大小
        Date zxTime=sdf.parse("2018-11-01");
        if(zxTime.getTime()>sdf.parse(startTime).getTime()){
        	startTime="2018-11-01";
        }
		 List<Map<String, Object>> list=disciplinePlanDao.createSQLQuery(sql,sdf.parse(startTime),sdf.parse(sdf.format(rightNow.getTime()))).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		 return list;
	}	
	
	public List<Map<String, Object>> oldGzfwTj(String startTime,String endTime) throws HibernateException, ParseException{
		String sql="select to_char(t.CREATE_DATE,'yyyy/mm') sj, "
		+ "sum(CASE  WHEN t.JUDGE_GRADE ='S1' OR t.JUDGE_GRADE IS NULL then 1 else 0 end) fcmy, "
		+ " sum(CASE  WHEN t.JUDGE_GRADE ='S2' then 1 else 0 end) my, "
		+ " sum(CASE  WHEN t.JUDGE_GRADE ='S3' then 1 else 0 end) yb"
		+ " from TJY2_DISCIPLINE_CALL t"
		+ " where t.CREATE_DATE>=? and t.CREATE_DATE<=?"
		+ " group by to_char(t.CREATE_DATE,'yyyy/mm')";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 List<Map<String, Object>> list=disciplinePlanDao.createSQLQuery(sql,sdf.parse(startTime),sdf.parse(endTime)).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		 return list;
	}
	
}
