package com.edu.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.bean.User;
import com.edu.jdbc.repository.impl.UniversityJDao;
import com.edu.service.CatalogService;
import com.edu.service.impl.RedisService;
import com.edu.util.StringUtil;


@Controller
@RequestMapping("catalogWeb")
public class CatalogWeb {
	@Autowired
	CatalogService catalogService;
	@Autowired
	UniversityJDao baseYxRepositoryImpl;
	@Autowired
	private RedisService redisService;
	
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
			yxdm = user.getYxdm();
		}
		//院校代号
		wapper.put("data",catalogService.listOneByYxdm(yxdm).getNodes());
		wapper.put("success",true);
		return wapper;
	}
	/*
	@RequestMapping(value="catalog")
	@ResponseBody
	public List<BaseList> exportCatalog(String yxdm){
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
				List<BaseYx> list = null;
				if(null!=yxdm&&!"".equals(yxdm)) {
					list = baseYxRepositoryImpl.findOne(yxdm);
				}else {
					list = baseYxRepositoryImpl.findAll();
				}
				if(list==null||list.size()==0) {
					throw new Exception("院校代码不正确");
				}
				for (BaseYx yx : list) {
					StringBuilder htmlBuilder = new StringBuilder();
					//开始拼接文档,每个学校一份
					htmlBuilder.append("<html><head></head><body>").append(mlgs1)
					.append(yx.getYxdm()).append("<span style='mso-tab-count:1'></span>").append(yx.getYxmc()).append("</span></p>")
					.append(mlgs3).append("<br/>核对结果：_______________</span></p>")
					.append(mlgs3).append("核对人签字：_______________联系方式：_______________</span></p>")
					.append(mlgs3).append("招生负责人签字（盖公章）：_______________</span></p>")
					.append(mlgs3).append("打印时间：").append(new Date()).append("</span><br/></p>")
					.append(mlgs3).append("——————————————————————————<br/>此稿为已出版稿：</span><br/></p>")
					.append("1.请核实自主招生专业是否有漏（出版稿不公布）；<br>")
					.append("2.请核实高水平艺术团、高水平运动队专业是否有漏（出版稿只公布院校,不公布专业）；<br>")
					.append("3.请贵校重点核对计划批次、计划类别、计划数、招考方向、专业备注及性别要求信息。请将需要修改的内容(签字盖章的扫描件)发送至scpzjh@qq.com邮箱。</span><br/></p><br>");
					//一个学校多个院校代号（省编代号）
					List<BaseYx> yxdhs =  baseYxRepositoryImpl.findObjectOfYxdh(yx.getYxdm());
					int index = 0;
					for(BaseYx yxdh : yxdhs) {
						index ++;
						htmlBuilder.append(mlgs1).append(yxdh.getYxdh()+"<span style=''mso-tab-count:1''></span>"+yxdh.getYxmc()).append("</span></p>");
						
						if(index>1) {
							String textBuilder = mlgs1+yxdh.getYxdh()+"<span style=''mso-tab-count:1''></span>"+yxdh.getYxmc()+"</span></p>";
							htmlBuilder.append("<span><br clear=all style=''mso-special-character:line-break;page-break-before:always''></span>")
							.append(yxdh.getYxdh())
							.append("<span style=''mso-tab-count:1''></span>")
							.append(yxdh.getYxmc()).append("</span></p>").append(textBuilder);
						}
						
						//专业目录
						/*
						List<Object[]> mls = enrollService.findPrintMulu(yxdh.getYxdh());
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
								List<Object[]> list3 = enrollService.queryPlan(yxdh.getYxdh(), ml);
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
								System.out.println("院校代号："+yxdh.getYxdh());
								System.out.println("目录："+ml);
								//接下来添加专业列表
								List<Object[]> majors = enrollService.queryMajorList(yxdh.getYxdh(), ml);
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
									htmlBuilder.append(zwkt1+"<span style='mso-spacerun:yes'>&nbsp;</span>"+(StringUtil.isNotEmpty(sbzydh)?xbzydh:sbzydh)+" "+sszymc+"<span style=''mso-tab-count:1''></span>"+sfbz.trim()+"</span></p>");
								}
								//显示该院校代号的备注及备注内容；高水平不显示
								if(!"12".equals($sjhlb)
										&&!"13".equals($sjhlb)
										&&!"14".equals($sjhlb)
										&&!(StringUtil.isNotEmpty($yxbz)&&StringUtil.isNotEmpty($jffs))
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
	}
	*/
}
