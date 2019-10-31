package com.fwxm.scientific.web;

import com.fwxm.scientific.bean.Tjy2ScientificInterim;
import com.fwxm.scientific.bean.Tjy2ScientificResearch;
import com.fwxm.scientific.service.Tjy2ScientificInterimManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;












import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("tjy2ScientificInterimAction")
public class Tjy2ScientificInterimAction extends SpringSupportAction<Tjy2ScientificInterim, Tjy2ScientificInterimManager> {

    @Autowired
    private Tjy2ScientificInterimManager  tjy2ScientificInterimManager;
    
    
    
    //保存中期检查表
  	@RequestMapping(value = "saveInterim")
  	@ResponseBody
  	public HashMap<String, Object> saveInterim(HttpServletRequest request, Tjy2ScientificInterim entity) throws Exception {
  		CurrentSessionUser user=SecurityUtil.getSecurityUser();
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try {
  			if(entity.getId()==null||entity.getId().equals("")){
  				entity.setCreateDate(new Date());
  				entity.setCreateMan(user.getName());
  			}
  			tjy2ScientificInterimManager.save(entity);
  			wrapper.put("data", entity);
  			wrapper.put("success", true);
  		} catch (Exception e) {
  			wrapper.put("success", false);
  		}
  		return wrapper;
  	}
  //按项目id查询中期检查表
  	@RequestMapping(value = "detailInterim")
  	@ResponseBody
  	public HashMap<String, Object> detailInterim(HttpServletRequest request, String id) throws Exception {
  		CurrentSessionUser user=SecurityUtil.getSecurityUser();
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try {
  			Tjy2ScientificInterim base=tjy2ScientificInterimManager.detailInterim(id);
  			wrapper.put("data", base);
  			wrapper.put("success", true);
  		} catch (Exception e) {
  			wrapper.put("success", false);
  		}
  		return wrapper;
  	}
	/**
	 * 导出word
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "input")
  	@ResponseBody
  	public void input(HttpServletRequest request,HttpServletResponse response,String id) throws Exception {
		Tjy2ScientificInterim entity = tjy2ScientificInterimManager.get(id);//获取中值期检查表
		Object[] o1=tjy2ScientificInterimManager.detailBase("10000");//获取中期检查表导出模板
		Clob clob1=(Clob)o1[0];
		String fileMb=clob1.getSubString((long)1,(int)clob1.length());
		fileMb=fileMb.replace("${projectName}", entity.getProjectName());
		fileMb=fileMb.replace("${projectNo}", entity.getProjectNo());
		fileMb=fileMb.replace("${projectSource}", entity.getProjectSource());
		fileMb=fileMb.replace("${projectParticipationMan}", entity.getProjectParticipationMan());
		fileMb=fileMb.replace("${approvalMoney}", entity.getApprovalMoney());
		fileMb=fileMb.replace("${projectStartEnd}", entity.getProjectStartEnd());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日"); 
	    String date1=sdf.format(entity.getProjectMidDate());
		fileMb=fileMb.replace("${projectMidDate}", date1);
		fileMb=fileMb.replace("${projectPhase}", entity.getProjectPhase()==null?"":entity.getProjectPhase());
		fileMb=fileMb.replace("${scheduledTask}", entity.getScheduledTask()==null?"":entity.getScheduledTask());
		fileMb=fileMb.replace("${projectProgress}", entity.getProjectProgress()==null?"":entity.getProjectProgress());
		fileMb=fileMb.replace("${progressInstructions}", entity.getProgressInstructions()==null?"":entity.getProgressInstructions());
		fileMb=fileMb.replace("${cost}", entity.getTotalCost()==null?"":entity.getTotalCost());
		fileMb=fileMb.replace("${cost1}", entity.getTotalCost1()==null?"":entity.getTotalCost1());
		fileMb=fileMb.replace("${cost2}", entity.getTotalCost2()==null?"":entity.getTotalCost2());
		fileMb=fileMb.replace("${cost3}", entity.getTotalCost3()==null?"":entity.getTotalCost3());
		fileMb=fileMb.replace("${cost4}", entity.getTotalCost4()==null?"":entity.getTotalCost4());
		fileMb=fileMb.replace("${cost5}", entity.getTotalCost5()==null?"":entity.getTotalCost5());
		fileMb=fileMb.replace("${cost6}", entity.getTotalCost6()==null?"":entity.getTotalCost6());
		fileMb=fileMb.replace("${cost7}", entity.getTotalCost7()==null?"":entity.getTotalCost7());
		fileMb=fileMb.replace("${c8}", entity.getTotalCost8()==null?"":entity.getTotalCost8());
		fileMb=fileMb.replace("${cost9}", entity.getTotalCost9()==null?"":entity.getTotalCost9());
		fileMb=fileMb.replace("${results1}", entity.getResults1()==null?"":entity.getResults1());
		fileMb=fileMb.replace("${results2}", entity.getResults2()==null?"":entity.getResults2());
		fileMb=fileMb.replace("${results3}", entity.getResults3()==null?"":entity.getResults3());
		fileMb=fileMb.replace("${results4}", entity.getResults4()==null?"":entity.getResults4());
		fileMb=fileMb.replace("${results5}", entity.getResults5()==null?"":entity.getResults5());
		fileMb=fileMb.replace("${results6}", entity.getResults6()==null?"":entity.getResults6());
		fileMb=fileMb.replace("${results7}", entity.getResults7()==null?"":entity.getResults7());
		 String content1="<html>"+fileMb+"</html>";
	        byte b[] = content1.getBytes("utf-8");  //这里是必须要设置编码的，不然导出中文就会乱码。
	        ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中  
	        
	        // 关键地方
	       // 生成word格式
	        
	        POIFSFileSystem poifs = new POIFSFileSystem();  
	        DirectoryEntry directory = poifs.getRoot();  
	        DocumentEntry documentEntry = directory.createDocument("WordDocument", bais); 
	        //输出文件
	        String fileName="科研项目中期检查表-"+entity.getProjectName();
	        request.setCharacterEncoding("utf-8");  
	        response.setContentType("application/msword");//导出word格式
	        response.addHeader("Content-Disposition", "attachment;filename=" +
	                 new String( (fileName + ".doc").getBytes(),  
	                         "iso-8859-1"));
	         
	        OutputStream ostream = response.getOutputStream(); 
	        poifs.writeFilesystem(ostream);  
	        bais.close();  
	        ostream.close(); 
	        
	}
	
}