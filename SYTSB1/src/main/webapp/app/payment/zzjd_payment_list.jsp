<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@ page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<style type="">
.l-icon-exportExcel {
	background: url('k/kui/images/icons/16/excel-export.png') no-repeat center;
}
</style>
<%
	String info_type = request.getParameter("info_type");
%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"device_type_code", compare:"like"},
			{name:"report_sn", compare:"like"},
			{name:"made_unit_name", compare:"like"},			
			{name:"inspection_unit_name", id:"inspection_unit_name", compare:"like"}
			<%
				if("3".equals(info_type)){
					%>
					,{ name : "fee_status", compare : "="},
					{group:[
						{name:"borrow_date", id:"borrow_start_date1", compare:">=", value:""},
						{label:"到", name:"borrow_date", id:"borrow_end_date1", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]}
					<%
				}else{
					if(!"1".equals(info_type)){
					%>
						,{group:[
							{name:"invoice_date", id:"invoice_start_date1", compare:">=", value:""},
							{label:"到", name:"invoice_date", id:"invoice_end_date1", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
						]},
						{name : "invoice_no", compare : "like"}	
					<%
					}
				}
			%>
		],
		tbar : [{
			text : '详情',
			id : 'detail',
			click : detail,
			icon : 'detail'
		}
		<%
			if(StringUtil.isNotEmpty(info_type)){
				if("1".equals(info_type) || "3".equals(info_type)){
					%>
					, '-', {
						text : '收费',
						id : 'pay',
						click : doPayment,
						icon : 'add'
					}
					<%
				}
				if("1".equals(info_type)){
					%>
					, '-', {
						text : '外借登记',
						id : 'borrow',
						click : doBorrow,
						icon : 'role'
					}
					<%
				}
				if("2".equals(info_type)){
					%>
					, '-', {
						text : '取消收费',
						id : 'cancel',
						click : cancelPayment,
						icon : 'del'
					}, '-', {
						text : '退款',
						id : 'back',
						click : backPayment,
						icon : 'back'
					}, '-', {
						text : '退款记录',
						id : 'backHistory',
						click : backHistory,
						icon : 'detail'
					}, '-', {
						text : '打印缴费单',
						id : 'printPayInfo',
						click : printPayInfo,
						icon : 'print'
					}
					<%
				}
				if("3".equals(info_type)){	// 外借查询，可修改外借记录（用作发票号录入）
					%>
					, '-', {
						text : '修改外借登记',
						id : 'mdyBorrow',
						click : mdyBorrow,
						icon : 'role'
					}, '-', {
						text : '取消外借',
						id : 'delBorrow',
						click : delBorrow,
						icon : 'delete'
					}, '-', {
						text : '打印借条',
						id : 'printBorrow',
						click : printBorrow,
						icon : 'print'
					}, '-', {
						text : '借报告导出',
						id : 'exportPayInfo2',
						click : exportPayInfo2,
						icon : 'excel-export'
					}, '-', {
						text : '借发票导出',
						id : 'exportPayInfo3',
						click : exportPayInfo3,
						icon : 'excel-export'
					}, '-', {
						text : '借报告和发票导出',
						id : 'exportPayInfo4',
						click : exportPayInfo4,
						icon : 'excel-export'
					}
					<%
				}
					%> 
					,'-', {
						text : '应收金额合计',
						id : 'pay_receive',
						click : doTotal1,
						icon : 'help'
					}
					<%
				if("2".equals(info_type)){
					%>
					, '-', {
						text : '实收金额合计',
						id : 'pay_received',
						click : doTotal2,
						icon : 'help'
					}, '-', {
					text : '交账明细导出',
					id : 'exportPayInfo',
					click : exportPayInfo,
					icon : 'excel-export'
				}, '-', {
					text : '收入明细导出',
					id : 'exportPayInfo1',
					click : exportPayInfo1,
					icon : 'excel-export'
				}
					<%
				}else if("3".equals(info_type)){
					%>
					, '-', {
						text : '历史记录',
						id : 'borrowHistory',
						click : borrowHistory,
						icon : 'detail'
					}
					<%
				}
			}
		%>
			<sec:authorize access="hasRole('margeCompany')">	
				, "-",
				{
					text : '批量修改',
					id : 'editcom',
					icon : 'modify',
					click : editcom
				}
				, "-",
				{
					text : '合并使用单位',
					id : 'margeCom',
					icon : 'modify',
					click : margeCom
				}
			</sec:authorize>
		],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange()
			},
			rowAttrRender : function(rowData, rowid) {

			}
		}
	};

	// 行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				pay : false,
				detail : false,
				borrow : false,
				pay_receive : false,
				exportPayInfo : true,
				editcom : false,
				margeCom : false
				<%
					if("2".equals(info_type)){
						%>
						,cancel : false,
						back : false,
						backHistory : false,
						printPayInfo : false,
						pay_received : false
						<%
					}else if("3".equals(info_type)){
						%>
						,mdyBorrow : false,
						delBorrow : false,
						printBorrow : false,
						borrowHistory : true
						<%
					}
				%>
			});
		} else if (count == 1) {
			Qm.setTbar({
				pay : true,
				detail : true,
				borrow : true,
				pay_receive : true,
				exportPayInfo : true,
				editcom : true,
				margeCom : true
				<%
					if("2".equals(info_type)){
						%>
						,cancel : true,
						back : true,
						backHistory : true,
						printPayInfo : true,
						pay_received : true
						<%
					}else if("3".equals(info_type)){
						%>
						,mdyBorrow : true,
						delBorrow : true,
						printBorrow : true,
						borrowHistory : true
						<%
					}
				%>
			});
		} else {
			Qm.setTbar({
				pay : true,
				detail : false,
				borrow : true,
				pay_receive : true,
				exportPayInfo : true,
				editcom : true,
				margeCom : true
				<%
					if("2".equals(info_type)){
						%>
						,cancel : true,
						back : false,
						backHistory : false,
						printPayInfo : false,
						pay_received : true
						<%
					}else if("3".equals(info_type)){
						%>
						,mdyBorrow : false,
						delBorrow : false,
						printBorrow : false,
						borrowHistory : true
						<%
					}
				%>
			});
		}
	}
	
	// 查看详情
	function detail() {
		var selectedIds = Qm.getValuesByName("inspection_info_id").toString();
		var url = "";
		if("3" == "<%=info_type%>"){	// 外借详情
			url = 'url:app/payment/report_zzjd_borrow_detail.jsp?status=detail&id='+ selectedIds;
		}else{	// 缴费详情
			url = 'url:app/payment/zzjd_payment_detail.jsp?status=detail&id='+ selectedIds;
		}
		top.$.dialog({
			width : 1000,
			height : 550,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},
			content : url	
		});
	}
	
	
	// 收费
	function doPayment() {
		var ids = Qm.getValuesByName("inspection_info_id").toString();	// 报检业务ID
		var comArr = Qm.getValuesByName("made_unit_name");				// 制造单位
		var first_com = comArr[0];
		for(var i=1;i<comArr.length;i++){
			if(comArr[i] != first_com){
				$.ligerDialog.error("亲，批量收费时，只能收取相同制造单位的报告哦，请重新选择！");
				return;
			}
		}
		top.$.dialog({
			width : 1000,
			height : 620,
			lock : true,
			title : "收费",
			data : {
				"window" : window
			},
			content : 'url:app/payment/zzjd_payment_detail.jsp?status=add&id='+ids
		});
	}
	
	// 取消收费
	function cancelPayment() {
		var selectedIds = Qm.getValuesByName("inspection_info_id").toString();
		var comArr = Qm.getValuesByName("made_unit_name");				// 制造单位
		var first_com = comArr[0];
		for(var i=1;i<comArr.length;i++){
			if(comArr[i] != first_com){
				$.ligerDialog.error("亲，取消收费时，只能取消相同制造单位的报告哦，请重新选择！");
				return;
			}
		}
		$.ligerDialog.confirm("您确定要取消收费？取消收费后，所选报告将退回到“待收费”状态！", function(yes) {
			if (yes) {
				$.ajax({
					url : "payment/payInfo/cancelPayment.do?ids=" + selectedIds + "&type=<%=info_type%>",
					type : "post",
					async : false,
					success : function(resp) {
						if (resp.success) {
							top.$.notice("取消收费成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("取消收费失败！" + resp.msg);
						}
					}
				});
			}
		});
	}
	
	// 外借登记
	function doBorrow() {
		var selectedIds = Qm.getValuesByName("inspection_info_id").toString();	// 报检业务ID
		var comArr = Qm.getValuesByName("made_unit_name");				// 制造单位
		var first_com = comArr[0];
		for(var i=1;i<comArr.length;i++){
			if(comArr[i] != first_com){
				$.ligerDialog.error("亲，外借时，只能外借相同制造单位的报告哦，请重新选择！");
				return;
			}
		}
		top.$.dialog({
			width : 1000,
			height : 620,
			lock : true,
			title : "外借登记",
			data : {
				"window" : window
			},
			content : 'url:app/payment/report_zzjd_borrow_detail.jsp?status=add&id='+ selectedIds+'&com_name='+encodeURI(encodeURI(first_com))
		});
	}
	
	// 修改外借登记
	function mdyBorrow() {
		var selectedIds = Qm.getValuesByName("inspection_info_id").toString();	// 报检业务ID
		var comArr = Qm.getValuesByName("made_unit_name");				// 制造单位
		var first_com = comArr[0];
		for(var i=1;i<comArr.length;i++){
			if(comArr[i] != first_com){
				$.ligerDialog.error("亲，修改外借时，只能修改相同制造单位的外借报告哦，请重新选择！");
				return;
			}
		}
		top.$.dialog({
			width : 1000,
			height : 620,
			lock : true,
			title : "修改外借登记",
			data : {
				"window" : window
			},
			content : 'url:app/payment/report_zzjd_borrow_detail.jsp?status=modify&id='+ selectedIds+'&com_name='+encodeURI(encodeURI(first_com))
		});
	}
	
	// 取消外借登记
	function delBorrow() {
		var info_id = Qm.getValueByName("inspection_info_id").toString();	// 报检业务ID
		$.del("您确定要取消外借吗？取消后如需再次外借或进行收费，请到“待收费”进行处理！", "report/borrow/delBorrow.do", {
			"info_id" : info_id
		});
	}
	
	// 退款
	function backPayment() {
		var selectedIds = Qm.getValuesByName("inspection_info_id").toString();
		var comArr = Qm.getValuesByName("made_unit_name");				// 制造单位
		var first_com = comArr[0];
		for(var i=1;i<comArr.length;i++){
			if(comArr[i] != first_com){
				$.ligerDialog.error("亲，退款时，只能退回相同制造单位的收费哦，请重新选择！");
				return;
			}
		}
		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "退款",
			data : {
				"window" : window
			},
			content : 'url:app/payment/zzjd_payment_back_detail.jsp?status=add&info_id='+ selectedIds+'&com_name='+encodeURI(encodeURI(first_com))
		});
	}
	
	// 退款记录
	function backHistory() {
		var selectedId = Qm.getValueByName("inspection_info_id");
		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "退款记录",
			data : {
				"window" : window
			},
			content : 'url:app/payment/zzjd_payment_back_list.jsp?info_id='+ selectedId
		});
	}
	
	// 打印缴费单
	function printPayInfo(){
		var selectedIds = Qm.getValuesByName("inspection_info_id").toString();
		var comArr = Qm.getValuesByName("made_unit_name");				// 制造单位
		var first_com = comArr[0];
		for(var i=1;i<comArr.length;i++){
			if(comArr[i] != first_com){
				$.ligerDialog.error("亲，打印缴费单时，只能打印相同制造单位的缴费单哦，请重新选择！");
				return;
			}
		}
		$.getJSON("payment/payInfo/getZZJDDetail.do?status=detail&id="+selectedIds, function(resp){
			if(resp.success){
				if(resp.data["id"] != null){
					top.$.dialog({
						width : $(top).width(),
						height :  $(top).height()-40,
						lock : true,
						title : "打印缴费单",
						parent: api,
						content : 'url:app/payment/report_zzjd_docEditor.jsp?status=modify&type=1&isPrint=1',	// type 1：缴费单 2：借条
						data: {pwindow: window, bean: resp.data}
					}).max();
				}else{
					$.ligerDialog.alert("请先收费，再打印缴费单！");
				}
			}
		})
	}
	
	// 打印借条
	function printBorrow(){
		var selectedIds = Qm.getValuesByName("inspection_info_id").toString();
		var comArr = Qm.getValuesByName("made_unit_name");				// 制造单位
		var first_com = comArr[0];
		for(var i=1;i<comArr.length;i++){
			if(comArr[i] != first_com){
				$.ligerDialog.error("亲，打印借条时，只能打印相同制造单位的借条哦，请重新选择！");
				return;
			}
		}
		$.getJSON("report/borrow/getZZJDDetail.do?status=detail&id="+selectedIds, function(resp){
			if(resp.success){
				if(resp.data["id"] != null){
					top.$.dialog({
						width : $(top).width(),
						height :  $(top).height()-40,
						lock : true,
						title : "打印借条",
						parent: api,
						content : 'url:app/payment/report_zzjd_docEditor.jsp?status=modify&type=2&isPrint=1',	// type 1：缴费单 2：借条
						data: {pwindow: window, bean: resp.data}
					}).max();
				}else{
					$.ligerDialog.alert("请先进行外借登记，再打印借条！");
				}
			}
		})
	}
	
	function borrowHistory(){
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true, 
			title : "外借历史记录", 
			data : {"window" : window},
			content : 'url:app/payment/report_borrow_list.jsp'
		});
	}
	
	// 应收金额合计
	function doTotal1() {
		var ids = Qm.getValuesByName("advance_fees").toString();
		doTotal(ids,"应收金额");
	}
	
	// 实收金额合计
	function doTotal2() {
		var ids = Qm.getValuesByName("receivable").toString();
		doTotal(ids,"实收金额");
	}
	
	// 导出交账明细
	function exportPayInfo(){
		$("#check_dep_name").val($("#inspection_unit_name").val());
		$("#invoice_start_date").val($("#invoice_start_date1").val());
		$("#invoice_end_date").val($("#invoice_end_date1").val());
		$("body").mask("正在导出数据,请等待...");
    	$("#form1").attr("action","payment/payInfo/exportZZJD.do");
    	$("#form1").submit();
    	$("body").unmask();
	}
	
	// 导出收入明细
	function exportPayInfo1(){
		$("#check_dep_name").val($("#inspection_unit_name").val());
		$("#invoice_start_date").val($("#invoice_start_date1").val());
		$("#invoice_end_date").val($("#invoice_end_date1").val());
		$("body").mask("正在导出数据,请等待...");
    	$("#form1").attr("action","payment/payInfo/exportZZJD1.do");
    	$("#form1").submit();
    	$("body").unmask();
	}
	
	// 导出借报告
	function exportPayInfo2(){
		$("#check_dep_name").val($("#inspection_unit_name").val());
		$("#borrow_start_date").val($("#borrow_start_date1").val());
		$("#borrow_end_date").val($("#borrow_end_date1").val());
		$("body").mask("正在导出数据,请等待...");
    	$("#form1").attr("action","payment/payInfo/exportZZJD2.do");
    	$("#form1").submit();
    	$("body").unmask();
	}
	
	// 导出借发票
	function exportPayInfo3(){
		$("#check_dep_name").val($("#inspection_unit_name").val());
		$("#borrow_start_date").val($("#borrow_start_date1").val());
		$("#borrow_end_date").val($("#borrow_end_date1").val());
		$("body").mask("正在导出数据,请等待...");
    	$("#form1").attr("action","payment/payInfo/exportZZJD3.do");
    	$("#form1").submit();
    	$("body").unmask();
	}
	
	// 导出借报告和发票
	function exportPayInfo4(){
		$("#check_dep_name").val($("#inspection_unit_name").val());
		$("#borrow_start_date").val($("#borrow_start_date1").val());
		$("#borrow_end_date").val($("#borrow_end_date1").val());
		$("body").mask("正在导出数据,请等待...");
    	$("#form1").attr("action","payment/payInfo/exportZZJD4.do");
    	$("#form1").submit();
    	$("body").unmask();
	}
	
	function doTotal(ids,title){
		var str = new Array();
		str = ids.split(",");
		var total = 0;
		if (str != null && str.length > 0) {
			for ( var i = 0; i < str.length; i++) {
				if(str[i]==''||str[i]==null){
						str[i]=0;
					}
				total = total + parseFloat(str[i]);
			}
			$.ligerDialog.alert(title+'合计：' + total + "元。");
		}
	}
	
	function parseIs_borrow(thisValue){
		var str = "";
		if("2" == thisValue){
	    	str = "是";
		}else{
			str = "否";
		}
		return str;
	}
	
	function parseIs_report_input(thisValue){
		var str = "";
		if("2" == thisValue){
	    	str = "是";
		}else{
			str = "否";
		}
		return str;
	}
	
	// 批量修改
	function editcom(){
		device_type = Qm.getValueByName("big_class").substring(0, 1);
		if("9" == device_type){
			alert("客运索道暂不支持批量修改！");
			return;
		}else{
			top.$.dialog({
				width : 600,
				height : 260,
				lock : true,
				title : "批量修改",
				content : 'url:app/device/device_change.jsp?status=modify&id='
						+ Qm.getValuesByName("id"),
				data : {
					"window" : window
				}
			});
		}
	}
	
	// 合并使用单位
	function margeCom(){
		device_type = Qm.getValueByName("big_class").substring(0, 1);
		var com_ids = Qm.getValuesByName("fk_company_info_use_id");
		top.$.dialog({
			width : 600,
			height : 100,
			lock : true,
			title : "合并使用单位",
			content : 'url:app/device/device_marge.jsp?status=modify&id='
						+ Qm.getValuesByName("id")+'&com_ids='+com_ids,
			data : {
				"window" : window
			}
		});	
	}
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<form name="form1" id="form1" action="" getAction="" target="_blank">
		<input type="hidden" name="check_dep_name" id="check_dep_name" value=""/>
		<input type="hidden" name="invoice_start_date" id="invoice_start_date" value=""/>
		<input type="hidden" name="invoice_end_date" id="invoice_end_date" value=""/>
		<input type="hidden" name="borrow_start_date" id="borrow_start_date" value=""/>
		<input type="hidden" name="borrow_end_date" id="borrow_end_date" value=""/>
	</form>
	<qm:qm pageid="zzjd_payment_list" script="false" >
		<%
			if(StringUtil.isNotEmpty(info_type)){
				%>
				<qm:param name="fee_status" value="<%=info_type%>" compare="=" /><!-- 收费状态(0 默认 1 待收费 2 已收费 3 借报告 4 借发票 5 借报告和发票) -->
				<%
					if("1".equals(info_type)){
						%>
						<qm:param name="fee_status" value="0" compare="=" logic="or"/>
						<%
					}else if("3".equals(info_type)){	// 外借查询
						%>
						<qm:param name="fee_status" value="4" compare="=" logic="or"/>
						<qm:param name="fee_status" value="5" compare="=" logic="or"/>
						<%
					}
			}else{
				%>
				<qm:param name="fee_status" value="2" compare="=" />
				<qm:param name="advance_type" value="2" compare="=" /><!-- 收费类型 0 正常收费 1 协议收费 2 免收费 -->
				<%
			}
		%>
	</qm:qm>
	<script type="text/javascript">
Qm.config.columnsInfo.fee_status.binddata = [
	{id: '0', text: '外借类型', children: [
		{id: '3', text: '外借报告'},
		{id: '4', text: '外借发票'},
		{id: '5', text: '外借报告和发票'}
	]}
];
</script>
</body>
</html>