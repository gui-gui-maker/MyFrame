package com.lsts.report.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.base.Factory;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.pub.message.service.SysMessageManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.common.service.MessageXinxiService;
import com.lsts.humanresources.bean.WorkExperience;
import com.lsts.report.bean.ReportYjsz;
import com.lsts.report.dao.ReportYjszDao;
import com.lsts.report.service.ReportYjszManager;
import java.util.regex.Pattern;


@Controller
@RequestMapping("report/yjsz")
public class ReportYjszAction extends SpringSupportAction<ReportYjsz, ReportYjszManager> {
   
	@Autowired
    private ReportYjszManager  reportYjszManager;
    @Autowired
    private ReportYjszDao reportYjszDao;
	@Autowired
	private FlowExtManager flowExtManager;
	
	@Autowired
	private ActivityManager activityManager;
	@Autowired
	private SysMessageManager sysMessageManager;
	@Autowired
	private MessageXinxiService messageXinxiService;
    
//	public static void main(String[] args) throws ParseException{
//        DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        String c="2015-10-20";
//        Date date = sdf.parse(c);
//        System.out.println(date.getTime());
//        Date lrsj = new Date();
//		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(lrsj);
//	    String c=dateStr;
//	    Date date = sdf.parse(c);
//	    System.out.println(date.getTime());
		
//	}
//	@RequestMapping(value = "anniu")
//	@ResponseBody
//	public HashMap<String, Object> anniu(String flow) throws Exception {
//		
//		String list=reportYjszDao.getReportYjsz(flow).toString();
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		String ff="["+flow+"]";
//		//1成功，2失败，0重复
//		if(list.equals(ff)){
//			map.put("success", false);
//			map.put("msg", "此条已有！请勿重复添加！！");
//		}else{
//			map.put("success", true);
//		}
//		return map;
//		
//	}
	 /**
		 * 添加
		 * @param response
	     * @throws Exception 
		 */
	@RequestMapping(value = "save1")
  	@ResponseBody
	public HashMap<String, Object> save1(HttpServletRequest request,ReportYjsz reportYjsz) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		//开始环节
		String startGz=reportYjszDao.getReportYjsz(reportYjsz.getFlow(),reportYjsz.getFlows()).toString();
		//结束环节
		String endGz=reportYjszDao.getReportYjsz1(reportYjsz.getFlow(),reportYjsz.getFlows()).toString();
		//本环节的id
		String a=reportYjszDao.getids(reportYjsz.getFlow(),reportYjsz.getFlows()).toString();
		String ids = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString()
    			.substring(1,a.toString().length()-1)).replaceAll("'$1'");
		String startGz1="["+reportYjsz.getFlow()+"]";
		String endGz1="["+reportYjsz.getFlows()+"]";

		if(reportYjsz.getFlow()!=null && reportYjsz.getFlows()!=null){
			int flow=Integer.parseInt(reportYjsz.getFlow());
			int flows=Integer.parseInt(reportYjsz.getFlows());
			if(flows>flow){
				if(startGz.equals(startGz1) && endGz.equals(endGz1)){//判断是否有这个规则
					if(ids.equals(reportYjsz.getId())){
						super.save(request, reportYjsz);
						map.put("success", true);
					}else{
						map.put("success", false);
						map.put("msg", "环节选择不能重复！");
					}
				}else{
					super.save(request, reportYjsz);
					map.put("success", true);
				}
			}else{
				map.put("success", false);
				map.put("msg", "结束环节要在开始环节之后！");
			}
		}else{
			map.put("success", false);
			map.put("msg", "环节不能为空！");
		}
		
		return map;
	}
	/**
	 * 删除
	 * */
	@RequestMapping(value = "delete1")
  	@ResponseBody
	public HashMap<String, Object> delete1(String id) throws Exception {
		return super.delete(id);

	}
	/**
	 * 查询
	 * */
	@RequestMapping(value = "detailWork")
  	@ResponseBody
  	public HashMap<String, Object> detailWork(HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  			List<ReportYjsz> list= reportYjszManager.getByEmpId();
  			
  			wrapper.put("Rows", list);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "查询信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
	/**
	 * 测试
	 * */
	@RequestMapping(value = "reportYj")
	@ResponseBody 
	public void reportYj(String id,HttpServletRequest request) throws Exception {

//		reportYjszManager.reportYj(id,request);
	}
	
	
	/**启用
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "qiyong")
	@ResponseBody
	public HashMap<String, Object> qiyong(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			ReportYjsz reportYjsz = reportYjszManager.get(id);
			if(reportYjsz==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				reportYjsz.setState(ReportYjszManager.YJ_QY);//已启用
				reportYjszManager.save(reportYjsz);
				
				map.put("success", true);
			}
		}
		return map;
	}
	
	/**停用
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "tingyong")
	@ResponseBody
	public HashMap<String, Object> tingyong(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			ReportYjsz reportYjsz = reportYjszManager.get(id);
			if(reportYjsz==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				reportYjsz.setState(ReportYjszManager.YJ_TZ);//停用
				reportYjszManager.save(reportYjsz);
				
				map.put("success", true);
			}
		}
		return map;
	}
	
	
	
   
	
}