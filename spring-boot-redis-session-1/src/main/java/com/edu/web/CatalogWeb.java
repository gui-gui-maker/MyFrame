package com.edu.web;

import java.io.BufferedOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.bean.Catalog;
import com.edu.bean.CodeUniversity;
import com.edu.bean.PlanParam;
import com.edu.bean.User;
import com.edu.service.CatalogService;
import com.edu.service.PlanService;
import com.edu.service.UniversityService;
import com.edu.service.impl.RedisService;
import com.edu.util.BusinessUtil;
import com.edu.util.StringUtil;


@Controller
@RequestMapping("catalogWeb")
public class CatalogWeb {
	@Autowired
	CatalogService catalogService;
	@Autowired
	private RedisService redisService;
	@Autowired
	UniversityService universityService;
	@Autowired
	PlanService planService;
	
	@RequestMapping(value="showFullCatalog")
	@ResponseBody
	public Map<String,Object> listAll() throws Exception{
		Map<String,Object> wapper = new HashMap<String,Object>();
		wapper.put("success",true);
		wapper.put("data",catalogService.findRoot().getNodes());
		return wapper;
	}
	@RequestMapping(value="listOneByYxdh")
	@ResponseBody
	public Map<String,Object> listOneByYxdh(String yxdh) throws Exception{
		Map<String,Object> wapper = new HashMap<String,Object>();
		wapper.put("success",true);
		wapper.put("data",catalogService.listOneByYxdh(yxdh).getNodes());
		return wapper;
	}
	@RequestMapping(value="listOneByYxdm")
	@ResponseBody
	public Map<String,Object> listOneByYxdm(HttpServletRequest request,String yxdm) throws Exception{
		Map<String,Object> wapper = new HashMap<String,Object>();
		if(StringUtil.isEmpty(yxdm)) {
			User user = (User) redisService.get(request.getSession().getId());
			yxdm = user.getProfiles().iterator().next().getYxdm();
		}
		//院校代号
		wapper.put("data",catalogService.listOneByYxdm(yxdm).getNodes());
		wapper.put("success",true);
		return wapper;
	}
	@RequestMapping(value="print")
	public String print(HttpServletRequest request,String yxdm) throws Exception{
		
		return "paper";
	}
	@RequestMapping(value="catalog")
	public void exportCatalog(HttpServletRequest request,HttpServletResponse response,String yxdm) throws Exception{
		StringBuilder htmlBuilder = new StringBuilder();
		String fileName = "";
		//一级目录格式
		//String mlgs1  = "<p style='margin:0cm'><span style='font-size:21.5pt;font-family:黑体'>" ;
		//二级目录格式
		//String	mlgs2  = "<p style='margin:0cm'><span style='font-size:15.5pt;font-family:黑体'>" ;
		//三级目录格式
		//String mlgs3  = "<p style='margin:0cm'><span style='font-size:14.0pt;font-family:黑体'>";
		//计划标题栏格式
		//String	zwgs1  = "<p style='margin:0cm;tab-stops:right 336.0pt;'><span style='font-size:10.5pt;font-family:黑体'>"; 
		//学校代号、学校名称、计划数，栏格式
		//String	zwgs11 = "<p style='margin:0cm;'><span style='font-size:10.5pt;font-family:黑体'>" ;  
		//地址、备注的格式； 
		//String zwgs2  = "<p style='margin:0cm'><span style='font-size:10.5pt;font-family:黑体'>";   
		//地址内容的格式
		//String zwgs21 = "<span style='font-size:10.5pt;font-family:楷体_GB2312'>";
		//专业目录正文的格式
		//String zwkt1  = "<p style='margin:0cm;tab-stops:right 336.0pt;'><span style='font-size:10.5pt;font-family:宋体'>";
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		//按国标代码查院校
		List<CodeUniversity> list = null;
		if(!StringUtil.isEmpty(yxdm)) {
			list = universityService.findByYxdmAndNf(yxdm,c.get(Calendar.YEAR));
			fileName = list.get(0).getYxdm()+list.get(0).getYxmc();
		}else {
			list = universityService.findAll();
			fileName="通稿";
		}
		if(list==null||list.size()==0) {
			throw new Exception("院校代码不正确");
		}
		for (CodeUniversity yx : list) {
			//开始拼接文档,每个学校一份
			htmlBuilder.append(
				"<html>"
					+ "<head></head>"
					+ "<body>"
						+ "<div>"
							+ "<p style='margin:0cm'>"
								+ "<span style='font-size:21.5pt;font-family:黑体'>"+yx.getYxdm()
								+ "<span style='mso-tab-count:1'></span>"+yx.getYxmc()+"</span>"
							+ "</p>"
							+ "<p style='margin:0cm'><span style='font-size:14.0pt;font-family:黑体'><br/>核对结果：_______________</span></p>"
							+ "<p style='margin:0cm'><span style='font-size:14.0pt;font-family:黑体'>核对人签字：_______________联系方式：_______________</span></p>"
							+ "<p style='margin:0cm'><span style='font-size:14.0pt;font-family:黑体'>招生负责人签字（盖公章）：_______________</span></p>"
							+ "<p style='margin:0cm'><span style='font-size:14.0pt;font-family:黑体'>打印时间："+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"_______________</span><br/></p>"
							+ "<p style='margin:0cm'><span style='font-size:14.0pt;font-family:黑体'>——————————————————————————<br/>此稿为已出版稿，注意事项如下：</span><br/></p>"
							+ "<p style='margin:0cm'>"
								+ "<span style='font-size:12.0pt;font-family:黑体'>"
									+ "1.自主招生不公布。<br/>"
									+ "2.高水平艺术团、高水平运动队只公布院校户头,不公布具体专业。<br/>"
									+ "3.如有修改，请将修改内容签字盖章扫描发送至scpzjh@qq.com邮箱。"
									+ "否则，必须在来源计划系统中点击核对无误按钮。"
									+ "逾期未按此要求核对的高校我省将以教育部下达并经我省修正后的计划信息，向社会公布。<br/>"
									+ "4.因高校疏于核对产生的一切后果，由院校负责解释。"
								+ "</span><br/>"
							+ "</p><br/>");
			//一个学校多个院校代号（省编代号）
			List<CodeUniversity> yxdhs =  universityService.findByYxdhAndNf(yx.getYxdh(),c.get(Calendar.YEAR));
			int index = 0;
			for(CodeUniversity yxdh : yxdhs) {
				index ++;
				htmlBuilder.append(
						"<p style='margin:0cm'>"
							+ "<span style='font-size:21.5pt;font-family:黑体'>"+yxdh.getYxdh()
								+"<span style='mso-tab-count:1'></span>"+yxdh.getYxmc()
							+ "</span>"
						+ "</p>");
				
				if(index>1) {
					String textBuilder = 
							"<p style='margin:0cm'>"
									+ "<span style='font-size:21.5pt;font-family:黑体'>"+yxdh.getYxdh()
										+ "<span style='mso-tab-count:1'></span>"+yxdh.getYxmc()
									+"</span>"
							+ "</p>";
					htmlBuilder.append(
							"<p>"
							+ "<span><br clear=all style='mso-special-character:line-break;page-break-before:always'></span>"
									+yxdh.getYxdh()
									+"<span style='mso-tab-count:1'></span>"+yxdh.getYxmc()
											+ "</span>"
							+ "</p>"+textBuilder);
				}
				
				//专业目录
				List<Catalog> catalogs = catalogService.yxCatalog(yxdh.getYxdh());
				for (Catalog catalog : catalogs) {
					//招生大类
					if("0000".equals(catalog.getId().substring(1))) {
						htmlBuilder.append("<br/>"
								+ "<p style='margin:0cm'>"
									+ "<span style='font-size:21.5pt;font-family:黑体'>"+catalog.getText()+"</span>"
								+ "</p>");
					}else if("000".equals(catalog.getId().substring(2))) {
						htmlBuilder.append("<br/>"
								+ "<p style='margin:0cm'>"
									+ "<span style='font-size:15.5pt;font-family:黑体'>"+catalog.getText()+"</span>"
								+ "</p>");
					}else if("00".equals(catalog.getId().substring(3))||"0".equals(catalog.getId().substring(4))) {
						htmlBuilder.append("<br/>"
								+"<p style='margin:0cm'>"
									+ "<span style='font-size:14.0pt;font-family:黑体'>"+catalog.getText()+"</span>"
								+ "</p>");
					}else {
						htmlBuilder.append(
								"<p style='margin:0cm'>"
									+ "<span style='font-size:14.0pt;font-family:黑体'>"+catalog.getText()+"</span>"
								+ "</p>\t\t");
					}
					//加专业列表
					if("1".equals(catalog.getSflb())) {
						htmlBuilder.append(
								"<p style='margin:0cm;tab-stops:right 336.0pt;'>"
									+ "<span style='font-size:10.5pt;font-family:黑体'>学校代号、名称及专业代号、名称"
										+ "<span style='mso-tab-count:1'></span>计划"
										+ "<span style='mso-tab-count:1'></span>收费"
									+ "</span>"
								+ "</p>");
						List<PlanParam> list3 = planService.findByYxdhAndDyml(yxdh.getYxdh(), catalog.getId());
						//代号、院校名称、计划数
						PlanParam planParam = list3.get(0);
						htmlBuilder.append(
								"<p style='margin:0cm;'>"
									+ "<span style='font-size:10.5pt;font-family:黑体'>"
											+planParam.getYxdh()+planParam.getYxmc()+planParam.getYxjblxmc()
											+"<span style='mso-tab-count:1'></span>"+planParam.getZjhs()
									+ "</span>"
								+ "</p>");
						//院校地址
						htmlBuilder.append(
								"<p style='margin:0cm'>"
										+ "<span style='font-size:10.5pt;font-family:黑体'>地址：</span>"
										+ "<span style='font-size:10.5pt;font-family:楷体_GB2312'>"+planParam.getYxdz()+"</span>"
								+ "</p>");
						//院校代码名称
						String flag = null;
						if(planParam.getYxdhmc().contains("(民族班)")) {
							flag="◆以下为民族班计划";
						}else if(planParam.getYxdhmc().contains("(定藏就业)")&&"5".equals(planParam.getJhxzdm())) {
							flag="◆以下为定向西藏就业招生计划";
						}else if(planParam.getYxdhmc().contains("高端技术技能")) {
							flag="◆以下为高端技术技能型本科";
						}
						if(flag!=null) {
							htmlBuilder.append(
									"<p style='margin:0cm;tab-stops:right 336.0pt;'>"
											+ "<span style='font-size:10.5pt;font-family:黑体'>"+flag+"</span>"
									+ "</p>");
						}
						//System.out.println("院校代号："+yxdh.getYxdh());
						//接下来添加专业列表
						List<PlanParam> majors =planService.queryMajors(yxdh.getYxdh(), catalog.getId());
						int f2 = 1;
						for (PlanParam major : majors) {
							if("12".equals(major.getJhlbdm())) {
								htmlBuilder.append(
										"<p style='margin:0cm;tab-stops:right 336.0pt;'>"
												+ "<span style='font-size:10.5pt;font-family:宋体'>"
													+"<span style='mso-spacerun:yes'>&nbsp;</span>YS 高水平艺术团"
												+ "</span>"
										+ "</p>");
							}else if("13".equals(major.getJhlbdm())) {
								htmlBuilder.append(
										"<p style='margin:0cm;tab-stops:right 336.0pt;'>"
												+ "<span style='font-size:10.5pt;font-family:宋体'>"
													+"<span style='mso-spacerun:yes'>&nbsp;</span>YS 高水平运动队"
												+ "</span>"
										+ "</p>");
							}
							if("1".equals(major.getJhxzdm()) && f2==1) {
								f2++;
								htmlBuilder.append(
										"<p style='margin:0cm;tab-stops:right 336.0pt;'>"
												+ "<span style='font-size:10.5pt;font-family:黑体'>"+"◆以下为定向就业招生计划</span>"
										+ "</p>");
							}
							if("6".equals(major.getJhxzdm())&&"0".equals(major.getPcdm()) && f2==1) {
								f2++;
								htmlBuilder.append(
										"<p style='margin:0cm;tab-stops:right 336.0pt;'>"
												+ "<span style='font-size:10.5pt;font-family:黑体'>"+"◆以下为公费师范生招生计划</span>"
										+ "</p>");
							}
							//
							String sszymc = BusinessUtil.getZymc(major);
				
							htmlBuilder.append(
									"<p style='margin:0cm;tab-stops:right 336.0pt;'>"
										+ "<span style='font-size:10.5pt;font-family:宋体'>"
												+"<span style='mso-spacerun:yes'>&nbsp;</span>"
													+(StringUtil.isEmpty(major.getSbzydh())?major.getXbzydh():major.getSbzydh())+" "+sszymc
												+"<span style='mso-tab-count:1'></span>"+major.getZsjhs()
												+"<span style='mso-tab-count:1'></span>"+major.getSfbz()
										+"</span>"
									+ "</p>");
						}
						//显示该院校代号的备注及备注内容；高水平不显示
						if(!"12".equals(planParam.getJhlbdm())
								&&!"13".equals(planParam.getJhlbdm())
								&&!"14".equals(planParam.getJhlbdm())
								&& StringUtil.isNotEmpty(planParam.getYxbz())
								&& StringUtil.isNotEmpty(planParam.getJffs())
								) {
							htmlBuilder.append(
									"<p style='margin:0cm'>"
											+ "<span style='font-size:10.5pt;font-family:黑体'>备注：</span>"
											+ "<span style='font-size:10.5pt;font-family:楷体_GB2312'>"+("41".equals(planParam.getJhlbdm())?"":planParam.getJffs())+planParam.getYxbz()+"</span>"
									+ "</p>");
						} 
					}
				}
				htmlBuilder.append("</div></body></html>");
			}
		}
		/* BufferedWriter out=null;*/
	    ServletOutputStream outSTr = response.getOutputStream();// 建立;
	    BufferedOutputStream buff = null;
	    try{
	        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".doc");
	        response.setContentType("text/plain");
	        buff = new BufferedOutputStream(outSTr);
	        buff.write(htmlBuilder.toString().getBytes(StandardCharsets.UTF_8));
	        buff.flush();
	        buff.close();
	    }catch (Exception ex){
	        ex.printStackTrace();
	    }finally {
	        try {
	            buff.close();
	            outSTr.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
}
