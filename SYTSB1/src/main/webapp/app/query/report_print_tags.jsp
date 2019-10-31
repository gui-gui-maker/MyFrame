 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.lsts.inspection.bean.ReportItemValue"%>
<%@ page import="com.lsts.report.bean.ReportItem"%>
<%@page import="com.khnt.pub.fileupload.bean.Attachment"%>
<%@page import="com.khnt.pub.fileupload.service.AttachmentManager"%>
<%@page import="com.lsts.inspection.dao.ReportPerDao"%>
<%@page import="com.lsts.common.dao.AttachmentsDao"%>
<%@page import="java.io.IOException"%>
<%@page import="com.lsts.device.bean.DeviceDocument"%>
<%@page import="org.apache.commons.beanutils.converters.StringArrayConverter"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ taglib uri="/WEB-INF/Ming.tld" prefix="ming" %>
<%@ page import="com.ming.webreport.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.khnt.utils.StringUtil" %>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@ page import="com.lsts.ImageTool"%>
<%@ page import="com.lsts.inspection.bean.*" %>
<%@ page import="com.lsts.report.bean.*" %>
<%@ page import="oracle.sql.*" %>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<%

	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String user_name = user.getName();

	Calendar cal = Calendar.getInstance();
	String printout = request.getParameter("printout");
	String isLast = request.getParameter("isLast");	// 状态（提交后关闭窗口）
	//判断是否是提交数据后，如是则关闭窗口
	if (StringUtil.isNotEmpty(isLast))
	{
%>
	<script>
		//alert("打印完成！");
		parent.left.printAll();
	</script>
<%
	//return;
	}
