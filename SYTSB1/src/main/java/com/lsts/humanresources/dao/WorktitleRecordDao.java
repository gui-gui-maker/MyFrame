package com.lsts.humanresources.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository; 
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.humanresources.bean.WorktitleRecord;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName WorktitleRecordDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("WorktitleRecordDao")
public class WorktitleRecordDao extends EntityDaoImpl<WorktitleRecord> {
	//通过员工ID查询员工职务记录
	public List<WorktitleRecord> getWorkTitles(String empId) throws Exception {
		String sql = "select * from TJY2_RL_WORKTITLE_RECORD t where t.emp_id=? order by t.create_date desc";
		System.out.println(sql.toString());
		List<?> list = this.createSQLQuery(sql,empId).list();
		List<WorktitleRecord> list1 = new ArrayList<WorktitleRecord>();
		
		if(list.size()>0&&list!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
    		for(int j=0;j<list.size();j++){
    			Object[] obj = (Object[]) list.get(j);
				WorktitleRecord worktitleRecord = new WorktitleRecord();
				worktitleRecord.setId(obj[0]!=null?obj[0].toString():null);
	    		worktitleRecord.setEmpId(obj[1]!=null?obj[1].toString():null);
	    		worktitleRecord.setWorktitleId(obj[2]!=null?obj[2].toString():null);
	    		worktitleRecord.setWorktitleName(obj[3]!=null?obj[3].toString():null);
	    		if(obj[4]!=null){
	    			Date startDate = sdf.parse(obj[4].toString());  
		    		worktitleRecord.setStartDate(startDate);
	    		}else{
	    			worktitleRecord.setStartDate(null);
	    		}
	    		if(obj[5]!=null){
	    			Date startDate = sdf.parse(obj[5].toString());  
	    			worktitleRecord.setEndDate(startDate);
	    		}else{
	    			worktitleRecord.setEndDate(null);
	    		}
	    		worktitleRecord.setStatus(obj[6]!=null?obj[6].toString():null);
	    		worktitleRecord.setCreateId(obj[7]!=null?obj[7].toString():null);
	    		worktitleRecord.setCreateBy(obj[8]!=null?obj[8].toString():null);
	    		worktitleRecord.setUploadFiles(obj[9]!=null?obj[9].toString():null);
	    		list1.add(worktitleRecord);
    		}
		}else{
			list1=null;
		}
		return list1;
	}
	//通过员工ID查询员工当前职务
	public WorktitleRecord getWorkTitle(String empId) throws Exception {
		WorktitleRecord worktitleRecord = new WorktitleRecord();
		String sql = "select * from TJY2_RL_WORKTITLE_RECORD t where t.emp_id=? and t.status='1'";
		System.out.println(sql.toString());
		List<?> list = this.createSQLQuery(sql,empId).list();
		if(list.size()>=1&&list!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
    		Object[] obj = (Object[]) list.get(0);
    		worktitleRecord.setId(obj[0]!=null?obj[0].toString():null);
    		worktitleRecord.setEmpId(obj[1]!=null?obj[1].toString():null);
    		worktitleRecord.setWorktitleId(obj[2]!=null?obj[2].toString():null);
    		worktitleRecord.setWorktitleName(obj[3]!=null?obj[3].toString():null);
    		if(obj[4]!=null){
    			Date startDate = sdf.parse(obj[4].toString());  
	    		worktitleRecord.setStartDate(startDate);
    		}else{
    			worktitleRecord.setStartDate(null);
    		}
    		if(obj[5]!=null){
    			Date startDate = sdf.parse(obj[5].toString());  
    			worktitleRecord.setEndDate(startDate);
    		}else{
    			worktitleRecord.setEndDate(null);
    		}
    		worktitleRecord.setStatus(obj[6]!=null?obj[6].toString():null);
    		worktitleRecord.setCreateId(obj[7]!=null?obj[7].toString():null);
    		worktitleRecord.setCreateBy(obj[8]!=null?obj[8].toString():null);
    		worktitleRecord.setUploadFiles(obj[9]!=null?obj[9].toString():null);
    		if(obj[10]!=null){
    			Date createDate = sdf.parse(obj[10].toString());  
    			worktitleRecord.setCreateDate(createDate);
    		}else{
    			worktitleRecord.setCreateDate(null);
    		}
		}else{
			worktitleRecord=null;
		}
		return worktitleRecord;
	}
}