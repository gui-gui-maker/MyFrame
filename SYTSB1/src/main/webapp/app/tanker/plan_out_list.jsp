<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">

	var unitcode = 	<sec:authentication property='principal.department.id' htmlEscape='false' />
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},				
		sp_fields : [
		{
			name : "ladle_car_number",
			compare : "like",
			value : ""
		},{
			name : "com_name",
			compare : "like",
			value : ""
		}
		
		],
		tbar : [
		
		{
			text : '修改',
			id : 'modify',
			icon : 'modify',
			click : modify
		}


	
		],
	
	
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					add : count == 1,
					modify : count == 1,
					copy : count == 1,
					editcom : count>0,
					margeCom : count>0,
					modify : count > 0,
					enable:count>0,
					disable:count>0,
					useless:count>0,
					modifyDevice:count>0
				});
			}
		}
	};

	function modify() {
		var is_plan = Qm.getValuesByName("is_plan").toString();
	
		
		if(is_plan.indexOf(",")!=-1){
			
			var temp = is_plan.split(",");
		
			
			for(var i = 0 ; i < temp.length ; i++){
				
			
				if(temp[i]=="已确认"){
					
				
						$.ligerDialog.alert("选择数据中包含已确认数据，请重新选择");
						return;
				}
			}
		
		}else{
			if(is_plan=="已确认"){
				$.ligerDialog.alert("选择数据中包含已确认数据，请重新选择");
				return;
			}
		}
		
		
		var infoId=Qm.getValuesByName("infoId"); 
				top.$.dialog({
						width : 600,
						height : 300,
						lock : true,
						title : "修改",
						content : 'url:app/tanker/plan_change.jsp?status=modify&infoId='+infoId+'&org_id='+unitcode,
						data : {
							"window" : window
						}
					});
			
		}


	

	function submitAction() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="plan_out_tanker" singleSelect="false">
	</qm:qm>
	
</body>
</html>
