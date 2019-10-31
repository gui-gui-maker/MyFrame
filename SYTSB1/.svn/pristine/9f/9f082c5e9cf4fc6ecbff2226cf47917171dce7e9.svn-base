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
	var beanData = api.data.bean;//父窗口的数据

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
				if("2" == "${param.isPrint}"){
					api.data.pwindow.api.close();
					api.data.pwindow.api.data.window.refreshGrid();
				}
				api.close();
				return true;
			}
		};
		closeBtn={
			id: "close",
			text: "关闭",
			icon:"close",
			click: function(){
				if("2" == "${param.isPrint}"){
					api.data.pwindow.api.close();
					api.data.pwindow.api.data.window.refreshGrid();
				}
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
		if(${param.type}==1){	// 缴费单
			TANGER_OCX_OBJ.OpenFromURL("/app/payment/01zzjd_pay.doc");
			setBookMarkValue1("pay_no", beanData.pay_no);			// 编号
			setBookMarkValue1("com_name", beanData.company_name);		// 受检单位
			if(beanData.invoice_no!="" && beanData.invoice_no!=null){
				setBookMarkValue1("invoice_no", beanData.invoice_no);	// 发票号
			}
			
			var datas = beanData["inspectionZZJDPayInfoDTOList"];
			var num = 0;
			for(var i in datas){
				num ++;
				if(num == 1){
					setBookMarkValue1("check_department", datas[i]["inspection_unit_name"]);	// 检验部门
				}
				setBookMarkValue1("report_sn"+num, datas[i]["report_sn"]);	// 报告书编号
				//setBookMarkValue1("device_no"+num, datas[i]["device_no"]);	// 产品编号
				if(num == 5){
					break;
				}
			}
			var report_count = beanData.fk_inspection_info_id.split(",").length;
			setBookMarkValue1("report_count", report_count+"份报告");	// 报告数量合计
			setBookMarkValue1("total", beanData.pay_received);		// 监检费合计
			if("1" == beanData.pay_type){	// 1：现金
				setBookMarkValue1("cash_pay", beanData.pay_received + "元");	
			}else if("2" == beanData.pay_type){	// 2：转账
				//setBookMarkValue1("transfer_pay", beanData.pay_received + "元（" + beanData.remark + "）");	
				setBookMarkValue1("transfer_pay", beanData.remark);	// 转账（只显示备注内容，不显示转账金额） -- 2014-09-23修改
			}else if("4" == beanData.pay_type){	// 4：现金及转账
				setBookMarkValue1("cash_pay", beanData.cash_pay + "元");		// 现金
				setBookMarkValue1("transfer_pay", beanData.remark + "元");	// 转账
			}else if("5" == beanData.pay_type){	// 5：POS机刷卡
				setBookMarkValue1("pay_by_card", beanData.pay_received + "元");	
			}else if("6" == beanData.pay_type){	// 6：现金及POS机刷卡
				setBookMarkValue1("cash_pay", beanData.cash_pay + "元");		// 现金
				setBookMarkValue1("pay_by_card", beanData.remark + "元");	// POS机刷卡
			}
			setBookMarkValue1("print_date", "<%=DateUtil.getDateTime("yyyy-MM-dd", new Date()) %>");	// 打印日期
		}else if(${param.type}==2){	// 借条
			TANGER_OCX_OBJ.OpenFromURL("/app/payment/02zzjd_borrow.doc");
			var borrow_type = "";
			if("0" == beanData.borrow_type){
				borrow_type = "外借报告";
			}else if("1" == beanData.borrow_type){
				borrow_type = "外借发票";
			}else if("2" == beanData.borrow_type){
				borrow_type = "外借报告和发票";
			}		
			setBookMarkValue1("borrow_type", borrow_type);	// 外借类型
			var datas = beanData["inspectionZZJDPayInfoDTOList"];
			var num = 0;
			for(var i in datas){
				num ++;
				if(num == 1){
					setBookMarkValue1("check_department", datas[i]["inspection_unit_name"]);// 检验部门（科室名称）
					setBookMarkValue1("report_com_name", datas[i]["made_unit_name"]);		// 制造单位
					setBookMarkValue1("check_date", datas[i]["inspection_date"]);			// 检验日期
					setBookMarkValue1("check_user_name", datas[i]["check_op_name"]);		// 检验员
				}
			}
			setBookMarkValue1("borrow_count", num);	// 报告数量
			setBookMarkValue1("unpay_amount_uppercase", beanData.unpay_amount_uppercase);	// 欠款金额大写
			setBookMarkValue1("unpay_amount_lowercase", beanData.unpay_amount);	// 欠款金额小写
			setBookMarkValue1("invoice_no", beanData.invoice_no);	// 发票号
			setBookMarkValue1("borrow_name", beanData.borrow_name);	// 欠款人
			setBookMarkValue1("contack_number", beanData.contack_number);	// 电话
			setBookMarkValue1("borrow_date", "<%=DateToChinese.getDateChineseStr(new Date()) %>");	// 打印日期之年份（大写）
		}else{
			TANGER_OCX_OBJ.OpenFromURL("/app/payment/templete.doc");
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