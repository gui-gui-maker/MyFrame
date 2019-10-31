package com.lsts.archives.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.archives.bean.ArchivesBox;
import com.lsts.archives.dao.ArchivesBoxDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("ArchivesBox")
public class ArchivesBoxManager extends EntityManageImpl<ArchivesBox,ArchivesBoxDao>{
    @Autowired
    ArchivesBoxDao archivesBoxDao;
    
    @SuppressWarnings("rawtypes")
	public void setReport_snn(HashMap map,HttpServletRequest request) throws Exception{
		String report_sn= request.getParameter("report_sn").toString();
		String info_id = request.getParameter("infoId").toString();
		String INO="0";
		//取到的报告编号
		int a=Integer.parseInt(report_sn.replaceAll("CO-TA", ""));
		
		List<ArchivesBox> archivesBoxlist = archivesBoxDao.getTaskAllot();
		int docNumArray = archivesBoxlist.size();//有几个档案盒
		for (int i=0;i<archivesBoxlist.size();i++) {
			//将编号去掉“CO-TD”，转换成int型存入数组
			docNumArray = Integer.parseInt(archivesBoxlist.get(i).getIdentifier2().replaceAll("CO-TA", ""));
			String archivesBoxId = archivesBoxlist.get(i).getId();
			//将档案放入档案盒
			if(docNumArray-10<=a && docNumArray>=a){
				ArchivesBox archivesBox=this.get(archivesBoxId);
				if(archivesBox.getReportNumber()==null || archivesBox.getReportNumber().equals("")){
					INO="1";
					archivesBox.setReportNumber(report_sn);
					archivesBox.setReportNumberId(info_id);
					int b=archivesBox.getReportNum()-1;//报告数量减一
					archivesBox.setReportNum(b);
				}else{
					INO="1";
					String m1=archivesBox.getReportNumber();
					String m2=archivesBox.getReportNumberId();
					archivesBox.setReportNumber(m1+","+report_sn);
					archivesBox.setReportNumberId(m2+","+info_id);
					int b=archivesBox.getReportNum()-1;
					archivesBox.setReportNum(b);
				}
				this.save(archivesBox);
			}
			
		}
		List<ArchivesBox> archivesBoxlist1 = archivesBoxDao.getReportNumber(info_id);
		String Array = String.valueOf(archivesBoxlist1.size());//有几个档案盒
		if(Array.equals("0")){
			if(INO.equals("1")){
			}else{
				this.seth(map, request);
			}
		}
    }
    /**
     * 将没盒子的档案放入档案盒
     * */
    public void seth(HashMap map,HttpServletRequest request) throws Exception{
    	String report_sn= request.getParameter("report_sn").toString();
		String info_id = request.getParameter("infoId").toString();

		//取到的报告编号
		int a=Integer.parseInt(report_sn.replaceAll("CO-TA", ""));
		
		List<ArchivesBox> archivesBoxlist = archivesBoxDao.getTaskAllot();
    	int b = Integer.parseInt(String.valueOf(a).substring(8));
		String docNum1;//开始
		String docNum11;//结束
		if(b==1){
			docNum1 = "CO-TA"+a;
			int q=a+9;
			docNum11 = "CO-TA"+q;
		}else if(b==0){
			int w=a-9;
			docNum1 = "CO-TA"+w;
			docNum11 = "CO-TA"+a;
		}else{
			String temp = String.valueOf(a);
			temp = temp.substring(0,8);
			docNum1 = "CO-TA"+temp+"1";
			int temp1=Integer.parseInt(temp)+1;
			docNum11 = "CO-TA"+temp1+"0";
		}
		//生成档案盒编号
		int[] NumArray = new int[archivesBoxlist.size()];
		for (int y=0;y<archivesBoxlist.size();y++) {
			//将编号去掉“-”，转换成int型存入数组
			NumArray[y] = Integer.parseInt(archivesBoxlist.get(y).getArchivesBoxId());
		}
		int max1 = NumArray[0];
		//获取数组中最大的值
		for (int u : NumArray) {
			max1 = max1>u?max1:u;
		}
		String Num=String.valueOf(max1+1);
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
		ArchivesBox archivesBox=new ArchivesBox();
		archivesBox.setIdentifier(docNum1);//开始
		archivesBox.setIdentifier2(docNum11);
		archivesBox.setArchivesBoxId(Num);
		archivesBox.setReportNumber(report_sn);
		archivesBox.setReportNumberId(info_id);
		archivesBox.setRegistrant(user.getName());
    	archivesBox.setRegistrantId(user.getId());
    	archivesBox.setRegistrantTime(new Date());
    	archivesBox.setManagerId(emp.getId());
    	archivesBox.setManagerName(user.getName());
    	archivesBox.setReportNum(9);
    	this.save(archivesBox);
    }
    /**
  	 * 生成修改单编号
  	 * */
  	public void saveTask(ArchivesBox archivesBox,CurrentSessionUser user){
		//新增保存时，生成新编号，修改功能不需要重新生成编号
		if(null==archivesBox.getId() || "".equals(archivesBox.getId())){
			String docNum = "";
			String docNum2 = "";
			String Num = "";
			Calendar a=Calendar.getInstance();
    		int nowYear = a.get(Calendar.YEAR);
    		List<ArchivesBox> archivesBoxlist = archivesBoxDao.getTaskAllot();
    		if(archivesBoxlist==null || archivesBoxlist.size()==0) {
    			docNum = "CO-TA"+nowYear+"00001";
    			docNum2 = "CO-TA"+nowYear+"00010";
    			Num="1";
    		} else {
    			int[] docNumArray = new int[archivesBoxlist.size()];
    			for (int i=0;i<archivesBoxlist.size();i++) {
    				//将编号去掉“-”，转换成int型存入数组
    				docNumArray[i] = Integer.parseInt(archivesBoxlist.get(i).getIdentifier2().replaceAll("CO-TA", ""));
    			}
    			int max = docNumArray[0];
    			//获取数组中最大的值
    			for (int i : docNumArray) {
    				max = max>i?max:i;
    			}
    			String docNum1 = "CO-TA"+String.valueOf(max+1);
    			docNum=docNum1;
    			String docNum11 = "CO-TA"+String.valueOf(max+10);
    			docNum2=docNum11;
    			//编号加1后重新组
    			//docNum2 = docNum1.substring(0, 4)+"-"+docNum1.substring(4, 9);
    			//生成档案盒编号
    			int[] NumArray = new int[archivesBoxlist.size()];
    			for (int i=0;i<archivesBoxlist.size();i++) {
    				//将编号去掉“-”，转换成int型存入数组
    				NumArray[i] = Integer.parseInt(archivesBoxlist.get(i).getArchivesBoxId());
    			}
    			int max1 = NumArray[0];
    			//获取数组中最大的值
    			for (int y : NumArray) {
    				max1 = max1>y?max1:y;
    			}
    			Num=String.valueOf(max1+1);
    		}
    		archivesBox.setIdentifier(docNum);//开始
    		archivesBox.setIdentifier2(docNum2);//结束
			archivesBox.setArchivesBoxId(Num);//档案盒编号

		}
		archivesBox.setRegistrant(user.getName());
    	archivesBox.setRegistrantId(user.getId());
    	archivesBox.setRegistrantTime(new Date());
    	User uu = (User)user.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
    	archivesBox.setManagerId(emp.getId());
    	archivesBox.setManagerName(user.getName());
    	archivesBox.setReportNum(10);
    	archivesBoxDao.save(archivesBox);
	}
	public void saveManul(ArchivesBox archivesBox) throws Exception {
		/*String sql1 = "select * from TJY2_ARCHIVES_BOX "+
				"where prefix = ? "+
				"and years = ? "+
				"and (startnumber <=? and endNumber >=?) or (startnumber <=? and endNumber >=?)";*/
		String prefix = archivesBox.getPrefix();
		int years = archivesBox.getYears();
		int start =  archivesBox.getStartNumber();
		int end = archivesBox.getEndNumber();
		//List list = archivesBoxDao.createSQLQuery(sql1,prefix,years,start,start,end,end).list();
		//if(list.size()>0){
			//throw new Exception("此区间编号已经成生成！");
		//}else{
			saveBox(archivesBox, prefix, years, start, end);
		//}
	}
	/**
	 * 更新档案盒
	 * */
	public void updateArchivesBox(String reportId) throws Exception{
		String sql0 = "select report_sn from tzsb_inspection_info where id =?"; 
		List<Object> snList =  archivesBoxDao.createSQLQuery(sql0,reportId).list();
		String reportSn = "";
		if(snList.size()>0){
			reportSn = snList.get(0).toString();
		}else{
			throw new Exception("档案编号为空！");
		}
		//取到的报告编号
		Integer num = Integer.parseInt(reportSn.substring(reportSn.length()-5,reportSn.length()));
		Integer years = Integer.parseInt(reportSn.substring(reportSn.length()-9,reportSn.length()-5));
		String prefix = reportSn.substring(0,reportSn.length()-9);
		String sql = "from ArchivesBox where prefix = ? and years = ? and startnumber <= ? and endNumber >=?";
		List<ArchivesBox> list = archivesBoxDao.createQuery(sql,prefix,years,num,num).list();
		if(list.size() > 0){
			for (ArchivesBox box : list) {
				//在此盒子移除
				int start =  box.getStartNumber();
				int end = box.getEndNumber();
				
				Long startNumber = Long.valueOf(years*100000+start);
				//将已经有的档案加入盒子中
		    	List<String> sns = new ArrayList<String>();
		    	String reportSns = "";
		    	for(int i=0,max = end-start+1;i<max;i++){
		    		if(i!=0){
		    			reportSns+=",'"+prefix+(startNumber+i)+"'";
		    		}else{
		    			reportSns+="'"+prefix+(startNumber+i)+"'";
		    		}
		    		sns.add(prefix+(startNumber+i));
		    	}
		    	String sql2 = "select distinct i.id,i.report_sn from tzsb_inspection_info i,TJY2_ARCHIVES_UPLOAD a where i.id = a.file_id and i.report_sn in ("+reportSns+") order by i.report_sn";
		    	List<Object[]> list2 = archivesBoxDao.createSQLQuery(sql2).list();
		    	List<String> readySn = new ArrayList<String>();
		    	List<String> readyReportIds = new ArrayList<String>();
		    	int readyNumber = 0;
		    	for (Object[] obj : list2) {
		    		String id = obj[0].toString();
		    		readyReportIds.add(id);
		    		String sn = obj[1].toString();
		    		sns.remove(sn);
		    		readySn.add(sn);
		    		readyNumber++;
				}
		    	box.setReportNumber(jionList(readySn));
		    	box.setReportNumberId(jionList(readyReportIds));
		    	box.setReportNumber2(jionList(sns));
		    	box.setReportNum(end-start+1-readyNumber);
		    	archivesBoxDao.save(box);
			}
		}
	}

