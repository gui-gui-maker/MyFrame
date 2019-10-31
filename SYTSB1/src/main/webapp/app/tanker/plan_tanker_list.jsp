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
			text : '任务分配',
			id : 'add',
			icon : 'add',
			click : add
		},"-",
		{
			text : '已分配设备',
			id : 'detail',
			icon : 'detail',
			click : detail
		}


		
	
		],
	
	
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					add : count == 1,
					
					copy : count == 1,
					editcom : count>0,
					margeCom : count>0,
					add : count > 0,
					enable:count>0,
					disable:count>0,
					useless:count>0,
					modifyDevice:count>0
				});
			}
		}
	};

	function add() {

				top.$.dialog({
						width : 600,
						height : 300,
						lock : true,
						title : "分配任务",
						content : 'url:app/tanker/plan_detail.jsp?status=add&ids='+Qm.getValuesByName("id")+'&org_id='+unitcode,
						data : {
							"window" : window
						}
					});
			
		}
	function detail() {

		top.$.dialog({
				width : 800,
				height : 600,
				lock : true,
				title : "已分配设备",
				content : 'url:app/tanker/plan_out_list.jsp',
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
	<qm:qm pageid="device_info_plan" singleSelect="false">
	</qm:qm>
	
</body>
</html>
