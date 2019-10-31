package com.edu.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.edu.anno.AuthToken;
import com.edu.bean.Plan;
import com.edu.bean.PlanEditApply;
import com.edu.bean.User;
import com.edu.service.PlanEditApplyService;
import com.edu.service.PlanService;
import com.edu.service.impl.RedisService;
import com.edu.util.StringUtil;

import net.sf.json.JSONObject;




@Controller
@RequestMapping("planWeb")
public class PlanWeb {

	@Autowired
	private RedisService redisService;
	@Autowired
	private PlanService publishPlanService;
	@Autowired
	private PlanEditApplyService planEditApplyService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
	
	@RequestMapping(value="viewAllPlans")
	//@AuthToken(hasRole = { "admin", "college" })
	public String toJyJh() {
		
		return "PlanList";
	}
	@RequestMapping(value="viewUniversityPlans")
	//@AuthToken(hasRole = { "admin", "college" })
	public String viewUniversityPlans(HttpServletRequest request) {
		//院校代号
		User user = (User) redisService.get(request.getSession().getId());
		request.setAttribute("yxdm",user.getYxdm());
		return "PlanUniversityList";
	}
	
	/**
	 * 获取分页数据（所有）
	 * @param page
	 * @param pageSize
	 * @param jyjh
	 * @return
	 */
	@RequestMapping(value="list")
	@ResponseBody
	//@AuthToken(hasRole = { "admin", "college" })
	public HashMap<String,Object> findByCondition (
			HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer pageSize,
            Plan temp) throws Exception {
		
		HashMap<String,Object> map =new HashMap<String,Object>();
		Sort sort = new Sort(Sort.Direction.DESC, "yxdm");
	    Pageable pageable = PageRequest.of(page-1, pageSize, sort);
		Page<Plan> rows;
		
		try {
			rows = publishPlanService.findByCondition(temp,pageable);
			map.put("total",rows.getTotalElements());
			map.put("rows",rows.getContent());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("total",0);
			map.put("rows",null);
		}
		return map;
	}
	@RequestMapping(value="getPlanEditApply")
	@ResponseBody
	public HashMap<String,Object> getPlanEditApply (HttpServletRequest request,String id) throws Exception {
		
		HashMap<String,Object> map =new HashMap<String,Object>();
		List<PlanEditApply> apps = planEditApplyService.findByFid(id);
		map.put("total",apps.size());
		map.put("rows",apps);
		return map;
	}

	@RequestMapping(value="save")
	@ResponseBody
	public HashMap<String,Object> save(HttpServletRequest request,Plan plan) throws Exception{
		
		HashMap<String,Object> map =new HashMap<String,Object>();
		try {
			User user = (User) redisService.get(request.getSession().getId());
			if(StringUtil.isEmpty(plan.getId())) {
				plan.setJhid(user.getUserName() + plan.getJhid());
			}
			plan.setXgbj(plan.getXgbj()+1);
			//院校代号
			plan.setLastUpdateBy(user.getUserName());
			plan.setLastUpdateTime(new Date());
			plan.setStatus("0");
			publishPlanService.save(plan);
			map.put("success",true);
			map.put("data", plan);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("msg",e.getMessage());
		}
		return map;
	}
	@RequestMapping(value="editApply")
	@ResponseBody
	public HashMap<String,Object> editApply(HttpServletRequest request,String apply) throws Exception {
		HashMap<String,Object> map =new HashMap<String,Object>();
		User user = (User) redisService.get(request.getSession().getId());
		planEditApplyService.save(apply,user);
		map.put("success", true);
		return map;
	}
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="ldel")
	@ResponseBody
	@AuthToken(hasRole = { "admin", "college" })
	public HashMap<String,Object> ldel(String ids) {
		HashMap<String,Object> map =new HashMap<String,Object>();
		try {
			
			publishPlanService.ldel(ids);
			map.put("success",true);
			map.put("data",ids);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg",e.getMessage());
			map.put("success",false);
		}
		return map;
	}
	


