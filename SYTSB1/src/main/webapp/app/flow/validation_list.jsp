<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>提交记录</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">

	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"validation_man",compare:"like"},
			{group:[
					{name:"validation_date", compare:">=", value:"", width:"100"},
					{label:"到", name:"validation_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
				]},
			{name:"validation_status",compare:"like"}
	    ], tbar:[],
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
     			Qm.setTbar({back:count>0,getinput : count==1,flow_note : count==1,returnIput: count==1,print: count==1,showReport : count==1,history : count==1,input : count == 1,detail : count == 1,device : count == 1, check : count == 1,copy : count > 0});
			}
			
			
			
			
			
		}
	};

	
	function detail() {

		top.$.dialog({
			width : 800,
			height : 500,
			lock : true,
			title : "业务详情",
			content : 'url:app/flow/info_detail.jsp?status=detail&id='+ Qm.getValueByName("id"),
			data : {
				"window" : window
			}
		});

	}

	function submitAction(o) {
   		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="validation" script="false" singleSelect="false" >
	</qm:qm>
</body>
</html>
