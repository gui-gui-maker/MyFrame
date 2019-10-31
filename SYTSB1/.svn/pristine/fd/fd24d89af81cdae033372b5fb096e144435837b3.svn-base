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
<script type="text/javascript">
	var check_deps = <u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
	
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"com_name",compare:"like"},
			{name:"order_no", id:"order_no", compare:"like"},		
			{name:"pay_type", compare : "="},	
			{name:"trade_no", id:"trade_no", compare:"like"},	
			{name:"buyer_logon_id", id:"buyer_logon_id", compare:"like"},
			{group:[
				{name:"send_pay_date", id:"send_pay_date", compare:">=", value:""},
				{label:"到", name:"send_pay_date", id:"send_pay_date2", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
			]},
			{name:"trade_status", compare : "="}
		],
		tbar : [
 			{text : '刷新', id : 'refresh', icon : 'refresh', click : refreshGrid}     
		],
		listeners : {
			rowClick : function(rowData, rowIndex) {
				
			},
			rowDblClick : function(rowData, rowIndex) {
	
			},
			selectionChange : function(rowData, rowIndex) {

			},
			rowAttrRender : function(rowData, rowid) {
				var fontColor="black";
				if (rowData.pay_type == "alipay"){
					rowData.pay_type = "支付宝";
					fontColor="blue";
				}
				if (rowData.pay_type == "weixin"){
					rowData.pay_type = "微信";
					fontColor="green";
				}
		   		return "style='color:"+fontColor+";'";
			},
	        pageSizeOptions:[10,20,30,50,100,200]
		}
	};
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
		<div class="l-page-title">
		<div class="l-page-title-note">提示：列表数据项
			<font color="blue">“蓝色”</font>代表支付宝业务；
			<font color="green">“绿色”</font>代表微信业务。
		</div>
		</div>
	</div>
	
	<qm:qm pageid="machine_trade_list" script="false">
	</qm:qm>
</body>
</html>