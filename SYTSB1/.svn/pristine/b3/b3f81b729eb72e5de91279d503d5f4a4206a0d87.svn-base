<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>外借历史记录</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:80},	// 默认值，可自定义
		sp_fields:[
			{name:"report_sn", compare:"like"},
			{name:"report_com_name", compare:"like"},
			{name:"check_unit_id", id:"check_unit_id", compare:"="},
			{name:"check_op_name", compare:"like"},
			{name : "fee_status", compare : "="},
			{group:[
						{name:"borrow_date", compare:">=", value:""},
						{label:"到", name:"borrow_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
			]},
			{name : "invoice_no", compare : "like"}
	    ],
		tbar:[
			{ text : '详情', id : 'detail', icon : 'detail', click : detail},
			 '-', 
        	{ text:'打印借条', id:'print', icon:'print', click: printBorrow}
        ],
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({detail:count==1,print:count==1});
			},
			rowDblClick : function(rowData, rowIndex) {
				//Qm.getQmgrid().selectRange("id", [rowData.id]);
			}
		}
	};	
	
	// 查看详情
	function detail() {
		var selectedIds = Qm.getValuesByName("inspection_info_id").toString();
		top.$.dialog({
			width : 1000,
			height : 550,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},
			content : 'url:app/payment/report_borrow_detail.jsp?status=detail&id='+ selectedIds	
		});
	}
	
	// 打印借条
	function printBorrow(){
		var selectedIds = Qm.getValuesByName("inspection_info_id").toString();
		var feeStatusArr = Qm.getValuesByName("fee_status");
		for(var i=0;i<feeStatusArr.length;i++){
			if("已收费" == feeStatusArr[i]){
				$.ligerDialog.error("已收费的报告，不能打印借条！");
				return;
			}else if("初始" == feeStatusArr[i] || "待收费" == feeStatusArr[i]){
				$.ligerDialog.error("未进行外借登记，不能打印借条！");
				return;
			}
		}
		$.getJSON("report/borrow/getDetail.do?status=detail&id="+selectedIds, function(resp){
			if(resp.success){
				if(resp.data["id"] != null){
					top.$.dialog({
						width : $(top).width(),
						height :  $(top).height()-40,
						lock : true,
						title : "打印借条",
						parent: api,
						content : 'url:app/payment/docEditor.jsp?status=modify&type=2&isPrint=1',	// type 1：缴费单 2：借条
						data: {pwindow: window, bean: resp.data}
					}).max();
				}else{
					$.ligerDialog.alert("请先进行外借登记，再打印借条！");
				}
			}
		})
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
	
	// 刷新Grid
	function refreshGrid() {
   		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="report_borrow_list" script="false">
	</qm:qm>
	<script type="text/javascript">
//    根据 sql或码表名称获得Json格式数据
<%--var aa=<u:dict code="community_type"></u:dict>;--%>
//Qm.config.columnsInfo.area_id.binddata=<u:dict sql='select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE'></u:dict>;
Qm.config.columnsInfo.check_unit_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
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