/*
	
	@RequestMapping(value="print")
	@ResponseBody
	public String print(String yxdm) throws Exception {
		
		//一级目录格式
		String mlgs1  = "<p style='margin:0cm'><span style='font-size:21.5pt;font-family:黑体'>" ;
		//二级目录格式
		String	mlgs2  = "<p style='margin:0cm'><span style='font-size:15.5pt;font-family:黑体'>" ;
		//三级目录格式
		String mlgs3  = "<p style='margin:0cm'><span style='font-size:14.0pt;font-family:黑体'>";
		//计划标题栏格式
		String	zwgs1  = "<p style='margin:0cm;tab-stops:right 336.0pt;'><span style='font-size:10.5pt;font-family:黑体'>"; 
		//学校代号、学校名称、计划数，栏格式
		String	zwgs11 = "<p style='margin:0cm;'><span style='font-size:10.5pt;font-family:黑体'>" ;  
		//地址、备注的格式； 
		String zwgs2  = "<p style='margin:0cm'><span style='font-size:10.5pt;font-family:黑体'>";   
		//地址内容的格式
		String zwgs21 = "<span style='font-size:10.5pt;font-family:楷体_GB2312'>";
		//专业目录正文的格式
		String zwkt1  = "<p style='margin:0cm;tab-stops:right 336.0pt;'><span style='font-size:10.5pt;font-family:宋体'>";
	    
		//按国标代码查院校
		List<Object[]> list = null;
		if(!StringUtil.isNullOrEmpty(yxdm)) {
			list = publishPlanService.findYxdmAndYxmc(yxdm);
		}else {
			list = publishPlanService.findAllYxdmAndYxmc();
		}
		if(list==null||list.size()==0) {
			throw new Exception("院校代码不正确");
		}
		for (Object[] yx : list) {
			StringBuilder htmlBuilder = new StringBuilder();
			String v_yxdm = yx[0].toString();
			//开始拼接文档,每个学校一份
			htmlBuilder.append("<html><head></head><body>").append(mlgs1)
			.append(v_yxdm).append("<span style='mso-tab-count:1'></span>").append(yx[1].toString()).append("</span></p>")
			.append(mlgs3).append("<br/>核对结果：_______________</span></p>")
			.append(mlgs3).append("核对人签字：_______________联系方式：_______________</span></p>")
			.append(mlgs3).append("招生负责人签字（盖公章）：_______________</span></p>")
			.append(mlgs3).append("打印时间：").append(new Date()).append("</span><br/></p>")
			.append(mlgs3).append("——————————————————————————<br/>此稿为已出版稿：</span><br/></p>")
			.append("1.请核实自主招生专业是否有漏（出版稿不公布）；<br>")
			.append("2.请核实高水平艺术团、高水平运动队专业是否有漏（出版稿只公布院校,不公布专业）；<br>")
			.append("3.请贵校重点核对计划批次、计划类别、计划数、招考方向、专业备注及性别要求信息。请将需要修改的内容(签字盖章的扫描件)发送至scpzjh@qq.com邮箱。</span><br/></p><br>");
			//一个学校多个院校代号（省编代号）
			List<Object[]> list2 =  publishPlanService.findyxdhYxdmAndYxmc(yx[0].toString());
			int index = 0;
			for(Object[] yxs : list2) {
				index ++;
				htmlBuilder.append(mlgs1).append(yxs[0].toString()+"<span style=''mso-tab-count:1''></span>"+yxs[2].toString()).append("</span></p>");
				
				if(index>1) {
					String textBuilder = mlgs1+yxs[0].toString()+"<span style=''mso-tab-count:1''></span>"+yxs[2].toString()+"</span></p>";
					htmlBuilder.append("<span><br clear=all style=''mso-special-character:line-break;page-break-before:always''></span>")
					.append(yxs[0].toString())
					.append("<span style=''mso-tab-count:1''></span>")
					.append(yxs[2].toString()).append("</span></p>").append(textBuilder);
				}
				
				//专业目录
				List<Object[]> mls = publishPlanService.findPrintMulu(yxs[0].toString());
				for (int i = 0; i < mls.size(); i++) {
					String ml = mls.get(i)[0].toString();
					String msg = mls.get(i)[1].toString();
					String sflb = mls.get(i)[2].toString();
					//招生大类
					if("0000".equals(ml.substring(1))) {
						htmlBuilder.append("<br>").append(mlgs1).append(msg).append("</span></p>");
					}else if("000".equals(ml.substring(2))) {
						htmlBuilder.append("<br>").append(mlgs2).append(msg).append("</span></p>");
					}else if("00".equals(ml.substring(3))||"0".equals(ml.substring(4))) {
						htmlBuilder.append("<br>").append(mlgs3).append(msg).append("</span></p>");
					}else {
						htmlBuilder.append(mlgs3).append(msg).append("</span></p>\t\t");
					}
					//加专业列表
					if("1".equals(sflb)) {
						htmlBuilder.append(zwgs1).append("学校代号、名称及专业代号、名称<span style='mso-tab-count:1'></span>计划<span style='mso-tab-count:1'></span>收费</span></p>");
						List<Object[]> list3 = publishPlanService.queryPlan(yxs[0].toString(), ml);
						//代号、院校名称、计划数
						Object[] objs = list3.get(0);
						//String $yx = objs[0].toString();
						String $yxbz = objs[1]!=null?objs[1].toString():"";
						String $sjhlb = objs[2]!=null?objs[2].toString():"";
						//String $yxmc = objs[3].toString();
						//String $yxjblxmc = objs[42].toString();
						//String $zjhs = objs[5].toString();
						//String $yxdz = objs[6].toString();
						//String $yxdhmc = objs[7].toString();
						//String $jhxzdm = objs[8].toString();
						String $jffs = objs[9]!=null?objs[9].toString():"";
								
						htmlBuilder.append(zwgs11)
						.append(objs[0].toString().trim())
						.append(objs[3].toString().trim())
						.append(objs[4].toString().trim())
						.append("<span style='mso-tab-count:1'></span>")
						.append(Integer.parseInt(objs[5].toString().substring(0,objs[5].toString().indexOf(".")))==0?"":Integer.parseInt(objs[5].toString().substring(0,objs[5].toString().indexOf("."))))
						.append("</span></p>");
						//院校地址
						htmlBuilder.append(zwgs2)
						.append("地址：</span>")
						.append(zwgs21)
						.append(objs[6].toString().trim())
						.append("</span></p>");
						//院校代码名称
						String yxdhmc = objs[7].toString();
						//计划性质
						String jhxzdm = objs[8].toString();
						String flag = null;
						if(yxdhmc.contains("(民族班)")) {
							flag="◆以下为民族班计划";
						}else if(yxdhmc.contains("(定藏就业)")&&"5".equals(jhxzdm)) {
							flag="◆以下为定向西藏就业招生计划";
						}else if(yxdhmc.contains("高端技术技能")) {
							flag="◆以下为高端技术技能型本科";
						}
						if(flag!=null) {
							htmlBuilder.append(zwgs1+flag+"</span></p>");
						}
						System.out.println("院校代号："+yxs[0].toString());
						System.out.println("目录："+ml);
						//接下来添加专业列表
						List<Object[]> majors = publishPlanService.queryMajorList(yxs[0].toString(), ml);
						int f2 = 1;
						for (Object[] major : majors) {
							String jhlbdm = major[7].toString();
							if("12".equals(jhlbdm)) {
								htmlBuilder.append(zwkt1+"<span style='mso-spacerun:yes'>&nbsp;</span>YS 高水平艺术团</span></p>");
							}else if("13".equals(jhlbdm)) {
								htmlBuilder.append(zwkt1+"<span style='mso-spacerun:yes'>&nbsp;</span>YS 高水平运动队</span></p>");
							}
							String jhxzdm2 = major[14].toString();
							String pcdm = major[5].toString();
							if("1".equals(jhxzdm2)&&f2==1) {
								f2++;
								htmlBuilder.append(zwgs1+"◆以下为定向就业招生计划</span></p>");
							}
							if("6".equals(jhxzdm2)&&"0".equals(pcdm)&&f2==1) {
								f2++;
								htmlBuilder.append(zwgs1+"◆以下为公费师范生招生计划</span></p>");
							}
							//
							String sszymc = BusinessUtil.getZymc(major[0],major[1],major[2],major[3],major[4],major[5],major[6],
									major[7],major[8],major[9],major[10],major[11],major[12],major[13],major[14]);
				
							String sbzydh= major[15].toString();
							String xbzydh= major[16].toString();
							String sfbz= major[18].toString();
							htmlBuilder.append(zwkt1+"<span style='mso-spacerun:yes'>&nbsp;</span>"+(StringUtil.isNullOrEmpty(sbzydh)?xbzydh:sbzydh)+" "+sszymc+"<span style=''mso-tab-count:1''></span>"+sfbz.trim()+"</span></p>");
						}
						//显示该院校代号的备注及备注内容；高水平不显示
						if(!"12".equals($sjhlb)
								&&!"13".equals($sjhlb)
								&&!"14".equals($sjhlb)
								&&!(StringUtil.isNullOrEmpty($yxbz)&&StringUtil.isNullOrEmpty($jffs))
								) {
							htmlBuilder.append(zwgs2+"备注：</span>"+zwgs21+("41".equals($sjhlb)?"":$jffs.trim())+$yxbz.trim());
						} 
					}
				}
				htmlBuilder.append("</div></body></html>");
			}
			return htmlBuilder.toString();
			
		}

		return "打印成功";
	}*/
}