%>
<%

	DeviceDocument deviceDocument = (DeviceDocument)request.getSession().getAttribute("deviceDocument");
	List<ReportItemValue> reportItemValueList = (List<ReportItemValue>)request.getSession().getAttribute("reportItemValueList");
	String printer_name = (String)request.getSession().getAttribute("printer_name");	// 打印机名称 
	String inspection_info_id = (String)request.getSession().getAttribute("inspection_info_id");	// 业务信息ID
	InspectionInfo inspectionInfo = (InspectionInfo)request.getSession().getAttribute("inspectionInfo");	// 业务信息
	String check_unit_id = inspectionInfo.getCheck_unit_id();
	String report_id = (String)request.getSession().getAttribute("report_id");	// 报告类型ID
	String big_class = deviceDocument.getDevice_sort_code().substring(0, 1);	// 设备大类
	String wbdw = "";
	String check_op_name = "";
	String com_name = "";
	String device_registration_code = "";
	String next_inspection_date = "";
	String year = "";
    String month = "";
    
    // 构造DataRecord、MRDataSet对象
    MRDataSet mrds = new MRDataSet();
    DataRecord rec = new DataRecord();
    
    boolean haveInternal_num = false;	// 记录报告中是否有“单位内部编号”，如果没有，就取设备基础信息表
    for (int i = 0; i < reportItemValueList.size(); i++)
    {
    	ReportItemValue reportItemValue = reportItemValueList.get(i);
    	if(reportItemValue.getItem_name().equals("DEVICE_REGISTRATION_CODE")){
    		if(StringUtil.isNotEmpty(reportItemValue.getItem_value())){
    			device_registration_code = reportItemValue.getItem_value();
    			rec.setValue("DEVICE_REGISTRATION_CODE", reportItemValue.getItem_value());	// 设备注册代码
    		}else{
    			device_registration_code = deviceDocument.getDevice_registration_code();
    			rec.setValue("DEVICE_REGISTRATION_CODE", deviceDocument.getDevice_registration_code());	// 设备注册代码
    		}
    		
    	}else if(reportItemValue.getItem_name().equals("DEVICE_CODE")){
    		// 所有起重机监检，打印合格证时，设备注册代码取“设备代码”不取系统生成的“设备注册代码”
    		if("4".equals(big_class) && inspectionInfo.getReport_sn().contains("QA")){	
    			if(StringUtil.isNotEmpty(reportItemValue.getItem_value())){
        			device_registration_code = reportItemValue.getItem_value();
        			rec.setValue("DEVICE_REGISTRATION_CODE", reportItemValue.getItem_value());	// 设备代码
        		}else{
        			device_registration_code = deviceDocument.getDevice_code();
        			rec.setValue("DEVICE_REGISTRATION_CODE", deviceDocument.getDevice_code());	// 设备代码
        		}
    		}
    	}else if(reportItemValue.getItem_name().equals("COM_NAME")){
    		com_name = reportItemValue.getItem_value();
    		rec.setValue("COMPANY_NAME", reportItemValue.getItem_value());	// 使用单位
    	}else if(reportItemValue.getItem_name().equals("INTERNAL_NUM")){
    		if(StringUtil.isNotEmpty(reportItemValue.getItem_value())){
    			haveInternal_num = true;
    			if("6".equals(big_class)){
    				rec.setValue("INTERNAL_NUM", reportItemValue.getItem_value()+"（"+deviceDocument.getDevice_name()+"）");	// 使用单位设备编号
    			}else{
        			rec.setValue("INTERNAL_NUM", reportItemValue.getItem_value());	// 使用单位设备编号
    			}
    		}else{
    			rec.setValue("INTERNAL_NUM", deviceDocument.getInternal_num());	// 使用单位设备编号
    		}
    	}else if(reportItemValue.getItem_name().equals("MAKE_UNITS_NAME")){
    		if(StringUtil.isNotEmpty(reportItemValue.getItem_value())){
    			rec.setValue("MAKE_UNITS_NAME", reportItemValue.getItem_value());	// 制造单位
    		}else{
    			rec.setValue("MAKE_UNITS_NAME", deviceDocument.getMake_units_name());	// 制造单位
    		}
    	}else if(reportItemValue.getItem_name().equals("SECURITY_TEL")){
    		if(StringUtil.isNotEmpty(reportItemValue.getItem_value())){
    			rec.setValue("SECURITY_TEL", reportItemValue.getItem_value());		// 安全管理员联系电话
    		}else{
    			rec.setValue("SECURITY_TEL", deviceDocument.getSecurity_tel());		// 安全管理员联系电话
    		}
    	}else if(reportItemValue.getItem_name().equals("MAINTAIN_UNIT_NAME")){
    		if(StringUtil.isNotEmpty(reportItemValue.getItem_value())){
    			if("6".equals(big_class)){
        			// 游乐设施合格证中维保单位取值使用单位
        			wbdw = com_name;
            		rec.setValue("MAINTAIN_UNIT_NAME", com_name);	// 维保单位
        		}else{
        			wbdw = reportItemValue.getItem_value();
            		rec.setValue("MAINTAIN_UNIT_NAME", reportItemValue.getItem_value());	// 维保单位
        		}
    		}else{ 
    			wbdw = deviceDocument.getMaintain_unit_name();
            	rec.setValue("MAINTAIN_UNIT_NAME", deviceDocument.getMaintain_unit_name());	// 维保单位
    		}   		
    	}else if(reportItemValue.getItem_name().equals("LAST_INSPECTION_DATE")){	// 下一次检验日期  		
    		String next_check_date = reportItemValue.getItem_value();
    		next_inspection_date = next_check_date;
    		System.out.println("下一次检验日期========item======="+next_check_date+"=============下一次检验日期");
    		if(StringUtil.isNotEmpty(next_check_date)){
				if(next_check_date.contains("/")){
    				next_check_date = next_check_date.replaceAll("/","-");
    			}
    			year = DateUtil.getDateTime("yyyy", DateUtil.convertStringToDate("yyyy-MM-dd",next_check_date)); 
    			month = DateUtil.getDateTime("MM", DateUtil.convertStringToDate("yyyy-MM-dd",next_check_date)); 
    			rec.setValue("DATE_YEAR", year);
    			rec.setValue("DATE_MONTH", month);
    		}else{
    			Date info_next_date = inspectionInfo.getLast_check_time();
    			if(info_next_date!=null){
    				System.out.println("下一次检验日期========info======="+next_check_date+"=============下一次检验日期");
    				year = DateUtil.getDateTime("yyyy", info_next_date); 
        			month = DateUtil.getDateTime("MM", info_next_date); 
        			rec.setValue("DATE_YEAR", year);
        			rec.setValue("DATE_MONTH", month);
    			}else{
    				Date device_next_date = deviceDocument.getInspect_next_date();
        			if(device_next_date!=null){
        				System.out.println("下一次检验日期========device======="+next_check_date+"=============下一次检验日期");
        				year = DateUtil.getDateTime("yyyy", device_next_date); 
            			month = DateUtil.getDateTime("MM", device_next_date); 
            			rec.setValue("DATE_YEAR", year);
            			rec.setValue("DATE_MONTH", month);
        			}
    			}
    		}
    	}else if(reportItemValue.getItem_name().equals("KY_LAST_INSPECTION_DATE")){	// 下一次检验日期
    		String next_check_date = reportItemValue.getItem_value();
    		System.out.println("下一次检验日期========item======="+next_check_date+"=============下一次检验日期");
    		if(StringUtil.isNotEmpty(next_check_date)){
				if(next_check_date.contains("/")){
    				next_check_date = next_check_date.replaceAll("/","-");
    			}
    			year = DateUtil.getDateTime("yyyy", DateUtil.convertStringToDate("yyyy-MM",next_check_date)); 
    			month = DateUtil.getDateTime("MM", DateUtil.convertStringToDate("yyyy-MM",next_check_date)); 
    			rec.setValue("DATE_YEAR", year);
    			rec.setValue("DATE_MONTH", month);
    		}else{
    			Date info_next_date = inspectionInfo.getLast_check_time();
    			if(info_next_date!=null){
    				System.out.println("下一次检验日期========info======="+next_check_date+"=============下一次检验日期");
    				year = DateUtil.getDateTime("yyyy", info_next_date); 
        			month = DateUtil.getDateTime("MM", info_next_date); 
        			rec.setValue("DATE_YEAR", year);
        			rec.setValue("DATE_MONTH", month);
    			}else{
    				Date device_next_date = deviceDocument.getInspect_next_date();
        			if(device_next_date!=null){
        				System.out.println("下一次检验日期========device======="+next_check_date+"=============下一次检验日期");
        				year = DateUtil.getDateTime("yyyy", device_next_date); 
            			month = DateUtil.getDateTime("MM", device_next_date); 
            			rec.setValue("DATE_YEAR", year);
            			rec.setValue("DATE_MONTH", month);
        			}
    			}
    		}
    	}else if(reportItemValue.getItem_name().equals("LAST_INSPECTION_DATE_Y")){
    		if("4".equals(big_class) || "5".equals(big_class)){
    			year = reportItemValue.getItem_value(); 
    			rec.setValue("DATE_YEAR", reportItemValue.getItem_value());	// 下次检验日期之年份
        	}
    	}else if(reportItemValue.getItem_name().equals("LAST_INSPECTION_DATE_M")){
    		if("4".equals(big_class) || "5".equals(big_class)){
    			month = reportItemValue.getItem_value(); 
    			rec.setValue("DATE_MONTH", reportItemValue.getItem_value());	// 下次检验日期之月份
        	}
    	}else if(reportItemValue.getItem_name().equals("LAST_YEAR")){	// 滑道
    		if("6".equals(big_class)){
    			year = reportItemValue.getItem_value(); 
    			rec.setValue("DATE_YEAR", reportItemValue.getItem_value());	// 下次检验日期之年份
        	}
    	}else if(reportItemValue.getItem_name().equals("LAST_MONTH")){	// 滑道
    		if("6".equals(big_class)){
    			month = reportItemValue.getItem_value(); 
    			rec.setValue("DATE_MONTH", reportItemValue.getItem_value());	// 下次检验日期之月份
        	}
    	}else if(reportItemValue.getItem_name().equals("INSPECTION_OP_STR")){
    		if(StringUtil.isNotEmpty(reportItemValue.getItem_value())){
    			if("6".equals(big_class)){
        			rec.setValue("CHECK_OP_NAME", reportItemValue.getItem_value());	// 检验人员
            	}else if("4".equals(big_class) || "5".equals(big_class)){
            		rec.setValue("INSPECTION_OP_STR", reportItemValue.getItem_value());	// 检验人员
            	}
    		}else{
    			if("6".equals(big_class)){
        			rec.setValue("CHECK_OP_NAME", inspectionInfo.getCheck_op_name());	// 检验人员
            	}else if("4".equals(big_class) || "5".equals(big_class)){
            		rec.setValue("INSPECTION_OP_STR", inspectionInfo.getCheck_op_name());	// 检验人员
            	}
    		}
    		
    	}else if(reportItemValue.getItem_name().equals("DEVICE_NAME")){
    		if("9".equals(big_class)){
    			rec.setValue("DEVICE_NAME", reportItemValue.getItem_value());	// 设备名称
        	}
    	}
    }
    
    
    if(!haveInternal_num){
    	if("6".equals(big_class)){
    		rec.setValue("INTERNAL_NUM", deviceDocument.getInternal_num()+"（"+deviceDocument.getDevice_name()+"）");	// 使用单位设备编号
    	}else{
    		rec.setValue("INTERNAL_NUM", deviceDocument.getInternal_num());	// 使用单位设备编号
    	}
    }
    
    if(StringUtil.isEmpty(year) || StringUtil.isEmpty(month)){
    	next_inspection_date = DateUtil.getDateTime("yyyy-MM-dd", deviceDocument.getInspect_next_date());
    	year = DateUtil.getDateTime("yyyy", DateUtil.convertStringToDate("yyyy-MM",next_inspection_date)); 
    	month = DateUtil.getDateTime("MM", DateUtil.convertStringToDate("yyyy-MM",next_inspection_date)); 
		rec.setValue("DATE_YEAR", year);
		rec.setValue("DATE_MONTH", month);
    }
    
    if(StringUtil.isEmpty(year) || StringUtil.isEmpty(month)){
    	next_inspection_date = DateUtil.getDateTime("yyyy-MM-dd", inspectionInfo.getLast_check_time());
    	year = DateUtil.getDateTime("yyyy", DateUtil.convertStringToDate("yyyy-MM",next_inspection_date)); 
    	month = DateUtil.getDateTime("MM", DateUtil.convertStringToDate("yyyy-MM",next_inspection_date)); 
		rec.setValue("DATE_YEAR", year);
		rec.setValue("DATE_MONTH", month);
    }

    ImageTool imageTool = new ImageTool();
    
	/* if(device_registration_code!=null){
			System.out.println("+=============="+imageTool.setCodeToByteArray(device_registration_code));
			rec.setValue("DTHGZ", imageTool.setCodeToByteArray(device_registration_code));
		
	} */

	
	
	//将下一次检验日期按照yyyy年MM月方式显示
	//String year = "";
    //String month = "";
	//if(deviceDocument.getInspect_next_date() != null){
	//	String next_check_date = DateUtil.getDate(deviceDocument.getInspect_next_date());
	//	if(StringUtil.isNotEmpty(next_check_date)){
	//		year = DateUtil.getDateTime("yyyy", deviceDocument.getInspect_next_date()); 
	//		month = DateUtil.getDateTime("MM", deviceDocument.getInspect_next_date()); 
	//		rec.setValue("DATE_YEAR", year);
	//		rec.setValue("DATE_MONTH", month);
	//	}
	//}
	if(StringUtil.isEmpty(wbdw)){
		if("6".equals(big_class)){	// 游乐设施合格证中维保单位取值使用单位
			rec.setValue("MAINTAIN_UNIT_NAME", deviceDocument.getCompany_name());	// 维保单位
		}else{
			rec.setValue("MAINTAIN_UNIT_NAME", deviceDocument.getMaintain_unit_name());	// 维保单位
		}
	}
	if(StringUtil.isEmpty(device_registration_code)){
		if("4".equals(big_class) && inspectionInfo.getReport_sn().contains("QA")){
			rec.setValue("DEVICE_REGISTRATION_CODE", deviceDocument.getDevice_code());	// 设备代码
		}else{
			rec.setValue("DEVICE_REGISTRATION_CODE", deviceDocument.getDevice_registration_code());	// 设备注册代码
		}
	}
	rec.setValue("MAINTENANCE_TEL", deviceDocument.getMaintenance_tel());		// 维保单位联系电话
	//rec.setValue("SECURITY_TEL", deviceDocument.getSecurity_tel());		// 安全管理员联系电话
	
	mrds.addRow(rec);
	//获取打印机名称
	String print_name = StringUtil.isNotEmpty(printer_name)?printer_name:"";
	
	
	if(device_registration_code!=null){
   		if(device_registration_code.equals("")){
			device_registration_code=deviceDocument.getDevice_registration_code();
		}
   		if("4".equals(big_class)){
   			device_registration_code = deviceDocument.getDevice_registration_code();
   		}
 		 if(print_name.equals("实达BP-3000XE")){
 			
 			rec.setValue("DTHGZ", imageTool.setCodeToByteArray(device_registration_code,400,400));
 		 }else{
 			rec.setValue("DTHGZ", imageTool.setCodeToByteArray(device_registration_code,400,400));
 		 }
 		
 	}
	
	 System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@"+print_name);
    // 创建报表引擎对象，指定报表根目录，传递结果集，绑定报表
    MREngine engine = new MREngine(pageContext, "app/flow/report/temple");    
    engine.setUnicodeOption(1);
    // 用MRDataSet对象为报表提供数据集：
    engine.addMRDataSet("BGDS", mrds);
    String flag = request.getAttribute("flag").toString();
    
   
    //根据打印机名称选取模版
    if(print_name.equals("实达BP-3000XE")){
		//if(user_name.equals("张芸") || user_name.equals("马竺君") || user_name.equals("朱黎")){
					if(flag.equals("yes")){
						if("3".equals(big_class)){
							//if(user_name.equals("张芸"){// 电梯合格证
							 //engine.addReport("DTBQ3_EWM.mrf");
							//}else{
							 engine.addReport("DTBQ2_EWM.mrf");
							//}
						}else if("4".equals(big_class)){	// 起重机械合格证
							engine.addReport("QZBQ2_EWM.mrf");
						}else if("5".equals(big_class)){	// 场（厂）内专用机动车辆合格证
							engine.addReport("QZBQ2_EWM.mrf");
						}else if("6".equals(big_class)){	// 游乐设施合格证
							engine.addReport("YLSSBQ.mrf");
						}else if("9".equals(big_class)){	// 客运索道合格证
							engine.addReport("KYSDBQ.mrf");
						}
						
						
					}else{
						if("3".equals(big_class)){	// 电梯合格证
							engine.addReport("DTBQ2.mrf");
						}else if("4".equals(big_class)){	// 起重机械合格证
							engine.addReport("QZBQ2.mrf");
						}else if("5".equals(big_class)){	// 场（厂）内专用机动车辆合格证
							engine.addReport("QZBQ2.mrf");
						}else if("6".equals(big_class)){	// 游乐设施合格证
							engine.addReport("YLSSBQ.mrf");
						}else if("9".equals(big_class)){	// 客运索道合格证
							engine.addReport("KYSDBQ.mrf");
						}
					}


		/*}else{



			if(flag.equals("yes")){
				if("3".equals(big_class)){	// 电梯合格证
					if("100069".equals(check_unit_id)){
						engine.addReport("XZ_DTBQ_EWM.mrf");
					}else{
						engine.addReport("DTBQ_EWM.mrf");
					}
				}else if("4".equals(big_class)){	// 起重机械合格证
					engine.addReport("QZBQ_EWM.mrf");
				}else if("5".equals(big_class)){	// 场（厂）内专用机动车辆合格证
					engine.addReport("QZBQ_EWM.mrf");
				}else if("6".equals(big_class)){	// 游乐设施合格证
					engine.addReport("YLSSBQ.mrf");
				}else if("9".equals(big_class)){	// 客运索道合格证
					engine.addReport("KYSDBQ.mrf");
				}
				
				
			}else{
				if("3".equals(big_class)){	// 电梯合格证
					engine.addReport("DTBQ.mrf");
				}else if("4".equals(big_class)){	// 起重机械合格证
					engine.addReport("QZBQ.mrf");
				}else if("5".equals(big_class)){	// 场（厂）内专用机动车辆合格证
					engine.addReport("QZBQ.mrf");
				}else if("6".equals(big_class)){	// 游乐设施合格证
					engine.addReport("YLSSBQ.mrf");
				}else if("9".equals(big_class)){	// 客运索道合格证
					engine.addReport("KYSDBQ.mrf");
				}
			}
		}*/
    }else{
    	
    	if(flag.equals("yes")){
    		if("3".equals(big_class)){	// 电梯合格证
				if("100069".equals(check_unit_id)){
					engine.addReport("XZ_DTBQ_EWM.mrf");
				}else{
					engine.addReport("DTBQ1_EWM.mrf");
				}
        	}else if("4".equals(big_class)){	// 起重机械合格证
        		engine.addReport("QZBQ1_EWM.mrf");
        	}else if("5".equals(big_class)){	// 场（厂）内专用机动车辆合格证
        		engine.addReport("QZBQ1_EWM.mrf");
        	}else if("6".equals(big_class)){	// 游乐设施合格证
        		engine.addReport("YLSSBQ2.mrf");
        	}else if("9".equals(big_class)){	// 客运索道合格证
        		engine.addReport("KYSDBQ.mrf");
        	}
			
		}else{
			
			if("3".equals(big_class)){	// 电梯合格证
	    		engine.addReport("DTBQ1.mrf");
	    	}else if("4".equals(big_class)){	// 起重机械合格证
	    		engine.addReport("QZBQ1.mrf");
	    	}else if("5".equals(big_class)){	// 场（厂）内专用机动车辆合格证
	    		engine.addReport("QZBQ1.mrf");
	    	}else if("6".equals(big_class)){	// 游乐设施合格证
	    		engine.addReport("YLSSBQ2.mrf");
	    	}else if("9".equals(big_class)){	// 客运索道合格证
	    		engine.addReport("KYSDBQ.mrf");
	    	}
			
		}
    	
    }
    engine.bind();
