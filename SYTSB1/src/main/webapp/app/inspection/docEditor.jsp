<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.lsts.sinspection.bean.SupervisionInspection" %>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<%@page import="util.MoneyUtil"%>
<%@page import="util.DateToChinese"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<%@include file="/k/kui-base-form.jsp"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<script src="app/pub/office/js/editor.js" type="text/javascript"></script>
<script type="text/javascript" src="app/payment/payment_list.js"></script>
<script type="text/javascript">
	var basepath = "${pageContext.request.contextPath}/";
	var toolBar;//工具条
	var beanData = api.data.bean;	// 父窗口的数据
	var device_type = api.data.device_type;	// 父窗口的数据-设备类型（大类）
	var check_type = api.data.check_type;	// 父窗口的数据-检验类别
	$(function(){
		var height = $(window).height()-$('.toolbar-tm').height();
		$("#scroll-tm").css({height:height});
	
		$(".layout").ligerLayout({
			bottomHeight:30,
			space : 0
		});
	});
	
	function initPage(){
		initToolBar();
		createNtkoEditor("editor_container");
		//远程加载文档
		loadBumfDoc();
		initDocView();
	}
	
	
	function initDocView(){
		if("${param.status}"=="detail"){
			initView("view");
		}
	}
	
	function initToolBar(){
		var printBtn;
		var closeBtn;
		printBtn={
			id: "print",
			text: "打印",
			icon:"print",
			click: function(){						
				doPrint();
				api.close();
				return true;
			}
		};
		closeBtn={
			id: "close",
			text: "关闭",
			icon:"close",
			click: function(){
				api.close();
				return true;
			}
		};
		var itemArr=new Array();
		itemArr.push(printBtn);
		itemArr.push(closeBtn);
		toolBar=$("#toolbar").ligerButton({
			items:itemArr
		});
	}
	
	//加载正文
	function loadBumfDoc(){
		TANGER_OCX_OBJ.ToolBars=true;
		if(beanData != null){	
			if("3" == device_type){	// 电梯
				TANGER_OCX_OBJ.OpenFromURL("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/inspection/inspection_apply_2015.doc");		// 电梯检验申请单
				if(beanData[0].check_unit_name!=null){
					setBookMarkValue1("check_department", beanData[0].check_unit_name);		// 检验部门
				}				
				if(beanData[0].device_area_name!=null){
					setBookMarkValue1("device_area_name", beanData[0].device_area_name);		// 设备所在区域（所属区县名称）
				}
				if(beanData[0].device_use_place!=null){
					setBookMarkValue1("device_use_place", beanData[0].device_use_place);		// 设备所在地（街道）
				}
				if(beanData[0].maintain_unit_name!=null){
					setBookMarkValue1("maintain_unit_name", beanData[0].maintain_unit_name);	// 维保单位名称
				}				
				if(beanData[0].maintenance_man!=null){
					setBookMarkValue1("maintenance_man", beanData[0].maintenance_man);			// 维保单位联系人
				}
				if(beanData[0].maintenance_tel!=null){
					setBookMarkValue1("maintenance_tel", beanData[0].maintenance_tel);			// 维保单位联系人电话
				}
				if(beanData[0].check_date!=null){
					setBookMarkValue1("check_date", beanData[0].check_date);					// 检验日期
				}
				if(beanData[0].company_name!=null){
					setBookMarkValue1("com_name", beanData[0].company_name);					// 使用单位（项目名称）
				}
				if(beanData[0].security_tel!=null){
					setBookMarkValue1("security_tel", beanData[0].security_tel);				// 安全管理/使用单位联系电话
				}
				//setBookMarkValue1("print_date", "<%=DateUtil.getDateTime("yyyy-MM-dd", new Date()) %>");	// 打印日期
			}else if("4" == device_type){	// 起重机
				// 起重机检验申请单
				TANGER_OCX_OBJ.OpenFromURL("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/inspection/qzj_inspection_apply_2017.doc");		
				if(beanData[0].com_addr!=null){
					setBookMarkValue1("com_addr", beanData[0].com_addr);					// 使用单位地址
				}				
				if(beanData[0].construction_licence_no!=null){
					setBookMarkValue1("construction_licence_no", beanData[0].construction_licence_no);	// 制造安装许可证编号
				}
				if(beanData[0].construction_units_name!=null){
					setBookMarkValue1("construction_units_name", beanData[0].construction_units_name);	// 安装改造修理单位名称
				}
				if(beanData[0].check_date!=null){
					setBookMarkValue1("check_date", beanData[0].check_date);					// 检验日期
				}
				if(beanData[0].company_name!=null){
					setBookMarkValue1("com_name", beanData[0].company_name);					// 使用单位（项目名称）
				}
				if(beanData[0].security_tel!=null){
					setBookMarkValue1("security_tel", beanData[0].security_tel);				// 安全管理/使用单位联系电话
				}
			}else if("5" == device_type){	// 厂车
				// 厂车检验申请单
				TANGER_OCX_OBJ.OpenFromURL("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/inspection/cc_inspection_apply_2017.doc");		
				if(beanData[0].device_use_place!=null){
					setBookMarkValue1("device_use_place", beanData[0].device_use_place);		// 设备使用地点
				}
				if(beanData[0].check_date!=null){
					setBookMarkValue1("check_date", beanData[0].check_date);					// 检验日期
				}
				if(beanData[0].company_name!=null){
					setBookMarkValue1("com_name", beanData[0].company_name);					// 使用单位（项目名称）
				}
				if(beanData[0].security_tel!=null){
					setBookMarkValue1("security_tel", beanData[0].security_tel);				// 安全管理/使用单位联系电话
				}
			}else if("6" == device_type){	// 游乐设施
				// 游乐设施检验申请单
				var report_com_name = "";
				var report_com_address = "";
				var make_units_name = "";
				var make_units_name_temp = "";
				var check_typeArr = check_type.split(",");
				var check_date = "";
				TANGER_OCX_OBJ.OpenFromURL("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/inspection/yl_inspection_apply_2018.doc");		
				for(var i=1;i<beanData.length+1;i++){
					if(i==1){
						report_com_name = (beanData[i-1].company_name==null || beanData[i-1].company_name=="null" || beanData[i-1].company_name=="undefined") ? "" : beanData[i-1].company_name;
						report_com_address = (beanData[i-1].device_use_place==null || beanData[i-1].device_use_place=="null" || beanData[i-1].device_use_place=="undefined") ? "" : beanData[i-1].device_use_place;
						make_units_name = (beanData[i-1].make_units_name==null || beanData[i-1].make_units_name=="null" || beanData[i-1].make_units_name=="undefined") ? "" : beanData[i-1].make_units_name;
					}else{
						if(report_com_name ==null || report_com_name =="null" || report_com_name ==""){
							report_com_name = beanData[i-1].company_name;
						}
						if(report_com_address ==null || report_com_address =="null" || report_com_address ==""){
							report_com_address = beanData[i-1].device_use_place;
						}
						if(make_units_name ==null || make_units_name =="null" || make_units_name ==""){
							make_units_name = beanData[i-1].make_units_name;
						}else{
							if(make_units_name != beanData[i-1].make_units_name){
								make_units_name_temp="等";
							}
						}
					}
					setBookMarkValue1("device_name"+i, (beanData[i-1].device_name==null || beanData[i-1].device_name=="null" || beanData[i-1].device_name=="undefined") ? "" : beanData[i-1].device_name); // 设备名称
					setBookMarkValue1("device_sort_name"+i, (beanData[i-1].device_sort_name==null || beanData[i-1].device_sort_name=="null" || beanData[i-1].device_sort_name=="undefined") ? "" : beanData[i-1].device_sort_name); // 设备类别
					setBookMarkValue1("check_type"+i, (check_typeArr[i-1]==null || check_typeArr[i-1]=="null" || check_typeArr[i-1]=="undefined") ? "" : check_typeArr[i-1]); // 检验类别
					setBookMarkValue1("factory_code"+i, (beanData[i-1].factory_code==null || beanData[i-1].factory_code=="null" || beanData[i-1].factory_code=="undefined") ? "" : beanData[i-1].factory_code); // 设备编号
					setBookMarkValue1("registration_num"+i, (beanData[i-1].registration_num==null || beanData[i-1].registration_num=="null" || beanData[i-1].registration_num=="undefined") ? "" : beanData[i-1].registration_num); // 使用登记证号
					if(check_date==null || check_date=="" || check_date=="undefined"){
						check_date = beanData[i-1].check_date;
					}
				}
				setBookMarkValue1("report_com_name", report_com_name); // 使用单位
				setBookMarkValue1("report_com_address", report_com_address); // 设备使用地点
				setBookMarkValue1("make_units_name", make_units_name+make_units_name_temp); // 制造单位名称
				if(check_date!=null && check_date!="" && check_date!="undefined"){
					setBookMarkValue1("check_date_year", check_date.substr(0,4)==""?"    ":check_date.substr(0,4)); // 检验时间-年
					setBookMarkValue1("check_date_month", check_date.substr(5,2)==""?"   ":check_date.substr(5,2)); // 检验时间-月
					setBookMarkValue1("check_date_day", check_date.substr(8,2)==""?"   ":check_date.substr(8,2)); // 检验时间-日
				}else{
					setBookMarkValue1("check_date_year", "    "); // 检验时间-年
					setBookMarkValue1("check_date_month", "   "); // 检验时间-月
					setBookMarkValue1("check_date_day", "   "); // 检验时间-日
				}
			}				
		}else{
			TANGER_OCX_OBJ.OpenFromURL("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/payment/templete.doc");
		}
		//saved属性用来判断文档是否被修改过,文档打开的时候设置成ture,当文档被修改,自动被设置为false,该属性由office提供.
		//TANGER_OCX_OBJ.activeDocument.saved=true;
	}

	function showBB(){
		$("#sssss").show();
	}
</script>
</head>
<body onload="initPage();">
<div class="scroll-tm" style="overflow: hidden" id="sssss"> 
<div class="layout">
    <div id="seal_container"></div>
	<div position="center" id="editor_container" style="width:100%;height:100%"></div>
	<div position="bottom" style="height: 50px;">
		<div class="div1" id="toolbar" style="padding:1px;text-align:right;"></div>
	</div>
</div>
<iframe id="export_doc_iframe" style="display:none;">
</iframe>
</div>
</body>
</html>