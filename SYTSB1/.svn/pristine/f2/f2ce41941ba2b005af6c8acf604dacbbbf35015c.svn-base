<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@ page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<%
	String info_id = request.getParameter("info_id");
%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:80},	// 默认值，可自定义
		sp_fields:[
			{name:"com_name", compare:"like"},
			{name:"cash_back", compare:"="},
			{name:"receive_man", compare:"like"},
			{group:[
				{name:"back_date", compare:">=", value:""},
				{label:"到", name:"back_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
			]}	
		],
		tbar : [{
			text : '详情',
			id : 'detail',
			click : detail,
			icon : 'detail'
		}],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();	// 行选择个数
				Qm.setTbar({
					detail : count == 1
				});
			},
			rowAttrRender : function(rowData, rowid) {

			}
		}
	};

	// 查看详情
	function detail() {
		var selectedId = Qm.getValueByName("id");
		var info_id = Qm.getValuesByName("fk_inspection_info_id");		
		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},
			content : 'url:app/payment/zzjd_payment_back_detail.jsp?status=detail&id='+ selectedId +'&info_id='+info_id
		});
	}
	

	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="zzjd_pay_back_list" script="false">
		<%
			if(StringUtil.isNotEmpty(info_id)){
				%>
				<qm:param name="fk_inspection_info_id" value="<%=info_id%>" compare="=" /><!-- 报检信息ID -->
				<%
			}
		%>
	</qm:qm>
</body>
</html>
