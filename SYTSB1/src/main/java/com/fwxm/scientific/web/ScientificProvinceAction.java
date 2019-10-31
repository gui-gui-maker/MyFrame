package com.fwxm.scientific.web;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fwxm.scientific.bean.InstrumentDevice;
import com.fwxm.scientific.bean.ScientificProvince;
import com.fwxm.scientific.bean.Tjy2ScientificRemark;
import com.fwxm.scientific.bean.Tjy2ScientificResearch;
import com.fwxm.scientific.service.ScientificProvinceManager;
import com.fwxm.scientific.service.Tjy2ScientificRemarkManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName ScientificProvince
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-01-15 13:24:16
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("com/tjy2/scientificProvince")
public class ScientificProvinceAction extends
		SpringSupportAction<ScientificProvince, ScientificProvinceManager> {

	@Autowired
	private ScientificProvinceManager scientificProvinceManager;
	   @Autowired
	    private Tjy2ScientificRemarkManager tjy2ScientificRemarkManager;
	 
    /**
  	 * 保存
  	 * 
  	 * @param request
  	 * @param employeeCert
  	 * @throws Exception
  	 */
  	@RequestMapping(value = "saveScientific")
  	@ResponseBody
  	public HashMap<String, Object> saveScientific(HttpServletRequest request) throws Exception {
  		CurrentSessionUser user=SecurityUtil.getSecurityUser();
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try {
  			 String base=request.getParameter("base").toString();
  			ScientificProvince entity=JSON.parseObject(base,ScientificProvince.class);
  			if(entity.getId()==null||entity.getId().equals("")){
  				entity.setCreateDate(new Date());
  				entity.setCreateMan(user.getName());
  				entity.setCreateId(user.getId());
  				entity.setStatus("0");
  			}else{
  				entity.setLastModifyDate(new Date());
  				entity.setLastModifyMan(user.getName());
  			}
            String P100001= request.getParameter("P100001");
            String P200001= request.getParameter("P100002");
            String P300001= request.getParameter("P100003");
            String P400001= request.getParameter("P100004");
            String P500001= request.getParameter("P100005");
            String P600001= request.getParameter("P100006");
           
            scientificProvinceManager.save(entity);
            scientificProvinceManager.saveBase(entity.getId(), P100001, P200001, P300001, P400001, P500001, P600001);
  			wrapper.put("success", true);
  		/*	SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日"); 
  			String date1=sdf.format(entity.getFillDate());
  			String date2=sdf.format(entity.getStartDate());
  			String date3=sdf.format(entity.getEndDate());*/
  			wrapper.put("data", entity); 
  		/*	wrapper.put("fillDate", date1);
  			wrapper.put("startDate", date2);
  			wrapper.put("endDate", date3);*/
			wrapper.put("id", entity.getId());
  		} catch (Exception e) {
  			log.error("保存信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "保存信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
  	/**
	 * 获取详情
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "detailBase")
  	@ResponseBody
	public HashMap<String, Object> detailBase(HttpServletRequest request,String id) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			ScientificProvince file = scientificProvinceManager.get(id);
			Object[] o=scientificProvinceManager.detailBase(id);
			if(o!=null){
				
				
				for (int i = 0; i < o.length; i++) {
					Clob clob=(Clob)o[i];
					if(clob!=null){
						int j=i+1;
						wrapper.put("P"+j+"00001", clob.getSubString((long)1,(int)clob.length()));
						}
				}
			}
			wrapper.put("data", file);
			wrapper.put("success", true);
			
		} catch (Exception e) {
			log.error("保存信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "保存信息出错！");
  			e.printStackTrace();	
		}
		
		return wrapper;
	}
	  //判断值是否更改
  	@RequestMapping(value = "judgeBase")
  	@ResponseBody
  	public HashMap<String, Object> judgeBase(HttpServletRequest request) throws Exception {
  		CurrentSessionUser user=SecurityUtil.getSecurityUser();
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try {
  		//判断实体类
 			 String base=request.getParameter("base").toString();
 			ScientificProvince entity=JSON.parseObject(base,ScientificProvince.class);
  	  	ScientificProvince entity1=scientificProvinceManager.get(entity.getId());
  			 String P100001= request.getParameter("P100001");
             String P200001= request.getParameter("P100002");
             String P300001= request.getParameter("P100003");
             String P400001= request.getParameter("P100004");
             String P500001= request.getParameter("P100005");
             String P600001= request.getParameter("P100006");
   	  	//先判断富文本框是否更改
   	 Object[] o=scientificProvinceManager.detailBase(entity.getId());
   	if(o!=null){
			for (int i = 0; i < o.length; i++) {
				Clob clob=(Clob)o[i];
				if(clob!=null){
					if(i==0){
					if(!P100001.equals(clob.getSubString((long)1,(int)clob.length()))){
						System.out.println(clob.getSubString((long)1,(int)clob.length()));
						wrapper.put("judge", true);
					}
					}else if(i==1){
						if(!P200001.equals(clob.getSubString((long)1,(int)clob.length()))){
							wrapper.put("judge", true);
						}
					}else if(i==2){
						if(!P300001.equals(clob.getSubString((long)1,(int)clob.length()))){
							wrapper.put("judge", true);
						}
					}else if(i==3){
						if(!P400001.equals(clob.getSubString((long)1,(int)clob.length()))){
							wrapper.put("judge", true);
						}
					}else if(i==4){
						if(!P500001.equals(clob.getSubString((long)1,(int)clob.length()))){
							wrapper.put("judge", true);
						}
					}else if(i==5){
						if(!P600001.equals(clob.getSubString((long)1,(int)clob.length()))){
							wrapper.put("judge", true);
						}
					}
					
					}else{
						if(i==0&&!P100001.equals("")){
							wrapper.put("judge", true);
						}else if(i==1&&!P200001.equals("")){
							wrapper.put("judge", true);
						}
						else if(i==2&&!P300001.equals("")){
							wrapper.put("judge", true);
						}else if(i==3&&!P400001.equals("")){
							wrapper.put("judge", true);
						}else if(i==4&&!P500001.equals("")){
							wrapper.put("judge", true);
						}else if(i==5&&!P600001.equals("")){
							wrapper.put("judge", true);
						}
					}
			}
		}
   	     Field[] field = entity.getClass().getDeclaredFields();
   	     Field[] field1 = entity1.getClass().getDeclaredFields();
   	     for (int j = 0; j < field.length; j++) {// 遍历所有属性
   	    	String name = field[j].getName(); // 获取属性的名字
   	    	name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
   	        String type = field[j].getGenericType().toString(); // 获取属性的类型
   	        if(!name.equals("LastModifyDate")&&!name.equals("LastModifyMan")&&!name.equals("LastModifyId")){
   	        if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
             Method m = entity.getClass().getMethod("get" + name);
             String value = (String) m.invoke(entity); // 调用getter方法获取属性值
             Method m1 = entity1.getClass().getMethod("get" + name);
             String value1 = (String) m1.invoke(entity1); // 调用getter方法获取属性值
             if(value!=null&&!value.equals("")){
             if(!value.equals(value1)){
            	 System.out.println("进啦");
            	 wrapper.put("judge", true);
             }
             }else{
            	 value=null;
            	 if(value!=value1){
            		 System.out.println("进啦1");
            		 wrapper.put("judge", true);
                 } 
             }
             
         }
   	     if (type.equals("class java.util.Date")) {
   	    	Method m = entity.getClass().getMethod("get" + name);
             Date value = (Date) m.invoke(entity);
             Method m1 = entity1.getClass().getMethod("get" + name);
             Date value1 = (Date) m1.invoke(entity1);
             if(value!=null&&!value.equals("")){
            	 if(!value.equals(value1)){
            		 System.out.println("进啦2");
            		 wrapper.put("judge", true);
                 }
             }else{
            	 value=null;
            	 if(value!=value1){
            		 System.out.println("进啦3");
            		 wrapper.put("judge", true);
                 } 
             }
             
             
         }
   	        }
   	     }
  				
  			
  			wrapper.put("success", true);
  		} catch (Exception e) {
  			log.error("保存信息：" + e.getMessage());
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
  	public void input(HttpServletRequest request,HttpServletResponse response,String id,String types) throws Exception {
		ScientificProvince entity = scientificProvinceManager.get(id);
		Object[] o=scientificProvinceManager.detailBase(id);//获取每页内容
		Object[] o1=scientificProvinceManager.detailBase("10000");//获取申请书导出模板
		Clob clob1=null;
		clob1=(Clob)o1[0];
		String fileMb=clob1.getSubString((long)1,(int)clob1.length());
		String content="";//装载每页内容
		byte data[] = null;
		for (int i1 = 0; i1 < o.length; i1++) {
			Clob clob=(Clob)o[i1];
			if(clob!=null){
				int j1=i1+1;
		//tjy2ScientificResearchManager.input(clob.getSubString((long)1,(int)clob.length()));
		 try {
			 content=clob.getSubString((long)1,(int)clob.length());
			 if(content.indexOf("<img")>0){
				String[] stringSplit_img=content.split("<img");//分割<img>标签
				for (int i = 0; i < stringSplit_img.length; i++) {
					if(i!=0){
						String[] stringSplit_p=("<img"+stringSplit_img[i]).split("</p>");//分割<p>标签
						for (int j = 0; j < stringSplit_p.length; j++) {
							if(j!=0){
								content="</p>"+stringSplit_p[j];
							}else{
								//判断是否为img标签
								if(stringSplit_p[j].indexOf("data")>-1){
								//此时stringSplit_p[j]已是完整的<img/>标签字符串
								//截取标签base64
								for(String s:stringSplit_p[j].split(" ")){
									if(s.startsWith("src=")){
										s=s.replace("src=\"", "");
										s=s.replace("\"", "");
										String[] type=s.split(",");//将base64分开解析
										String typeFile="";
										if((type[0].substring(11,14).equals("jpg")||type[0].substring(11,14).equals("jpe"))){
											typeFile="jpg";
										}else if(type[0].substring(11,14).equals("png")){
											typeFile="png";
										}
										Thread.sleep(1000);
										String path=scientificProvinceManager.inputFile(type[1], typeFile);
										//将解码后的图片替换到数据中
										String imgPath="";
										if(stringSplit_p[j].indexOf("style")>0){
											 imgPath=stringSplit_p[j].replace(stringSplit_p[j].substring(stringSplit_p[j].indexOf("src=")+5,stringSplit_p[j].indexOf("style")-2), path);
										}else{
											imgPath=stringSplit_p[j].replace(stringSplit_p[j].substring(stringSplit_p[j].indexOf("src=")+5,stringSplit_p[j].indexOf("/>")-2), path);
										}
										
										content=content.replace(stringSplit_p[j], imgPath);
										
									}
									
									
								}
								
								 fileMb=fileMb.replace("${p"+j1+"00001}", content);
								}
							}
						}
					}
				}
			 }else{
				 fileMb=fileMb.replace("${p"+j1+"00001}", content);
			 }
         }catch(Exception e){
        	 log.error("保存信息：" + e.getMessage());
   			e.printStackTrace();	
         }  
		
		
			}else{
				fileMb=fileMb.replace("${p"+(i1+1)+"00001}", "");
			}
			}
		//替换模板中的占位符
		
		fileMb=fileMb.replace("${projectName}",entity.getProjectName()==null?"":entity.getProjectName() );
		fileMb=fileMb.replace("${researchField}",entity.getResearchField()==null?"":entity.getResearchField() );
		fileMb=fileMb.replace("${phoneTel}",entity.getPhoneTel()==null?"":entity.getPhoneTel() );
		fileMb=fileMb.replace("${unitGk}",entity.getUnitGk()==null?"":entity.getUnitGk() );
		fileMb=fileMb.replace("${declareUnit}",entity.getDeclareUnit()==null?"":entity.getDeclareUnit() );
		fileMb=fileMb.replace("${projectNo}",entity.getProjectNo()==null?"":entity.getProjectNo() );
		fileMb=fileMb.replace("${jhNo}",entity.getJhNo()==null?"":entity.getJhNo() );
		fileMb=fileMb.replace("${classification}",entity.getClassification()==null?"":entity.getClassification() );
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日"); 
		    String date1=sdf.format(entity.getSubmitDate());
			String date2=sdf.format(entity.getStartDate());
			String date3=sdf.format(entity.getEndDate());
		fileMb=fileMb.replace("${startEndDate}",date2+"-"+date3 );
		fileMb=fileMb.replace("${submitDate}",date1 );
		fileMb=fileMb.replace("${projectHead}",entity.getProjectHead()==null?"":entity.getProjectHead() );
		fileMb=fileMb.replace("${p100002}",entity.getP100002()==null?"":entity.getP100002() );
		fileMb=fileMb.replace("${p700001}",entity.getP700001()==null?"":entity.getP700001() );
		fileMb=fileMb.replace("${p700002}",entity.getP700002()==null?"":entity.getP700002() );
		fileMb=fileMb.replace("${p700003}",entity.getP700003()==null?"":entity.getP700003() );
		fileMb=fileMb.replace("${p700004}",entity.getP700004()==null?"":entity.getP700004() );
		fileMb=fileMb.replace("${p700005}",entity.getP700005()==null?"":entity.getP700005() );
		fileMb=fileMb.replace("${p700006}",entity.getP700006()==null?"":entity.getP700006() );
		fileMb=fileMb.replace("${p700007}",entity.getP700007()==null?"":entity.getP700007() );
		fileMb=fileMb.replace("${p700008}",entity.getP700008()==null?"":entity.getP700008() );
		fileMb=fileMb.replace("${p700009}",entity.getP700009()==null?"":entity.getP700009() );
		fileMb=fileMb.replace("${p700010}",entity.getP700010()==null?"":entity.getP700010() );
		fileMb=fileMb.replace("${p700011}",entity.getP700011()==null?"":entity.getP700011() );
		fileMb=fileMb.replace("${p700012}",entity.getP700012()==null?"":entity.getP700012() );
		fileMb=fileMb.replace("${p700013}",entity.getP700013()==null?"":entity.getP700013() );
		fileMb=fileMb.replace("${p700014}",entity.getP700014()==null?"":entity.getP700014() );
		
		fileMb=fileMb.replace("${p800001}",entity.getP800001()==null?"":entity.getP800001() );
		fileMb=fileMb.replace("${p800002}",entity.getP800002()==null?"":entity.getP800002() );
		fileMb=fileMb.replace("${p800003}",entity.getP800003()==null?"":entity.getP800003() );
		fileMb=fileMb.replace("${p800004}",entity.getP800004()==null?"":entity.getP800004() );
		fileMb=fileMb.replace("${p800005}",entity.getP800005()==null?"":entity.getP800005() );
		fileMb=fileMb.replace("${p800006}",entity.getP800006()==null?"":entity.getP800006() );
		fileMb=fileMb.replace("${p800007}",entity.getP800007()==null?"":entity.getP800007() );
		fileMb=fileMb.replace("${p800008}",entity.getP800008()==null?"":entity.getP800008() );
		fileMb=fileMb.replace("${p800009}",entity.getP800009()==null?"":entity.getP800009() );
		fileMb=fileMb.replace("${p800010}",entity.getP8000010()==null?"":entity.getP8000010() );
		fileMb=fileMb.replace("${p800011}",entity.getP8000011()==null?"":entity.getP8000011() );
		fileMb=fileMb.replace("${p800012}",entity.getP8000012()==null?"":entity.getP8000012() );
		fileMb=fileMb.replace("${p800013}",entity.getP8000013()==null?"":entity.getP8000013() );
		fileMb=fileMb.replace("${p800014}",entity.getP8000014()==null?"":entity.getP8000014() );
		fileMb=fileMb.replace("${p800015}",entity.getP8000015()==null?"":entity.getP8000015() );
		fileMb=fileMb.replace("${p800016}",entity.getP8000016()==null?"":entity.getP8000016() );
		fileMb=fileMb.replace("${p800017}",entity.getP8000017()==null?"":entity.getP8000017() );
		fileMb=fileMb.replace("${p800018}",entity.getP8000018()==null?"":entity.getP8000018() );
		fileMb=fileMb.replace("${p800019}",entity.getP8000019()==null?"":entity.getP8000019() );
		fileMb=fileMb.replace("${p800020}",entity.getP8000020()==null?"":entity.getP8000020() );
		fileMb=fileMb.replace("${p800021}",entity.getP8000021()==null?"":entity.getP8000021() );
		fileMb=fileMb.replace("${p800022}",entity.getP8000022()==null?"":entity.getP8000022() );
		fileMb=fileMb.replace("${p800023}",entity.getP8000023()==null?"":entity.getP8000023() );
		fileMb=fileMb.replace("${p800024}",entity.getP8000024()==null?"":entity.getP8000024() );
		fileMb=fileMb.replace("${p800025}",entity.getP8000025()==null?"":entity.getP8000025() );
		fileMb=fileMb.replace("${p800026}",entity.getP8000026()==null?"":entity.getP8000026() );
		fileMb=fileMb.replace("${p800027}",entity.getP8000027()==null?"":entity.getP8000027() );
		fileMb=fileMb.replace("${p800028}",entity.getP8000028()==null?"":entity.getP8000028() );
		fileMb=fileMb.replace("${p800029}",entity.getP8000029()==null?"":entity.getP8000029() );
		fileMb=fileMb.replace("${p800030}",entity.getP8000030()==null?"":entity.getP8000030() );
		//第一行
		fileMb=fileMb.replace("${p800031}",entity.getP8000031()==null?"":entity.getP8000031() );
		fileMb=fileMb.replace("${p800032}",entity.getP8000032()==null?"":entity.getP8000032() );
		fileMb=fileMb.replace("${p800033}",entity.getP8000033()==null?"":entity.getP8000033() );
		fileMb=fileMb.replace("${p800034}",entity.getP8000034()==null?"":entity.getP8000034() );
		fileMb=fileMb.replace("${p800035}",entity.getP8000035()==null?"":entity.getP8000035() );
		fileMb=fileMb.replace("${p800036}",entity.getP8000036()==null?"":entity.getP8000036() );
		fileMb=fileMb.replace("${p800037}",entity.getP8000037()==null?"":entity.getP8000037() );
		//第二行
		fileMb=fileMb.replace("${p800038}",entity.getP8000038()==null?"":entity.getP8000038() );
		fileMb=fileMb.replace("${p800039}",entity.getP8000039()==null?"":entity.getP8000039() );
		fileMb=fileMb.replace("${p800040}",entity.getP8000040()==null?"":entity.getP8000040() );
		fileMb=fileMb.replace("${p800041}",entity.getP8000041()==null?"":entity.getP8000041() );
		fileMb=fileMb.replace("${p800042}",entity.getP8000042()==null?"":entity.getP8000042() );
		fileMb=fileMb.replace("${p800043}",entity.getP8000043()==null?"":entity.getP8000043() );
		fileMb=fileMb.replace("${p800044}",entity.getP8000044()==null?"":entity.getP8000044() );
		//第三行
		fileMb=fileMb.replace("${p800045}",entity.getP8000045()==null?"":entity.getP8000045() );
		fileMb=fileMb.replace("${p800046}",entity.getP8000046()==null?"":entity.getP8000046() );
		fileMb=fileMb.replace("${p800047}",entity.getP8000047()==null?"":entity.getP8000047() );
		fileMb=fileMb.replace("${p800048}",entity.getP8000048()==null?"":entity.getP8000048() );
		fileMb=fileMb.replace("${p800049}",entity.getP8000049()==null?"":entity.getP8000049() );
		fileMb=fileMb.replace("${p800050}",entity.getP8000050()==null?"":entity.getP8000050() );
		fileMb=fileMb.replace("${p800051}",entity.getP8000051()==null?"":entity.getP8000051() );
		//第四
		fileMb=fileMb.replace("${p800052}",entity.getP8000052()==null?"":entity.getP8000052() );
		fileMb=fileMb.replace("${p800053}",entity.getP8000053()==null?"":entity.getP8000053() );
		fileMb=fileMb.replace("${p800054}",entity.getP8000054()==null?"":entity.getP8000054() );
		fileMb=fileMb.replace("${p800055}",entity.getP8000055()==null?"":entity.getP8000055() );
		fileMb=fileMb.replace("${p800056}",entity.getP8000056()==null?"":entity.getP8000056() );
		fileMb=fileMb.replace("${p800057}",entity.getP8000057()==null?"":entity.getP8000057() );
		fileMb=fileMb.replace("${p800058}",entity.getP8000058()==null?"":entity.getP8000058() );
		//第五行
		fileMb=fileMb.replace("${p800059}",entity.getP8000059()==null?"":entity.getP8000059() );
		fileMb=fileMb.replace("${p800060}",entity.getP8000060()==null?"":entity.getP8000060() );
		fileMb=fileMb.replace("${p800061}",entity.getP8000061()==null?"":entity.getP8000061() );
		fileMb=fileMb.replace("${p800062}",entity.getP8000062()==null?"":entity.getP8000062() );
		fileMb=fileMb.replace("${p800063}",entity.getP8000063()==null?"":entity.getP8000063() );
		fileMb=fileMb.replace("${p800064}",entity.getP8000064()==null?"":entity.getP8000064() );
		fileMb=fileMb.replace("${p800065}",entity.getP8000065()==null?"":entity.getP8000065() );
		//第六行
		fileMb=fileMb.replace("${p800066}",entity.getP8000066()==null?"":entity.getP8000066() );
		fileMb=fileMb.replace("${p800067}",entity.getP8000067()==null?"":entity.getP8000067() );
		fileMb=fileMb.replace("${p800068}",entity.getP8000068()==null?"":entity.getP8000068() );
		fileMb=fileMb.replace("${p800069}",entity.getP8000069()==null?"":entity.getP8000069() );
		fileMb=fileMb.replace("${p800070}",entity.getP8000070()==null?"":entity.getP8000070() );
		fileMb=fileMb.replace("${p800071}",entity.getP8000071()==null?"":entity.getP8000071() );
		fileMb=fileMb.replace("${p800072}",entity.getP8000072()==null?"":entity.getP8000072() );

		if(entity.getP8000073()!=null&&!entity.getP8000073().equals("")){
			 String table11_1=table11(entity.getP8000073(),entity.getP8000074(),entity.getP8000075(),entity.getP8000076(),entity.getP8000077(),entity.getP8000078(),entity.getP8000079());
			 fileMb=fileMb.replace("rowspan=7 id='table'","rowspan=8 id='table'"); 
			 if(entity.getP8000080()!=null&&!entity.getP8000080().equals("")){
				 table11_1=table11_1+table11(entity.getP8000080(),entity.getP8000081(),entity.getP8000082(),entity.getP8000083(),entity.getP8000084(),entity.getP8000085(),entity.getP8000086());
				 fileMb=fileMb.replace("rowspan=8 id='table'","rowspan=9 id='table'"); 
				 if(entity.getP8000087()!=null&&!entity.getP8000087().equals("")){
					 table11_1=table11_1+table11(entity.getP8000087(),entity.getP8000088(),entity.getP8000089(),entity.getP8000090(),entity.getP8000091(),entity.getP8000092(),entity.getP8000093());
					 fileMb=fileMb.replace("rowspan=9 id='table'","rowspan=10 id='table'"); 
					 if(entity.getP8000094()!=null&&!entity.getP8000094().equals("")){
						 table11_1=table11_1+table11(entity.getP8000094(),entity.getP8000095(),entity.getP8000096(),entity.getP8000097(),entity.getP8000098(),entity.getP8000099(),entity.getP8000100());
						 fileMb=fileMb.replace("rowspan=10 id='table'","rowspan=11 id='table'"); 
						 if(entity.getP8000101()!=null&&!entity.getP8000101().equals("")){
							 table11_1=table11_1+table11(entity.getP8000101(),entity.getP8000102(),entity.getP8000103(),entity.getP8000104(),entity.getP8000105(),entity.getP8000106(),entity.getP8000107());
							 fileMb=fileMb.replace("rowspan=11 id='table'","rowspan=12 id='table'"); 
							 if(entity.getP8000108()!=null&&!entity.getP8000108().equals("")){
								 table11_1=table11_1+table11(entity.getP8000108(),entity.getP8000109(),entity.getP8000110(),entity.getP8000111(),entity.getP8000112(),entity.getP8000113(),entity.getP8000114());
								 fileMb=fileMb.replace("rowspan=12 id='table'","rowspan=13 id='table'"); 
								 if(entity.getP8000115()!=null&&!entity.getP8000115().equals("")){
									 table11_1=table11_1+table11(entity.getP8000115(),entity.getP8000116(),entity.getP8000117(),entity.getP8000118(),entity.getP8000119(),entity.getP8000120(),entity.getP8000121());
									 fileMb=fileMb.replace("rowspan=13 id='table'","rowspan=14 id='table'"); 
									 if(entity.getP8000122()!=null&&!entity.getP8000122().equals("")){
										 table11_1=table11_1+table11(entity.getP8000122(),entity.getP8000123(),entity.getP8000124(),entity.getP8000125(),entity.getP8000126(),entity.getP8000127(),entity.getP8000128());
										 fileMb=fileMb.replace("rowspan=14 id='table'","rowspan=15 id='table'"); 
										 if(entity.getP8000129()!=null&&!entity.getP8000122().equals("")){
											 table11_1=table11_1+table11(entity.getP8000129(),entity.getP8000130(),entity.getP8000131(),entity.getP8000132(),entity.getP8000133(),entity.getP8000134(),entity.getP8000135());
											 fileMb=fileMb.replace("rowspan=15 id='table'","rowspan=16 id='table'"); 
											 
										}
									}
								} 
							}
						} 
					}
				}
			}
			 fileMb=fileMb.replace("${table8_1}",table11_1);
		}else{
			fileMb=fileMb.replace("${table8_1}","");
		}
		
		
        String content1="<html>"+fileMb+"</html>";
        byte b[] = content1.getBytes("utf-8");  //这里是必须要设置编码的，不然导出中文就会乱码。
        ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中  
        
        // 关键地方
       // 生成word格式
        
        POIFSFileSystem poifs = new POIFSFileSystem();  
        DirectoryEntry directory = poifs.getRoot();  
        DocumentEntry documentEntry = directory.createDocument("WordDocument", bais); 
        //输出文件
        String fileName="";
       
        	   fileName="省国家科研项目预申报-"+entity.getProjectName();
      
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
	public String table11(String value,String value1,String value2,String value3,String value4,String value5,String value6){
		String table= " <tr style='mso-yfti-irow:16;height:30.7pt'> "+
  "<td width=107 style='width:64.4pt;border-top:none;border-left:none; "+
  " border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;mso-border-top-alt: "+
  " solid black .75pt;mso-border-left-alt:solid black .75pt;mso-border-alt:solid black .75pt; "+
  " padding:2.25pt 2.25pt 2.25pt 2.25pt;height:30.7pt'> "+
  " <p class=MsoNormal align=center style='text-align:center'><a name='OLE_LINK56'></a><a "+
  " name='OLE_LINK55'><span style='mso-bookmark:OLE_LINK56'><span lang=EN-US "+
  " style='font-size:10.5pt;letter-spacing:.75pt'>"+(value==null?"":value)+"</span></span></a></p> "+
  " </td> "+
  " <td width=107 style='width:64.1pt;border-top:none;border-left:none; "+
  " border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;mso-border-top-alt: "+
  "  solid black .75pt;mso-border-left-alt:solid black .75pt;mso-border-alt:solid black .75pt; "+
  "  padding:2.25pt 2.25pt 2.25pt 2.25pt;height:30.7pt'> "+
  "  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US "+
  "  style='font-size:10.5pt;letter-spacing:.75pt'>"+(value1==null?"":value1)+"</span></p> "+
  "  </td> "+
  "  <td width=75 style='width:45.1pt;border-top:none;border-left:none;border-bottom: "+
  " solid black 1.0pt;border-right:solid black 1.0pt;mso-border-top-alt:solid black .75pt; "+
  " mso-border-left-alt:solid black .75pt;mso-border-alt:solid black .75pt; "+
  " padding:2.25pt 2.25pt 2.25pt 2.25pt;height:30.7pt'> "+
  " <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US "+
  " style='font-size:10.5pt;letter-spacing:.75pt'>"+(value2==null?"":value2)+"</span></p> "+
  " </td> "+
  " <td width=89 style='width:53.3pt;border-top:none;border-left:none;border-bottom: "+
  " solid black 1.0pt;border-right:solid black 1.0pt;mso-border-top-alt:solid black .75pt; "+
  " mso-border-left-alt:solid black .75pt;mso-border-alt:solid black .75pt; "+
  " padding:2.25pt 2.25pt 2.25pt 2.25pt;height:30.7pt'> "+
  "  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US "+
  "  style='font-size:10.5pt;letter-spacing:.75pt'>"+(value3==null?"":value3)+"</span></p> "+
  "  </td> "+
  "  <td style='border-top:none;border-left:none;border-bottom:solid black 1.0pt; "+
  "  border-right:solid black 1.0pt;mso-border-top-alt:solid black .75pt; "+
  "  mso-border-left-alt:solid black .75pt;mso-border-alt:solid black .75pt; "+
  "  padding:2.25pt 2.25pt 2.25pt 2.25pt;height:30.7pt'> "+
  "  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US "+
  "  style='font-size:10.5pt;letter-spacing:.75pt'>"+(value4==null?"":value4)+"</span></p> "+
  "  </td> "+
  "  <td style='border-top:none;border-left:none;border-bottom:solid black 1.0pt; "+
  "  border-right:solid black 1.0pt;mso-border-top-alt:solid black .75pt; "+
  "  mso-border-left-alt:solid black .75pt;mso-border-alt:solid black .75pt; "+
  "  padding:2.25pt 2.25pt 2.25pt 2.25pt;height:30.7pt'> "+
  "  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US "+
  "  style='font-size:10.5pt;letter-spacing:.75pt'>"+(value5==null?"":value5)+"</span></p> "+
  " </td> "+
  " <td style='border-top:none;border-left:none;border-bottom:solid black 1.0pt; "+
  " border-right:solid black 1.0pt;mso-border-top-alt:solid black .75pt; "+
  "  mso-border-left-alt:solid black .75pt;mso-border-alt:solid black .75pt; "+
  "  padding:2.25pt 2.25pt 2.25pt 2.25pt;height:30.7pt'> "+
  "  <p class=MsoNormal><span lang=EN-US style='font-size:10.5pt;letter-spacing:.75pt'>"+(value6==null?"":value6)+"</span></p> "+
  "  </td></tr>";
		return table;
	}
	//分配审核人
  	@RequestMapping(value = "distribution")
  	@ResponseBody
  	public HashMap<String, Object> distribution(HttpServletRequest request, String id) throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		String audit_name=request.getParameter("audit_name");
  		String audit_id=request.getParameter("audit_id");
  		String ids[]=id.split(",");
  		for (int i = 0; i < ids.length; i++) {
  			ScientificProvince research=scientificProvinceManager.get(ids[i]);
  			research.setAuditName(audit_name);
  			research.setAuditId(audit_id);
  			//research.setAuditStatus("0");
  			scientificProvinceManager.save(research);
  		
  			
  		}
  		wrapper.put("success", true);
  		return wrapper;
  	}
	//分配审核人审核
  	@RequestMapping(value = "auditPass")
  	@ResponseBody
  	public HashMap<String, Object> auditPass(HttpServletRequest request, String id) throws Exception {
  		CurrentSessionUser user=SecurityUtil.getSecurityUser();
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		String remark=request.getParameter("remark");
  		String ids[]=id.split(",");
  		for (int i = 0; i < ids.length; i++) {
  			Tjy2ScientificRemark entity=new Tjy2ScientificRemark();
  			ScientificProvince research=scientificProvinceManager.get(ids[i]);
  			research.setRemark(remark);
  			research.setAuditId("");
  			//research.setAuditStatus("1");
  			entity.setCreate_date(new Date());
				entity.setCreate_man(user.getName());
				entity.setRemark(remark);
				entity.setProject_name(research.getProjectName());
				entity.setFk_scientific_id(research.getId());
				if(remark!=null&&!remark.equals("")){
					tjy2ScientificRemarkManager.save(entity);
				}
				scientificProvinceManager.save(research);
  		
  			
  		}
  		wrapper.put("success", true);
  		return wrapper;
  	}
  	//确认审批
  	@RequestMapping(value = "updateConfirm")
  	@ResponseBody
  	public HashMap<String, Object> updateConfirm(HttpServletRequest request, String id) throws Exception {
  		CurrentSessionUser user=SecurityUtil.getSecurityUser();
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		String result=request.getParameter("result");
  		String back=request.getParameter("back");
  		String remark=request.getParameter("remark");
  		try {
  			String ids[]=id.split(",");
  			for (int i = 0; i < ids.length; i++) {
  				Tjy2ScientificRemark entity=new Tjy2ScientificRemark();
  				ScientificProvince research=scientificProvinceManager.get(ids[i]);
  				int status =Integer.parseInt(research.getStatus());//获取申请书当前步骤
  				if(result.equals("1")){
  					entity.setProcess("审核通过");
  					//research.setAuditStatus("1");
  					research.setStatus(Integer.toString(status+1));//已审批
  				}else{
  					entity.setProcess("审核不通过");
  					if(back.equals("1")){
  						research.setStatus("0");//退回录入
  						//research.setProjectDepartment("1");//表示退回申请书
  					}else{
  						research.setStatus(Integer.toString(status-1));//退回上一步
  						//research.setProjectDepartment("1");//表示退回申请书
  					}
  				}
  				if(remark!=null&&!remark.equals("")){
  				research.setRemark(remark);
  				}
  				scientificProvinceManager.save(research);
  			
  				entity.setCreate_date(new Date());
  				entity.setCreate_man(user.getName());
  				entity.setRemark(remark);
  				entity.setProject_name(research.getProjectName());
  				entity.setFk_scientific_id(research.getId());
  				if(remark!=null&&!remark.equals("")){
  					tjy2ScientificRemarkManager.save(entity);
  				}
			}
  			
  			wrapper.put("success", true);
  		} catch (Exception e) {
  			 log.error("保存信息：" + e.getMessage());
    	e.printStackTrace();	
  			wrapper.put("success", false);
  		}
  		return wrapper;
  	}
	/** 删除**/
	@RequestMapping(value = "deleteBase")
	@ResponseBody
	public HashMap<String, Object> deleteBase(HttpServletRequest request,String id)
			throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		CurrentSessionUser user=SecurityUtil.getSecurityUser();
		try {
			String[] ids=id.split(",");
			for (int i = 0; i < ids.length; i++) {
				ScientificProvince instruction =scientificProvinceManager.get(ids[i]);
				instruction.setStatus("99");//删除
				scientificProvinceManager.save(instruction);
			}
			
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			// TODO: handle exception
		}
		
		return map;

	}
}
