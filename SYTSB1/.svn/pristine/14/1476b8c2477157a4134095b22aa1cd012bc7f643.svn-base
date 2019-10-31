package com.lsts.qualitymanage.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.qualitymanage.bean.QualityXybzFile;
import com.lsts.qualitymanage.service.QualityXybzFileManager;
import com.lsts.report.service.ReportYjszManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("xybz/file1")
public class QualityXybzFileAction extends SpringSupportAction<QualityXybzFile, QualityXybzFileManager> {

    @Autowired
    private QualityXybzFileManager  qualityXybzFileManager;
    
    
    DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    /**
   	 * 添加
   	 * @param response
     * @throws Exception 
   	 */
    @RequestMapping(value = "save1")
   	@ResponseBody
    public HashMap<String, Object> save1(HttpServletRequest request,QualityXybzFile qualityXybzFile) throws Exception {
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
       	HashMap<String, Object> map = new HashMap<String, Object>();
       	
   		qualityXybzFile.setRegistrantId(user.getId());
   		qualityXybzFile.setRegistrantTime(new Date());
   		qualityXybzFile.setStatus(ReportYjszManager.YJ_WQY);//未启用
       	return super.save(request, qualityXybzFile);

   	}
    /**
     * 计算日期
     * */
    @RequestMapping(value = "yxq")
	@ResponseBody
	public HashMap<String, Object> yxq(String d1,String d2) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(sdf.parse(d2).getTime()>=sdf.parse(d1).getTime()){
	   		map.put("success", true);
	   	}else{
	   		map.put("success", false);
	   		map.put("msg", "到期时间不能小于开始时间");
	   	}
		return map;
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
			QualityXybzFile qualityXybzFile = qualityXybzFileManager.get(id);
			if(qualityXybzFile==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				//设置停用人
				qualityXybzFile.setEndRegistrant(null);
				qualityXybzFile.setEndRegistrantId(null);
				qualityXybzFile.setStopTime(null);
				qualityXybzFile.setStatus(ReportYjszManager.YJ_QY);//已启用
				qualityXybzFileManager.save(qualityXybzFile);
				
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
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			QualityXybzFile qualityXybzFile = qualityXybzFileManager.get(id);
			if(qualityXybzFile==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				//设置停用人
				qualityXybzFile.setEndRegistrant(user.getName());
				qualityXybzFile.setEndRegistrantId(user.getId());
				qualityXybzFile.setStopTime(new Date());
				qualityXybzFile.setStatus(ReportYjszManager.YJ_TZ);//已停用
				qualityXybzFileManager.save(qualityXybzFile);
				
				map.put("success", true);
			}
		}
		return map;
	}
	
}