	private void saveBox(ArchivesBox archivesBox,String prefix,int years,int start,int end){
		Long startNumber = Long.valueOf(years*100000+start);
		Long endNumber = Long.valueOf(years*100000+end);
		archivesBox.setPrefix(prefix);
		archivesBox.setYears(years);
		archivesBox.setStartNumber(start);
		archivesBox.setEndNumber(end);
		archivesBox.setIdentifier(prefix+startNumber);//开始
		archivesBox.setIdentifier2(prefix+endNumber);//结束
		if(StringUtil.isEmpty(archivesBox.getId())){
			User user = (User) SecurityUtil.getSecurityUser().getSysUser();
			archivesBox.setRegistrant(user.getName());
	    	archivesBox.setRegistrantId(user.getId());
	    	archivesBox.setRegistrantTime(new Date());
	    	Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
	    	archivesBox.setManagerId(emp.getId());
	    	archivesBox.setManagerName(user.getName());
		}
    	//将已经有的档案加入盒子中
    	List<String> sns = new ArrayList<String>();
    	String reportSns = "";
    	for(int i=0,max = end-start+1;i<max;i++){
    		if(i!=0){
    			reportSns+=",'"+prefix+(startNumber+i)+"'";
    		}else{
    			reportSns+="'"+prefix+(startNumber+i)+"'";
    		}
    		sns.add(prefix+(startNumber+i));
    	}
    	String sql = "select distinct i.id,i.report_sn from tzsb_inspection_info i,TJY2_ARCHIVES_UPLOAD a where i.id = a.file_id and i.report_sn in ("+reportSns+") order by i.report_sn";
    	List<Object[]> list2 = archivesBoxDao.createSQLQuery(sql).list();
    	List<String> readySn = new ArrayList<String>();
    	List<String> readyReportIds = new ArrayList<String>();
    	int readyNumber = 0;
    	for (Object[] obj : list2) {
    		String reportId = obj[0].toString();
    		readyReportIds.add(reportId);
    		String reportSn = obj[1].toString();
    		sns.remove(reportSn);
    		readySn.add(reportSn);
    		readyNumber++;
		}
    	archivesBox.setReportNumber(jionList(readySn));
    	archivesBox.setReportNumberId(jionList(readyReportIds));
    	archivesBox.setReportNumber2(jionList(sns));
    	archivesBox.setReportNum(end-start+1-readyNumber);
    	archivesBox.setTotal_nums(end-start+1);
    	archivesBoxDao.save(archivesBox);	
	}
	public String jionList(List<String> list){
		if(list!=null&&list.size()>0){
			StringBuilder sb = new StringBuilder();
			for (String string : list) {
				sb.append(string+",");
			}
			String res = sb.toString();
			res = res.substring(0, res.length()-1);
			return res;
		}else{
			return "";
		}
	}
	public void delete(String id) throws Exception{
		archivesBoxDao.createQuery("delete from ArchivesBox where id = ?", id).executeUpdate();
	}
	
}