%>
<html>
<head>
<title> 报表信息 </title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<%@include file="/k/kui-base-list.jsp"%>
<script language="javascript">
	$(function() {
		var height = $(window).height()-$('.toolbar-tm').height();
		$("#scroll-tm").css({height:height});
		setReports();
	})
	//设置报表属性
	function setReports()
	{
		//MRViewer.ShowToolbar=false;
		report = MRViewer.Report;
		pagecount = MRViewer.PageCount;
		
		//设置显示页
		var ss = "1".split(",");	//标签打印只有1页
		var ss_length = ss.length;
		var status = false;
		for(var i=0;i<pagecount;i++){
			for(var j=0;j<ss_length;j++){
				if((i+1)==ss[j])
				{
					status = true;
					page = report.Pages(i);
					page.Prop("Visible") = "True";
					break;
				}
				else
				{
					try{
						page = report.Pages(i);
						page.Prop("Visible") = "False";
					} catch(e){}
				}
			}
		}

		//alert(ss_length+"-----"+(ss_length%2)+"-----"+(ss_length%2==1))
		//如果为基数页的话，自动在页后添加空白页
		//if(ss_length%2==1)
		//{
			//alert();
			//report.AddPage();
		//}
		if(!status)
		{
			alert("报告模板上没有对应的索引！");
			api.close();	// window.close();
		}
	
		MRViewer.Preview();
		<%
			if("yes".equals(printout)){				
		%>
				doPrintreport();
				MRViewer_AfterPrint();
		<%
			}
		%>
	}
	
	
	
	//打印报告
	function doPrintreport()
	{	
		
		
		MRViewer.PrintSetup(0,0,true,"",0,1,true,"<%=StringUtil.isNotEmpty(printer_name)?printer_name:""%>");
		MRViewer.Print(false);	// false不提示打印设置框，调用默认的			
		//subP();
	}
	
	function MRViewer_AfterPrint()
	{
		//alert("print_afer_begin")
		//alert("print_afer")
		parent.left._opid<%=request.getParameter("opid")%>.innerHTML="<img src='k/kui/images/icons/16/check.png' border='0' >";	
		subP();	// 打印完后写数据库记录打印操作
		//alert("MRViewer_AfterPrint")
	}
	
	function subP()
	{
		formObj.action="report/query/insertPrintTagsRecord.do?id=<%=inspection_info_id %>&report_id=<%=report_id %>&device_id=<%=deviceDocument.getId() %>&isLast=yes&flag=<%=flag%>&op_type=";
		formObj.submit();
	}

	function showBB(){
		$("#sssss").show();
	}

	$(window).load(function() {
		$("#MRViewer").append('<param name="wmode" value="transparent" />');
	});
</script>
</head>
<body >
<form name="formObj" method="post" action="">
</form>
<div class="scroll-tm" style="overflow: hidden" id="sssss"> 
	<ming:MRViewer id="MRViewer" shownow="true"  width="100%"   height="100%"
	 simple="false" 
	 invisiblebuttons="Print,Export,Close,PrintPopup,ExportPopup,Find,FindNext" 
	 postbackurl=""  canedit="false"   wrapparams="true"  />
</div>
</body>
</html>