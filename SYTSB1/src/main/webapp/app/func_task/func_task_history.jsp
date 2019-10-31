<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>历史台账记录</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/common/js/windowprint.js"></script>
<script type="text/javascript">   
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"pro_desc", id:"pro_desc", compare:"like"},
			{name:"org_id",compare:"="},
			{name:"advance_user_name", id:"advance_user_name", compare:"like"}
	    ],
		tbar:[
            {
				text : '详情',
				id : 'detail',
				click : detail,
				icon : 'detail'
			}
        ],
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({detail:count==1});
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
		}
	};
	
	//查看
	function detail() {
		top.$.dialog({
			width : 800,
			height : 600,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/maintenance/maintenance_info_detail.jsp?status=detail&id='+ Qm.getValueByName("id")
		});
	}
	
	// 刷新Grid
	function refreshGrid() {
   		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="maintenance_list2" script="false" singleSelect="false">
		<%
			String id = request.getParameter("id");
			if(StringUtil.isNotEmpty(id)){
				%>
				<qm:param name="fk_func_task_id" value="<%=id%>" compare="=" />
				<%
			}
		%>
	</qm:qm>	
	<script type="text/javascript">
		// 根据 sql或码表名称获得Json格式数据
		Qm.config.columnsInfo.org_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where property='dep' and status='used' order by orders "></u:dict>;
	</script>
</body>
</html>
